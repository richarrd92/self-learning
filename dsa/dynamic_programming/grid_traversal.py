# given  mxn grid
# return number of ways to traverse grid 
# assume only down and right moves
def grid_traversal(m: int, n: int) -> int:
    # base cases
    # empty grid
    if m == 0 or n == 0:
        return 0
    # single step
    if m == 1 and n == 1: 
        return 1
    
    # recursive step
    return grid_traversal(m - 1, n) + grid_traversal(m, n - 1)

# optimized version of grid_traversal
def optimized_grid_traversal(m: int, n: int, memo={}) -> int:
    # memoization base case
    # store based on key
    key = str(m) + "," + str(n)

    # check if key already computed
    if key in memo:
        return memo[key]

    # base cases
    # empty grid
    if m == 0 or n == 0:
        return 0
    
    # single step
    if m == 1 and n == 1:
        return 1
    
    # recursive step
    memo[key] = optimized_grid_traversal(m - 1, n, memo) + optimized_grid_traversal(m, n - 1, memo)
    return memo[key]

# find all possible paths
def find_all_path(m: int, n: int, path="") -> list:
    # Base case: If we're out of bounds (either m or n is 0), no valid path exists
    if m == 0 or n == 0:
        return ["No valid path exists"]

    # base case: reached bottom-right corner
    if m == 1 and n == 1:
        return [path]  # Add the current path and return it
    
    paths = []
    
    # If we can move down, add the "DOWN" direction to the path and recurse
    if m > 1:
        paths += find_all_path(m - 1, n, path + "DOWN ")  # Add "DOWN" to the path
    
    # If we can move right, add the "RIGHT" direction to the path and recurse
    if n > 1:
        paths += find_all_path(m, n - 1, path + "RIGHT ")  # Add "RIGHT" to the path
    
    return paths

print("find all path: ",find_all_path(13, 13))
print("find number of paths: ",grid_traversal(1, 1))
print("find number of paths: ",grid_traversal(2, 3))
print("Normal grid traversal 6x6: ",grid_traversal(6, 6))
print("Optimized grid traversal 18x18: ",optimized_grid_traversal(18, 18))