package module12;

import java.util.*;

/**
 * MODULE 12 — SOLID Principles
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Study the BAD classes to understand what they violate
 *   3. Implement the GOOD (refactored) classes below
 *   4. Run: javac *.java && java Test
 */

// ============================================================================
// EXERCISE 1 — SRP (Single Responsibility Principle)
// ============================================================================

// BAD: This class does too many things
class BadOrderProcessor {
    void processOrder(String customerName, double amount, String email) {
        if (customerName == null || customerName.isEmpty()) {
            System.out.println("Invalid customer name");
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }
        System.out.println("Saving order: " + customerName + " - $" + amount);
        System.out.println("Sending confirmation email to: " + email);
        System.out.println("=== RECEIPT ===");
        System.out.println("Customer: " + customerName);
        System.out.println("Amount: $" + amount);
        System.out.println("===============");
    }
}

class OrderValidator {
    boolean validate(String name, double amount) {
        return false; // placeholder
    }
}

class OrderRepository {
    String save(String customer, double amount) {
        return ""; // placeholder
    }
}

class OrderEmailService {
    String sendConfirmation(String email) {
        return ""; // placeholder
    }
}

class ReceiptPrinter {
    String print(String customer, double amount) {
        return ""; // placeholder
    }
}

// ============================================================================
// EXERCISE 2 — OCP (Open/Closed Principle)
// ============================================================================

// BAD: Adding a new notification type requires modifying this method
class BadNotificationService {
    void send(String type, String message) {
        if (type.equals("EMAIL")) {
            System.out.println("Email: " + message);
        } else if (type.equals("SMS")) {
            System.out.println("SMS: " + message);
        }
    }
}

interface Notifier {
    String send(String message);
}

class EmailNotifier implements Notifier {
    @Override
    public String send(String message) {
        return ""; // placeholder
    }
}

class SmsNotifier implements Notifier {
    @Override
    public String send(String message) {
        return ""; // placeholder
    }
}

class PushNotifier implements Notifier {
    @Override
    public String send(String message) {
        return ""; // placeholder
    }
}

class SlackNotifier implements Notifier {
    @Override
    public String send(String message) {
        return ""; // placeholder
    }
}

class NotificationService {
    private List<Notifier> notifiers = new ArrayList<>();

    void addNotifier(Notifier n) {
        // TO DO: notifiers.add(n)
    }

    List<String> notifyAll(String message) {
        return new ArrayList<>(); // placeholder
    }
}

// ============================================================================
// EXERCISE 3 — LSP (Liskov Substitution Principle)
// ============================================================================

// BAD hierarchy (demonstrates the violation — do not modify)
class BadRectangle {
    protected int width, height;
    void setWidth(int w)  { this.width = w; }
    void setHeight(int h) { this.height = h; }
    int area() { return width * height; }
}

class BadSquare extends BadRectangle {
    @Override
    void setWidth(int w)  { this.width = w; this.height = w; }
    @Override
    void setHeight(int h) { this.height = h; this.width = h; }
}

abstract class GoodShape {
    abstract int area();
}

class GoodRectangle extends GoodShape {
    int width, height;

    GoodRectangle(int width, int height) {
        // TO DO: store width and height
    }

    @Override
    int area() {
        return 0; // placeholder
    }
}

class GoodSquare extends GoodShape {
    int side;

    GoodSquare(int side) {
        // TO DO: store side
    }

    @Override
    int area() {
        return 0; // placeholder
    }
}

// ============================================================================
// EXERCISE 4 — ISP (Interface Segregation Principle)
// ============================================================================

// BAD: One fat interface (leave as-is to show the violation)
interface BadPrinter {
    void print(String doc);
    void scan(String doc);
    void fax(String doc);
    void staple(String doc);
}

interface Printable {
    String print(String doc);
}

interface Scannable {
    String scan(String doc);
}

interface Faxable {
    String fax(String doc);
}

interface Stapleable {
    String staple(String doc);
}

class BasicPrinter implements Printable {
    @Override
    public String print(String doc) {
        return ""; // placeholder
    }
}

class OfficePrinter implements Printable, Scannable {
    @Override
    public String print(String doc) {
        return ""; // placeholder
    }

    @Override
    public String scan(String doc) {
        return ""; // placeholder
    }
}

class AllInOnePrinter implements Printable, Scannable, Faxable, Stapleable {
    @Override
    public String print(String doc)   { return ""; }
    @Override
    public String scan(String doc)    { return ""; }
    @Override
    public String fax(String doc)     { return ""; }
    @Override
    public String staple(String doc)  { return ""; }
}

// ============================================================================
// EXERCISE 5 — DIP (Dependency Inversion Principle)
// ============================================================================

// BAD: Directly coupled to LegacyCsvExporter
class BadReportService {
    private LegacyCsvExporter exporter = new LegacyCsvExporter();
    void generateReport(List<String> data) { exporter.export(data); }
}

class LegacyCsvExporter {
    void export(List<String> data) { System.out.println("Exporting as CSV: " + data); }
}

interface ReportExporter {
    String export(List<String> data);
}

class CsvExporter implements ReportExporter {
    @Override
    public String export(List<String> data) {
        return ""; // placeholder
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public String export(List<String> data) {
        return ""; // placeholder
    }
}

class PdfExporter implements ReportExporter {
    @Override
    public String export(List<String> data) {
        return ""; // placeholder
    }
}

class ReportService {
    private ReportExporter exporter;

    ReportService(ReportExporter exporter) {
        // TO DO: store exporter
    }

    String generateReport(List<String> data) {
        return ""; // placeholder
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
