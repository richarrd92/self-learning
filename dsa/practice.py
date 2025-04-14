# 2.	(a) Given an integer array A[0..n-1], some of which could be negative, outline an efficient algorithm to find if there are two integers in that array that sum to zero. 
# 
# (b) Now suppose if we had to find whether there are two integers that sum to some given integer s. Design an efficient algorithm that uses the solution to part 
# 
# (a) as a function call and finds if there are two integers that sum to s. (We want to leverage the algorithm from part (a) rather than designing a completely now algorithm.)

numbers = [-3, -2, -4,  0, -3, 5, 1, 2, 3, 10, -1]

# brute force
def findSumZero(arr: list, counter=0) -> dict:
    for i in range(len(arr)):
        for j in range(i+1, len(arr)):
            # if i == j:
            #     # print("skipping i == j", i, j)
            #     continue
            total = arr[i] + arr[j]
            counter += 1
            if total == 0:
                 return {True : {counter : ("values at index: ", i, "and ", j)}}
               
    return {False : {counter : ("No values")}}

# Call the function
result = findSumZero(numbers)

# Extract bool key
bool_key = list(result.keys())[0]

# Extract the inner dictionary
inner_dict = list(result.values())[0]

# Extract the counter from inner dict
counter_key = list(inner_dict.keys())[0]
print()
print("Did it find a pair that sums to zero?", bool_key)
print("The findSumZero loop ran", counter_key, "times")
print("Details:", inner_dict[counter_key])

def findSumZeroOptimal(arr: list) -> dict:
    seen = set()
    counter = 0
    for i, num in enumerate(arr):
        counter += 1
        if -num in seen:
            return {True: {counter: f"Found: {num} and {-num}"}}
        seen.add(num)
    return {False: {counter: "No values"}}

# numbers = [-4, -3, -2, -1, 0, 1, 2, 3, 4, 5]
result = findSumZeroOptimal(numbers)

bool_key = list(result.keys())[0]
inner = list(result.values())[0]
counter = list(inner.keys())[0]
print()
print("Did it find a pair that sums to zero?", bool_key)
print("The findSumZeroOptimal loop ran", counter, "times")
print("Details:", inner[counter])

def findSumTarget(arr: list, target: int, counter=0) -> dict:
    for i in range(len(arr)):
        for j in range(i+1, len(arr)):
            # if i == j:
            #     # print("skipping i == j", i, j)
            #     continue
            total = arr[i] + arr[j]
            counter += 1
            if total == target:
                    return {True : {counter : ("values at index: ", i, "and ", j)}}
               
    return {False : {counter : ("No values")}}

# Call the function
result = findSumTarget(numbers, 15)

# Extract bool key
bool_key = list(result.keys())[0]

# Extract the inner dictionary
inner_dict = list(result.values())[0]

# Extract the counter from inner dict
counter_key = list(inner_dict.keys())[0]
print()
print("Did it find a pair that sums to zero?", bool_key)
print("The findSumTarget loop ran", counter_key, "times")
print("Details:", inner_dict[counter_key])

def findSumTargetOptimal(arr: list, target: int, counter=0) -> dict:
    mp = {}
    for i in range(len(arr)):
        diff = target - arr[i]
        counter += 1
        if diff in mp:
            return {True : {counter : ("values at index: ", i, "and ", mp[diff])}}
        # insert in mp
        # key -> value(index)
        mp[arr[i]] = i
               
    return {False : {counter : ("No values")}}

# Call the function
result = findSumTargetOptimal(numbers, 15)

# Extract bool key
bool_key = list(result.keys())[0]

# Extract the inner dictionary
inner_dict = list(result.values())[0]

# Extract the counter from inner dict
counter_key = list(inner_dict.keys())[0]
print()
print("Did it find a pair that sums to zero?", bool_key)
print("The findSumTargetOptimal loop ran", counter_key, "times")
print("Details:", inner_dict[counter_key])


# 4.	Explain how to use hashing to check whether all elements of a list are distinct.
# What is the time complexity of this algorithm (best case and worst case)? 
# Compare the efficiency of this algorithm with a presorting based algorithm.

def distinct_elements(arr: list, counter=0) -> bool:
    for i in range(len(arr)):
        for j in range(i+1, len(arr)):
            counter += 1
            if arr[i] == arr[j]:
                return {False: counter}
    return {True: counter}

result = distinct_elements(numbers)
bool_key = list(result.keys())[0]
counter_val = result[bool_key]

print()
print("Does the array contain distinct elements:", bool_key)
print("The distinct_elements loop ran", counter_val, "times")


def distinct_elements_optimal(arr: list, counter=0) -> bool:
    mp = {}
    for i in range(len(arr)):
        counter += 1
        if arr[i] not in mp: # checks for key
            mp[arr[i]] = i
        else:
            return {False: counter}

    return {True: counter}

result = distinct_elements_optimal(numbers)
bool_key = list(result.keys())[0]
counter_val = result[bool_key]

print()
print("Does the array contain distinct elements:", bool_key)
print("The distinct_elements_optimal loop ran", counter_val, "times")


def presorted_distinct_elements_optimal(arr: list, counter=0) -> bool:
    arr.sort()
    print("the sorted arr: ", arr)
    mp = {}
    for i in range(len(arr)):
        counter += 1
        if arr[i] not in mp: # checks for key
            mp[arr[i]] = i
        else:
            return {False: counter}

    return {True: counter}

result = presorted_distinct_elements_optimal(numbers)
bool_key = list(result.keys())[0]
counter_val = result[bool_key]

print()
print("Does the array contain distinct elements:", bool_key)
print("The presorted_distinct_elements_optimal loop ran", counter_val, "times")