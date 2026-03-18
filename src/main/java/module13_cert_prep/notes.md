# Module 13 — Certification Prep (OCP Java SE 17)

This module prepares you for the **Oracle Certified Professional: Java SE 17 Developer** exam (1Z0-829).

---

## Exam Overview

| Item | Details |
|------|---------|
| Exam code | 1Z0-829 |
| Questions | ~50 multiple choice / drag-and-drop |
| Duration | 90 minutes |
| Passing score | ~68% |
| Cost | ~$245 USD |
| Version | Java SE 17 |

---

## Exam Objectives Mapped to This Roadmap

| Objective | Module |
|-----------|--------|
| Handling Date, Time, Text, Numeric/Math operations | 07 |
| Controlling Program Flow | You already know this |
| Java Object-Oriented Approach | 02 |
| Exception Handling | 03 |
| Working with Arrays and Collections | 01 |
| Stream API | 06 |
| Lambda expressions and functional interfaces | 05 |
| Java I/O API | 08 |
| Concurrency | 09 |
| Java Platform Module System | This module |
| Annotations | This module |

---

## 1. Tricky Java Behaviors (common exam traps)

### Integer auto-boxing cache
```java
Integer a = 127;
Integer b = 127;
System.out.println(a == b);   // true (cached range: -128 to 127)

Integer c = 128;
Integer d = 128;
System.out.println(c == d);   // FALSE (outside cache range!)
System.out.println(c.equals(d));  // true (always use equals!)
```

### String pool
```java
String s1 = "hello";
String s2 = "hello";
String s3 = new String("hello");

System.out.println(s1 == s2);        // true (same pool reference)
System.out.println(s1 == s3);        // false (s3 is a new object)
System.out.println(s1.equals(s3));   // true (same content)
System.out.println(s1 == s3.intern()); // true (intern puts s3 in pool)
```

### Numeric promotion and casting
```java
byte b = 10;
byte result = b + b;    // COMPILE ERROR! + promotes bytes to int
byte result = (byte)(b + b);  // explicit cast needed

int i = 5;
double d = 2.0;
System.out.println(i / 2);    // 2 (integer division)
System.out.println(i / 2.0);  // 2.5 (double division)
System.out.println((double)i / 2);  // 2.5
```

### Short-circuit evaluation
```java
int x = 5;
if (x > 0 && ++x > 0) { }  // x becomes 6
if (x > 0 || ++x > 0) { }  // x stays 6 (short-circuits, ++ never runs)
```

---

## 2. OOP Tricky Cases

### Variable hiding vs method overriding
```java
class Parent {
    int x = 10;
    void show() { System.out.println("Parent: " + x); }
}

class Child extends Parent {
    int x = 20;                 // HIDES parent's x (not override!)
    @Override void show() { System.out.println("Child: " + x); }
}

Parent p = new Child();
System.out.println(p.x);    // 10 (variable access is based on REFERENCE TYPE)
p.show();                    // "Child: 20" (method is based on ACTUAL TYPE)
```

### Constructor chaining
```java
class A {
    A() { this(10); System.out.println("A()"); }
    A(int x) { System.out.println("A(int): " + x); }
}

class B extends A {
    B() { super(5); System.out.println("B()"); }
}

new B();
// Output:
// A(int): 5
// B()
// Note: super(5) calls A(int) directly — A() never runs!
```

### Interface default method conflict
```java
interface A { default void greet() { System.out.println("A"); } }
interface B { default void greet() { System.out.println("B"); } }

class C implements A, B {
    @Override
    public void greet() {
        A.super.greet();   // must explicitly pick one (or provide your own)
    }
}
```

---

## 3. Exception Handling Tricky Cases

### finally and return
```java
int test() {
    try {
        return 1;
    } finally {
        return 2;  // finally ALWAYS runs — this overrides the try's return!
    }
}
// Returns: 2
```

### Exception hierarchy in multi-catch
```java
try { /* ... */ }
catch (Exception e) { }      // OK
catch (RuntimeException e) { }  // COMPILE ERROR: RuntimeException is a subtype of Exception
                                 // must catch subtype FIRST, then supertype
```

