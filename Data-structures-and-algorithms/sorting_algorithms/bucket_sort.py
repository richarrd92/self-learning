# UNCOMMENT TO USE FILE
# # from data import generate_array  # Import the generate_array function from data.py

# # Generate the random array using the function from data.py
# random_array = generate_array(size=10, max_value=20)

# print("UNSORTED ARRAY:", random_array)


# Complexity: Time O(N + K) - Space O(N + K) -> K = Buckets
def bucket_sort(random_array):
    
    # Find the maximum value in the array to determine the bucket range
    max_value = max(random_array)
    bucket_count = len(random_array) # Number of buckets
    bucket_range = (max_value + 1) / bucket_count # Range of each bucket

    # Create empty buckets
    buckets = [[] for _ in range(bucket_count)]

    # Place each element in its corresponding bucket
    for num in random_array:
        index = int(num / bucket_range) # Calculate the bucket index
        buckets[index].append(num)

    # Sort each bucket and concatenate them
    sorted_arr = []
    for bucket in buckets:
        sorted_arr.extend(sorted(bucket)) # Concatenate sorted buckets

    return sorted_arr

# UNCOMMENT TO USE FILE
# print("SORTED ARRAY:  ", bucket_sort(random_array))