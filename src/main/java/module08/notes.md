# Module 08 — File I/O

Reading and writing files is essential for real applications. Java offers multiple ways to do it — from classic streams to modern NIO.

---

## 1. The java.io Package — Classic I/O

### Reading a File (Line by Line)

```java
import java.io.*;

// BufferedReader — most common for text files
try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    System.out.println("Error reading file: " + e.getMessage());
}
```

### Writing to a File

```java
// FileWriter — basic text writing
try (FileWriter writer = new FileWriter("output.txt")) {
    writer.write("Hello, File!\n");
    writer.write("Second line\n");
} catch (IOException e) {
    e.printStackTrace();
}

// BufferedWriter — buffered (more efficient for many writes)
try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
    writer.write("Line 1");
    writer.newLine();     // platform-safe newline
    writer.write("Line 2");
} catch (IOException e) {
    e.printStackTrace();
}

// PrintWriter — convenient println
try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
    pw.println("Line 1");
    pw.printf("Value: %d%n", 42);
} catch (IOException e) {
    e.printStackTrace();
}
```

### Appending to a File (don't overwrite)

```java
// Second argument 'true' = append mode
try (FileWriter writer = new FileWriter("log.txt", true)) {
    writer.write("New log entry\n");
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## 2. java.nio.file — Modern NIO (Java 7+)

Simpler and more powerful. Use `Files` and `Path` classes.

```java
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

Path path = Path.of("data.txt");  // or Paths.get("data.txt")

// Read all lines at once
List<String> lines = Files.readAllLines(path);

// Read entire file as one String (Java 11+)
String content = Files.readString(path);

// Write a list of lines
List<String> data = Arrays.asList("Line 1", "Line 2", "Line 3");
Files.write(path, data);

// Write a string (Java 11+)
Files.writeString(path, "Hello, NIO!");

// Append
Files.writeString(path, "More content\n", StandardOpenOption.APPEND);

// Copy a file
Files.copy(Path.of("source.txt"), Path.of("destination.txt"));

// Move/rename a file
Files.move(Path.of("old.txt"), Path.of("new.txt"));

// Delete a file
Files.delete(path);
Files.deleteIfExists(path);  // doesn't throw if missing

// Check if file exists
Files.exists(path)        // true/false
Files.isReadable(path)
Files.isWritable(path)
```

---

## 3. Working with Directories

```java
Path dir = Path.of("myDirectory");

// Create directory
Files.createDirectory(dir);
Files.createDirectories(Path.of("a/b/c"));  // creates all parents too

// List files in a directory
Files.list(dir).forEach(System.out::println);

// Walk directory tree recursively
Files.walk(dir)
    .filter(Files::isRegularFile)
    .forEach(System.out::println);
```

---

## 4. File — The Old Way (still useful)

```java
import java.io.File;

File file = new File("data.txt");

file.exists()         // does the file exist?
file.length()         // size in bytes
file.getName()        // "data.txt"
file.getAbsolutePath() // full path
file.isFile()         // is it a file (not directory)?
file.isDirectory()    // is it a directory?
file.canRead()
file.canWrite()
file.delete()         // delete the file
file.mkdir()          // create directory
file.mkdirs()         // create directory + parents
file.listFiles()      // array of files in directory
```

---

## 5. Scanner — Easy Reading

```java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

try (Scanner sc = new Scanner(new File("data.txt"))) {
    while (sc.hasNextLine()) {
        System.out.println(sc.nextLine());
    }
} catch (FileNotFoundException e) {
    System.out.println("File not found: " + e.getMessage());
}

// Reading specific types
try (Scanner sc = new Scanner(new File("numbers.txt"))) {
    while (sc.hasNextInt()) {
        int n = sc.nextInt();
        System.out.println(n * 2);
    }
}
```

---

## 6. CSV Processing Example

```java
// Read a CSV file like: Alice,30,Engineer
try (BufferedReader reader = new BufferedReader(new FileReader("people.csv"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        String name = parts[0];
        int age = Integer.parseInt(parts[1].trim());
        String job = parts[2].trim();
        System.out.printf("Name: %s, Age: %d, Job: %s%n", name, age, job);
    }
}
```

---

## 7. Best Practices

| Do | Why |
|----|-----|
| Always use try-with-resources | Auto-closes streams, prevents resource leaks |
| Use `BufferedReader/Writer` for text | Buffering dramatically improves performance |
| Use `Files.readAllLines()` for small files | Cleaner, less boilerplate |
| Use `Files.readString()` for reading entire file | Java 11+, very clean |
| Handle `IOException` properly | File operations can always fail |
| Use `Path.of()` over `new File()` | Modern, more feature-rich |

---

## Key Takeaways

- `BufferedReader` / `BufferedWriter` — classic buffered text I/O
- `Files.readAllLines()`, `Files.readString()`, `Files.writeString()` — modern NIO
- Always use `try-with-resources` for file operations
- `Files.exists()`, `Files.copy()`, `Files.move()`, `Files.delete()` — file management
- `Files.walk()` — recursive directory traversal

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
