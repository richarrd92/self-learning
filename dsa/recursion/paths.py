# Write a function that takes two inputs n and m and outputs the number of unique paths from the top left corner to bottom right corner of an n x m grid
# Constraint: can only move down or right 1 unit at a time

# 0 0 0 0
# 0 0 0 0
# 0 0 0 0
# 0 0 0 0
# Grid: 4x4

# find how many possible paths
def find_paths(n: int, m: int) -> int:
    if n == 1 or m == 1:
        return 1
    
    return find_paths(n-1, m) + find_paths(n, m-1)

# print all possible paths
def print_all_paths(n: int, m: int, path : str = "") -> str:
    if n == 1 and m == 1: 
        print(path)
        return 
    if n > 1:
        print_all_paths(n-1, m, path + "D")
    
    if m > 1:
        print_all_paths(n, m-1, path + "R")



# print(find_paths(4, 4))
print_all_paths(3,3)