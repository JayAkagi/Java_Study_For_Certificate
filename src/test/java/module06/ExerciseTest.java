package module06;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.*;

public class ExerciseTest {

    @Test
    void testEx1_Filter() {
        List<Integer> evens = Exercise.evensTo20();
        assertEquals(10, evens.size(), "1a: evens count = 10");
        assertTrue(evens.stream().allMatch(n -> n % 2 == 0), "1a: all even");
        assertEquals(2, evens.size() > 0 ? evens.get(0) : -1, "1a: first even = 2");
        assertEquals(20, evens.size() > 9 ? evens.get(9) : -1, "1a: last even = 20");

        List<Integer> div3gt10 = Exercise.divisibleBy3AndGt10();
        assertEquals(Arrays.asList(12, 15, 18), div3gt10, "1b: [12,15,18]");

        List<String> words = Arrays.asList("stream", "lambda", "java", "api", "functional", "map");
        List<String> long4 = Exercise.wordsLongerThan4(words);
        assertTrue(long4.contains("stream"), "1c: stream in result");
        assertTrue(long4.contains("lambda"), "1c: lambda in result");
        assertTrue(long4.contains("functional"), "1c: functional in result");
        assertFalse(long4.contains("java"), "1c: java NOT in result");
        assertFalse(long4.contains("api"), "1c: api NOT in result");

        List<String> withA = Exercise.wordsContainingA(words);
        assertTrue(withA.contains("lambda"), "1d: lambda has a");
        assertTrue(withA.contains("java"), "1d: java has a");
        assertTrue(withA.contains("api"), "1d: api has a");
        assertTrue(withA.contains("map"), "1d: map has a");
        assertFalse(withA.contains("stream"), "1d: stream no a");
    }

    @Test
    void testEx2_MapCollect() {
        List<String> names = Arrays.asList("alice", "bob", "charlie", "dave");

        List<String> upper = Exercise.toUpperCaseList(names);
        assertEquals(Arrays.asList("ALICE", "BOB", "CHARLIE", "DAVE"), upper, "2a: upper list");

        List<Integer> lengths = Exercise.nameLengths(names);
        assertEquals(Arrays.asList(5, 3, 7, 4), lengths, "2b: name lengths");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> sq = Exercise.squares(numbers);
        assertEquals(Arrays.asList(1, 4, 9, 16, 25), sq, "2d: squares");

        String joined = Exercise.joinWithPipe(names);
        assertEquals("alice | bob | charlie | dave", joined, "2e: joined with pipe");
    }

    @Test
    void testEx3_SortedLimitSkip() {
        List<Integer> nums = Arrays.asList(5, 3, 8, 1, 9, 2, 7, 4, 6, 3, 1, 8);

        List<Integer> distSorted = Exercise.distinctSorted(nums);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), distSorted, "3a: distinct sorted");

        List<Integer> top3 = Exercise.topThree(nums);
        assertEquals(Arrays.asList(9, 8, 7), top3, "3b: top 3");

        List<Integer> skip3take4 = Exercise.skipThreeTakeNext4(nums);
        assertEquals(Arrays.asList(4, 5, 6, 7), skip3take4, "3c: skip 3 take 4");

        List<String> words = Arrays.asList("banana", "apple", "cherry", "apricot", "blueberry", "avocado");
        List<String> startA = Exercise.wordsStartingWithA(words);
        assertEquals(Arrays.asList("apple", "apricot", "avocado"), startA, "3d: words starting with a");
    }

    @Test
    void testEx4_Aggregation() {
        List<Integer> numbers = Arrays.asList(3, 7, 2, 9, 4, 8, 1, 6, 5);

        assertEquals(4L, Exercise.countGreaterThan5(numbers), "4a: count > 5 = 4");
        assertEquals(45, Exercise.sumAll(numbers), "4b: sum = 45");
        assertEquals(1, Exercise.minValue(numbers), "4c: min = 1");
        assertEquals(5.0, Exercise.averageOf(numbers), 0.001, "4d: average = 5.0");
        assertEquals(362880L, Exercise.productOf(numbers), "4e: product = 362880");
    }

    @Test
    void testEx5_Collectors() {
        List<String> words = Arrays.asList("java", "stream", "api", "lambda", "java", "stream", "filter");

        List<String> setSort = Exercise.collectToSetSorted(words);
        assertEquals(Arrays.asList("api", "filter", "java", "lambda", "stream"), setSort, "5a: deduped sorted");

        Map<Character, List<String>> grouped = Exercise.groupByFirstChar(words);
        assertTrue(grouped.containsKey('a') && grouped.get('a').contains("api"), "5c: 'a' group has api");
        assertTrue(grouped.containsKey('j') && grouped.get('j').contains("java"), "5c: 'j' group has java");
        assertTrue(grouped.containsKey('s') && grouped.get('s').contains("stream"), "5c: 's' group has stream");
        assertTrue(grouped.containsKey('l') && grouped.get('l').contains("lambda"), "5c: 'l' group has lambda");
        assertTrue(grouped.containsKey('f') && grouped.get('f').contains("filter"), "5c: 'f' group has filter");
        assertTrue(grouped.get('j') == null || grouped.get('j').size() == 1, "5c: java not duplicated");

        Map<String, Long> cats = Exercise.countByCategory();
        assertEquals(3L, cats.get("Electronics"), "5e: Electronics = 3");
        assertEquals(3L, cats.get("Furniture"), "5e: Furniture = 3");
        assertEquals(3L, cats.get("Clothing"), "5e: Clothing = 3");
        assertEquals(2L, cats.get("Food"), "5e: Food = 2");
    }

    @Test
    void testEx6_Matching() {
        List<Integer> all6 = Arrays.asList(2, 4, 6, 8, 10, 12);

        assertTrue(Exercise.anyGreaterThan10(all6), "6a: any > 10 = true");
        assertFalse(Exercise.anyGreaterThan10(Arrays.asList(1, 2, 3)), "6a: any > 10 false case");
        assertTrue(Exercise.allEven(all6), "6b: all even = true");
        assertFalse(Exercise.allEven(Arrays.asList(2, 4, 5)), "6b: all even false");
        assertTrue(Exercise.noneNegative(all6), "6c: none negative = true");
        assertFalse(Exercise.noneNegative(Arrays.asList(1, -1, 2)), "6c: none negative false");

        List<String> emails = Arrays.asList("alice@gmail.com", "bob@yahoo.com", "charlie@gmail.com");
        Optional<String> first = Exercise.firstGmail(emails);
        assertTrue(first.isPresent(), "6d: firstGmail present");
        assertEquals("alice@gmail.com", first.orElse(""), "6d: firstGmail = alice@gmail.com");
        assertFalse(Exercise.firstGmail(Arrays.asList("a@yahoo.com", "b@hotmail.com")).isPresent(), "6d: no gmail returns empty");
    }

    @Test
    void testChallenge() {
        double totalValue = Exercise.totalInventoryValue();
        assertTrue(totalValue > 60000, "challenge: total inventory value > 60000");
        assertTrue(totalValue < 70000, "challenge: total inventory value < 70000");

        String mostExpensive = Exercise.mostExpensiveProduct();
        assertEquals("Laptop", mostExpensive, "challenge: most expensive = Laptop");
    }
}
