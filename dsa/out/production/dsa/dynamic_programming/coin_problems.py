from data import coins

# You’re walking down a row of houses. Each house is offering you some money if you stop there.
# But there’s a rule: if you stop at a house, you can’t stop at the one right next to it.
# You want to collect as much money as possible by stopping at non-adjacent houses.

# function recieves array of integers (coins)
# return max possible collected integers (coins)
def collect_coins(arr: list[int]) -> int:
    n = len(arr)

    # no coins to be collected
    if n == 0:
        return 0
    
    # only one coin to be collected
    if n == 1:
        return arr[0]
    
    # must be a slice of the array
    return max(collect_coins(arr[:-2]) + arr[n-1], collect_coins(arr[:-1]))

# total_coins = collect_coins(coins)
# print("The max coins collected: ", total_coins)

# Optimized version with memoization technique
def memoized_collect_coins(arr: list[int], memo={}) -> int:

    # Helper function -> performs the recursive calls
    def dp(index: int) -> int: 
        # no coins to be collected
        if index < 0:
            return 0
        
        # check if already computed 
        if index in memo:
            return memo[index]
        
        # must be a slice of the array
        memo[index] = max(dp(index-2) + arr[index], dp(index-1))

        return memo[index]
    
    return dp(len(arr) -1)

total_coins = memoized_collect_coins(coins)
print("The max coins collected: ", total_coins)