#!/bin/bash

# Node base port
base_port=12345

# Ask user for the total number of nodes
read -p "Enter the total number of nodes: " total_nodes

file_path="/Users/victorbillaud/dev/efrei/distributed_system/project/run_node.py"
credentials_file="/Users/victorbillaud/dev/efrei/distributed_system/project/credentials.txt"

# read json file to get the list of usernames and passwords
usernames=()
passwords=()

# Read the credentials file line by line
while IFS='|' read -r username password; do
  usernames+=("$username")
  passwords+=("$password")
done < "$credentials_file"

# Run nodes
for ((i=0; i<$total_nodes; i++)); do
    port=$((base_port+i))
    if [[ "$OSTYPE" == "linux-gnu"* ]]; then
        # Linux
        gnome-terminal -- python3 $file_path $port $total_nodes $i $base_port -u ${usernames[$i]} -p ${passwords[$i]}
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        # MacOS
        osascript -e "tell app \"Terminal\" to do script \"python3 $file_path $port $total_nodes $i $base_port -u ${usernames[$i]} -p ${passwords[$i]}\""
    fi
done
