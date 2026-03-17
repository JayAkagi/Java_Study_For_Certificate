package module01;

import java.util.*;

/**
 * MODULE 01 — Advanced Collections
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each method — each returns a value so it can be tested
 *   3. Run: javac *.java && java Test
 */
public class Exercise {

    // -------------------------------------------------------------------------
    // EXERCISE 1 — ArrayList
    // -------------------------------------------------------------------------

    // TO DO 1a: Create and return an ArrayList<String> containing:
    //           "Tokyo", "London", "Paris", "New York", "Sydney"  (in that order)
    static List<String> buildCities() {
        List<String> cities = new ArrayList<>(
            Arrays.asList(
                "Tokyo",
                "London",
                "Paris",
                "New York",
                "Sydney"
            )
        );
        return cities; // placeholder
    }

    // TO DO 1b: Return the element at the given index from the list
    static String getCityAt(List<String> list, int index) {
        return list.get(index); // placeholder
    }

    // TO DO 1c: Remove 'toRemove' from the list, then sort it alphabetically.
    //           Return the modified list.
    static List<String> removeThenSort(List<String> list, String toRemove) {
        list.remove(toRemove);

        String temp = "";
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if(list.get(j).compareTo(list.get(j + 1)) > 0){
                    temp=list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list; // placeholder
    }

    // TO DO 1d: Return true if the list contains 'item', false otherwise
    static boolean listContains(List<String> list, String item) {
        for(String cities : list){
            if(cities.equals(item)) return true;
        }
        return false; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 2 — LinkedList
    // -------------------------------------------------------------------------

    // TO DO 2a: Create a LinkedList<Integer>.
    //           Add 100, 200, 300.
    //           Then addFirst(50) and addLast(400).
    //           Return the LinkedList. Expected: [50, 100, 200, 300, 400]
    static LinkedList<Integer> buildQueue() {
        LinkedList<Integer> linkedList = new LinkedList<>(
            Arrays.asList(
                100,
                200,
                300
            )
        );
        linkedList.addFirst(50);
        linkedList.addLast(400);
        return linkedList; // placeholder
    }

    // TO DO 2b: Use poll() to remove and return the first element of the queue.
    //           (This modifies the queue as a side effect.)
    static int pollFirst(LinkedList<Integer> queue) {
        return queue.poll(); // placeholder
    }

    // TO DO 2c: Use peek() to look at the first element WITHOUT removing it.
    //           Return it.
    static int peekFirst(LinkedList<Integer> queue) {
        return queue.peek(); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 3 — HashSet
    // -------------------------------------------------------------------------

    // TO DO 3a: Given a list that may have duplicates, create and return a
    //           HashSet<String> containing only unique values.
    static Set<String> deduplicateAnimals(List<String> withDupes) {
        Set<String> uniques = new HashSet<>();
        for(String item : withDupes){
            uniques.add(item);
        }
        return uniques; // placeholder
    }

    // TO DO 3b: Given a list with duplicates, return a sorted ArrayList<String>
    //           with all duplicates removed.
    //           For ["cat","dog","cat","bird","dog","fish","bird"]: [bird, cat, dog, fish]
    static List<String> sortedUniqueAnimals(List<String> withDupes) {
        List<String> noDupes = new ArrayList<>();
        for(String i : withDupes){
            if(noDupes.contains(i)) continue;
            noDupes.add(i);
        }

        Collections.sort(noDupes);
        return noDupes; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 4 — TreeSet
    // -------------------------------------------------------------------------

    // TO DO 4a: Create a TreeSet<Integer> and add: 85, 42, 91, 67, 55, 91, 42
    //           Return it. Expected contents (auto-sorted, deduped): [42, 55, 67, 85, 91]
    static TreeSet<Integer> buildScores() {
        TreeSet<Integer> treeSet = new TreeSet<>(
            Arrays.asList(85,42,91,67,55,91,42)
        );
        return treeSet; // placeholder
    }

    // TO DO 4b: Return all scores strictly less than 70 using headSet(70).
    //           For buildScores(): [42, 55, 67]
    static SortedSet<Integer> scoresBelowSeventy(TreeSet<Integer> scores) {
        SortedSet<Integer> scoresLessThanSeventy = scores.headSet(70);
        return scoresLessThanSeventy; // placeholder
    }

    // TO DO 4c: Return all scores 70 and above using tailSet(70).
    //           For buildScores(): [85, 91]
    static SortedSet<Integer> scoresSeventyAndAbove(TreeSet<Integer> scores) {
        SortedSet<Integer> scoresAboveSeventy = scores.tailSet(70);
        return scoresAboveSeventy; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 5 — HashMap
    // -------------------------------------------------------------------------

    // TO DO 5a: Create a HashMap<String, Integer>.
    //           Add: "apples"->50, "bananas"->30, "oranges"->20
    //           Update "apples" to 75.
    //           Use merge() to subtract 10 from "bananas":
    //             map.merge("bananas", -10, Integer::sum)
    //           Return the final map. Expected: {apples=75, bananas=20, oranges=20}
    static Map<String, Integer> buildInventory() {
        return new HashMap<>(); // placeholder
    }

    // TO DO 5b: Return map.getOrDefault(key, 0).
    //           If key doesn't exist, return 0.
    static int getOrDefaultZero(Map<String, Integer> map, String key) {
        return -1; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 6 — TreeMap
    // -------------------------------------------------------------------------

    // TO DO 6a: Create a TreeMap<String, String> phonebook.
    //           Add: "Charlie"->"555-0001", "Alice"->"555-0002", "Bob"->"555-0003"
    //           Return it. (TreeMap sorts alphabetically by key.)
    //           Expected first key: "Alice", last key: "Charlie"
    static TreeMap<String, String> buildPhonebook() {
        return new TreeMap<>(); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 7 — Iterator
    // -------------------------------------------------------------------------

    // TO DO 7a: Use an Iterator to remove all EVEN numbers from the given list.
    //           Hint: use it.hasNext(), it.next(), it.remove()
    //           Return the modified list.
    //           For [1,2,3,4,5,6,7,8,9,10]: [1, 3, 5, 7, 9]
    static List<Integer> removeEvens(List<Integer> numbers) {
        return numbers; // placeholder
    }

    // TO DO 7b: Use an Iterator to remove all words with length > maxLength.
    //           Return the modified list.
    //           For ["hello","world","java","programming","fun"], maxLength=4: [java, fun]
    static List<String> removeByLength(List<String> words, int maxLength) {
        return words; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 8 — Collections Utility
    // -------------------------------------------------------------------------

    // TO DO 8a: Sort the list ascending using Collections.sort() and return it.
    static List<Integer> sortAscending(List<Integer> nums) {
        return nums; // placeholder
    }

    // TO DO 8b: Reverse the list using Collections.reverse() and return it.
    static List<Integer> reverseList(List<Integer> nums) {
        return nums; // placeholder
    }

    // TO DO 8c: Return the minimum value using Collections.min()
    static int minOf(List<Integer> nums) {
        return 0; // placeholder
    }

    // TO DO 8d: Return the maximum value using Collections.max()
    static int maxOf(List<Integer> nums) {
        return 0; // placeholder
    }

    // TO DO 8e: Return how many times 'val' appears in 'list' using Collections.frequency()
    static int frequencyOf(List<Integer> list, int val) {
        return 0; // placeholder
    }

    // -------------------------------------------------------------------------
    // CHALLENGE — Word Frequency Counter
    // -------------------------------------------------------------------------

    // TO DO: Given a sentence, return a TreeMap<String, Integer> where:
    //         - key = word, value = how many times it appears
    //         - TreeMap ensures alphabetical order
    //        Hint: String.split(" ") splits by space.
    //              Use merge() or getOrDefault() to count.
    //        For "the cat sat on the mat the cat sat":
    //              {cat=2, mat=1, on=1, sat=2, the=3}
    static Map<String, Integer> wordFrequency(String sentence) {
        return new TreeMap<>(); // placeholder
    }
}
