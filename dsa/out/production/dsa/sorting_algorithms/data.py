# data used for sorting algorithms
import random, time

# Function to generate an array of random integers
def generate_array(size=15, max_value=10):
    """
    Generates an array of random integers.

    :param size: The size of the array (default is 15).
    :param max_value: The maximum value for the random numbers (default is 10).
    :return: A list of random integers.
    """
    return [int(random.random() * max_value) for _ in range(size)]

# Function to measure execution time of a sorting algorithm
def measure_time(sort_function, arr):
    """
    Measures execution time of a sorting function, excluding copying time.
    
    :param sort_function: Sorting algorithm function to test.
    :param arr: The array to sort.
    :return: Execution time in seconds.
    """
    arr_copy = arr[:]  # Copy before starting the timer for more accuracy
    start_time = time.time()  # Start the timer
    sort_function(arr_copy)  # Sort the copied array
    end_time = time.time()  # Stop the timer
    return end_time - start_time  # Return elapsed time

# Function to check if an array is sorted
def is_sorted(arr):
    return all(arr[i] <= arr[i + 1] for i in range(len(arr) - 1))