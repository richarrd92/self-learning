# Implementation of the Breadth-First Traversal (BFS) algorithm using an adjacency list.
# The adjacency list is represented as a dictionary.
# - Keys: Nodes (character values).
# - Values: Lists of neighboring nodes.
# A queue is used to maintain the traversal order, ensuring nodes are explored level by level.
from data import graph  # Import adjacency list from another file
from typing import Dict, List
from collections import deque  # Import deque for efficient queue operations

def breadth_first_traversal(adjacency_list: Dict[str, List[str]], start_node: str) -> None:
    """
    Performs Breadth-First Traversal (BFS) on a graph represented as an adjacency list.
    
    :param adjacency_list: Dictionary where keys are nodes and values are lists of neighboring nodes.
    :param start_node: The node from which BFS starts.
    """
    queue = deque()  # Initialize a queue for BFS
    queue.append(start_node)  # Start traversal from the given node

    while queue:  # Continue traversal until the queue is empty
        node = queue.popleft()  # Dequeue a node (FIFO order)
        print(node, end=" ")  # Print the current node
        
        # Iterate through the neighbors of the current node
        for neighbor in adjacency_list.get(node, []):  
            queue.append(neighbor)  # Enqueue each neighbor for future traversal

# Run BFS from node 'A'
breadth_first_traversal(graph, 'A')
print()  # Print newline for better output formatting
