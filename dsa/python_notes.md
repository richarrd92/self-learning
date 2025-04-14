# Python

### Common Built-in Data Structures and Their Functions

#### List
A list is an ordered, mutable collection of elements.

- **Initialization**: `x = []`
- **Adding Elements**:
  - `x.append(item)` – Adds `item` to the end of the list.
  - `x.insert(index, item)` – Inserts `item` at a specified `index`.
  - `x.extend(iterable)` – Adds all elements from an iterable (list, tuple, etc.) to the end of the list.
  
- **Accessing Elements**:
  - `x[index]` – Access element at `index`.
  - `x[index:start:step]` – Slice the list from `index` to `start` with a given `step`.
  - `x[-1]` – Access the last element.
  
- **Removing Elements**:
  - `x.pop()` – Removes and returns the last item.
  - `x.pop(index)` – Removes and returns the item at the given `index`.
  - `x.remove(item)` – Removes the first occurrence of `item`.
  - `del x[index]` – Deletes item at `index`.
  
- **Other Methods**:
  - `x.sort()` – Sorts the list in place.
  - `x.reverse()` – Reverses the order of the list.
  - `x.count(item)` – Returns the count of `item` in the list.
  - `x.index(item)` – Returns the index of the first occurrence of `item`.
  - `x.clear()` – Removes all elements from the list.
  - `x.copy()` – Returns a shallow copy of the list.


#### Tuple
A tuple is an ordered, immutable collection of elements.

- **Initialization**: `x = ()`
- **Accessing Elements**: `x[index]` – Access element at `index`.
- **Other Methods**:
  - `x.count(item)` – Returns the count of `item` in the tuple.
  - `x.index(item)` – Returns the index of the first occurrence of `item`.

#### Set
A set is an unordered collection of unique elements.

- **Initialization**: `x = set()`
- **Adding Elements**:
  - `x.add(item)` – Adds `item` to the set.
  - `x.update(iterable)` – Adds all elements from an iterable (list, tuple, etc.) to the set.
  
- **Removing Elements**:
  - `x.remove(item)` – Removes `item` from the set. Raises a KeyError if `item` is not found.
  - `x.discard(item)` – Removes `item` from the set. Does not raise an error if `item` is not found.
  - `x.pop()` – Removes and returns an arbitrary element.
  
- **Other Methods**:
  - `x.clear()` – Removes all elements from the set.
  - `x.copy()` – Returns a shallow copy of the set.
  - `x.union(set2)` – Returns a new set with all elements from both sets.
  - `x.intersection(set2)` – Returns a new set with common elements from both sets.
  - `x.difference(set2)` – Returns a new set with elements in `x` but not in `set2`.
  - `x.symmetric_difference(set2)` – Returns a new set with elements in either `x` or `set2`, but not both.


#### Dictionary
A dictionary is an unordered collection of key-value pairs.

- **Initialization**: `x = {}` or `x = dict()`
- **Adding/Updating Elements**: `x[key] = value` – Adds or updates `key` with `value`.
- **Accessing Elements**: `x[key]` – Accesses the value associated with `key`.
- **Removing Elements**:
  - `del x[key]` – Removes the item with `key` from the dictionary.
  - `x.pop(key)` – Removes and returns the value associated with `key`.
  - `x.popitem()` – Removes and returns an arbitrary (key, value) pair.
  
- **Other Methods**:
  - `x.keys()` – Returns a view object containing all keys.
  - `x.values()` – Returns a view object containing all values.
  - `x.items()` – Returns a view object containing all (key, value) pairs.
  - `x.get(key)` – Returns the value for `key` or `None` if `key` is not found.
  - `x.update(other_dict)` – Updates the dictionary with key-value pairs from `other_dict`.
  - `x.clear()` – Removes all elements from the dictionary.
  - `x.copy()` – Returns a shallow copy of the dictionary.
  

