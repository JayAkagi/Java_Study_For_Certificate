# Module 02 — OOP Deep Dive

You know classes and objects. This module goes deeper into the four pillars of OOP and the features that make Java's type system powerful.

---

## The Four Pillars (Quick Review)

| Pillar | What it means |
|--------|---------------|
| **Encapsulation** | Hide data with `private`, expose via getters/setters |
| **Inheritance** | A class can extend another, reusing its code |
| **Polymorphism** | One interface, many implementations |
| **Abstraction** | Define *what* to do, not *how* |

---

## 1. Inheritance

A child class `extends` a parent class and inherits its fields and methods.

```java
class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    void speak() {
        System.out.println(name + " makes a sound.");
    }
}

class Dog extends Animal {
    String breed;

    Dog(String name, String breed) {
        super(name);        // calls Animal's constructor
        this.breed = breed;
    }

    @Override
    void speak() {         // overrides parent method
        System.out.println(name + " barks!");
    }

    void fetch() {
        System.out.println(name + " fetches the ball!");
    }
}

// Usage
Dog d = new Dog("Rex", "Labrador");
d.speak();   // Rex barks!
d.fetch();   // Rex fetches the ball!
```

### `super` keyword
- `super(...)` — calls the parent's constructor (must be first line)
- `super.method()` — calls the parent's version of an overridden method

### Rules
- A class can only extend **one** class (single inheritance)
- Use `final class` to prevent a class from being extended
- Use `final` on a method to prevent it from being overridden

---

## 2. Polymorphism

The same variable type can hold objects of different subclasses. The correct method is called at runtime.

```java
Animal a1 = new Dog("Rex", "Lab");
Animal a2 = new Cat("Whiskers");

Animal[] animals = { a1, a2 };

for (Animal a : animals) {
    a.speak();   // calls Dog's speak() or Cat's speak() — decided at runtime
}
```

### instanceof
Check what type an object actually is:

```java
Animal a = new Dog("Rex", "Lab");

if (a instanceof Dog) {
    Dog d = (Dog) a;   // cast to Dog to access Dog-specific methods
    d.fetch();
}

// Java 16+ pattern matching (cleaner):
if (a instanceof Dog d) {
    d.fetch();         // d is already cast
}
```

---

## 3. Abstract Classes

An abstract class is a **template** — it can have both concrete methods AND abstract methods (no body). You **cannot** instantiate it directly.

```java
abstract class Shape {
    String color;

    Shape(String color) {
        this.color = color;
    }

    // Abstract method — subclasses MUST implement this
    abstract double area();

    // Concrete method — shared by all shapes
    void printInfo() {
        System.out.println(color + " shape with area: " + area());
    }
}

class Circle extends Shape {
    double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double width, height;

    Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}

// Shape s = new Shape("red");  // ERROR: cannot instantiate abstract class
Shape s = new Circle("red", 5.0);
s.printInfo();  // red shape with area: 78.53...
```

---

## 4. Interfaces

An interface is a **contract** — it defines what methods a class must have, but not how. A class can implement **multiple** interfaces.

```java
interface Flyable {
    // All methods are public abstract by default
    void fly();

    // Default method (Java 8+) — has a body, can be overridden
    default void land() {
        System.out.println("Landing...");
    }

    // Static method (Java 8+)
    static String getDescription() {
        return "This thing can fly";
    }
}

interface Swimmable {
    void swim();
}

// A Duck can both fly AND swim
class Duck implements Flyable, Swimmable {
    String name;

    Duck(String name) { this.name = name; }

    @Override
    public void fly() {
        System.out.println(name + " flaps wings and flies!");
    }

    @Override
    public void swim() {
        System.out.println(name + " paddles in the water!");
    }
}

Duck duck = new Duck("Donald");
duck.fly();     // Donald flaps wings and flies!
duck.swim();    // Donald paddles in the water!
duck.land();    // Landing... (from default method)
```

---

## 5. Abstract Class vs Interface — When to Use Which?

| | Abstract Class | Interface |
|---|---|---|
| Can have state (fields)? | Yes | Only constants (`static final`) |
| Can have constructors? | Yes | No |
| Multiple inheritance? | No (one extends) | Yes (many implements) |
| Use when... | Shared code + template | Define a contract/capability |

**Rule of thumb:**
- IS-A relationship with shared state → `abstract class` (Dog IS-A Animal)
- CAN-DO capability → `interface` (Duck CAN fly, CAN swim)

---

## 6. The Object Class

Every class in Java implicitly extends `Object`. Important methods you can override:

```java
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override toString: called when you print an object
    @Override
    public String toString() {
        return "Person{name=" + name + ", age=" + age + "}";
    }

    // Override equals: defines what "equal" means
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return this.name.equals(other.name) && this.age == other.age;
    }

    // Always override hashCode when you override equals!
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

Person p1 = new Person("Alice", 30);
Person p2 = new Person("Alice", 30);

System.out.println(p1);             // Person{name=Alice, age=30}
System.out.println(p1.equals(p2));  // true
```

---

## 7. Access Modifiers (Quick Reference)

| Modifier | Same class | Same package | Subclass | Everywhere |
|----------|-----------|--------------|----------|------------|
| `private` | Yes | No | No | No |
| (default) | Yes | Yes | No | No |
| `protected` | Yes | Yes | Yes | No |
| `public` | Yes | Yes | Yes | Yes |

---

## Key Takeaways

- Use `extends` for inheritance (one parent only)
- Use `implements` for interfaces (many allowed)
- `abstract` class: has state + partial implementation
- `interface`: pure contract, no state, enables multiple "capabilities"
- Always `@Override` when overriding — the compiler will catch typos
- Override `toString()`, `equals()`, and `hashCode()` when needed

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