### Checked exception with overriding
```java
class Parent {
    void method() throws IOException { }
}

class Child extends Parent {
    @Override
    void method() throws FileNotFoundException { }  // OK (FileNotFoundException extends IOException)
    // void method() throws Exception { }           // COMPILE ERROR (broader than parent)
}
```

---

## 4. Collections & Generics Tricky Cases

### Unmodifiable vs Immutable
```java
List<String> list = new ArrayList<>(Arrays.asList("a", "b"));
List<String> unmod = Collections.unmodifiableList(list);
unmod.add("c");   // UnsupportedOperationException at runtime

list.add("c");    // Works! unmod reflects the change
System.out.println(unmod);  // [a, b, c] — it IS affected by original list

// Truly immutable:
List<String> immutable = List.of("a", "b", "c");  // cannot be modified
```

### Type erasure
```java
// At runtime, generics are erased:
List<String> strings = new ArrayList<>();
List<Integer> ints = new ArrayList<>();
System.out.println(strings.getClass() == ints.getClass());  // TRUE!
// Both are just ArrayList at runtime
```

---

## 5. Java 9+ Module System (briefly)

The module system (`module-info.java`) is tested lightly:

```java
// module-info.java
module com.myapp {
    requires java.sql;           // depends on this module
    exports com.myapp.api;       // makes this package available to others
    opens com.myapp.internal;    // opens for reflection (e.g., frameworks)
}
```

Key terms:
- `module` — defines a module
- `requires` — declares a dependency
- `exports` — makes a package public to other modules
- `opens` — allows reflection access

---

## 6. Records (Java 16+)

Records are compact, immutable data classes:

```java
record Point(int x, int y) { }

// Equivalent to a class with:
// - final fields x and y
// - Constructor: Point(int x, int y)
// - Getters: x(), y() (NOT getX() — note this!)
// - toString(), equals(), hashCode() auto-generated

Point p = new Point(3, 4);
System.out.println(p.x());       // 3 (NOT p.getX())
System.out.println(p);           // Point[x=3, y=4]
System.out.println(p.equals(new Point(3, 4)));  // true
```

---

## 7. Sealed Classes (Java 17)

```java
// Only these classes can extend Shape
sealed class Shape permits Circle, Rectangle, Triangle { }

final class Circle extends Shape { double radius; }
final class Rectangle extends Shape { double width, height; }
non-sealed class Triangle extends Shape { }  // can be further extended
```

---

## 8. Text Blocks (Java 15+)

```java
String json = """
    {
        "name": "Alice",
        "age": 30
    }
    """;
// No escape characters needed, preserves formatting
```

---

## 9. Switch Expressions (Java 14+)

```java
// New switch expression (returns a value)
String result = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
};

// With yield for block bodies:
int numLetters = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> 6;
    case TUESDAY -> 7;
    default -> {
        System.out.println("Calculating...");
        yield day.toString().length();
    }
};
```

---

## 10. Exam Strategies

1. **Read the question carefully** — Java exams often have subtle traps (missing imports, wrong access modifiers, compile errors)
2. **"What is the output?"** — trace through line by line, check constructors, static blocks, inheritance
3. **"Does it compile?"** — check access modifiers, type compatibility, checked exceptions
4. **Eliminate wrong answers** — even if unsure, eliminate obvious wrong choices
5. **Know the common trap categories:**
   - Static vs instance context
   - `==` vs `.equals()`
   - Integer cache (-128 to 127)
   - `finally` always runs
   - Autoboxing edge cases
   - Access modifiers in subclasses

---

## Recommended Study Plan

| Week | Focus |
|------|-------|
| 1-2 | Review Modules 01-04 (collections, OOP, exceptions, generics) |
| 3-4 | Review Modules 05-07 (lambdas, streams, optional, datetime) |
| 5 | Module 08-09 (I/O, concurrency) |
| 6 | This module: tricky cases + practice questions |
| 7-8 | Practice exams (Enthuware or Whizlabs) |

---

## Next Step

Open `PracticeQuestions.java` and work through all the questions. Try to answer each one BEFORE looking at the answer.
