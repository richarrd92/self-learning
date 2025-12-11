# UNCOMMENT TO USE FILE
# from data import generate_array  # Import the generate_array function from data.py

# # Generate the random array using the function from data.py
# random_array = generate_array(size=10, max_value=20)

# print("UNSORTED ARRAY:", random_array)


# Complexity: Time O(N log N) - Space O(N)
def merge(random_array, low, mid, high):

    # Initialize pointers for both halves
    left_pointer = low       # Pointer for the left half
    right_pointer = mid + 1  # Pointer for the right half
    temp_arr = []            # Temporary array to store the merged result

    # Merge the two halves until one of them is exhausted
    while left_pointer <= mid and right_pointer <= high:
        if random_array[left_pointer] <= random_array[right_pointer]:
            # Append the smaller element from the left half
            temp_arr.append(random_array[left_pointer])
            left_pointer += 1
        else:
            # Append the smaller element from the right half
            temp_arr.append(random_array[right_pointer])
            right_pointer += 1

    # Copy any remaining elements from the left half
    while left_pointer <= mid:
        temp_arr.append(random_array[left_pointer])
        left_pointer += 1

    # Copy any remaining elements from the right half
    while right_pointer <= high:
        temp_arr.append(random_array[right_pointer])
        right_pointer += 1

    # Write the sorted elements back into the original array
    for i in range(low, high + 1):
        random_array[i] = temp_arr[i - low]  # Map temp_arr indices back to the array



# Complexity: Time O(N log N) - Space O(N)
def merge_sort(random_array, low, high):

    if low < high:
        # Calculate the middle index of the range
        mid = (low + high) // 2

        # Recursively sort the left half of the range
        merge_sort(random_array, low, mid)

        # Recursively sort the right half of the range
        merge_sort(random_array, mid + 1, high)

        # Merge the two sorted halves
        merge(random_array, low, mid, high)
    
    return random_array

# UNCOMMENT TO USE FILE
# print("SORTED ARRAY:  ", merge_sort(random_array, 0, len(random_array) - 1))