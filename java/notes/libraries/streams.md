# STREAMS

### 1. File Input/Output Streams

**FileInputStream**: Reads raw bytes from a file (useful for images, audio, etc.)
  ```java
  FileInputStream fis = new FileInputStream("input.txt");
  int data;
  while ((data = fis.read()) != -1) {
      System.out.print((char) data);
  }
  fis.close();
  ```

**FileOutputStream**: Writes raw bytes to a file

```java
FileOutputStream fos = new FileOutputStream("output.txt");
String text = "Hello World!";
fos.write(text.getBytes());
fos.close();
```
### 2. Object Input/Output Streams

**ObjectOutputStream**: Writes Java objects to a file (serialization)

```java
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"));
oos.writeObject(myObject); // Object must implement Serializable
oos.close();
```

**ObjectInputStream**: Reads Java objects from a file (deserialization)

```java
ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"));
MyClass obj = (MyClass) ois.readObject();
ois.close();
```
### 3. Scanner Class with Files
Used for reading text files line by line, word by word, or token by token

```java
File file = new File("input.txt");
Scanner sc = new Scanner(file);

while (sc.hasNextLine()) {
    String line = sc.nextLine();
    System.out.println(line);
}
sc.close();
```

### 4. Key Notes
- FileInputStream / FileOutputStream: Work with bytes (good for binary data).
- FileReader / FileWriter: Work with characters (good for text).
- ObjectInputStream / ObjectOutputStream: Work with serialized objects.
- Scanner: Convenient for parsing text from files (supports regex, tokenizing).

*Always close streams (or use try-with-resources) to avoid memory leaks.*