from data import numbers, bills, checks, pattern1, text1
# **************************************************************************************************** 
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

# **************************************************************************************************** 
# Distinct check using sorted array
def presorted_distinct_elements_optimal(arr, counter=0):
    arr = sorted(arr)
    for i in range(1, len(arr)):
        counter += 1
        if arr[i] == arr[i-1]:
            return False, counter
    return True, counter


# **************************************************************************************************** 
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

# **************************************************************************************************** 
# You are given n telephone bills and m checks sent to pay the bills (n ≥ m).
# Assuming that telephone numbers are written on the checks, we want to find out who (all) failed to pay. 
# Assume that only one check is written for a particular bill and that it covers the bill in full.)
# use of set
def find_unpaid_bills(bills_arr: list, checks_arr: list) -> list:
    check_set = set() # O(n)

    for check in checks_arr: # O(n)
        check_set.add(check)
    
    unpaid = []
    for bill in bills_arr: # O(n)
        if bill not in check_set: 
            unpaid.append(bill)

    return unpaid

# **************************************************************************************************** 
# Horspool algorithm - pattern matching
# Step 1: Make alphabet shift table
def build_alphabet_shift_table(pattern: str, alphabet_shift_table={}) -> dict:
    for i in range(26):
        uppercase_letter = chr(ord('A') + i)
        alphabet_shift_table[uppercase_letter] = len(pattern)

    alphabet_shift_table[' '] = len(pattern)

    print("{")
    for character, shift_value in alphabet_shift_table.items():
        print(f'    {character} : {shift_value}')
    print("}")

    return alphabet_shift_table

# Step 2: Create position table for characters in the pattern
def build_character_position_table(pattern: str) -> dict:
    character_position_map = {}
    for index, character in enumerate(pattern):
        position_from_right = abs(index - (len(pattern) - 1))
        character_position_map[position_from_right] = character

    print("{")
    for shift_distance, character in character_position_map.items():
        print(f'    {shift_distance} : {character}')
    print("}")

    return character_position_map


# Step 3: Update alphabet table with actual pattern character positions
def update_shift_table_with_pattern(alphabet_table: dict, character_position_table: dict) -> dict:
    for shift_distance, character in character_position_table.items():
        alphabet_table[character] = shift_distance
    print("{")
    for character, shift_value in alphabet_table.items():
        print(f'    {character} : {shift_value}')
    print("}")

    return alphabet_table

# Step 4: Perform Horspool algorithm
def horspool_algorithm(pattern: str, text: str, shift_table: dict) -> list:
    n = len(text)
    m = len(pattern)
    match_positions = []

    if m > n:
        return match_positions

    i = 0
    while i <= n - m:
        j = m - 1
        while j >= 0 and pattern[j] == text[i + j]:
            j -= 1
        if j < 0:
            match_positions.append(i)
            i += m
        else:
            mismatched_char = text[i + m - 1]
            shift = shift_table.get(mismatched_char, m)
            i += max(1, shift)

    return match_positions

# We are given two strings S and T , each n characters long. 
# We have to check whether one of them is a right cyclic shift of the other. 
# For example, PLEA is a right cyclic shift of LEAP, and vice versa. 
# (Formally, T is a right cyclic shift of S if T can be obtained by concatenating the (n − i)-character suffix of
# S and the i-character prefix of S for some 1≤ i ≤ n.)
# (a) Design a space-efficient algorithm for the task. Indicate the space and time
# efficiencies of your algorithm.

def is_right_cyclic(text1: str, text2: str) -> bool:
    # strings not equal in length
    if len(text1) != len(text2):
        return False
    
    # check if text2 is a substring of text1
    new_text = text1 + text1
    pos = new_text.find(text2)

    # -1 not found and 0 text1 == text 2
    return pos != -1 and pos != 0

word1, word2 = "LEAP", "PLEA"
if is_right_cyclic(word1, word2):
    print("The strings are right cyclic")
else:
    print("The strings are not right cyclic")







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

# print("\nOriginal numbers:", numbers)
# print(rankSort(numbers))

# print("\nUnpaid bills")
# print(find_unpaid_bills(bills, checks))

# print("\nAlphabet table")
# alphabet_table = build_alphabet_shift_table(pattern1)

# print("\nPattern table")
# character_position_table = build_character_position_table(pattern1)

# main_table = update_shift_table_with_pattern(alphabet_table, character_position_table)

# print("\nHorspool algorithm result:")
# print(horspool_algorithm(pattern1, text1, main_table))