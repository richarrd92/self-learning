from data import numbers

# (a) Find if two integers in array sum to zero (Brute Force)
def findSumZero(arr, counter=0):
    for i in range(len(arr)):
        for j in range(i+1, len(arr)):
            counter += 1
            if arr[i] + arr[j] == 0:
                return True, counter, f"indices {i} and {j} => {arr[i]} + {arr[j]}"
    return False, counter, "No values found"

# (a) Optimized version using hashing
def findSumZeroOptimal(arr):
    seen = set()
    counter = 0
    for num in arr:
        counter += 1
        if -num in seen:
            return True, counter, f"Found: {num} and {-num}"
        seen.add(num)
    return False, counter, "No values found"

# (b) Using part (a) logic to find if two integers sum to target
def findSumTarget(arr, target, counter=0):
    for i in range(len(arr)):
        for j in range(i+1, len(arr)):
            counter += 1
            if arr[i] + arr[j] == target:
                return True, counter, f"indices {i} and {j} => {arr[i]} + {arr[j]}"
    return False, counter, "No values found"

# Optimized using hash map
def findSumTargetOptimal(arr, target, counter=0):
    mp = {}
    for i, num in enumerate(arr):
        diff = target - num
        counter += 1
        if diff in mp:
            return True, counter, f"indices {i} and {mp[diff]} => {num} + {diff}"
        mp[num] = i
    return False, counter, "No values found"

# Distinct element check - brute force
def distinct_elements(arr, counter=0):
    for i in range(len(arr)):
        for j in range(i+1, len(arr)):
            counter += 1
            if arr[i] == arr[j]:
                return False, counter
    return True, counter

# Distinct element check - optimal with hashing
def distinct_elements_optimal(arr, counter=0):
    seen = set()
    for num in arr:
        counter += 1
        if num in seen:
            return False, counter
        seen.add(num)
    return True, counter

# Distinct check using sorted array
def presorted_distinct_elements_optimal(arr, counter=0):
    arr = sorted(arr)
    for i in range(1, len(arr)):
        counter += 1
        if arr[i] == arr[i-1]:
            return False, counter
    return True, counter

# count sort algorithm
def rankSort(arr: list) -> list:
    n = len(arr)
    count = [0] * n
    result = [0] * n

    # Count how many elements are less than each element
    for i in range(n):
        for j in range(n):
            if arr[j] < arr[i]:
                count[i] += 1

    # Place elements in the correct sorted position
    for i in range(n):
        result[count[i]] = arr[i]

    return result


# === Run and print results ===

# def print_result(title, result):
#     print(f"\n{title}")
#     found, counter, detail = result
#     print("Found:", found)
#     print("Loop ran:", counter, "times")
#     print("Details:", detail)

# print_result("Sum Zero Brute Force", findSumZero(numbers))
# print_result("Sum Zero Optimal", findSumZeroOptimal(numbers))
# print_result("Sum Target Brute Force", findSumTarget(numbers, 15))
# print_result("Sum Target Optimal", findSumTargetOptimal(numbers, 15))

# distinct, count = distinct_elements(numbers)
# print(f"\nDistinct Elements Brute Force:\nResult: {distinct}\nLoop ran: {count} times")

# distinct, count = distinct_elements_optimal(numbers)
# print(f"\nDistinct Elements Optimal:\nResult: {distinct}\nLoop ran: {count} times")

# distinct, count = presorted_distinct_elements_optimal(numbers)
# print(f"\nDistinct Elements Presorted Optimal:\nResult: {distinct}\nLoop ran: {count} times")

print("\nOriginal numbers:", numbers)
print(rankSort(numbers))