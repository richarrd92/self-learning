prices = [7,1,5,3,6,4]
# prices = [7,6,4,3,1] # no profit

# approach 1 
# using nested loops: time O(N^2)
def brute_force(arr: list) -> int:
    profit = -float("inf") # assume no profit on day 1

    for i in range(len(arr)):
        buy = arr[i]
        for j in range(i, len(arr)):
            sell = arr[j]
            profit = max(profit, (sell - buy))

    # no profit can be made
    if profit == -float("inf"):
        return 0
    
    # returns best day to sell: max profit
    return profit

print(brute_force(prices))

# approach 2 -> doesnt work due to the ability of selling pointers to go back in time ie decrementation
# using two pointers: time O(N)
def two_pointer(arr: list) -> int:
    profit = -float("inf") # assume no profit on day 1
    buy_pointer = 0
    sell_pointer = len(arr) - 1

    while buy_pointer < sell_pointer:
        curr_profit = arr[sell_pointer] - arr[buy_pointer]

        # made a profit
        if curr_profit > profit:
            profit = max(profit, curr_profit)
            sell_pointer -= 1 # decrement sell pointer
        else:
            buy_pointer += 1

    # no profit can be made
    if profit == -float("inf"):
        return 0
    
    # returns best day to sell: max profit
    return profit 

print(two_pointer(prices))

# improve two pointer logic
def better_two_pointer(arr: list) -> int:
    buy = 0
    sell = 1 # can only sell day after buying
    profit = 0 # on day 1, zero profit is made

    # iterate until last possible sell day
    while sell < len(arr):
        # no profit made
        if (arr[sell] - arr[buy]) < 0:
            buy = sell # move to new buy day
        
        # profit was made
        else:
            profit = max(profit, (arr[sell] - arr[buy]))

        sell += 1 # go to next sell day

    return profit

print(better_two_pointer(prices))