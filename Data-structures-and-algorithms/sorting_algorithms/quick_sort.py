# UNCOMMENT TO USE FILE
# from data import generate_array  # Import the generate_array function from data.py

# # Generate the random array using the function from data.py
# random_array = generate_array(size=10, max_value=20)

# print("UNSORTED ARRAY:", random_array)


# Complexity: Time O(N log N) - Space O(log N)
def partition(random_array, low, high):
    pivot = random_array[high]  # Choose the pivot as the last element
    i = low - 1  # Pointer for the smaller element

    # Iterate through the range and rearrange elements
    for j in range(low, high):
        if random_array[j] <= pivot:
            i += 1  # Move the pointer forward
            random_array[i], random_array[j] = random_array[j], random_array[i]  # Swap elements
    
    # Swap the pivot into its correct position
    random_array[i + 1], random_array[high] = random_array[high], random_array[i + 1]
    return i + 1  # Return the partition index


# Complexity: Time O(N log N) - Space O(log N)
def quick_sort(random_array, low, high):
    if low < high:
        # Partition the array and get the pivot index
        partition_index = partition(random_array, low, high)
        
        # Recursively sort elements before and after partition
        quick_sort(random_array, low, partition_index - 1)
        quick_sort(random_array, partition_index + 1, high)
    
    return random_array

# UNCOMMENT TO USE FILE
# print("SORTED ARRAY:  ", quick_sort(random_array, 0, len(random_array) - 1))