#### Queue (using `collections.deque`)
A deque (double-ended queue) allows for efficient append and pop operations from both ends.

- **Initialization**: `from collections import deque`<br> `queue = deque()`
- **Adding Elements**:
  - `queue.append(item)` – Adds `item` to the right end.
  - `queue.appendleft(item)` – Adds `item` to the left end.
  
- **Removing Elements**:
  - `queue.pop()` – Removes and returns the item from the right end.
  - `queue.popleft()` – Removes and returns the item from the left end.
  
- **Other Methods**:
  - `queue.clear()` – Removes all elements from the deque.
  - `queue.extend(iterable)` – Adds all elements from `iterable` to the right end.
  - `queue.extendleft(iterable)` – Adds all elements from `iterable` to the left end.
  - `queue.rotate(n)` – Rotates the deque by `n` steps to the right. Negative `n` rotates to the left.


#### Stack (using List)
A stack is a collection that follows the Last In, First Out (LIFO) principle. Python lists can be used as stacks.

- **Initialization**: `stack = []`
- **Pushing to the Stack**:
  - `stack.append(item)` – Adds `item` to the top of the stack.
  
- **Popping from the Stack**:
  - `stack.pop()` – Removes and returns the top element of the stack.
  
- **Peeking at the Stack**:
  - `stack[-1]` – Returns the top element without removing it.


#### Heap (using `heapq`)
A heap is a binary tree-based data structure that maintains the heap property (min-heap by default).

- **Initialization**: `import heapq`<br> `heap = []`
- **Adding Elements**:
  - `heapq.heappush(heap, item)` – Adds `item` to the heap.
  
- **Removing Elements**:
  - `heapq.heappop(heap)` – Removes and returns the smallest element (min-heap property).
  
- **Other Methods**:
  - `heapq.heappushpop(heap, item)` – Pushes `item` to the heap and then pops and returns the smallest element.
  - `heapq.heapify(list)` – Converts a list into a valid heap in-place.

---

### Common Built-In Functions

- **`Counter()`**: takes an iterable (like a list or string) and returns a dictionary-like object mapping elements to their counts.
- **`input()`**: Prompts the user for input and returns it as a string.
- **`all()`**: Returns `True` if all elements in an iterable are `True`, otherwise `False`.
- **`iterable[:]`**: Returns a shallow copy of the iterable eg list, tuple, or dictionary.
- **`any()`**: Returns `True` if any element in an iterable is `True`, otherwise `False`.
- **`isdigit()`**: Returns `True` if the character is a digit.
- **`islower()`**: Returns `True` if the character is lowercase.
- **`isupper()`**: Returns `True` if the character is uppercase.
- **`len()`**: Returns the length of a variable.
- **`type()`**: Returns the type of a variable.
- **`abs()`**: Returns the absolute value of a number.
- **`round()`**: Returns the rounded value of a number.
- **`pow()`**: Raises a number to a power.
- **`sum()`**: Returns the sum of all elements in an iterable.
- **`chr()`**: Returns the character corresponding to an integer's ASCII value.
- **`str()`**: Converts to a string.
- **`reversed()`**: Reverses a variable but returns an iterator object.
- **`[::-1]`**: Reverses using slicing.
- **`[-1]`**: returns the last element of a list
- **`sys.maxsize`**: largest possible int. Must import sys
- **`float('inf')`**: represents positive infinity in Python
- **`max(item1, item2)`**: returns the max element between item1 and item2
- **`min(item1, item2)`**: returns the min element between item1 and item2
- **`ord(char)`**: returns the ASCII (or Unicode) value of a character
- **`chr(ascii_value)`**: returns the character corresponding to an integer's ASCII value
- **`int(numeric_char)`**: returns integer representation of numeric char
- **`sorted(data_structure)`**: Sorts the data struture
- **`[*list]`**: Spread operator used to unpack and list into new list as a copy 
- **`{**dictionary}`**: Spread operator used to unpack and dictionary into new new dictionary as a copy
- **`list.append(element)`**: Adds a single element to the end of the list. if iterable (e.g., list, tuple) adds as a single whole 
- **`list.extend(iterable)`**: Adds each element from the iterable (e.g., list, tuple) to the end of the list.

