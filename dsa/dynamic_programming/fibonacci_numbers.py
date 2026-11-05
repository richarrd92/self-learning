# brute force find nth fibonacci number
# given integer -> return the fibonacci number at the integer
def fib_recursive(number: int) -> int:
    # base cases
    if number == 0:
        return 0
    if number <= 2:
        return 1
    
    # recursive case
    return fib_recursive(number - 1) + fib_recursive(number - 2)

# given integer
# return the fibonacci number at the integer
def fib_iterative(number: int):
    # base cases
    if number == 0:
        return 0  # fib(0) is 0
    elif number == 1:
        return 1 # fib(1) is 1
    elif number == 2:
        return 1  # fib(2) is 1

    # Start from 3
    # fib(2) = 1, fib(1) = 1
    prev1, prev2 = 1, 1
    for i in range(3, number + 1):  # Start from 3 to `number`
        next_value = prev1 + prev2
        prev2 = prev1  # Move prev1 to prev2 (second-to-last)
        prev1 = next_value  # Move next_value to prev1 (last)

    return prev1  # The last value is the Fibonacci number at `number`

# given integer
# return list of fibonacci sequence until integer
def fib_sequence_recursively(number: int) -> list:
    # base cases
    if number == 0:
        return []
    elif number == 1:
        return [1]
    elif number == 2:
        return [1,2]
    
    # recursive case
    prev = fib_sequence_recursively(number - 1) 

    # compute next value
    next_value = prev[-1] + prev[-2]

    # return fibonacci sequence -> list
    return prev + [next_value]

# given integer 
# compute fib numbers until the integer
# return list containing the sequence
def fib_sequence_iterative(number: int, res=[]) -> list:
    # base cases
    # case 1 : empty 
    if number == 0:
        return res
    # case 2: length 1
    elif number == 1:
        res.append(1)
        return res

    # base case for length 2 or greater
    res = [1, 2]
    while len(res) < number:
        next_val = res[-1] + res[-2] # compute next value in sequence
        res.append(next_val) # append to result 

    return res # sequence of fib numbers in list 

# Optimized fibonacci algorithm using memoization
def optimized_fib(number: int, memo={}) -> int:

    # Return memoized value if exists
    if number in memo:
        return memo[number]

    # base case fib(0) = 0
    if number == 0:
        return 0
    
    # base case fib(1) = 1, fib(2) = 1
    if number == 1 or number == 2:
        return 1
    
    # Recursive case with memoization
    memo[number] = optimized_fib(number - 1, memo) + optimized_fib(number - 2, memo)
    return memo[number]

print("recursive: ",fib_recursive(10))
print("iterative: ",fib_iterative(10))
print("recursive fib sequence: ",fib_sequence_recursively(10))
print("iterative fib sequence: ",fib_sequence_iterative(10))
print("optimized fib: ",optimized_fib(10))
