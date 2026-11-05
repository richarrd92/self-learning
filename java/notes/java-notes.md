# Java

### Common Built-in Data Structures and Their Methods

---

#### **Array**
A fixed-size, ordered collection of elements of the same type.

- **Initialization**:
```java
int[] arr = new int[5];         // default values 0
int[] arr2 = {1, 2, 3, 4, 5};   // initialized with values
```
- **Accessing Elements**: 
```java
arr[0];    // first element
arr[arr.length - 1]; // last element
```
- **Other Methods**:
- `arr.length` – Returns array length.
- `Arrays.sort(arr)` – Sorts array.
- `Arrays.toString(arr)` – Converts array to a string.
- `Arrays.copyOf(arr, newLength)` – Copies array.
- `Arrays.asList(arr)` – Wraps array in a List.

---

#### **ArrayList**
A resizable array implementation (`java.util.ArrayList`).

- **Initialization**:
```java
ArrayList<Integer> list = new ArrayList<>();
```
- **Adding Elements**:
```java
list.add(10);
list.add(1, 20);   // add at index
```
- **Accessing Elements**:
```java
list.get(0);   // get element at index
```
- **Removing Elements**:
```java
list.remove(0);    // remove at index
list.remove(Integer.valueOf(20)); // remove by value
list.clear();      // removes all elements
```
- **Other Methods**:
- `list.size()` – Returns number of elements.
- `list.contains(10)` – Returns `true` if 10 exists.
- `list.indexOf(10)` – Returns index of 10.
- `Collections.sort(list)` – Sorts the list.

---

#### **LinkedList**
A doubly-linked list implementation (`java.util.LinkedList`). Supports list, stack, and queue operations.

- **Initialization**:
```java
LinkedList<String> ll = new LinkedList<>();
```
- **Adding Elements**:
```java
ll.add("A");
ll.addFirst("B");
ll.addLast("C");
```
- **Removing Elements**:
```java
ll.removeFirst();
ll.removeLast();
ll.remove("A");
```
- **Accessing Elements**:
```java
ll.getFirst();
ll.getLast();
ll.get(0);
```

---

#### **HashSet**
Unordered collection of unique elements (`java.util.HashSet`).

- **Initialization**:
```java
HashSet<Integer> set = new HashSet<>();
```
- **Adding Elements**:
```java
set.add(10);
set.add(20);
```
- **Removing Elements**:
```java
set.remove(10);
set.clear();
```
- **Other Methods**:
- `set.contains(20)` – Returns `true` if element exists.
- `set.size()` – Returns number of elements.
- `set.isEmpty()` – Checks if empty.

---

#### **HashMap**
A collection of key-value pairs (`java.util.HashMap`).

- **Initialization**:
```java
HashMap<String, Integer> map = new HashMap<>();
```
- **Adding/Updating Elements**:
```java
map.put("Alice", 25);
map.put("Bob", 30);
```
- **Accessing Elements**:
```java
map.get("Alice");  // returns 25
map.getOrDefault("Eve", 0);
```
- **Removing Elements**:
```java
map.remove("Bob");
map.clear();
```
- **Other Methods**:
- `map.containsKey("Alice")`
- `map.containsValue(25)`
- `map.keySet()`
- `map.values()`
- `map.entrySet()`

---

#### **Queue (Interface)**
Implemented by `LinkedList` or `PriorityQueue`.

- **Initialization**:
```java
Queue<Integer> q = new LinkedList<>();
```
- **Adding Elements**:
```java
q.add(10);       // throws exception if fails
q.offer(20);     // returns false if fails
```
- **Removing Elements**:
```java
q.remove();      // removes head, throws exception if empty
q.poll();        // removes head, returns null if empty
```
- **Accessing Elements**:
```java
q.element();     // returns head, exception if empty
q.peek();        // returns head, null if empty
```

---

#### **Stack**
LIFO collection (`java.util.Stack`).

- **Initialization**:
```java
Stack<Integer> stack = new Stack<>();
```
- **Push**:
```java
stack.push(10);
```
- **Pop**:
```java
stack.pop();  // removes and returns top
```
- **Peek**:
```java
stack.peek(); // view top element
```
- **Other Methods**:
- `stack.isEmpty()`
- `stack.search(10)` – Returns 1-based position.

---

#### **PriorityQueue**
A min-heap implementation (`java.util.PriorityQueue`).

- **Initialization**:
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```
- **Adding Elements**:
```java
pq.add(5);
pq.add(1);
pq.add(10);
```
- **Removing Elements**:
```java
pq.poll();   // removes smallest
pq.remove(); // removes smallest
```
- **Other Methods**:
- `pq.peek()` – returns smallest.
- `pq.size()` – returns number of elements.

---

### Common Utility Methods

- `System.out.println()` – Print to console.
- `Integer.parseInt("123")` – Convert string to int.
- `String.valueOf(123)` – Convert int to string.
- `Math.abs(x)` – Absolute value.
- `Math.pow(a, b)` – a raised to power b.
- `Math.max(a, b)` / `Math.min(a, b)`
- `Math.round(x)` / `Math.floor(x)` / `Math.ceil(x)`
- `Collections.sort(list)` – Sorts list in ascending order.
- `Collections.reverse(list)` – Reverses order.
- `Collections.max(list)` / `Collections.min(list)`
- `Collections.frequency(list, element)` – Count occurrences.

---

### Loops

- **For Loop**:
```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```
- **Enhanced For Loop**:
```java
for (int num : arr) {
    System.out.println(num);
}
```
- **While Loop**:
```java
while (condition) { ... }
```
- **Do-While Loop**:
```java
do { ... } while (condition);
```

---

### String Methods

- `"hello".length()`
- `"hello".charAt(0)`
- `"hello".substring(1, 3)`
- `"hello".toUpperCase()`
- `"HELLO".toLowerCase()`
- `"hello".contains("he")`
- `"hello".equals("hi")`
- `"hello".equalsIgnoreCase("HELLO")`
- `"hello world".split(" ")`
- `"hello".replace("h", "y")`
- `"   hi  ".trim()`

---

### File Handling (Java I/O)

- **Reading a File**:
```java
Scanner sc = new Scanner(new File("file.txt"));
while (sc.hasNextLine()) {
    System.out.println(sc.nextLine());
}
sc.close();
```
- **Writing to a File**:
```java
FileWriter fw = new FileWriter("file.txt");
fw.write("Hello World");
fw.close();
```