---

#### Sorted() function

- **iterable**: The data structure to be sorted (e.g., lists, tuples, dictionaries).
- **key (optional)**: A function that defines the sorting criteria. Default is None (sorts in ascending order).
- **reverse (optional):** A boolean flag to set the sorting order:
  - False (default): Sorts in ascending order.
  - True: Sorts in descending order.

```
  x = [5, 2, 9, 1, 7]
  y = sorted(x)  # Returns [1, 2, 5, 7, 9]
  print(y)  # [1, 2, 5, 7, 9]
  print(x)  # Original list remains unchanged: [5, 2, 9, 1, 7]
```

```
  names = ["John", "Alice", "Bob"]
  sorted_names = sorted(names, key=len)  # ['Bob', 'John', 'Alice']
```

```
  x = [3, 1, 4, 1, 5]
  y = sorted(x, reverse=True)  # Returns [5, 4, 3, 1, 1]
```

```
  dictionary = {3: 'c', 1: 'a', 2: 'b'}
  sorted_keys = sorted(dictionary)  # Returns [1, 2, 3]
```

#### .sort() function

.sort() is a method for lists that sorts the list in place and returns None. <br>
sorted() creates a new sorted list without modifying the original.

```
  x = [3, 1, 4]
  x.sort()  # x is now [1, 3, 4]
  y = sorted(x)  # y is [1, 3, 4], but x is unchanged
```

---

#### For Loop

- **Syntax**:
  - `for i in range(stop)`
  - `for i in range(start, stop)`
  - `for i in range(start, stop, increment/step)`

#### While Loop

- **Basic Syntax**: `while (condition)`

#### Slice Operator

- **Syntax**: `x[start:stop:step]`, `x[start:]`, `x[:stop]`
- **Description**: Picks a section of a data structure (e.g., list). Can also reverse the list with `x[::-1]`.

#### Comprehensions

- **List Comprehension**:
  - `x = [i for i in range(5)]`
  - `x = [i for i in range(5) for j in range(5)]`

#### Functions

- **Define a Function**:
  - `def func():`
- **Return Values**: Functions can return any value or object.


#### Additional Functions

- **`dir()`**: Lists all functions of a data type.
  - Example: `print(dir(str))`
- **`help()`**: Provides documentation on functions.
  - Example: `print(help(str.upper))`

#### String Formatting

- **Old-style Formatting**:
  - `message = "Hello %s" % user_input`
- **f-Strings**:
  - `message = f"Hello {user_input}"`

#### Iterator Concatenation

- **Joining Elements**: Use `"".join(list_or_tuple)` to join the strings in a list or tuple.

#### Arguments

- **Variadic Arguments**:
  - `def mean(*args)` returns a tuple.
- **Keyword Arguments**:
  - `def mean(**kwargs)` returns a dictionary.

---

#### Files

- **Reading a File**:
  - `my_file = open("file.txt")` (File must be in the same directory).
  - `print(my_file.read())`
- **Closing a File**: Always close files after use to erase content from RAM.
  - `my_file.close()`
- **With Statement**: Handles opening and closing of files automatically.
  - ```python
    with open("my_file.txt") as my_file:
        content = my_file.read()
        print(content)
    ```
- **Writing to a File**:
  - ```python
    with open("my_file.txt", "w") as my_file:
        my_file.write("snail")
    ```
- **Append to a File**:
  - ```python
    with open("file.txt", "a+") as my_file:
        my_file.write("\nthis text is appended")
        my_file.seek(0)  # returns cursor to 0
        print(my_file.read())
    ```
