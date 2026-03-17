package module10;

/**
 * MODULE 10 — Unit Testing (plain Java, no JUnit)
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement ALL methods in Calculator, StringUtils, and BankAccount below
 *   3. Run: javac *.java && java Test
 *
 * This module teaches you what unit tests look like while you implement
 * the classes being tested.
 */

// ============================================================================
// PART 1 — Calculator
// ============================================================================

/**
 * A simple calculator class.
 * TO DO: Implement all methods below.
 */
class Calculator {

    // TO DO: Return a + b
    public int add(int a, int b) {
        return 0; // placeholder
    }

    // TO DO: Return a - b
    public int subtract(int a, int b) {
        return 0; // placeholder
    }

    // TO DO: Return a * b
    public int multiply(int a, int b) {
        return 0; // placeholder
    }

    // TO DO: Return a / b as a double.
    //        Throw ArithmeticException("Cannot divide by zero") if b == 0.
    public double divide(int a, int b) {
        return 0; // placeholder
    }

    // TO DO: Return true if n is even (n % 2 == 0).
    public boolean isEven(int n) {
        return false; // placeholder
    }

    // TO DO: Return n! (factorial of n).
    //        Throw IllegalArgumentException("Factorial of negative number") if n < 0.
    public long factorial(int n) {
        return 0; // placeholder
    }
}

// ============================================================================
// PART 2 — StringUtils
// ============================================================================

/**
 * A string utility class.
 * TO DO: Implement all methods.
 */
class StringUtils {

    // TO DO: Return true if s is a palindrome (case-insensitive).
    public boolean isPalindrome(String s) {
        return false; // placeholder
    }

    // TO DO: Count and return the number of vowels (a,e,i,o,u) in s.
    public int countVowels(String s) {
        return 0; // placeholder
    }

    // TO DO: Reverse the words in a sentence.
    public String reverseWords(String sentence) {
        return ""; // placeholder
    }

    // TO DO: Return true if s contains only digit characters.
    public boolean isNumeric(String s) {
        return false; // placeholder
    }
}

// ============================================================================
// PART 3 — BankAccount
// ============================================================================

/**
 * A bank account class.
 * TO DO: Implement all methods.
 */
class BankAccount {
    private String owner;
    private double balance;

    // TO DO: Store owner and initialBalance.
    //        Throw IllegalArgumentException("Initial balance cannot be negative") if initialBalance < 0.
    public BankAccount(String owner, double initialBalance) {
        // TO DO: validate initialBalance, then store fields
        this.owner = owner;
        this.balance = initialBalance;
    }

    // TO DO: Add amount to balance.
    //        Throw IllegalArgumentException("Deposit amount must be positive") if amount <= 0.
    public void deposit(double amount) {
        // TO DO: validate, then balance += amount
    }

    // TO DO: Subtract amount from balance.
    //        Throw IllegalArgumentException("Withdrawal amount must be positive") if amount <= 0.
    //        Throw IllegalStateException("Insufficient funds") if amount > balance.
    public void withdraw(double amount) {
        // TO DO: validate, check balance, then balance -= amount
    }

    public double getBalance() { return balance; }
    public String getOwner()   { return owner; }
}

// ============================================================================
// MAIN (leave as-is, students use Test.java)
// ============================================================================
public class Exercise {
    public static void main(String[] args) {
        System.out.println("Run: javac *.java && java Test");
    }
}
