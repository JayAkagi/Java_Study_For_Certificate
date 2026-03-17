# Java Study Certificate

A structured Java learning project covering core and advanced Java topics through hands-on exercises. Each module contains method stubs to implement, verified by JUnit 5 tests.

## Modules

| Module | Topic |
|--------|-------|
| 01 | Advanced Collections (ArrayList, LinkedList, HashSet, TreeSet, HashMap, TreeMap, Iterator) |
| 02 | OOP Deep Dive (inheritance, polymorphism, interfaces, abstract classes) |
| 03 | Exception Handling (custom exceptions, try/catch/finally) |
| 04 | Generics (generic classes and methods) |
| 05 | Lambdas & Functional Interfaces |
| 06 | Stream API |
| 07 | Optional & Date/Time API |
| 08 | File I/O (java.io, java.nio) |
| 09 | Multithreading & Concurrency |
| 10 | Unit Testing |
| 11 | Design Patterns |
| 12 | SOLID Principles |

## How to Run Tests

Run all tests:
```bash
mvn test
```

Run tests for a specific module:
```bash
mvn test -Dtest="module01.ExerciseTest"
```

With console output:
```bash
mvn test -Dtest="module01.ExerciseTest" -Dsurefire.useFile=false
```

## Requirements

- Java 17+
- Maven 3.6+
