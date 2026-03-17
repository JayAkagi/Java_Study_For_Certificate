package module02;

import java.util.Objects;

/**
 * MODULE 02 — OOP Deep Dive
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each class stub — fill in the TO DO sections
 *   3. Run: javac *.java && java Test
 */

// ============================================================================
// TO DO 1a: Vehicle class
// ============================================================================
class Vehicle {
    String make;
    int year;

    Vehicle(String make, int year) {
        // TO DO: store make and year
    }

    String getInfo() {
        return ""; // TO DO: return "Make: [make], Year: [year]"
    }

    String getStartSound() {
        return ""; // TO DO: return "[make]'s engine starts: Vroom!"
    }
}

// ============================================================================
// TO DO 1b: Car extending Vehicle
// ============================================================================
class Car extends Vehicle {
    int numDoors;

    Car(String make, int year, int numDoors) {
        super(make, year);
        // TO DO: store numDoors
    }

    @Override
    String getStartSound() {
        return ""; // TO DO: return "[make]'s car engine purrs: Vroooom!"
    }

    String honk() {
        return ""; // TO DO: return "[make] goes: Beep beep!"
    }
}

// ============================================================================
// TO DO 1c: Truck extending Vehicle
// ============================================================================
class Truck extends Vehicle {
    double payloadTons;

    Truck(String make, int year, double payloadTons) {
        super(make, year);
        // TO DO: store payloadTons
    }

    @Override
    String getStartSound() {
        return ""; // TO DO: return "[make]'s truck engine roars: BRRRMM!"
    }

    String loadCargo() {
        return ""; // TO DO: return "Loading [payloadTons] tons of cargo"
    }
}

// ============================================================================
// TO DO 2a: abstract class Employee
// ============================================================================
abstract class Employee {
    String name;
    double baseSalary;

    Employee(String name, double baseSalary) {
        // TO DO: store fields
    }

    abstract double calculateBonus();

    double totalPay() {
        return 0.0; // TO DO: return baseSalary + calculateBonus()
    }
}

// ============================================================================
// TO DO 2b: Manager extends Employee
// ============================================================================
class Manager extends Employee {
    int teamSize;

    Manager(String name, double baseSalary, int teamSize) {
        super(name, baseSalary);
        // TO DO: store teamSize
    }

    @Override
    public double calculateBonus() {
        return 0.0; // TO DO: return baseSalary * 0.20
    }
}

// ============================================================================
// TO DO 2c: Developer extends Employee
// ============================================================================
class Developer extends Employee {
    String programmingLanguage;

    Developer(String name, double baseSalary, String programmingLanguage) {
        super(name, baseSalary);
        // TO DO: store programmingLanguage
    }

    @Override
    public double calculateBonus() {
        return 0.0; // TO DO: return baseSalary * 0.15
    }
}

// ============================================================================
// TO DO 3a: interface Printable
// ============================================================================
interface Printable {
    String print();
}

// ============================================================================
// TO DO 3b: interface Saveable
// ============================================================================
interface Saveable {
    String save();
    default String backup() {
        return ""; // TO DO: return "Creating backup..."
    }
}

// ============================================================================
// TO DO 3c: class Document implements BOTH Printable and Saveable
// ============================================================================
class Document implements Printable, Saveable {
    String title;

    Document(String title) {
        // TO DO: store title
    }

    @Override
    public String print() {
        return ""; // TO DO: return "Printing document: [title]"
    }

    @Override
    public String save() {
        return ""; // TO DO: return "Saving document: [title] to disk"
    }
}

// ============================================================================
// TO DO 4a: Book class
// ============================================================================
class Book {
    String title;
    String author;
    int year;

    Book(String title, String author, int year) {
        // TO DO: store fields
    }

    @Override
    public boolean equals(Object o) {
        return false; // TO DO: return true if same title AND author (cast to Book, check fields)
    }

    @Override
    public int hashCode() {
        return 0; // TO DO: return Objects.hash(title, author)
    }

    @Override
    public String toString() {
        return ""; // TO DO: return "Book{title='[title]', author='[author]', year=[year]}"
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
