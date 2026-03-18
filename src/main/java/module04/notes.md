# Module 04 — Generics

Generics let you write type-safe, reusable code that works with any type — while catching type errors at compile time, not runtime.

---

## 1. Why Generics?

Without generics (old way):
```java
ArrayList list = new ArrayList();
list.add("hello");
list.add(42);           // different types — no error yet
String s = (String) list.get(1);  // ClassCastException at RUNTIME!
```

With generics:
```java
ArrayList<String> list = new ArrayList<>();
list.add("hello");
list.add(42);           // COMPILE ERROR — caught immediately!
String s = list.get(0); // no cast needed, type is guaranteed
```

---

## 2. Generic Classes

Define a class that works with any type:

```java
// T is the type parameter (by convention: T, E, K, V, N)
class Box<T> {
    private T value;

    Box(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Box[" + value + "]";
    }
}

// Usage — T becomes String:
Box<String> strBox = new Box<>("Hello");
System.out.println(strBox.getValue());  // Hello

// T becomes Integer:
Box<Integer> intBox = new Box<>(42);
System.out.println(intBox.getValue());  // 42
```

---

## 3. Generic Methods

A method can have its own type parameter:

```java
class Utils {
    // <T> before return type declares T as a type parameter for this method
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // Returns the larger of two comparable items
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }
}

// Usage:
Integer[] ints = {1, 2, 3, 4, 5};
String[] strs = {"banana", "apple", "cherry"};

Utils.printArray(ints);     // 1 2 3 4 5
Utils.printArray(strs);     // banana apple cherry

System.out.println(Utils.max(10, 20));        // 20
System.out.println(Utils.max("apple", "zoo")); // zoo
```

---

## 4. Bounded Type Parameters

Restrict what types T can be:

```java
// T must be a Number or a subclass of Number
class MathBox<T extends Number> {
    private T value;

    MathBox(T value) { this.value = value; }

    double doubled() {
        return value.doubleValue() * 2;
    }
}

MathBox<Integer> intBox = new MathBox<>(5);
MathBox<Double> dblBox  = new MathBox<>(3.14);
// MathBox<String> strBox = new MathBox<>("hi");  // COMPILE ERROR

System.out.println(intBox.doubled());   // 10.0
System.out.println(dblBox.doubled());   // 6.28
```

---

## 5. Multiple Bounds

```java
// T must implement both Comparable and Serializable
class SortableItem<T extends Comparable<T> & java.io.Serializable> {
    // ...
}
```

---

## 6. Wildcards (?)

Use wildcards when you want to work with a generic type but you don't know (or care) the exact type parameter.

### Unbounded wildcard `<?>` — any type
```java
public static void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}

printList(new ArrayList<String>(Arrays.asList("a", "b")));
printList(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
```

### Upper bounded `<? extends T>` — T or any subtype (read-friendly)
```java
// Accept a list of Number or any subclass (Integer, Double, etc.)
public static double sum(List<? extends Number> list) {
    double total = 0;
    for (Number n : list) {
        total += n.doubleValue();
    }
    return total;
}

List<Integer> ints = Arrays.asList(1, 2, 3);
List<Double>  dbls = Arrays.asList(1.5, 2.5);
System.out.println(sum(ints));   // 6.0
System.out.println(sum(dbls));   // 4.0
```

### Lower bounded `<? super T>` — T or any supertype (write-friendly)
```java
// Accept a list that can hold Integers (Integer or Object or Number)
public static void addNumbers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3);
}
```

---

## 7. Generic Pair — Real-World Example

```java
class Pair<K, V> {
    private K first;
    private V second;

    Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    K getFirst()  { return first; }
    V getSecond() { return second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

// Usage:
Pair<String, Integer> nameAge = new Pair<>("Alice", 30);
Pair<String, String>  cityCode = new Pair<>("Tokyo", "TYO");

System.out.println(nameAge);   // (Alice, 30)
System.out.println(cityCode);  // (Tokyo, TYO)
```

---

## 8. Generic Stack Implementation

A classic data structure — great for practicing generics:

```java
import java.util.ArrayList;

class Stack<T> {
    private ArrayList<T> items = new ArrayList<>();

    void push(T item) {
        items.add(item);
    }

    T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return items.remove(items.size() - 1);
    }

    T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return items.get(items.size() - 1);
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    int size() {
        return items.size();
    }
}

Stack<String> stack = new Stack<>();
stack.push("first");
stack.push("second");
stack.push("third");
System.out.println(stack.pop());   // third
System.out.println(stack.peek());  // second
```

---

## Type Parameter Naming Convention

| Letter | Meaning |
|--------|---------|
| `T` | Type (general) |
| `E` | Element (collections) |
| `K` | Key (maps) |
| `V` | Value (maps) |
| `N` | Number |
| `R` | Return type |

---

## Key Takeaways

- Generics = type safety + code reuse
- `class Box<T>` — generic class
- `<T> void method(T val)` — generic method
- `<T extends Number>` — bounded (T must be Number or subclass)
- `<?>` — wildcard (any type)
- `<? extends T>` — upper bound (for reading)
- `<? super T>` — lower bound (for writing)

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
