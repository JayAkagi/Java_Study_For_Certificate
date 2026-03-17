package module02;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ExerciseTest {

    @Test
    void testEx1_Inheritance() {
        Car car = new Car("Toyota", 2022, 4);
        assertEquals("Toyota", car.make, "1a: car.make");
        assertEquals(2022, car.year, "1a: car.year");
        assertEquals(4, car.numDoors, "1a: car.numDoors");
        assertEquals("Make: Toyota, Year: 2022", car.getInfo(), "1b: car.getInfo()");
        assertEquals("Toyota's car engine purrs: Vroooom!", car.getStartSound(), "1b: car startSound");
        assertEquals("Toyota goes: Beep beep!", car.honk(), "1b: car honk");

        Truck truck = new Truck("Ford", 2020, 5.0);
        assertEquals("Ford", truck.make, "1c: truck.make");
        assertEquals(5.0, truck.payloadTons, "1c: truck.payloadTons");
        assertEquals("Ford's truck engine roars: BRRRMM!", truck.getStartSound(), "1c: truck startSound");
        assertEquals("Loading 5.0 tons of cargo", truck.loadCargo(), "1c: truck loadCargo");

        Vehicle v = new Car("Honda", 2021, 2);
        assertEquals("Honda's car engine purrs: Vroooom!", v.getStartSound(), "1d: poly startSound via Vehicle ref");
    }

    @Test
    void testEx2_AbstractClasses() {
        Manager mgr = new Manager("Sarah", 80000.0, 6);
        assertEquals("Sarah", mgr.name, "2a: manager name");
        assertEquals(6, mgr.teamSize, "2a: manager teamSize");
        assertEquals(16000.0, mgr.calculateBonus(), 0.01, "2a: manager bonus (20%)");
        assertEquals(96000.0, mgr.totalPay(), 0.01, "2a: manager totalPay");

        Developer dev = new Developer("Mike", 90000.0, "Java");
        assertEquals("Mike", dev.name, "2b: developer name");
        assertEquals("Java", dev.programmingLanguage, "2b: developer language");
        assertEquals(13500.0, dev.calculateBonus(), 0.01, "2b: developer bonus (15%)");
        assertEquals(103500.0, dev.totalPay(), 0.01, "2b: developer totalPay");

        Employee e1 = mgr;
        Employee e2 = dev;
        assertTrue(e1 instanceof Employee, "2c: Manager is an Employee");
        assertTrue(e2 instanceof Employee, "2c: Developer is an Employee");
    }

    @Test
    void testEx3_Interfaces() {
        Document doc = new Document("Annual Report");
        assertEquals("Printing document: Annual Report", doc.print(), "3a: print()");
        assertEquals("Saving document: Annual Report to disk", doc.save(), "3b: save()");
        assertEquals("Creating backup...", doc.backup(), "3c: backup() default");
        assertTrue(doc instanceof Printable, "3d: Document is Printable");
        assertTrue(doc instanceof Saveable, "3e: Document is Saveable");
    }

    @Test
    void testEx4_EqualsHashCode() {
        Book b1 = new Book("Clean Code", "Robert Martin", 2008);
        Book b2 = new Book("Clean Code", "Robert Martin", 2020);
        Book b3 = new Book("Effective Java", "Joshua Bloch", 2018);

        assertEquals("Book{title='Clean Code', author='Robert Martin', year=2008}", b1.toString(), "4a: toString");
        assertTrue(b1.equals(b2), "4b: b1.equals(b2) → true (same title+author)");
        assertFalse(b1.equals(b3), "4c: b1.equals(b3) → false (diff title)");
        assertTrue(b1.hashCode() == b2.hashCode(), "4d: hashCode consistent between equal books");
        assertFalse(b1.equals(null), "4e: b1 not equal to null");
        assertFalse(b1.equals("Clean Code"), "4f: b1 not equal to a String");
    }

    @Test
    void testChallenge_Polymorphism() {
        Employee[] team = {
            new Manager("Alice", 85000, 4),
            new Developer("Bob", 75000, "Python"),
            new Manager("Carol", 95000, 10),
            new Developer("Dave", 80000, "JavaScript")
        };
        int managers = 0, developers = 0;
        for (Employee e : team) {
            if (e instanceof Manager)   managers++;
            if (e instanceof Developer) developers++;
        }
        assertEquals(2, managers, "challenge: 2 managers");
        assertEquals(2, developers, "challenge: 2 developers");
        assertEquals("Python", ((Developer) team[1]).programmingLanguage, "challenge: Bob's language");
        assertEquals(4, ((Manager) team[0]).teamSize, "challenge: Alice's teamSize");
    }
}
