package module05;

import java.util.*;
import java.util.function.*;

/**
 * MODULE 05 — Lambdas & Functional Interfaces
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each method stub — replace the placeholders with real lambdas
 *   3. Run: javac *.java && java Test
 */

// TO DO: Define a @FunctionalInterface called StringTransformer
//        with one method: String transform(String input)
@FunctionalInterface
interface StringTransformer {
    String transform(String input);
}

public class Exercise {

    // TO DO 1a: Return a Runnable lambda that prints "Hello from lambda!"
    static Runnable makeHelloLambda() {
        return () -> {}; // placeholder
    }

    // TO DO 1b: Return a BiFunction<Integer,Integer,Integer> that multiplies two ints
    static BiFunction<Integer,Integer,Integer> makeMultiplier() {
        return (a, b) -> 0; // placeholder
    }

    // TO DO 1c: Return a Function<String,String> that converts a string to uppercase
    static Function<String,String> makeUppercaser() {
        return s -> s; // placeholder
    }

    // TO DO 2a: Return a Consumer<String> that prints the string with ">> " prefix
    static Consumer<String> makePrefixPrinter() {
        return s -> {}; // placeholder
    }

    // TO DO 2c: Return a Supplier<Double> that returns Math.random()
    static Supplier<Double> makeRandomSupplier() {
        return () -> 0.0; // placeholder
    }

    // TO DO 3a: Return a Function<String,Integer> that returns the word count
    static Function<String,Integer> makeWordCounter() {
        return s -> 0; // placeholder
    }

    // TO DO 3c: Return a Predicate<String> that returns true if the string starts with "J"
    static Predicate<String> makeStartsWithJ() {
        return s -> false; // placeholder
    }

    // TO DO 3d: Return a Predicate<Integer> that is true when number is EVEN AND > 10
    static Predicate<Integer> makeEvenAndGtTen() {
        return n -> false; // placeholder
    }

    // TO DO 4a: Sort the list alphabetically (A-Z) IN PLACE and return it.
    static List<String> sortAlpha(List<String> names) {
        return names; // placeholder
    }

    // TO DO 4b: Sort the list reverse alphabetically (Z-A) IN PLACE and return it.
    static List<String> sortReverseAlpha(List<String> names) {
        return names; // placeholder
    }

    // TO DO 4c: Sort by string length (shortest first) IN PLACE and return it.
    static List<String> sortByLength(List<String> names) {
        return names; // placeholder
    }

    // TO DO 4e: Remove names with length > 4 from the list using removeIf(), return it.
    static List<String> removeNamesLongerThan4(List<String> names) {
        return names; // placeholder
    }

    // TO DO 4f: Sort integers in descending order IN PLACE and return the list.
    static List<Integer> sortDescending(List<Integer> numbers) {
        return numbers; // placeholder
    }

    // TO DO 5b: Sort the list using String::compareToIgnoreCase (method reference) and return it.
    static List<String> sortCaseInsensitive(List<String> words) {
        return words; // placeholder
    }

    // TO DO 5c: Return a Function<String,String> using the method reference String::toUpperCase
    static Function<String,String> makeUpperCaseMethodRef() {
        return s -> s; // placeholder
    }

    // TO DO 5d: Return a Function<String,Integer> using Integer::parseInt
    static Function<String,Integer> makeParserMethodRef() {
        return s -> 0; // placeholder
    }

    // TO DO 6b-reverse: Return a StringTransformer that reverses the input string.
    static StringTransformer makeReverser() {
        return s -> s; // placeholder
    }

    // TO DO 6b-bracket: Return a StringTransformer that wraps the string in brackets.
    static StringTransformer makeBracketer() {
        return s -> s; // placeholder
    }

    // CHALLENGE: Process names through a pipeline
    static List<String> processNames(List<String> rawNames) {
        return new ArrayList<>(); // placeholder
    }
}
