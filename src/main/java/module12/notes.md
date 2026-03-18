# Module 12 — SOLID Principles

SOLID is a set of five design principles that make code easier to maintain, test, and extend. Every Java developer is expected to know these.

---

## S — Single Responsibility Principle (SRP)

**A class should have only ONE reason to change.**

Each class should do ONE thing and do it well.

### Bad:
```java
class UserService {
    public void createUser(String name, String email) { /* ... */ }
    public void sendWelcomeEmail(String email) { /* ... */ }   // email logic
    public void saveToDatabase(User user) { /* ... */ }        // DB logic
    public String generateReport() { /* ... */ }               // reporting logic
}
// This class changes when email logic changes, DB logic changes, AND reporting changes
```

### Good:
```java
class UserService {
    private UserRepository repo;
    private EmailService email;

    public void createUser(String name, String email) {
        User user = new User(name, email);
        repo.save(user);
        emailService.sendWelcome(email);
    }
}

class UserRepository {
    public void save(User user) { /* only DB logic */ }
}

class EmailService {
    public void sendWelcome(String email) { /* only email logic */ }
}
```

---

## O — Open/Closed Principle (OCP)

**Open for extension, closed for modification.**

You should be able to add new behavior without changing existing code.

### Bad:
```java
class DiscountCalculator {
    double calculate(String customerType, double price) {
        if (customerType.equals("VIP")) return price * 0.8;
        if (customerType.equals("MEMBER")) return price * 0.9;
        // Adding "STUDENT" discount requires MODIFYING this method
        return price;
    }
}
```

### Good (use polymorphism/strategy):
```java
interface DiscountStrategy {
    double apply(double price);
}

class VipDiscount implements DiscountStrategy {
    @Override public double apply(double price) { return price * 0.8; }
}

class MemberDiscount implements DiscountStrategy {
    @Override public double apply(double price) { return price * 0.9; }
}

// Adding new type: just ADD a new class — existing code unchanged
class StudentDiscount implements DiscountStrategy {
    @Override public double apply(double price) { return price * 0.85; }
}

class DiscountCalculator {
    double calculate(DiscountStrategy strategy, double price) {
        return strategy.apply(price);  // never needs to change
    }
}
```

---

## L — Liskov Substitution Principle (LSP)

**Subtypes must be substitutable for their base types.**

If you have a `Bird` variable, you should be able to use any `Bird` subclass without the program breaking.

### Bad:
```java
class Bird {
    void fly() { System.out.println("Flying!"); }
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
        // Breaks LSP — a Penguin IS-A Bird but CAN'T use Bird's fly()
    }
}
```

### Good (restructure the hierarchy):
```java
abstract class Bird {
    abstract void eat();
}

interface Flyable {
    void fly();
}

class Eagle extends Bird implements Flyable {
    @Override public void eat() { System.out.println("Eagle eats fish."); }
    @Override public void fly() { System.out.println("Eagle soars!"); }
}

class Penguin extends Bird {
    @Override public void eat() { System.out.println("Penguin eats fish."); }
    public void swim() { System.out.println("Penguin swims!"); }
    // No fly() — Penguin doesn't claim to fly
}
```

---

## I — Interface Segregation Principle (ISP)

**Clients should not be forced to depend on interfaces they don't use.**

Split large interfaces into smaller, more specific ones.

### Bad:
```java
// Fat interface — forces all implementors to implement everything
interface Worker {
    void work();
    void eat();
    void sleep();
    void code();     // only developers do this
    void manage();   // only managers do this
}

// Robot must implement eat() and sleep() — it doesn't need them!
class Robot implements Worker {
    @Override public void work() { /* yes */ }
    @Override public void eat() { throw new UnsupportedOperationException(); }
    @Override public void sleep() { throw new UnsupportedOperationException(); }
    @Override public void code() { throw new UnsupportedOperationException(); }
    @Override public void manage() { throw new UnsupportedOperationException(); }
}
```

### Good (split interfaces):
```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Codeable {
    void code();
}

class Human implements Workable, Eatable {
    @Override public void work() { /* ... */ }
    @Override public void eat()  { /* ... */ }
}

class Developer extends Human implements Codeable {
    @Override public void code() { /* ... */ }
}

class Robot implements Workable {
    @Override public void work() { /* just work */ }
    // No unnecessary methods!
}
```

---

## D — Dependency Inversion Principle (DIP)

**Depend on abstractions, not on concrete implementations.**

High-level modules should not depend on low-level modules. Both should depend on abstractions (interfaces).

### Bad:
```java
class MySQLDatabase {
    void save(String data) { System.out.println("Saving to MySQL: " + data); }
}

class UserService {
    private MySQLDatabase db = new MySQLDatabase();  // tightly coupled to MySQL!

    void saveUser(String name) {
        db.save(name);  // can't switch to PostgreSQL without changing UserService
    }
}
```

### Good (depend on interface):
```java
interface Database {
    void save(String data);
}

class MySQLDatabase implements Database {
    @Override
    public void save(String data) { System.out.println("MySQL: " + data); }
}

class PostgreSQLDatabase implements Database {
    @Override
    public void save(String data) { System.out.println("PostgreSQL: " + data); }
}

class InMemoryDatabase implements Database {
    @Override
    public void save(String data) { System.out.println("In-memory: " + data); }
}

class UserService {
    private Database db;  // depends on abstraction, not concrete class!

    // Inject the dependency from outside (Dependency Injection)
    public UserService(Database db) {
        this.db = db;
    }

    void saveUser(String name) {
        db.save(name);  // works with ANY database implementation
    }
}

// Usage:
UserService mysqlService = new UserService(new MySQLDatabase());
UserService pgService    = new UserService(new PostgreSQLDatabase());
UserService testService  = new UserService(new InMemoryDatabase());  // great for testing!
```

---

## SOLID Quick Reference

| Letter | Principle | One-line Summary |
|--------|-----------|-----------------|
| S | Single Responsibility | One class = one job |
| O | Open/Closed | Extend, don't modify |
| L | Liskov Substitution | Subclass must work as parent |
| I | Interface Segregation | Small, focused interfaces |
| D | Dependency Inversion | Depend on abstractions |

---

## Why SOLID Matters for Jr. Dev

- **Interviewers will ask about this** — knowing SOLID is expected
- **Easier testing** — DIP makes unit testing much easier (inject mocks)
- **Team collaboration** — SOLID code is easier for others to understand and extend
- **Refactoring confidence** — well-structured code is safer to change

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
