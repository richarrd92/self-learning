# UNCOMMENT TO USE FILE
# from data import generate_array  # Import the generate_array function from data.py

# # Generate the random array using the function from data.py
# random_array = generate_array(size=10, max_value=20)

# print("UNSORTED ARRAY:", random_array)


# Complexity: Time O(N^2) - Space 0(1)
def selection_sort(random_array):

    # Iterate over array
    for i in range(len(random_array)):
        min_index = i # assume current element is the smallest

        # find the smallest element in the remaining array
        for j in range(i+1, len(random_array)):
            if random_array[j] < random_array[min_index]:
                min_index = j # update the smallest element index

        # Swap the current element with the smallest element found
        random_array[i], random_array[min_index] = random_array[min_index], random_array[i]

    return random_array

# UNCOMMENT TO USE FILE
# print("SORTED ARRAY:  ", selection_sort(random_array))