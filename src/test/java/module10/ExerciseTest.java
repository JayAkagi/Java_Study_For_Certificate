package module10;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ExerciseTest {

    // -------------------------------------------------------------------------
    // Calculator tests
    // -------------------------------------------------------------------------

    @Test
    void testCalculator_Add() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3), "add(2,3) = 5");
        assertEquals(0, calc.add(0, 0), "add(0,0) = 0");
        assertEquals(0, calc.add(-5, 5), "add(-5,5) = 0");
        assertEquals(300, calc.add(100, 200), "add(100,200) = 300");
        assertEquals(-20, calc.add(-10, -10), "add(-10,-10) = -20");
    }

    @Test
    void testCalculator_Subtract() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.subtract(5, 3), "subtract(5,3) = 2");
        assertEquals(0, calc.subtract(0, 0), "subtract(0,0) = 0");
        assertEquals(-2, calc.subtract(3, 5), "subtract(3,5) = -2");
        assertEquals(0, calc.subtract(-3, -3), "subtract(-3,-3) = 0");
    }

    @Test
    void testCalculator_Multiply() {
        Calculator calc = new Calculator();
        assertEquals(12, calc.multiply(3, 4), "multiply(3,4) = 12");
        assertEquals(0, calc.multiply(0, 100), "multiply(0,100) = 0");
        assertEquals(-10, calc.multiply(-2, 5), "multiply(-2,5) = -10");
        assertEquals(12, calc.multiply(-3, -4), "multiply(-3,-4) = 12");
    }

    @Test
    void testCalculator_Divide() {
        Calculator calc = new Calculator();
        assertEquals(2.5, calc.divide(10, 4), 0.001, "divide(10,4) = 2.5");
        assertEquals(3.0, calc.divide(9, 3), 0.001, "divide(9,3) = 3.0");
        assertEquals(0.25, calc.divide(1, 4), 0.001, "divide(1,4) = 0.25");
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0), "divide(10,0) should throw ArithmeticException");
    }

    @Test
    void testCalculator_IsEven() {
        Calculator calc = new Calculator();
        assertTrue(calc.isEven(0), "isEven(0) = true");
        assertTrue(calc.isEven(2), "isEven(2) = true");
        assertTrue(calc.isEven(100), "isEven(100) = true");
        assertTrue(calc.isEven(-6), "isEven(-6) = true");
        assertFalse(calc.isEven(1), "isEven(1) = false");
        assertFalse(calc.isEven(3), "isEven(3) = false");
        assertFalse(calc.isEven(99), "isEven(99) = false");
        assertFalse(calc.isEven(-5), "isEven(-5) = false");
    }

    @Test
    void testCalculator_Factorial() {
        Calculator calc = new Calculator();
        assertEquals(1L, calc.factorial(0), "factorial(0) = 1");
        assertEquals(1L, calc.factorial(1), "factorial(1) = 1");
        assertEquals(2L, calc.factorial(2), "factorial(2) = 2");
        assertEquals(120L, calc.factorial(5), "factorial(5) = 120");
        assertEquals(3628800L, calc.factorial(10), "factorial(10) = 3628800");
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1), "factorial(-1) should throw IllegalArgumentException");
    }

    // -------------------------------------------------------------------------
    // StringUtils tests
    // -------------------------------------------------------------------------

    @Test
    void testStringUtils_IsPalindrome() {
        StringUtils utils = new StringUtils();
        assertTrue(utils.isPalindrome("racecar"), "isPalindrome('racecar') = true");
        assertTrue(utils.isPalindrome("Racecar"), "isPalindrome('Racecar') = true");
        assertTrue(utils.isPalindrome("MADAM"), "isPalindrome('MADAM') = true");
        assertFalse(utils.isPalindrome("hello"), "isPalindrome('hello') = false");
        assertFalse(utils.isPalindrome(null), "isPalindrome(null) = false");
        assertFalse(utils.isPalindrome(""), "isPalindrome('') = false");
        assertTrue(utils.isPalindrome("a"), "isPalindrome('a') = true");
    }

    @Test
    void testStringUtils_CountVowels() {
        StringUtils utils = new StringUtils();
        assertEquals(3, utils.countVowels("Hello World"), "countVowels('Hello World') = 3");
        assertEquals(5, utils.countVowels("AEIOU"), "countVowels('AEIOU') = 5");
        assertEquals(0, utils.countVowels("rhythm"), "countVowels('rhythm') = 0");
        assertEquals(0, utils.countVowels(null), "countVowels(null) = 0");
        assertEquals(0, utils.countVowels(""), "countVowels('') = 0");
        assertEquals(2, utils.countVowels("Java"), "countVowels('Java') = 2");
    }

    @Test
    void testStringUtils_ReverseWords() {
        StringUtils utils = new StringUtils();
        assertEquals("Java World Hello", utils.reverseWords("Hello World Java"), "reverseWords('Hello World Java')");
        assertEquals("single", utils.reverseWords("single"), "reverseWords('single')");
        assertEquals("", utils.reverseWords(""), "reverseWords('')");
        assertEquals("", utils.reverseWords(null), "reverseWords(null)");
        assertEquals("C B A", utils.reverseWords("A B C"), "reverseWords('A B C')");
    }

    @Test
    void testStringUtils_IsNumeric() {
        StringUtils utils = new StringUtils();
        assertTrue(utils.isNumeric("12345"), "isNumeric('12345') = true");
        assertTrue(utils.isNumeric("0"), "isNumeric('0') = true");
        assertFalse(utils.isNumeric("abc"), "isNumeric('abc') = false");
        assertFalse(utils.isNumeric("12a3"), "isNumeric('12a3') = false");
        assertFalse(utils.isNumeric(null), "isNumeric(null) = false");
        assertFalse(utils.isNumeric(""), "isNumeric('') = false");
        assertFalse(utils.isNumeric("3.14"), "isNumeric('3.14') = false");
    }

    // -------------------------------------------------------------------------
    // BankAccount tests
    // -------------------------------------------------------------------------

    @Test
    void testBankAccount_Constructor() {
        BankAccount acc = new BankAccount("Alice", 1000.0);
        assertEquals("Alice", acc.getOwner(), "constructor: owner = Alice");
        assertEquals(1000.0, acc.getBalance(), 0.001, "constructor: balance = 1000.0");

        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Bad", -100.0),
                "constructor: negative balance should throw IllegalArgumentException");

        BankAccount zero = new BankAccount("Zero", 0.0);
        assertEquals(0.0, zero.getBalance(), 0.001, "constructor: zero balance is valid");
    }

    @Test
    void testBankAccount_Deposit() {
        BankAccount acc = new BankAccount("Bob", 500.0);
        acc.deposit(200.0);
        assertEquals(700.0, acc.getBalance(), 0.001, "deposit: balance after +200 = 700");
        acc.deposit(50.0);
        assertEquals(750.0, acc.getBalance(), 0.001, "deposit: balance after +50 = 750");

        assertThrows(IllegalArgumentException.class, () -> acc.deposit(0.0), "deposit(0) should throw IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> acc.deposit(-10.0), "deposit(-10) should throw IllegalArgumentException");
    }

    @Test
    void testBankAccount_Withdraw() {
        BankAccount acc = new BankAccount("Carol", 500.0);
        acc.withdraw(100.0);
        assertEquals(400.0, acc.getBalance(), 0.001, "withdraw: balance after -100 = 400");

        acc.withdraw(400.0);
        assertEquals(0.0, acc.getBalance(), 0.001, "withdraw: balance after exact withdrawal = 0");

        assertThrows(IllegalStateException.class, () -> acc.withdraw(1.0), "withdraw: insufficient funds should throw IllegalStateException");

        BankAccount acc2 = new BankAccount("Dave", 100.0);
        assertThrows(IllegalArgumentException.class, () -> acc2.withdraw(0.0), "withdraw(0) should throw IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> acc2.withdraw(-50.0), "withdraw(-50) should throw IllegalArgumentException");
    }

    @Test
    void testBankAccount_FullScenario() {
        BankAccount acc = new BankAccount("Eve", 1000.0);
        acc.deposit(500.0);
        assertEquals(1500.0, acc.getBalance(), 0.001, "scenario: after deposit 500 → 1500");
        acc.withdraw(200.0);
        assertEquals(1300.0, acc.getBalance(), 0.001, "scenario: after withdraw 200 → 1300");
        acc.deposit(100.0);
        acc.withdraw(400.0);
        assertEquals(1000.0, acc.getBalance(), 0.001, "scenario: final balance = 1000");
    }
}
