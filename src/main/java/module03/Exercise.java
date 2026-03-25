package module03;

import java.util.*;

/**
 * MODULE 03 — Exception Handling
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each method and class stub below
 *   3. Run: javac *.java && java Test
 */

// ============================================================================
// TO DO 3a: Custom CHECKED exception InsufficientFundsException
// ============================================================================
class InsufficientFundsException extends Exception {
    private double shortfall;

    InsufficientFundsException(double shortfall) {
        super("Insufficient funds. Short by: $" + shortfall);
        // TO DO: also store the shortfall field
        this.shortfall = shortfall;
    }

    double getShortfall() {
        return shortfall; // TO DO: return shortfall
    }
}

// ============================================================================
// TO DO 3b: BankAccount class
// ============================================================================
class BankAccount {
    String owner;
    double balance;

    BankAccount(String owner, double initialBalance) {
        // TO DO: store owner and initialBalance
        this.owner = owner;
        this.balance = initialBalance;
    }

    void deposit(double amount) {
        // TO DO: validate amount > 0, then add to balance
        if(amount > 0) balance += amount;
    }

    void withdraw(double amount) throws InsufficientFundsException {
        // TO DO: validate amount > 0, check balance, then subtract
        if(amount > balance){
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
    }

    double getBalance() {
        return balance; // TO DO: return balance
    }
}

// ============================================================================
// TO DO 4a: Custom UNCHECKED exception InvalidAgeException
// ============================================================================
class InvalidAgeException extends RuntimeException{
    int age;
    InvalidAgeException(int age){
        super("Invalid age: " + age + ". Age must be 0 and 150");
        this.age = age;
    }
}
// ============================================================================
// TO DO 4b: Person class with constructor validation
// ============================================================================
class Person {
    String name;
    int age;

    Person(String name, int age) {
        // TO DO: validate age, throw InvalidAgeException if out of range
        if(age < 0 || age > 150) throw new InvalidAgeException(age);
        this.name = name;
        this.age = age;
        // TO DO: store name and age
    }

    @Override
    public String toString() {
        return String.format("Person{name=%s, age=%d}",name,age); // TO DO: return "Person{name=[name], age=[age]}"
    }
}

// ============================================================================
// MAIN — static methods used by Test.java
// ============================================================================
public class Exercise {


    // TO DO 1a: Catch ArithmeticException from dividing by zero.
    //           Return "Cannot divide by zero!"
    static String ex1a_divideByZero() {
        int a = 10;
        int b = 0;
        int c =0;
        try {
            c = a / b;
        } catch (ArithmeticException e) {            
            return "Cannot divide by zero!";
        }
        return ""; // placeholder
    }

    // TO DO 1b: Catch NumberFormatException from Integer.parseInt("hello").
    //           Return "Not a valid number: hello"
    static String ex1b_parseInvalid() {
        try {
            int num = Integer.parseInt("hello");
        } catch (NumberFormatException e) {
            return "Not a valid number: hello";
        }
        return ""; // placeholder
    }

    // TO DO 1c: Catch NullPointerException from calling .length() on null.
    //           Return "Null reference caught!"
    static String ex1c_nullLength() {
        try {
            String str = null;
            int num = str.length();
        } catch (NullPointerException e) {
            return "Null reference caught!";
        }
        return ""; // placeholder
    }

    // TO DO 2: Process each element in data[]:
    //          Try Integer.parseInt(elem).
    //          On NullPointerException  → add "Null value encountered" to results.
    //          On NumberFormatException → add "Not a number: [elem]" to results.
    //          On success               → add "Parsed: [number]" to results.
    //          Return the list of result strings.
    static List<String> ex2_parseArray(String[] data) {
        List<String> results = new ArrayList<>();
        for(String s : data){
            try {
                if(s == null){
                    results.add("Null value encountered");
                } else{
                    int num = Integer.parseInt(s);
                results.add(String.format("Parsed: %s", Integer.toString(num)));
                }                
            } catch (NumberFormatException e) {
                    results.add(String.format("Not a number: %s", s));
                }
        }
        return results; // placeholder
    }

    // TO DO 5: Declare throws NumberFormatException.
    //          Parse s as an int and return it.
    //          Let the exception propagate to the caller (no try/catch here).
    static int ex5_parseNumber(String s) throws NumberFormatException {
        return Integer.parseInt(s); // placeholder
    }

    // TO DO challenge: Implement calculate(String a, String b, String op):
    //   1. Parse both strings as doubles — if either fails, throw
    //      IllegalArgumentException("Invalid number input: " + value)
    //   2. If op is "/" and b == 0.0 → throw ArithmeticException("Division by zero")
    //   3. If op is not +,-,*,/ → throw IllegalArgumentException("Unknown operator: " + op)
    //   4. Perform the operation and return the result.
    static double calculate(String a, String b, String op) {
        return 0.0; // placeholder
    }

    public static void main(String[] args) {
        System.out.println("Run: javac *.java && java Test");
    }
}
