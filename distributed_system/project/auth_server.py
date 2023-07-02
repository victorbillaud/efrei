# create a auth server to handle the authentication
# and authorization of the user

import socket
import threading


class AuthServer:
    def __init__(self, host, port):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.sock.bind((host, port))
        self.users = {}
        self.user_lock = threading.Lock()

    def add_user(self, username, password):
        with self.user_lock:
            self.users[username] = password

    def authenticate(self, username, password):
        with self.user_lock:
            return username in self.users and self.users[username] == password

    def get_all_users(self):
        with self.user_lock:
            return self.users

    def receive_messages(self):
        while True:
            data, addr = self.sock.recvfrom(1024)
            message = data.decode()
            split_message = message.split("|", 1)

            if len(split_message) == 2:
                username, password = split_message
                if self.authenticate(username, password):
                    self.sock.sendto(b"OK", addr)
                    print("Authenticated user:", username, "from:", addr, "\n")
                else:
                    self.sock.sendto(b"INVALID", addr)
                    print("Invalid credentials from:", addr, "\n")
            else:
                self.sock.sendto(b"INVALID", addr)
                print("Invalid message from:", addr, "\n")

    def start_receiving_messages(self):
        threading.Thread(target=self.receive_messages).start()


if __name__ == "__main__":
    host = "localhost"
    port = 5000

    auth_server = AuthServer(host, port)

    # import users from txt file crendentials.txt splited by |
    with open("credentials.txt", "r") as f:
        for line in f:
            username, password = line.strip().split("|")
            auth_server.add_user(username, password)

    auth_server.start_receiving_messages()
