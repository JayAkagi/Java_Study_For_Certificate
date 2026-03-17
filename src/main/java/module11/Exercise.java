package module11;

import java.util.*;

/**
 * MODULE 11 — Design Patterns
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each class stub below
 *   3. Run: javac *.java && java Test
 */

// ============================================================================
// PATTERN 1 — SINGLETON
// ============================================================================

class AppConfig {
    private static AppConfig instance;
    private Map<String, String> config = new HashMap<>();

    private AppConfig() {
    }

    static synchronized AppConfig getInstance() {
        return null; // placeholder
    }

    void set(String key, String value) {
        // TO DO: config.put(key, value)
    }

    String get(String key) {
        return "not found"; // placeholder
    }
}

// ============================================================================
// PATTERN 2 — FACTORY
// ============================================================================

interface Shape {
    double area();
    double perimeter();
    String draw();
}

class Circle implements Shape {
    double radius;

    Circle(double radius) {
        // TO DO: store radius
    }

    @Override
    public double area() {
        return 0.0; // placeholder
    }

    @Override
    public double perimeter() {
        return 0.0; // placeholder
    }

    @Override
    public String draw() {
        return ""; // placeholder
    }
}

class Rectangle implements Shape {
    double width, height;

    Rectangle(double width, double height) {
        // TO DO: store width and height
    }

    @Override
    public double area() {
        return 0.0; // placeholder
    }

    @Override
    public double perimeter() {
        return 0.0; // placeholder
    }

    @Override
    public String draw() {
        return ""; // placeholder
    }
}

class ShapeFactory {
    static Shape create(String type, double... dimensions) {
        throw new IllegalArgumentException("Unknown shape: " + type); // placeholder
    }
}

// ============================================================================
// PATTERN 3 — BUILDER
// ============================================================================

class User {
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String role;

    private User(Builder b) {
        this.username  = b.username;
        this.email     = b.email;
        this.firstName = b.firstName;
        this.lastName  = b.lastName;
        this.age       = b.age;
        this.role      = b.role;
    }

    String getUsername()  { return username; }
    String getEmail()     { return email; }
    String getFirstName() { return firstName; }
    String getLastName()  { return lastName; }
    int    getAge()       { return age; }
    String getRole()      { return role; }

    @Override
    public String toString() {
        return "User{username='" + username + "', email='" + email +
               "', firstName='" + firstName + "', lastName='" + lastName +
               "', age=" + age + ", role='" + role + "'}";
    }

    static class Builder {
        private String username;
        private String email;
        private String firstName = null;
        private String lastName  = null;
        private int    age       = 0;
        private String role      = null;

        Builder(String username, String email) {
            // TO DO: store username and email
        }

        Builder firstName(String v) { return this; }
        Builder lastName(String v)  { return this; }
        Builder age(int v)          { return this; }
        Builder role(String v)      { return this; }

        User build() {
            return new User(this);
        }
    }
}

// ============================================================================
// PATTERN 4 — OBSERVER
// ============================================================================

interface StockObserver {
    void onPriceChange(String ticker, double oldPrice, double newPrice);
}

class StockMarket {
    private Map<String, Double> stockPrices = new HashMap<>();
    private List<StockObserver> observers   = new ArrayList<>();

    void subscribe(StockObserver o) {
        // TO DO: observers.add(o)
    }

    void unsubscribe(StockObserver o) {
        // TO DO: observers.remove(o)
    }

    void setPrice(String ticker, double price) {
        // TO DO: notify observers
    }

    double getPrice(String ticker) {
        return stockPrices.getOrDefault(ticker, 0.0);
    }

    int observerCount() {
        return observers.size();
    }
}

class CountingObserver implements StockObserver {
    int callCount = 0;
    String lastTicker = null;
    double lastNewPrice = 0.0;

    @Override
    public void onPriceChange(String ticker, double oldPrice, double newPrice) {
        // TO DO: callCount++; store lastTicker and lastNewPrice
    }
}

// ============================================================================
// PATTERN 5 — STRATEGY
// ============================================================================

interface DiscountStrategy {
    double applyDiscount(double originalPrice);
    String getDescription();
}

class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice;
    }

    @Override
    public String getDescription() {
        return "No discount";
    }
}

class PercentageDiscount implements DiscountStrategy {
    private double percent;

    PercentageDiscount(double percent) {
        // TO DO: store percent
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice; // placeholder
    }

    @Override
    public String getDescription() {
        return ""; // placeholder
    }
}

class FlatDiscount implements DiscountStrategy {
    private double amount;

    FlatDiscount(double amount) {
        // TO DO: store amount
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice; // placeholder
    }

    @Override
    public String getDescription() {
        return ""; // placeholder
    }
}

class ShoppingCart {
    private List<Double> items = new ArrayList<>();
    private DiscountStrategy strategy = new NoDiscount();

    void addItem(double price) {
        // TO DO: items.add(price)
    }

    void setStrategy(DiscountStrategy s) {
        // TO DO: this.strategy = s
    }

    double getTotal() {
        return 0.0; // placeholder
    }
}

// ============================================================================
// PATTERN 6 — DECORATOR
// ============================================================================

interface TextFormatter {
    String format(String text);
}

class PlainText implements TextFormatter {
    @Override
    public String format(String text) {
        return text;
    }
}

abstract class TextDecorator implements TextFormatter {
    protected TextFormatter wrapped;

    TextDecorator(TextFormatter wrapped) {
        // TO DO: store wrapped
    }

    @Override
    public String format(String text) {
        return wrapped.format(text);
    }
}

class UpperCaseDecorator extends TextDecorator {
    UpperCaseDecorator(TextFormatter wrapped) {
        super(wrapped);
    }

    @Override
    public String format(String text) {
        return text; // placeholder
    }
}

class BracketDecorator extends TextDecorator {
    BracketDecorator(TextFormatter wrapped) {
        super(wrapped);
    }

    @Override
    public String format(String text) {
        return text; // placeholder
    }
}

class StarDecorator extends TextDecorator {
    StarDecorator(TextFormatter wrapped) {
        super(wrapped);
    }

    @Override
    public String format(String text) {
        return text; // placeholder
    }
}

// ============================================================================
// MAIN (leave as-is, students use Test.java)
// ============================================================================
public class Exercise {
    public static void main(String[] args) {
        System.out.println("Run: javac *.java && java Test");
    }
}
