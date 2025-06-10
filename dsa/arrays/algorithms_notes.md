# Algorithms to use on Arrays

### 1. Searching Algorithms

Used to find elements in an array.

- **Linear Search** – Traverse each element Time: `O(n)` Works on unsorted arrays

- **Binary Search** – Efficient search in **sorted arrays**  Time: `O(log n)`

### 2. Sorting Algorithms

Used to sort arrays in ascending/descending order.

- **Bubble Sort** – Simple but inefficient  Time: `O(n²)`

- **Selection Sort** – Picks min/max each pass  Time: `O(n²)`

- **Insertion Sort** – Efficient on nearly sorted arrays  Time: `O(n²)`

- **Merge Sort** – Divide and conquer, stable sort  Time: `O(n log n)`

- **Quick Sort** – Fast on average, divide and conquer  Time: `O(n log n)` average, `O(n²)` worst

- **Heap Sort** – Uses a binary heap  Time: `O(n log n)`

- **Counting / Radix / Bucket Sort** – Good for integers or limited ranges  Time: `O(n)` under certain conditions

### 3. Prefix / Suffix Algorithms

Useful for preprocessing or optimizing range queries.

- **Prefix Sum**  
  `prefix[i] = arr[0] + arr[1] + ... + arr[i]`  
  Optimizes range sum queries

- **Suffix Sum / Product**  
  Useful when processing from the end

### 4. Two-Pointer Techniques

Process elements from both ends of the array.

- Find pairs with a given sum
- Reverse an array
- Merge sorted arrays

### 5. Sliding Window

Optimize problems involving subarrays.

- **Fixed-size window** – e.g., max sum of subarrays of length `k`
- **Variable-size window** – e.g., longest substring with constraints

### 6. Greedy Algorithms

Make the best local choice at each step.
- Maximize profit
- Minimize operations
- Interval scheduling


### 7. Divide and Conquer

Break problems into subproblems and combine solutions.

- Merge Sort
- Quick Sort
- Finding majority element

### 8. Dynamic Programming (DP)

Solve problems with overlapping subproblems and optimal substructure.

- **Longest Increasing Subsequence (LIS)**
- **Maximum Subarray** – Kadane’s Algorithm
- **Subset Sum / Knapsack**

### 9. Hashing-Based Algorithms

Use hash tables for faster lookups.

- Find duplicates
- Count element frequencies
- Solve the Two Sum problem
- Longest consecutive sequence

### 10. Bit Manipulation

Handle problems using bitwise operations.

- Find non-duplicate elements
- Generate all subsets
- Count set bits

### 11. Matrix (2D Array) Algorithms

Algorithms applied to 2D arrays.

- Rotate matrix
- Spiral traversal
- Search in a 2D matrix

**Tip: When solving an array problem:**
- Can I sort it?
- Can I use extra space (hashing)?
- Can I use two pointers or a sliding window?
- Is it better solved with DP or greedy logic?

