package module06;

import java.util.*;
import java.util.stream.*;

/**
 * MODULE 06 — Stream API
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each method stub — each returns a value so it can be tested
 *   3. Run: javac *.java && java Test
 */
public class Exercise {

    // Product helper class (keep as-is)
    static class Product {
        String name;
        String category;
        double price;
        int stock;

        Product(String name, String category, double price, int stock) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ")";
        }
    }

    static List<Product> getProducts() {
        return Arrays.asList(
            new Product("Laptop",      "Electronics", 999.99, 15),
            new Product("Phone",       "Electronics", 699.99, 30),
            new Product("Headphones",  "Electronics", 149.99, 50),
            new Product("Desk",        "Furniture",   299.99,  8),
            new Product("Chair",       "Furniture",   199.99, 12),
            new Product("Bookshelf",   "Furniture",    89.99, 20),
            new Product("T-Shirt",     "Clothing",     24.99, 100),
            new Product("Jeans",       "Clothing",     59.99,  75),
            new Product("Jacket",      "Clothing",    129.99,  40),
            new Product("Coffee",      "Food",          9.99, 200),
            new Product("Tea",         "Food",          7.99, 150)
        );
    }

    // -------------------------------------------------------------------------
    // EXERCISE 1 — filter
    // -------------------------------------------------------------------------

    static List<Integer> evensTo20() {
        return new ArrayList<>(); // placeholder
    }

    static List<Integer> divisibleBy3AndGt10() {
        return new ArrayList<>(); // placeholder
    }

    static List<String> wordsLongerThan4(List<String> words) {
        return new ArrayList<>(); // placeholder
    }

    static List<String> wordsContainingA(List<String> words) {
        return new ArrayList<>(); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 2 — map + collect
    // -------------------------------------------------------------------------

    static List<String> toUpperCaseList(List<String> names) {
        return new ArrayList<>(); // placeholder
    }

    static List<Integer> nameLengths(List<String> names) {
        return new ArrayList<>(); // placeholder
    }

    static List<Integer> squares(List<Integer> numbers) {
        return new ArrayList<>(); // placeholder
    }

    static String joinWithPipe(List<String> names) {
        return ""; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 3 — sorted, limit, skip, distinct
    // -------------------------------------------------------------------------

    static List<Integer> distinctSorted(List<Integer> nums) {
        return new ArrayList<>(); // placeholder
    }

    static List<Integer> topThree(List<Integer> nums) {
        return new ArrayList<>(); // placeholder
    }

    static List<Integer> skipThreeTakeNext4(List<Integer> nums) {
        return new ArrayList<>(); // placeholder
    }

    static List<String> wordsStartingWithA(List<String> words) {
        return new ArrayList<>(); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 4 — Aggregation
    // -------------------------------------------------------------------------

    static long countGreaterThan5(List<Integer> numbers) {
        return 0; // placeholder
    }

    static int sumAll(List<Integer> numbers) {
        return 0; // placeholder
    }

    static int minValue(List<Integer> numbers) {
        return 0; // placeholder
    }

    static double averageOf(List<Integer> numbers) {
        return 0.0; // placeholder
    }

    static long productOf(List<Integer> numbers) {
        return 0; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 5 — Collectors
    // -------------------------------------------------------------------------

    static List<String> collectToSetSorted(List<String> words) {
        return new ArrayList<>(); // placeholder
    }

    static Map<Character, List<String>> groupByFirstChar(List<String> words) {
        return new HashMap<>(); // placeholder
    }

    static Map<String, Long> countByCategory() {
        return new HashMap<>(); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 6 — Matching
    // -------------------------------------------------------------------------

    static boolean anyGreaterThan10(List<Integer> numbers) {
        return false; // placeholder
    }

    static boolean allEven(List<Integer> numbers) {
        return false; // placeholder
    }

    static boolean noneNegative(List<Integer> numbers) {
        return false; // placeholder
    }

    static Optional<String> firstGmail(List<String> emails) {
        return Optional.empty(); // placeholder
    }

    // -------------------------------------------------------------------------
    // CHALLENGE
    // -------------------------------------------------------------------------

    static double totalInventoryValue() {
        return 0.0; // placeholder
    }

    static String mostExpensiveProduct() {
        return ""; // placeholder
    }
}
