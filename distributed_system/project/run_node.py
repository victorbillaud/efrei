import argparse

from node import Node

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-u", "--username", help="Username", required=True)
    parser.add_argument("-p", "--password", help="Password", required=True)
    parser.add_argument("port", type=int, help="Port number")
    parser.add_argument("total_nodes", type=int, help="Total number of nodes")
    parser.add_argument("node_id", type=int, help="Node ID")
    parser.add_argument("base_port", type=int, help="Base port for other nodes")
    args = parser.parse_args()

    host = "127.0.0.1"
    port = args.port
    total_nodes = args.total_nodes
    node_id = args.node_id
    base_port = args.base_port
    username = args.username
    password = args.password

    node = Node(host, port, total_nodes, node_id, username, password)

    # Add other nodes (you might want to make this more dynamic)
    for i in range(total_nodes):
        other_port = base_port + i
        if other_port != port:
            node.add_node((host, other_port))

    node.handle_auth_response()  # Authenticate with the auth server

    node.start_receiving_messages()  # Start listening for messages

    while True:
        # log the id of the node
        print(f"Node {node_id}:", end=" ")
        msg = input("Enter your message: ")

        # Check if the user wants to send a private message
        if msg.startswith("/p"):
            _, target_node_id, private_msg = msg.split(" ", 2)
            node.send_private_message(int(target_node_id), private_msg)
        else:
            node.broadcast_message(msg)  # Broadcast message to all other nodes
