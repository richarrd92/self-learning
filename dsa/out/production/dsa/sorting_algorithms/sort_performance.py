from data import generate_array, measure_time  # Import utility functions
from bubble_sort import bubble_sort  
from selection_sort import selection_sort  
from insertion_sort import insertion_sort  
from merge_sort import merge_sort  
from quick_sort import quick_sort 
from sort_test import test_sorting_algorithms

# Generate a small random array and demonstrate sorting
test_sorting_algorithms(array_size=14, max_value=100)
print("-" * 65)

# Define array sizes for performance testing
array_sizes = [10, 100, 1000, 10000, 100000, 1000000]  # Larger sizes for more accurate results

# Generate test arrays for each size
test_arrays = {size: generate_array(size=size, max_value=10000) for size in array_sizes}

print("\nSORTING PERFORMANCE:")
# Measure and print sorting times for different array sizes
for size in array_sizes:
    arr = test_arrays[size]  # Retrieve pre-generated array
    bubble_time = measure_time(bubble_sort, arr)
    selection_time = measure_time(selection_sort, arr)
    insertion_time = measure_time(insertion_sort, arr)
    merge_time = measure_time(lambda arr: merge_sort(arr, 0, len(arr) - 1), arr)
    quick_time = measure_time(lambda arr: quick_sort(arr, 0, len(arr) - 1), arr)
    
    print(f"\nArray Size: {size}")
    print(f"  Bubble Sort:    {bubble_time:.6f} seconds")
    print(f"  Selection Sort: {selection_time:.6f} seconds")
    print(f"  Insertion Sort: {insertion_time:.6f} seconds")
    print(f"  Merge Sort:     {merge_time:.6f} seconds")
    print(f"  Quick Sort:     {quick_time:.6f} seconds")
    print("-" * 65)