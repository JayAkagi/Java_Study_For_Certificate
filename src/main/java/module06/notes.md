# Module 06 — Stream API

Streams are one of the most powerful Java 8+ features. They let you process collections declaratively — filtering, transforming, and aggregating data in clean, readable pipelines.

---

## 1. What is a Stream?

A Stream is a **pipeline of operations** on a sequence of elements. It does NOT store data — it processes it.

```
Source → Intermediate operations → Terminal operation
```

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

int sumOfEvenSquares = numbers.stream()       // source
    .filter(n -> n % 2 == 0)                  // intermediate: keep evens
    .map(n -> n * n)                          // intermediate: square them
    .reduce(0, Integer::sum);                 // terminal: sum them up

System.out.println(sumOfEvenSquares);  // 220 (4+16+36+64+100)
```

**Key properties:**
- Streams are **lazy** — intermediate operations don't run until a terminal operation is called
- Streams are **consumed once** — you can't reuse a stream after a terminal operation
- Streams do NOT modify the original collection

---

## 2. Creating Streams

```java
// From a Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> s1 = list.stream();

// From an array
String[] arr = {"x", "y", "z"};
Stream<String> s2 = Arrays.stream(arr);

// Stream.of() — inline values
Stream<Integer> s3 = Stream.of(1, 2, 3, 4, 5);

// Infinite stream (with limit)
Stream<Integer> s4 = Stream.iterate(0, n -> n + 2).limit(5);  // 0, 2, 4, 6, 8

Stream<Double> s5 = Stream.generate(Math::random).limit(3);   // 3 random doubles

// IntStream, LongStream, DoubleStream (primitives — avoid boxing overhead)
IntStream range = IntStream.range(1, 6);       // 1, 2, 3, 4, 5
IntStream rangeClosed = IntStream.rangeClosed(1, 5);  // 1, 2, 3, 4, 5
```

---

## 3. Intermediate Operations

These return a new Stream — you can chain as many as you want.

### `filter(Predicate)` — keep elements matching condition
```java
list.stream()
    .filter(s -> s.startsWith("A"))
    .forEach(System.out::println);
```

### `map(Function)` — transform each element
```java
List<String> names = Arrays.asList("alice", "bob", "charlie");
names.stream()
    .map(String::toUpperCase)
    .forEach(System.out::println);  // ALICE, BOB, CHARLIE
```

### `mapToInt/mapToDouble/mapToLong` — convert to primitive stream
```java
List<String> words = Arrays.asList("hello", "world", "java");
int totalLength = words.stream()
    .mapToInt(String::length)
    .sum();  // 5 + 5 + 4 = 14
```

### `flatMap(Function)` — flatten nested collections
```java
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2, 3),
    Arrays.asList(4, 5),
    Arrays.asList(6, 7, 8, 9)
);

List<Integer> flat = nested.stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList());
// [1, 2, 3, 4, 5, 6, 7, 8, 9]
```

### `sorted()` — sort elements
```java
stream.sorted()                        // natural order
stream.sorted(Comparator.reverseOrder()) // reverse
stream.sorted(Comparator.comparing(String::length)) // by field
```

### `distinct()` — remove duplicates
```java
Stream.of(1, 2, 2, 3, 3, 3).distinct().forEach(System.out::println);  // 1 2 3
```

### `limit(n)` / `skip(n)` — truncate / skip
```java
stream.limit(5)   // only first 5 elements
stream.skip(2)    // skip first 2 elements
```

### `peek(Consumer)` — for debugging (runs action without changing stream)
```java
stream
    .filter(n -> n > 3)
    .peek(n -> System.out.println("After filter: " + n))
    .map(n -> n * 2)
    .peek(n -> System.out.println("After map: " + n))
    .collect(Collectors.toList());
```

---

## 4. Terminal Operations

These trigger the pipeline and produce a result.

### `collect()` — gather into a collection
```java
import java.util.stream.Collectors;

List<String> result = stream.collect(Collectors.toList());
Set<String>  set    = stream.collect(Collectors.toSet());
String joined = stream.collect(Collectors.joining(", "));      // "a, b, c"
String joined2 = stream.collect(Collectors.joining(", ", "[", "]")); // "[a, b, c]"

// Group by
Map<Integer, List<String>> byLength = names.stream()
    .collect(Collectors.groupingBy(String::length));

// Count by group
Map<Integer, Long> countByLength = names.stream()
    .collect(Collectors.groupingBy(String::length, Collectors.counting()));
```

### `forEach(Consumer)` — perform action on each element
```java
stream.forEach(System.out::println);
```

### `count()` — number of elements
```java
long count = stream.filter(n -> n > 5).count();
```

### `findFirst()` / `findAny()` — returns Optional
```java
Optional<String> first = stream.filter(s -> s.startsWith("A")).findFirst();
first.ifPresent(System.out::println);
```

### `anyMatch / allMatch / noneMatch` — returns boolean
```java
boolean hasEven   = stream.anyMatch(n -> n % 2 == 0);   // at least one
boolean allPositive = stream.allMatch(n -> n > 0);       // all match
boolean noNulls   = stream.noneMatch(Objects::isNull);   // none match
```

### `reduce()` — fold elements into one value
```java
// Sum
int sum = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);  // 15

// Max string length
Optional<String> longest = stream.reduce((a, b) -> a.length() >= b.length() ? a : b);
```

### `min() / max()` — with comparator
```java
Optional<Integer> min = stream.min(Integer::compare);
Optional<Integer> max = stream.max(Integer::compare);
```

### `toArray()` — convert to array
```java
String[] arr = stream.toArray(String[]::new);
```

---

## 5. Real-World Example — Student Report

```java
class Student {
    String name;
    String subject;
    double grade;

    Student(String name, String subject, double grade) {
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }
}

List<Student> students = Arrays.asList(
    new Student("Alice", "Math", 92.0),
    new Student("Bob", "Math", 78.0),
    new Student("Charlie", "Science", 88.0),
    new Student("Alice", "Science", 95.0),
    new Student("Bob", "Science", 62.0)
);

// Average grade across all students
double avg = students.stream()
    .mapToDouble(s -> s.grade)
    .average()
    .orElse(0);

// Names of students who passed (grade >= 70), sorted
List<String> passed = students.stream()
    .filter(s -> s.grade >= 70)
    .map(s -> s.name)
    .distinct()
    .sorted()
    .collect(Collectors.toList());

// Group students by subject
Map<String, List<Student>> bySubject = students.stream()
    .collect(Collectors.groupingBy(s -> s.subject));
```

---

## Key Takeaways

- `stream()` — start a pipeline
- **Intermediate**: `filter`, `map`, `sorted`, `distinct`, `limit`, `skip`, `flatMap`
- **Terminal**: `collect`, `forEach`, `count`, `reduce`, `findFirst`, `anyMatch`, `min`, `max`
- `Collectors.toList()`, `joining()`, `groupingBy()` — essential collectors
- Streams are lazy and single-use

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
