import socket
import sys
import threading


class Node:
    def __init__(self, host, port, total_nodes, node_id, username, password):
        # Socket initialization
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.sock.bind((host, port))

        # Vector clock initialization
        self.vector_clock = [0] * total_nodes
        self.buffer = []
        self.node_id = node_id
        self.nodes = []  # List to keep track of other nodes
        self.port = port

        auth_server_host = "localhost"
        auth_server_port = 5000

        self.auth_server = (auth_server_host, auth_server_port)

        self.sock.sendto(
            f"{username}|{password}".encode(), self.auth_server
        )  # Register with the auth server

    def add_node(self, addr):
        self.nodes.append(addr)

    def broadcast_message(self, message):
        # send the message to all nodes
        self.vector_clock[self.node_id] += 1
        deps = self.vector_clock.copy()
        message = str(deps) + "|" + message
        print("\nVector clock:", self.vector_clock)
        print("Broadcasting message:", message)
        for node in self.nodes:
            self.sock.sendto(message.encode(), node)

    def send_private_message(self, target_node_id, message):
        # send the message to a specific node
        self.vector_clock[self.node_id] += 1
        message = str(self.vector_clock) + "|" + message
        print("\nVector clock:", self.vector_clock)
        print("Sending message:", message, "to:", target_node_id)
        try:
            target_node_address = self.nodes[target_node_id]
            self.sock.sendto(message.encode(), target_node_address)
        except IndexError:
            print("Invalid node id")

    def receive_messages(self):
        # receive messages from other nodes
        while True:
            data, addr = self.sock.recvfrom(1024)
            message = data.decode()
            split_message = message.split("|", 1)
            incoming_port = addr[1]

            if (
                len(split_message) == 2
                and self.is_valid_vector_clock(split_message[0])
                and incoming_port != self.port
            ):
                vector_clock, message = split_message
                # update our vector clock
                self.vector_clock[self.node_id] += 1
                self.update_vector_clock(eval(vector_clock))

                # add the received message to the buffer
                sender = self.nodes.index(addr)
                deps = eval(vector_clock)
                self.buffer.append((sender, deps, message))

                # deliver messages from the buffer in the correct order
                while True:
                    delivered = False
                    for msg in self.buffer:
                        sender, deps, m = msg
                        if all(
                            [deps[i] <= self.vector_clock[i] for i in range(len(deps))]
                        ):
                            print("Delivered message:", m, "from:", addr)
                            print("\nVector clock:", self.vector_clock)
                            self.buffer.remove(msg)
                            self.vector_clock[sender] += 1
                            delivered = True

                    if not delivered:
                        break

        else:
            print("\nReceived invalid message:", message, "from:", addr)

    def is_valid_vector_clock(self, vector_clock_str):
        try:
            vector_clock = eval(vector_clock_str)
            return isinstance(vector_clock, list) and all(
                isinstance(i, int) for i in vector_clock
            )
        except Exception as e:
            print(e)
            return False

    def update_vector_clock(self, other_clock):
        for i in range(len(self.vector_clock)):
            self.vector_clock[i] = max(self.vector_clock[i], other_clock[i])

    def start_receiving_messages(self):
        threading.Thread(target=self.receive_messages).start()

    def handle_auth_response(self):
        data, addr = self.sock.recvfrom(1024)
        message = data.decode()

        if message == "OK":
            print("Authenticated with auth server")
        else:
            print("Failed to authenticate with auth server", message)
            sys.exit(1)
