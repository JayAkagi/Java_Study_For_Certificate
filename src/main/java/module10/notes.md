# Module 10 — Unit Testing with JUnit 5

Unit testing is non-negotiable for professional Java development. This module covers JUnit 5, the standard Java testing framework.

---

## 1. Why Unit Tests?

- Catch bugs early (before code reaches production)
- Safely refactor — tests confirm nothing broke
- Document behavior (tests show what code is *supposed* to do)
- Faster debugging — failed test tells you exactly what broke

---

## 2. Setup

Add JUnit 5 to your project. For Maven (`pom.xml`):

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

For Gradle (`build.gradle`):
```gradle
testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
```

---

## 3. Your First Test

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAddition() {
        // Arrange
        Calculator calc = new Calculator();

        // Act
        int result = calc.add(2, 3);

        // Assert
        assertEquals(5, result);
    }

    @Test
    void testDivisionByZero() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}
```

---

## 4. Core Annotations

| Annotation | What it does |
|------------|-------------|
| `@Test` | Marks a method as a test |
| `@BeforeEach` | Runs before EACH test method |
| `@AfterEach` | Runs after EACH test method |
| `@BeforeAll` | Runs ONCE before all tests (must be `static`) |
| `@AfterAll` | Runs ONCE after all tests (must be `static`) |
| `@Disabled` | Skip this test |
| `@DisplayName` | Custom name for the test in reports |

```java
class ExampleTest {

    static Connection db;

    @BeforeAll
    static void setupDatabase() {
        db = Database.connect();  // expensive setup once
    }

    @AfterAll
    static void tearDownDatabase() {
        db.close();
    }

    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();   // fresh instance per test
    }

    @AfterEach
    void tearDown() {
        // cleanup after each test
    }

    @Test
    @DisplayName("Addition of two positive numbers")
    void add_positiveNumbers() {
        assertEquals(7, calc.add(3, 4));
    }

    @Test
    @Disabled("Not implemented yet")
    void testNotReady() {
        // ...
    }
}
```

---

## 5. Assertions Reference

```java
// Equality
assertEquals(expected, actual)
assertEquals(expected, actual, "message on failure")
assertEquals(3.14, result, 0.001)   // delta for doubles!

// Null checks
assertNull(value)
assertNotNull(value)

// Boolean
assertTrue(condition)
assertFalse(condition)

// Same object reference
assertSame(obj1, obj2)
assertNotSame(obj1, obj2)

// Arrays and iterables
assertArrayEquals(expected, actual)

// Exception
assertThrows(ExceptionType.class, () -> codeToRun());

// Multiple assertions — all run even if one fails
assertAll(
    () -> assertEquals(5, result),
    () -> assertNotNull(result),
    () -> assertTrue(result > 0)
);

// Fail explicitly
fail("This should not be reached");
```

---

## 6. Test Naming Convention

Good test names describe behavior, not the method name:

```
methodName_scenario_expectedBehavior
```

Examples:
- `add_twoPositiveIntegers_returnsSum`
- `divide_byZero_throwsArithmeticException`
- `findUser_nonExistentId_returnsEmptyOptional`
- `isValid_nullEmail_returnsFalse`

---

## 7. Parameterized Tests

Run the same test with multiple inputs:

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

@ParameterizedTest
@ValueSource(ints = {2, 4, 6, 8, 10})
void isEven_evenNumbers_returnsTrue(int number) {
    assertTrue(MathUtils.isEven(number));
}

@ParameterizedTest
@CsvSource({
    "2, 3, 5",
    "0, 0, 0",
    "-1, 1, 0",
    "100, 200, 300"
})
void add_variousInputs_returnsCorrectSum(int a, int b, int expected) {
    assertEquals(expected, new Calculator().add(a, b));
}
```

---

## 8. What Makes a Good Unit Test?

| Principle | Meaning |
|-----------|---------|
| **Fast** | Tests should run in milliseconds |
| **Independent** | Each test is isolated — no shared state |
| **Repeatable** | Same result every time |
| **Self-validating** | Pass or fail, no manual inspection |
| **Timely** | Write tests close to (or before) the code |

This is the **F.I.R.S.T.** principle.

---

## 9. Test-Driven Development (TDD)

Write the test BEFORE the code:

1. **Red** — Write a failing test
2. **Green** — Write just enough code to make it pass
3. **Refactor** — Clean up the code, tests stay green

```
Red → Green → Refactor → repeat
```

---

## 10. What to Test

- Happy path (normal input, expected output)
- Edge cases (zero, empty string, null, max values)
- Error cases (invalid input, expected exceptions)
- Boundary values (just inside/outside limits)

---

## Note on Running Tests

Without Maven/Gradle, you can run JUnit 5 tests by:
1. Downloading `junit-platform-console-standalone.jar`
2. Running: `java -jar junit-platform-console-standalone.jar --select-class=YourTestClass`

For this module, the `Exercise.java` shows the test structure — use an IDE (IntelliJ or VS Code with Java extension) to run JUnit tests easily.

---

## Next Step

Open `Exercise.java` — it contains both the classes to test AND the test class. Complete the TODOs.
