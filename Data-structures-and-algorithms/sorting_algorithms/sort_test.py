from data import generate_array, is_sorted  # Import utility functions
from bubble_sort import bubble_sort  # Import Bubble Sort function
from selection_sort import selection_sort  # Import Selection Sort function
from insertion_sort import insertion_sort  # Import Insertion Sort function
from merge_sort import merge_sort  # Import Merge Sort function
from quick_sort import quick_sort  # Import Quick Sort function

def test_sorting_algorithms(array_size=14, max_value=100):
    """
    Generates a random array and tests multiple sorting algorithms.
    
    Parameters:
    - array_size (int): The size of the array to generate.
    - max_value (int): The maximum value an element in the array can have.
    
    Returns:
    - True if all sorting algorithms correctly sort the array.
    - False if any algorithm fails.
    """
    print("TESTING SORTING ALGORITHMS:")

    # Generate a random array
    random_array = generate_array(size=array_size, max_value=max_value)
    print("\nUNSORTED ARRAY:               ", random_array)
    print

    # Sort the array using different algorithms (passing a copy to preserve the original)
    sorted_bubble = bubble_sort(random_array[:])  
    sorted_selection = selection_sort(random_array[:])  
    sorted_insertion = insertion_sort(random_array[:])  
    sorted_merge = merge_sort(random_array[:], 0, len(random_array) - 1)  
    sorted_quick = quick_sort(random_array[:], 0, len(random_array) - 1)  

    print("SORTED ARRAY (Bubble Sort):   ", sorted_bubble)  
    print("SORTED ARRAY (Selection Sort):", sorted_selection)  
    print("SORTED ARRAY (Insertion Sort):", sorted_insertion)  
    print("SORTED ARRAY (Merge Sort):    ", sorted_merge)  
    print("SORTED ARRAY (Quick Sort):    ", sorted_quick)  
    print("-" * 87)


    print("\nTEST RESULTS: \n")
    # Verify if all algorithms sorted correctly
    results = {
        "Bubble Sort": is_sorted(sorted_bubble),
        "Selection Sort": is_sorted(sorted_selection),
        "Insertion Sort": is_sorted(sorted_insertion),
        "Merge Sort": is_sorted(sorted_merge),
        "Quick Sort": is_sorted(sorted_quick),
    }

    # Print results
    for sort_name, passed in results.items():
        print(f"{sort_name}: {passed}")

    # Return True only if all sorting algorithms pass
    return all(results.values())

# UNCOMMENT TO USE FILE

# # Example usage
# if test_sorting_algorithms():
#     print("\nAll sorting algorithms passed!")
# else:
#     print("\nAt least one sorting algorithm failed.")
# print("-" * 87)