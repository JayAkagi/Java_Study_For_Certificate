# Module 11 — Design Patterns

Design patterns are reusable solutions to common programming problems. Knowing these makes you stand out in interviews and write cleaner, more maintainable code.

---

## 1. Singleton — One Instance Only

**Problem:** Ensure a class has only one instance (e.g., config, logger, DB connection).

```java
public class DatabaseConnection {
    // Static field holds the single instance
    private static DatabaseConnection instance;

    private String url;

    // Private constructor — nobody can call 'new DatabaseConnection()'
    private DatabaseConnection() {
        this.url = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Database connected!");
    }

    // Thread-safe lazy initialization
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public String getUrl() { return url; }
}

// Usage:
DatabaseConnection db1 = DatabaseConnection.getInstance();
DatabaseConnection db2 = DatabaseConnection.getInstance();
System.out.println(db1 == db2);  // true — same object!
```

---

## 2. Factory — Let a Method Create Objects

**Problem:** Create objects without exposing construction logic. Decide which subclass to create at runtime.

```java
// Product interface
interface Animal {
    void speak();
}

// Concrete products
class Dog implements Animal {
    @Override public void speak() { System.out.println("Woof!"); }
}

class Cat implements Animal {
    @Override public void speak() { System.out.println("Meow!"); }
}

class Bird implements Animal {
    @Override public void speak() { System.out.println("Tweet!"); }
}

// Factory
class AnimalFactory {
    public static Animal create(String type) {
        return switch (type.toLowerCase()) {
            case "dog"  -> new Dog();
            case "cat"  -> new Cat();
            case "bird" -> new Bird();
            default -> throw new IllegalArgumentException("Unknown animal: " + type);
        };
    }
}

// Usage — caller doesn't need to know which class to instantiate
Animal pet = AnimalFactory.create("dog");
pet.speak();  // Woof!
```

---

## 3. Builder — Step-by-Step Object Construction

**Problem:** Constructing complex objects with many optional fields. Avoids long constructor argument lists.

```java
class Pizza {
    // Required
    private final String size;
    // Optional
    private final String crust;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;

    // Private constructor — only Builder can call it
    private Pizza(Builder builder) {
        this.size = builder.size;
        this.crust = builder.crust;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.mushrooms = builder.mushrooms;
    }

    @Override
    public String toString() {
        return size + " pizza on " + crust + " crust" +
               (cheese ? ", cheese" : "") +
               (pepperoni ? ", pepperoni" : "") +
               (mushrooms ? ", mushrooms" : "");
    }

    // Static inner Builder class
    public static class Builder {
        private final String size;   // required
        private String crust = "thin";  // defaults
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean mushrooms = false;

        public Builder(String size) {
            this.size = size;
        }

        public Builder crust(String crust) { this.crust = crust; return this; }
        public Builder cheese()    { this.cheese = true;    return this; }
        public Builder pepperoni() { this.pepperoni = true; return this; }
        public Builder mushrooms() { this.mushrooms = true; return this; }

        public Pizza build() { return new Pizza(this); }
    }
}

// Usage — fluent, readable, no confusion about which argument is which
Pizza p1 = new Pizza.Builder("Large")
    .crust("stuffed")
    .cheese()
    .pepperoni()
    .build();

Pizza p2 = new Pizza.Builder("Small")
    .mushrooms()
    .build();

System.out.println(p1);  // Large pizza on stuffed crust, cheese, pepperoni
System.out.println(p2);  // Small pizza on thin crust, mushrooms
```

---

## 4. Observer — Subscribe to Events

**Problem:** One object needs to notify many others when its state changes (event system).

```java
import java.util.ArrayList;
import java.util.List;

// Observer interface — those who want to be notified
interface Observer {
    void update(String event, Object data);
}

// Subject — the thing that emits events
class EventBus {
    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer o) {
        observers.add(o);
    }

    public void unsubscribe(Observer o) {
        observers.remove(o);
    }

    public void publish(String event, Object data) {
        for (Observer o : observers) {
            o.update(event, data);
        }
    }
}

// Concrete observers
class Logger implements Observer {
    @Override
    public void update(String event, Object data) {
        System.out.println("[LOG] Event: " + event + " | Data: " + data);
    }
}

class EmailNotifier implements Observer {
    @Override
    public void update(String event, Object data) {
        System.out.println("[EMAIL] Sending notification for: " + event);
    }
}

// Usage:
EventBus bus = new EventBus();
bus.subscribe(new Logger());
bus.subscribe(new EmailNotifier());

bus.publish("USER_REGISTERED", "alice@email.com");
// [LOG] Event: USER_REGISTERED | Data: alice@email.com
// [EMAIL] Sending notification for: USER_REGISTERED
```

---

## 5. Strategy — Swap Algorithms at Runtime

**Problem:** Different algorithms for the same problem. Choose which to use at runtime.

```java
// Strategy interface
interface SortStrategy {
    void sort(int[] data);
}

// Concrete strategies
class BubbleSort implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting with Bubble Sort...");
        // bubble sort implementation
    }
}

class QuickSort implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting with Quick Sort...");
        // quick sort implementation
    }
}

// Context — uses a strategy
class Sorter {
    private SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(int[] data) {
        strategy.sort(data);
    }
}

// Usage:
int[] data = {5, 2, 8, 1, 9};

Sorter sorter = new Sorter(new BubbleSort());
sorter.sort(data);   // uses Bubble Sort

sorter.setStrategy(new QuickSort());
sorter.sort(data);   // now uses Quick Sort

// With lambdas (for simple strategies):
sorter.setStrategy(d -> System.out.println("Lambda sort!"));
sorter.sort(data);
```

---

## 6. Decorator — Add Behavior Without Inheritance

**Problem:** Add features to an object at runtime without subclassing.

```java
// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete component
class SimpleCoffee implements Coffee {
    @Override public String getDescription() { return "Coffee"; }
    @Override public double getCost() { return 1.00; }
}

// Base decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }
}

// Concrete decorators
class Milk extends CoffeeDecorator {
    Milk(Coffee c) { super(c); }
    @Override public String getDescription() { return coffee.getDescription() + ", Milk"; }
    @Override public double getCost() { return coffee.getCost() + 0.25; }
}

class Sugar extends CoffeeDecorator {
    Sugar(Coffee c) { super(c); }
    @Override public String getDescription() { return coffee.getDescription() + ", Sugar"; }
    @Override public double getCost() { return coffee.getCost() + 0.10; }
}

class WhippedCream extends CoffeeDecorator {
    WhippedCream(Coffee c) { super(c); }
    @Override public String getDescription() { return coffee.getDescription() + ", Whipped Cream"; }
    @Override public double getCost() { return coffee.getCost() + 0.50; }
}

// Usage:
Coffee order = new SimpleCoffee();
order = new Milk(order);
order = new Sugar(order);
order = new WhippedCream(order);

System.out.println(order.getDescription());   // Coffee, Milk, Sugar, Whipped Cream
System.out.printf("Total: $%.2f%n", order.getCost());  // Total: $1.85
```

---

## Pattern Summary

| Pattern | Category | Use When |
|---------|----------|----------|
| Singleton | Creational | One instance needed globally |
| Factory | Creational | Decide which subclass to create at runtime |
| Builder | Creational | Complex object with many optional fields |
| Observer | Behavioral | One-to-many event notification |
| Strategy | Behavioral | Swap algorithms at runtime |
| Decorator | Structural | Add behavior without changing the class |

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
