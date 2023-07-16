class Node:
    def __init__(self, node_id):
        self.node_id = node_id
        self.vector_clock = [0] * 9

    def broadcast_message(self, message_content):
        self.vector_clock[self.node_id] += 1
        message = {
            'sender_id': self.node_id,
            'vector_clock': self.vector_clock.copy(),
            'message_content': message_content
        }
        for i in range(9):
            if i != self.node_id:
                self.send_message(message, i)

    def send_private_message(self, message_content, recipient_id):
        self.vector_clock[self.node_id] += 1 
        message = {
            'sender_id': self.node_id,
            'vector_clock': self.vector_clock.copy(),
            'message_content': message_content
        }
        self.send_message(message, recipient_id)

    def receive_message(self, message):
        sender_id = message['sender_id']
        received_vector_clock = message['vector_clock']
        message_content = message['message_content']

        for i in range(9):
            self.vector_clock[i] = max(self.vector_clock[i], received_vector_clock[i])

        print(f"Node {self.node_id} : Received message from Node {sender_id}: {message_content}")

    def send_message(self, message, recipient_id):
        recipient_node = nodes[recipient_id]
        recipient_node.receive_message(message)


nodes = []
for i in range(9):
    nodes.append(Node(i))

nodes[0].broadcast_message("Hello, everyone!")

nodes[1].send_private_message("Hi, Node 3!", 3)
