# Module 03 — Exception Handling

Exceptions are Java's way of handling errors at runtime. Proper exception handling is what separates professional code from beginner code.

---

## 1. What is an Exception?

When something goes wrong at runtime, Java throws an **Exception** — an object that describes what went wrong.

Without handling:
```java
int[] arr = {1, 2, 3};
System.out.println(arr[10]);  // ArrayIndexOutOfBoundsException — program crashes!
```

With handling:
```java
try {
    System.out.println(arr[10]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Index out of bounds: " + e.getMessage());
}
// Program continues normally
```

---

## 2. The Exception Hierarchy

```
Throwable
├── Error             (serious JVM errors — don't catch these)
│     └── OutOfMemoryError, StackOverflowError
└── Exception
      ├── Checked Exceptions    (must be handled or declared)
      │     ├── IOException
      │     ├── SQLException
      │     └── FileNotFoundException
      └── RuntimeException      (unchecked — optional to handle)
            ├── NullPointerException
            ├── ArrayIndexOutOfBoundsException
            ├── NumberFormatException
            ├── IllegalArgumentException
            └── ClassCastException
```

**Checked vs Unchecked:**
- **Checked**: compiler forces you to handle or declare them (e.g., file reading)
- **Unchecked** (RuntimeException): optional — usually bugs you should fix

---

## 3. try-catch-finally

```java
try {
    // Code that might throw an exception
    int result = 10 / 0;

} catch (ArithmeticException e) {
    // Handle this specific exception
    System.out.println("Math error: " + e.getMessage());

} catch (Exception e) {
    // Catch-all for any other exception (always put LAST)
    System.out.println("Something went wrong: " + e.getMessage());

} finally {
    // ALWAYS runs — whether exception occurred or not
    // Use for cleanup: closing files, connections, etc.
    System.out.println("Finally block always runs.");
}
```

### Multiple catch blocks
```java
try {
    String s = null;
    int[] arr = new int[3];
    arr[s.length()] = 5;  // could throw NullPointerException OR ArrayIndexOutOfBoundsException

} catch (NullPointerException e) {
    System.out.println("Null reference: " + e.getMessage());

} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Array index error: " + e.getMessage());
}
```

### Multi-catch (Java 7+)
```java
try {
    // ...
} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
    System.out.println("Either null or bad index: " + e.getMessage());
}
```

---

## 4. throws — Declaring Exceptions

If you don't want to handle an exception yourself, you can **declare** it with `throws` so the caller handles it:

```java
// This method might throw IOException — caller must handle it
public void readFile(String path) throws IOException {
    // ... file reading code
}

// Caller must handle:
public void run() {
    try {
        readFile("data.txt");
    } catch (IOException e) {
        System.out.println("File error: " + e.getMessage());
    }
}
```

---

## 5. throw — Throwing Exceptions Manually

Use `throw` to raise an exception yourself (for validation, etc.):

```java
public void setAge(int age) {
    if (age < 0 || age > 150) {
        throw new IllegalArgumentException("Age must be between 0 and 150. Got: " + age);
    }
    this.age = age;
}

// Calling code:
try {
    setAge(-5);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());  // Age must be between 0 and 150. Got: -5
}
```

---

## 6. Custom Exceptions

Create your own exceptions for domain-specific errors:

```java
// Custom checked exception
class InsufficientFundsException extends Exception {
    double amount;

    InsufficientFundsException(double amount) {
        super("Insufficient funds. Need: $" + amount);
        this.amount = amount;
    }
}

// Custom unchecked exception
class InvalidUsernameException extends RuntimeException {
    InvalidUsernameException(String username) {
        super("Invalid username: " + username);
    }
}

// Using custom exceptions:
class BankAccount {
    double balance;

    BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
        System.out.println("Withdrew $" + amount + ". Balance: $" + balance);
    }
}

// Usage:
BankAccount account = new BankAccount(100.0);
try {
    account.withdraw(150.0);
} catch (InsufficientFundsException e) {
    System.out.println(e.getMessage());  // Insufficient funds. Need: $50.0
}
```

---

## 7. try-with-resources (Java 7+)

Automatically closes resources (like files) — no need for `finally`:

```java
// Old way (needs finally):
Scanner sc = null;
try {
    sc = new Scanner(new File("data.txt"));
    // use sc...
} catch (FileNotFoundException e) {
    e.printStackTrace();
} finally {
    if (sc != null) sc.close();  // manual cleanup
}

// New way (auto-closes):
try (Scanner sc = new Scanner(new File("data.txt"))) {
    // use sc...
    // sc.close() is called automatically
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
```

The resource must implement `AutoCloseable` interface.

---

## 8. Common Exception Methods

```java
try {
    // ...
} catch (Exception e) {
    e.getMessage()     // short description of the error
    e.toString()       // class name + message
    e.printStackTrace()  // full stack trace — great for debugging
    e.getCause()       // the original exception that caused this one
}
```

---

## 9. Best Practices

| Do | Don't |
|----|-------|
| Catch specific exceptions | Catch bare `Exception` unless necessary |
| Log or handle exceptions meaningfully | `catch (Exception e) {}` (swallow silently) |
| Use custom exceptions for domain errors | Use generic exceptions for everything |
| Use try-with-resources for I/O | Forget to close resources |
| Let exceptions propagate if you can't handle them | Catch just to re-throw identically |

---

## Key Takeaways

- `try` — code that might fail
- `catch` — handle the failure
- `finally` — always runs (cleanup)
- `throw` — raise an exception manually
- `throws` — declare that a method may throw (caller must handle)
- Checked exceptions: must handle or declare
- Unchecked (RuntimeException): optional, usually programming errors
- Custom exceptions: extend `Exception` (checked) or `RuntimeException` (unchecked)

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
