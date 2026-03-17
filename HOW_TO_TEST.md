# How to Check Your Answers

## Run all modules at once

```bash
mvn test
```

Runs every test across all 12 modules. Expect failures until you implement the methods.

---

## Run a single module

```bash
mvn test -Dtest="module01.ExerciseTest"
```

Replace `module01` with whichever module you're working on:

| Module | Command |
|--------|---------|
| 01 — Advanced Collections   | `mvn test -Dtest="module01.ExerciseTest"` |
| 02 — OOP Deep Dive          | `mvn test -Dtest="module02.ExerciseTest"` |
| 03 — Exception Handling     | `mvn test -Dtest="module03.ExerciseTest"` |
| 04 — Generics               | `mvn test -Dtest="module04.ExerciseTest"` |
| 05 — Lambdas & Functional   | `mvn test -Dtest="module05.ExerciseTest"` |
| 06 — Streams                | `mvn test -Dtest="module06.ExerciseTest"` |
| 07 — Optional & Date/Time   | `mvn test -Dtest="module07.ExerciseTest"` |
| 08 — File I/O               | `mvn test -Dtest="module08.ExerciseTest"` |
| 09 — Multithreading         | `mvn test -Dtest="module09.ExerciseTest"` |
| 10 — Unit Testing           | `mvn test -Dtest="module10.ExerciseTest"` |
| 11 — Design Patterns        | `mvn test -Dtest="module11.ExerciseTest"` |
| 12 — SOLID Principles       | `mvn test -Dtest="module12.ExerciseTest"` |


## REMOVE VERBOSE OUTPUT
`mvn test -Dsurefire.useFile=false`

---

## Reading the output

**A passing test looks like:**
```
[INFO] Tests run: 43, Failures: 0, Errors: 0, Skipped: 0
```

**A failing test looks like:**
```
ExerciseTest.testEx1_ArrayList:12 1a: size is 5
  ==> expected: <5> but was: <0>
```

The number after the colon (`12`) is the line in the test file — useful if you want to see exactly what's being checked.

---

## Workflow

1. Open `src/main/java/moduleXX/Exercise.java`
2. Find a method with `// TO DO` and implement it
3. Run `mvn test -Dtest="moduleXX.ExerciseTest"` to check
4. Keep going until you see `BUILD SUCCESS`

---

## Where to find your exercise files

```
JL/
└── src/
    ├── main/java/        ← your Exercise.java files (implement these)
    │   ├── module01/
    │   ├── module02/
    │   └── ...
    └── test/java/        ← ExerciseTest.java files (don't edit these)
        ├── module01/
        ├── module02/
        └── ...
```
