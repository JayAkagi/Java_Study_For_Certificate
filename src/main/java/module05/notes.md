# Module 05 — Lambdas & Functional Interfaces

Java 8 introduced lambdas — a concise way to write anonymous functions. This is one of the most important modern Java features and heavily tested in certification exams.

---

## 1. What is a Lambda?

A lambda is a short, anonymous function you can pass around as a value.

**Before lambdas** (anonymous class):
```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running!");
    }
};
r.run();
```

**With lambda:**
```java
Runnable r = () -> System.out.println("Running!");
r.run();
```

---

## 2. Lambda Syntax

```
(parameters) -> expression
(parameters) -> { statements; }
```

```java
// No parameters
() -> System.out.println("Hello!")

// One parameter (parentheses optional for single param)
x -> x * 2
(x) -> x * 2

// Multiple parameters
(a, b) -> a + b

// Multiple statements — use block body
(a, b) -> {
    int sum = a + b;
    System.out.println("Sum: " + sum);
    return sum;
}
```

---

## 3. Functional Interfaces

A **functional interface** has exactly **one abstract method**. Lambdas implement functional interfaces.

Java provides built-in ones in `java.util.function`:

### `Runnable` — no input, no output
```java
Runnable task = () -> System.out.println("Task running!");
task.run();
```

### `Consumer<T>` — takes T, returns nothing
```java
import java.util.function.Consumer;

Consumer<String> printer = s -> System.out.println(">> " + s);
printer.accept("Hello");  // >> Hello
```

### `Supplier<T>` — no input, returns T
```java
import java.util.function.Supplier;

Supplier<String> greeting = () -> "Hello, World!";
System.out.println(greeting.get());  // Hello, World!
```

### `Function<T, R>` — takes T, returns R
```java
import java.util.function.Function;

Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("hello"));  // 5

// Chaining with andThen:
Function<Integer, Integer> doubleIt = n -> n * 2;
Function<String, Integer> lengthThenDouble = length.andThen(doubleIt);
System.out.println(lengthThenDouble.apply("hello"));  // 10
```

### `Predicate<T>` — takes T, returns boolean
```java
import java.util.function.Predicate;

Predicate<String> isLong = s -> s.length() > 5;
System.out.println(isLong.test("hi"));      // false
System.out.println(isLong.test("hello world"));  // true

// Combining predicates:
Predicate<Integer> isEven = n -> n % 2 == 0;
Predicate<Integer> isPositive = n -> n > 0;

Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
Predicate<Integer> isEvenOrPositive  = isEven.or(isPositive);
Predicate<Integer> isOdd             = isEven.negate();

System.out.println(isEvenAndPositive.test(4));   // true
System.out.println(isEvenAndPositive.test(-4));  // false
```

### `BiFunction<T, U, R>` — takes T and U, returns R
```java
import java.util.function.BiFunction;

BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
System.out.println(repeat.apply("ha", 3));  // hahaha
```

---

## 4. Method References

A shorthand for lambdas that just call an existing method.

| Type | Syntax | Lambda equivalent |
|------|--------|-------------------|
| Static method | `ClassName::staticMethod` | `x -> ClassName.staticMethod(x)` |
| Instance method (specific) | `obj::instanceMethod` | `x -> obj.instanceMethod(x)` |
| Instance method (any instance) | `ClassName::instanceMethod` | `x -> x.instanceMethod()` |
| Constructor | `ClassName::new` | `x -> new ClassName(x)` |

```java
// Static method reference
Function<String, Integer> parse = Integer::parseInt;
System.out.println(parse.apply("42"));  // 42

// Instance method reference (specific object)
String prefix = "Hello: ";
Function<String, String> greet = prefix::concat;
System.out.println(greet.apply("Alice"));  // Hello: Alice

// Instance method reference (any instance of String)
Function<String, String> upper = String::toUpperCase;
System.out.println(upper.apply("hello"));  // HELLO

// Constructor reference
Supplier<ArrayList<String>> listFactory = ArrayList::new;
ArrayList<String> list = listFactory.get();
```

---

## 5. Using Lambdas with Collections

This is where lambdas really shine — sorting and processing lists:

```java
import java.util.*;

List<String> names = new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob", "Dave"));

// Sort with lambda (instead of anonymous Comparator)
names.sort((a, b) -> a.compareTo(b));                    // alphabetical
names.sort((a, b) -> b.compareTo(a));                    // reverse
names.sort((a, b) -> a.length() - b.length());           // by length

// Method reference equivalent:
names.sort(String::compareTo);

// forEach with lambda
names.forEach(name -> System.out.println(name));
names.forEach(System.out::println);   // method reference version

// removeIf — remove elements matching a predicate
names.removeIf(name -> name.startsWith("A"));
```

---

## 6. `@FunctionalInterface` Annotation

Mark your own functional interfaces:

```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);  // exactly one abstract method
}

MathOperation add      = (a, b) -> a + b;
MathOperation multiply = (a, b) -> a * b;
MathOperation power    = (a, b) -> (int) Math.pow(a, b);

System.out.println(add.operate(3, 4));       // 7
System.out.println(multiply.operate(3, 4));  // 12
System.out.println(power.operate(2, 8));     // 256
```

---

## Quick Reference

| Interface | Method | Input → Output |
|-----------|--------|----------------|
| `Runnable` | `run()` | nothing → void |
| `Consumer<T>` | `accept(T)` | T → void |
| `Supplier<T>` | `get()` | nothing → T |
| `Function<T,R>` | `apply(T)` | T → R |
| `Predicate<T>` | `test(T)` | T → boolean |
| `BiFunction<T,U,R>` | `apply(T,U)` | T, U → R |
| `BiConsumer<T,U>` | `accept(T,U)` | T, U → void |
| `UnaryOperator<T>` | `apply(T)` | T → T |
| `BinaryOperator<T>` | `apply(T,T)` | T, T → T |

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
