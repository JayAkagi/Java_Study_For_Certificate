package module01;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ExerciseTest {

    @Test
    void testEx1_ArrayList() {
        List<String> cities = Exercise.buildCities();
        assertTrue(cities != null && cities.size() == 5, "1a: size is 5");
        assertTrue(cities != null && cities.size() > 0 && "Tokyo".equals(cities.get(0)), "1a: first element is Tokyo");
        assertTrue(cities != null && cities.contains("London"), "1a: contains London");
        assertTrue(cities != null && cities.size() > 2 && "Paris".equals(Exercise.getCityAt(new ArrayList<>(cities), 2)), "1b: getCityAt(2) = Paris");

        List<String> sorted = cities != null && !cities.isEmpty()
                ? Exercise.removeThenSort(new ArrayList<>(cities), "London")
                : new ArrayList<>();
        assertFalse(sorted.contains("London"), "1c: London removed");
        assertEquals(Arrays.asList("New York", "Paris", "Sydney", "Tokyo"), sorted, "1c: sorted correctly");

        assertTrue(Exercise.listContains(new ArrayList<>(cities), "Paris"), "1d: contains Paris → true");
        assertFalse(Exercise.listContains(new ArrayList<>(cities), "Berlin"), "1d: contains Berlin → false");
    }

    @Test
    void testEx2_LinkedList() {
        LinkedList<Integer> q = Exercise.buildQueue();
        assertEquals(new LinkedList<>(Arrays.asList(50, 100, 200, 300, 400)), q, "2a: queue = [50,100,200,300,400]");

        LinkedList<Integer> q2 = new LinkedList<>(Arrays.asList(50, 100, 200, 300, 400));
        assertEquals(50, Exercise.pollFirst(q2), "2b: pollFirst returns 50");
        assertEquals(new LinkedList<>(Arrays.asList(100, 200, 300, 400)), q2, "2b: queue after poll = [100,200,300,400]");

        LinkedList<Integer> q3 = new LinkedList<>(Arrays.asList(100, 200, 300, 400));
        assertEquals(100, Exercise.peekFirst(q3), "2c: peekFirst returns 100");
        assertEquals(new LinkedList<>(Arrays.asList(100, 200, 300, 400)), q3, "2c: queue unchanged after peek");
    }

    @Test
    void testEx3_HashSet() {
        List<String> withDupes = Arrays.asList("cat", "dog", "cat", "bird", "dog", "fish", "bird");
        Set<String> unique = Exercise.deduplicateAnimals(withDupes);
        assertEquals(4, unique != null ? unique.size() : -1, "3a: unique count = 4");
        assertTrue(unique != null && unique.contains("cat"), "3a: contains cat");

        List<String> sorted = Exercise.sortedUniqueAnimals(new ArrayList<>(withDupes));
        assertEquals(Arrays.asList("bird", "cat", "dog", "fish"), sorted, "3b: sorted unique");
    }

    @Test
    void testEx4_TreeSet() {
        TreeSet<Integer> scores = Exercise.buildScores();
        assertEquals(new TreeSet<>(Arrays.asList(42, 55, 67, 85, 91)), scores, "4a: scores (sorted, no dupes)");
        assertEquals(42, scores != null && !scores.isEmpty() ? scores.first() : -1, "4a: first = 42");
        assertEquals(91, scores != null && !scores.isEmpty() ? scores.last() : -1, "4a: last = 91");

        SortedSet<Integer> below = Exercise.scoresBelowSeventy(Exercise.buildScores());
        assertEquals(new TreeSet<>(Arrays.asList(42, 55, 67)), below, "4b: below 70 = [42,55,67]");

        SortedSet<Integer> above = Exercise.scoresSeventyAndAbove(Exercise.buildScores());
        assertEquals(new TreeSet<>(Arrays.asList(85, 91)), above, "4c: 70 and above = [85,91]");
    }

    @Test
    void testEx5_HashMap() {
        Map<String, Integer> inv = Exercise.buildInventory();
        assertEquals(75, inv != null ? inv.getOrDefault("apples", -1) : -1, "5a: apples = 75");
        assertEquals(20, inv != null ? inv.getOrDefault("bananas", -1) : -1, "5a: bananas = 20");
        assertEquals(20, inv != null ? inv.getOrDefault("oranges", -1) : -1, "5a: oranges = 20");

        Map<String, Integer> m = new HashMap<>();
        m.put("apples", 10);
        assertEquals(10, Exercise.getOrDefaultZero(m, "apples"), "5b: getOrDefault existing = 10");
        assertEquals(0, Exercise.getOrDefaultZero(m, "grapes"), "5b: getOrDefault missing = 0");
    }

    @Test
    void testEx6_TreeMap() {
        TreeMap<String, String> pb = Exercise.buildPhonebook();
        assertTrue(pb != null && pb.size() == 3, "6a: size = 3");
        assertEquals("Alice", pb != null && !pb.isEmpty() ? pb.firstKey() : "", "6a: firstKey = Alice");
        assertEquals("Charlie", pb != null && !pb.isEmpty() ? pb.lastKey() : "", "6a: lastKey = Charlie");
        assertEquals("555-0002", pb != null ? pb.get("Alice") : "", "6a: Alice's number");
    }

    @Test
    void testEx7_Iterator() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> odds = Exercise.removeEvens(nums);
        assertEquals(Arrays.asList(1, 3, 5, 7, 9), odds, "7a: evens removed = [1,3,5,7,9]");

        List<String> words = new ArrayList<>(Arrays.asList("hello", "world", "java", "programming", "fun"));
        List<String> short_ = Exercise.removeByLength(words, 4);
        assertEquals(Arrays.asList("java", "fun"), short_, "7b: length>4 removed = [java,fun]");
    }

    @Test
    void testEx8_Collections() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6));
        List<Integer> asc = Exercise.sortAscending(new ArrayList<>(nums));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), asc, "8a: sorted ascending");

        List<Integer> rev = Exercise.reverseList(new ArrayList<>(asc));
        assertEquals(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1), rev, "8b: reversed = [9..1]");

        assertEquals(1, Exercise.minOf(new ArrayList<>(nums)), "8c: min = 1");
        assertEquals(9, Exercise.maxOf(new ArrayList<>(nums)), "8d: max = 9");

        List<Integer> withRep = Arrays.asList(5, 3, 5, 7, 5, 2, 5);
        assertEquals(4, Exercise.frequencyOf(withRep, 5), "8e: frequency of 5 = 4");
    }

    @Test
    void testChallenge() {
        Map<String, Integer> freq = Exercise.wordFrequency("the cat sat on the mat the cat sat");
        assertEquals(3, freq != null ? freq.getOrDefault("the", -1) : -1, "challenge: 'the' appears 3");
        assertEquals(2, freq != null ? freq.getOrDefault("cat", -1) : -1, "challenge: 'cat' appears 2");
        assertEquals(2, freq != null ? freq.getOrDefault("sat", -1) : -1, "challenge: 'sat' appears 2");
        assertEquals(1, freq != null ? freq.getOrDefault("mat", -1) : -1, "challenge: 'mat' appears 1");
        assertEquals(1, freq != null ? freq.getOrDefault("on", -1) : -1, "challenge: 'on' appears 1");
        assertTrue(freq instanceof TreeMap, "challenge: sorted (TreeMap)");
    }
}
