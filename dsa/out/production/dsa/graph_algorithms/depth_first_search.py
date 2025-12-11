# Implementation of the Depth-First Traversal (DFS) algorithm using an adjacency list.
# The adjacency list is represented as a dictionary.
# - Keys: Nodes (character values).
# - Values: Lists of neighboring nodes.
# A stack (or recursive function call stack) maintains the traversal order.
from typing import Dict, List 
from data import graph # data imported from data.py

# Iterative implementation of Depth-First Search (DFS)
def iterative_depth_first_traversal(adjacency_list, start_node):
    """
    Performs an iterative depth-first traversal on a graph represented as an adjacency list.

    :param adjacency_list: A dictionary where keys are node labels and values are lists of adjacent nodes.
    :param start_node: The node from which the DFS traversal begins.
    """
    stack = [start_node]  # Initialize the stack with the starting node

    # Traverse the graph until the stack is empty
    while stack:
        node = stack.pop()  # Remove and process the last node added to the stack
        print(node, end=" ")  # Print the current node

        """"
        Iterate through the node's neighbors and push them onto the stack.
        The `get()` method returns the value associated with the specified key.
        - If the key does not exist, it returns an empty list (default value).
        This prevents KeyErrors if the node is not present in the adjacency list.
        """
        for neighbor in adjacency_list.get(node, []):
            stack.append(neighbor)  # Add each neighbor to the stack

# Recursive implementation of Depth-First Search (DFS)
# This approach leverages Python's function call stack instead of using an explicit stack.
def recursive_depth_first_traversal(adjacency_list: Dict[str, List[str]], start_node: str) -> None:
    """
    Performs a recursive depth-first traversal on a graph represented as an adjacency list.

    :param adjacency_list: A dictionary where keys are node labels (strings) and values are lists of adjacent node labels.
    :param start_node: The node from which the DFS traversal begins.
    """
    # Print the current node
    print(start_node, end=" ")

    # Iterate through the neighbors of the current node
    for neighbor in adjacency_list.get(start_node, []):
        # Recursively visit each neighbor
        recursive_depth_first_traversal(adjacency_list, neighbor)

# Run DFS from node 'A'
recursive_depth_first_traversal(graph, 'A')
print() # Print newline for better output formatting