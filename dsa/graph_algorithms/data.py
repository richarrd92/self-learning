# data used for searching algorithms

graph = {
    'A': ['B', 'C'],
    'B': ['D'],
    'C': ['E'],
    'D': ['F'],
    'E': [],
    'F': []
}

# adjancecy matrix
adj_matrix = [
    [0, 1, 1, 0, 0, 0, 0],  # A
    [1, 0, 1, 1, 0, 0, 0],  # B
    [1, 1, 0, 0, 1, 0, 0],  # C
    [0, 1, 0, 0, 1, 1, 0],  # D
    [0, 0, 1, 1, 0, 1, 1],  # E
    [0, 0, 0, 1, 1, 0, 1],  # F
    [0, 0, 0, 0, 1, 1, 0]   # G
]

# node labels
nodes = ["A", "B", "C", "D", "E", "F", "G"]


graph2 = {
    0: [1, 2],
    1: [3],
    2: [3],
    3: []
}