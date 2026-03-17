package module03;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ExerciseTest {

    @Test
    void testEx1_BasicTryCatch() {
        assertEquals("Cannot divide by zero!", Exercise.ex1a_divideByZero(), "1a: divide by zero caught");
        assertEquals("Not a valid number: hello", Exercise.ex1b_parseInvalid(), "1b: invalid parse caught");
        assertEquals("Null reference caught!", Exercise.ex1c_nullLength(), "1c: null reference caught");
    }

    @Test
    void testEx2_MultipleExceptions() {
        String[] data = {"42", null, "hello", "7"};
        List<String> results = Exercise.ex2_parseArray(data);
        assertTrue(results != null && results.size() == 4, "2: results has 4 entries");
        assertEquals("Parsed: 42", (results != null && results.size() > 0) ? results.get(0) : "", "2: index 0 = Parsed: 42");
        assertEquals("Null value encountered", (results != null && results.size() > 1) ? results.get(1) : "", "2: index 1 = Null value encountered");
        assertEquals("Not a number: hello", (results != null && results.size() > 2) ? results.get(2) : "", "2: index 2 = Not a number: hello");
        assertEquals("Parsed: 7", (results != null && results.size() > 3) ? results.get(3) : "", "2: index 3 = Parsed: 7");
    }

    @Test
    void testEx3_BankAccount() throws Exception {
        BankAccount acc = new BankAccount("Alice", 500.0);
        acc.deposit(200.0);
        assertEquals(700.0, acc.getBalance(), 0.01, "3a: balance after deposit 200");
        acc.withdraw(100.0);
        assertEquals(600.0, acc.getBalance(), 0.01, "3b: balance after withdraw 100");

        assertThrows(InsufficientFundsException.class, () -> acc.withdraw(1000.0));
        assertThrows(IllegalArgumentException.class, () -> acc.deposit(-50.0));
        assertThrows(IllegalArgumentException.class, () -> acc.withdraw(0.0));
    }

    @Test
    void testEx4_Person() {
        Person alice = new Person("Alice", 25);
        assertEquals("Person{name=Alice, age=25}", alice.toString(), "4a: valid person toString");

        assertThrows(InvalidAgeException.class, () -> new Person("Bad", -5));
        assertThrows(InvalidAgeException.class, () -> new Person("Old", 200));

        // These should not throw
        assertDoesNotThrow(() -> new Person("Zero", 0));
        assertDoesNotThrow(() -> new Person("Elder", 150));
    }

    @Test
    void testEx5_Propagation() {
        assertEquals(123, Exercise.ex5_parseNumber("123"), "5a: parse '123' = 123");
        assertThrows(NumberFormatException.class, () -> Exercise.ex5_parseNumber("abc"));
    }

    @Test
    void testChallenge_Calculate() {
        assertEquals(15.0, Exercise.calculate("10", "5", "+"), 0.001, "challenge: 10+5=15");
        assertEquals(7.0, Exercise.calculate("10", "3", "-"), 0.001, "challenge: 10-3=7");
        assertEquals(24.0, Exercise.calculate("4", "6", "*"), 0.001, "challenge: 4*6=24");
        assertEquals(2.5, Exercise.calculate("10", "4", "/"), 0.001, "challenge: 10/4=2.5");

        assertThrows(ArithmeticException.class, () -> Exercise.calculate("10", "0", "/"));
        assertThrows(IllegalArgumentException.class, () -> Exercise.calculate("abc", "5", "+"));
        assertThrows(IllegalArgumentException.class, () -> Exercise.calculate("10", "5", "^"));
    }
}
