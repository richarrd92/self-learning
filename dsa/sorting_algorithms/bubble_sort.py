# UNCOMMENT TO USE FILE
# from data import generate_array  # Import the generate_array function from data.py

# # Generate the random array using the function from data.py
# random_array = generate_array(size=10, max_value=20)

# print("UNSORTED ARRAY:", random_array)


# Complexity: Time O(N^2) - Space O(1)
def bubble_sort(random_array):

    # Iterate through the entire array
    for i in range(len(random_array)):
        swapped = False  # Keep track of whether any swaps occurred in this pass

        # Compare adjacent elements up to the unsorted portion
        for j in range(len(random_array) - i - 1): 
            if random_array[j] > random_array[j + 1]:
                # Swap if elements are out of order
                random_array[j], random_array[j + 1] = random_array[j + 1], random_array[j]
                swapped = True  # Mark that a swap has occurred

        # If no swaps occurred during the pass, the array is already sorted - break out of loop
        if not swapped:
            break 
    
    # Return the sorted array after all passes are complete
    return random_array

# UNCOMMENT TO USE FILE
# print("SORTED ARRAY:  ", bubble_sort(random_array))