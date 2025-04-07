# UNCOMMENT TO USE FILE
# from data import generate_array  # Import the generate_array function from data.py

# # Generate the random array using the function from data.py
# random_array = generate_array(size=10, max_value=20)

# print("UNSORTED ARRAY:", random_array)


# Complexity: Time O(N^2) - Space O(1)
def insertion_sort(random_array):

    # Iterate through the entire array starting from the second element
    for i in range(1, len(random_array)):
        j = i
        # Compare and shift elements that are larger than the current element
        while j > 0 and random_array[j] < random_array[j - 1]:
            # Swap the elements to place the current element in the correct position
            random_array[j], random_array[j - 1] = random_array[j - 1], random_array[j]
            j = j - 1

    # Return the sorted array
    return random_array

# UNCOMMENT TO USE FILE
# print("SORTED ARRAY:  ", insertion_sort(random_array))