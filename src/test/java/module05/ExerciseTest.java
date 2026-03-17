package module05;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.function.*;

public class ExerciseTest {

    @Test
    void testEx1_BasicLambdas() {
        Runnable r = Exercise.makeHelloLambda();
        assertNotNull(r, "1a: makeHelloLambda returns non-null Runnable");
        assertDoesNotThrow(r::run, "1a: Runnable runs without exception");

        BiFunction<Integer, Integer, Integer> mult = Exercise.makeMultiplier();
        assertEquals(42, mult.apply(6, 7), "1b: multiply 6*7 = 42");
        assertEquals(0, mult.apply(0, 5), "1b: multiply 0*5 = 0");
        assertEquals(-12, mult.apply(-3, 4), "1b: multiply -3*4 = -12");

        Function<String, String> upper = Exercise.makeUppercaser();
        assertEquals("HELLO WORLD", upper.apply("hello world"), "1c: uppercase 'hello world'");
        assertEquals("JAVA", upper.apply("JAVA"), "1c: uppercase already upper");
    }

    @Test
    void testEx2_ConsumerSupplier() {
        Consumer<String> printer = Exercise.makePrefixPrinter();
        assertNotNull(printer, "2a: makePrefixPrinter non-null");
        assertDoesNotThrow(() -> printer.accept("Java is awesome"), "2a: Consumer runs without exception");

        Supplier<Double> rnd = Exercise.makeRandomSupplier();
        double v1 = rnd.get();
        assertTrue(v1 >= 0.0, "2c: supplier returns value >= 0.0");
        assertTrue(v1 < 1.0, "2c: supplier returns value < 1.0");
    }

    @Test
    void testEx3_FunctionPredicate() {
        Function<String, Integer> wc = Exercise.makeWordCounter();
        assertEquals(4, wc.apply("the quick brown fox"), "3a: 'the quick brown fox' = 4");
        assertEquals(1, wc.apply("hello"), "3a: 'hello' = 1");
        assertEquals(3, wc.apply("a b c"), "3a: 'a b c' = 3");

        Predicate<String> startJ = Exercise.makeStartsWithJ();
        assertTrue(startJ.test("Java"), "3c: 'Java' starts with J → true");
        assertFalse(startJ.test("Python"), "3c: 'Python' starts with J → false");
        assertTrue(startJ.test("JavaScript"), "3c: 'JavaScript' → true");

        Predicate<Integer> evenGt10 = Exercise.makeEvenAndGtTen();
        assertTrue(evenGt10.test(12), "3d: 12 → true (even, >10)");
        assertFalse(evenGt10.test(8), "3d: 8 → false (even, <=10)");
        assertFalse(evenGt10.test(15), "3d: 15 → false (odd, >10)");
        assertTrue(evenGt10.test(20), "3d: 20 → true");
    }

    @Test
    void testEx4_Sorting() {
        List<String> names = new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob", "Eve", "Dave"));

        List<String> alpha = Exercise.sortAlpha(new ArrayList<>(names));
        assertEquals(Arrays.asList("Alice", "Bob", "Charlie", "Dave", "Eve"), alpha, "4a: sort alpha");

        List<String> revAlpha = Exercise.sortReverseAlpha(new ArrayList<>(names));
        assertEquals(Arrays.asList("Eve", "Dave", "Charlie", "Bob", "Alice"), revAlpha, "4b: sort reverse alpha");

        List<String> byLen = Exercise.sortByLength(new ArrayList<>(names));
        assertTrue(byLen.get(0).length() <= byLen.get(2).length(), "4c: shortest first");

        List<String> removed = Exercise.removeNamesLongerThan4(new ArrayList<>(names));
        assertFalse(removed.contains("Alice"), "4e: Alice removed");
        assertFalse(removed.contains("Charlie"), "4e: Charlie removed");
        assertTrue(removed.contains("Bob"), "4e: Bob remains");
        assertTrue(removed.contains("Eve"), "4e: Eve remains");

        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));
        List<Integer> desc = Exercise.sortDescending(nums);
        assertEquals(9, desc.get(0), "4f: sort descending first = 9");
        assertEquals(1, desc.get(desc.size() - 1), "4f: sort descending last = 1");
    }

    @Test
    void testEx5_MethodReferences() {
        List<String> words = new ArrayList<>(Arrays.asList("banana", "Apple", "cherry", "date"));

        List<String> sorted = Exercise.sortCaseInsensitive(new ArrayList<>(words));
        assertEquals("apple", sorted.get(0).toLowerCase(), "5b: case-insensitive sort first");
        assertEquals("banana", sorted.get(1).toLowerCase(), "5b: case-insensitive sort second");

        Function<String, String> upperRef = Exercise.makeUpperCaseMethodRef();
        assertEquals("HELLO", upperRef.apply("hello"), "5c: String::toUpperCase ref");

        Function<String, Integer> parseRef = Exercise.makeParserMethodRef();
        assertEquals(123, parseRef.apply("123"), "5d: Integer::parseInt ref '123'");
        assertEquals(0, parseRef.apply("0"), "5d: Integer::parseInt ref '0'");
    }

    @Test
    void testEx6_CustomInterface() {
        StringTransformer reverser = Exercise.makeReverser();
        assertEquals("olleh", reverser.transform("hello"), "6a: reverse 'hello'");
        assertEquals("cba", reverser.transform("abc"), "6a: reverse 'abc'");
        assertEquals("x", reverser.transform("x"), "6a: reverse single");

        StringTransformer bracketer = Exercise.makeBracketer();
        assertEquals("[hello]", bracketer.transform("hello"), "6b: bracket 'hello'");
        assertEquals("[world]", bracketer.transform("world"), "6b: bracket 'world'");
    }

    @Test
    void testChallenge_Pipeline() {
        List<String> raw = new ArrayList<>(Arrays.asList("  alice  ", "BOB", "charlie", "  DAVE  ", "eve"));
        List<String> processed = Exercise.processNames(raw);
        assertEquals(3, processed != null ? processed.size() : -1, "challenge: size = 3");
        assertTrue(processed != null && processed.contains("Alice"), "challenge: contains Alice");
        assertTrue(processed != null && processed.contains("Charlie"), "challenge: contains Charlie");
        assertTrue(processed != null && processed.contains("Dave"), "challenge: contains Dave");
        assertTrue(processed == null || !processed.contains("Bob"), "challenge: Bob removed");
        assertTrue(processed == null || !processed.contains("Eve"), "challenge: Eve removed");
        if (processed != null && processed.size() == 3) {
            assertEquals("Alice", processed.get(0), "challenge: sorted [0] = Alice");
            assertEquals("Charlie", processed.get(1), "challenge: sorted [1] = Charlie");
            assertEquals("Dave", processed.get(2), "challenge: sorted [2] = Dave");
        }
    }
}
