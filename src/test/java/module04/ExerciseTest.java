package module04;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ExerciseTest {

    @Test
    void testEx1_Container() {
        Container<String> cs = new Container<>("Hello, Generics!");
        assertEquals("Hello, Generics!", cs.getContent(), "1a: getContent()");
        assertFalse(cs.isEmpty(), "1a: not empty");
        assertEquals("Container[Hello, Generics!]", cs.toString(), "1a: toString");

        Container<Integer> ci = new Container<>(42);
        assertEquals(42, ci.getContent(), "1b: Integer content");
        assertEquals("Container[42]", ci.toString(), "1b: toString");

        Container<Double> empty = new Container<>(null);
        assertTrue(empty.isEmpty(), "1c: isEmpty() = true for null");
        assertEquals("Container[empty]", empty.toString(), "1c: toString for empty");

        empty.setContent(3.14);
        assertFalse(empty.isEmpty(), "1d: not empty after setContent");
        assertEquals(3.14, empty.getContent(), "1d: content after set");
    }

    @Test
    void testEx2_Pair() {
        Pair<String, Integer> p = new Pair<>("Alice", 95);
        assertEquals("Alice", p.getFirst(), "2a: getFirst()");
        assertEquals(95, p.getSecond(), "2a: getSecond()");
        assertEquals("(Alice, 95)", p.toString(), "2a: toString");

        Pair<Integer, String> swapped = p.swap();
        assertEquals(95, swapped.getFirst(), "2b: swap first = 95");
        assertEquals("Alice", swapped.getSecond(), "2b: swap second = Alice");
        assertEquals("(95, Alice)", swapped.toString(), "2b: swap toString");

        Pair<String, String> cities = new Pair<>("Tokyo", "Japan");
        assertEquals("Tokyo", cities.getFirst(), "2c: city pair first");
        assertEquals("Japan", cities.getSecond(), "2c: city pair second");
    }

    @Test
    void testEx3_Stack() {
        Stack<String> s = new Stack<>();
        assertTrue(s.isEmpty(), "3a: new stack is empty");
        assertEquals(0, s.size(), "3a: size = 0");

        s.push("bottom");
        s.push("middle");
        s.push("top");
        assertFalse(s.isEmpty(), "3b: not empty after pushes");
        assertEquals(3, s.size(), "3b: size = 3");

        assertEquals("top", s.peek(), "3c: peek = top");
        assertEquals(3, s.size(), "3c: size unchanged after peek");

        assertEquals("top", s.pop(), "3d: pop = top");
        assertEquals("middle", s.pop(), "3d: pop = middle");
        assertEquals("bottom", s.pop(), "3d: pop = bottom");
        assertTrue(s.isEmpty(), "3d: empty after all pops");

        assertThrows(RuntimeException.class, s::pop, "3e: empty pop should throw RuntimeException");
        assertThrows(RuntimeException.class, s::peek, "3f: empty peek should throw RuntimeException");
    }

    @Test
    void testEx4_NumberBox() {
        NumberBox<Integer> ib = new NumberBox<>(42);
        assertEquals(42.0, ib.doubleValue(), 0.001, "4a: doubleValue() = 42.0");
        assertTrue(ib.isPositive(), "4a: isPositive() = true");

        NumberBox<Double> db = new NumberBox<>(-3.14);
        assertEquals(-3.14, db.doubleValue(), 0.001, "4b: doubleValue() = -3.14");
        assertFalse(db.isPositive(), "4b: isPositive() = false");

        NumberBox<Long> lb = new NumberBox<>(0L);
        assertFalse(lb.isPositive(), "4c: zero is not positive");
    }

    @Test
    void testEx5_GenericUtils() {
        Integer[] nums = {1, 2, 3, 4, 5};
        GenericUtils.swap(nums, 0, 4);
        assertEquals(5, nums[0], "5a: swap [0] and [4]");
        assertEquals(1, nums[4], "5a: swap [4] after");

        List<String> words = Arrays.asList("alpha", "beta", "gamma");
        assertEquals("alpha", GenericUtils.getFirst(words), "5b: getFirst = alpha");

        assertThrows(RuntimeException.class, () -> GenericUtils.getFirst(new ArrayList<>()));

        List<Integer> scores = Arrays.asList(34, 91, 55, 72, 88);
        assertEquals(91, GenericUtils.findMax(scores), "5c: findMax of integers = 91");

        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        assertEquals("Charlie", GenericUtils.findMax(names), "5c: findMax of strings = Charlie");

        assertThrows(RuntimeException.class, () -> GenericUtils.findMax(new ArrayList<Integer>()));

        List<String> hellos = GenericUtils.repeat("hello", 4);
        assertEquals(4, hellos.size(), "5d: repeat 4 times size");
        assertTrue(hellos.stream().allMatch(x -> "hello".equals(x)), "5d: all elements = hello");
        assertEquals(0, GenericUtils.repeat("x", 0).size(), "5d: repeat 0 times");
    }

    @Test
    void testChallenge_Inventory() {
        Inventory<Integer> stock = new Inventory<>();
        assertEquals(0, stock.count(), "ch: count starts at 0");

        stock.addItem("apples", 50);
        stock.addItem("bananas", 30);
        stock.addItem("oranges", 20);
        assertEquals(3, stock.count(), "ch: count = 3");

        assertEquals(30, stock.getItem("bananas"), "ch: getItem bananas = 30");
        assertTrue(stock.hasItem("apples"), "ch: hasItem apples");
        assertFalse(stock.hasItem("grapes"), "ch: !hasItem grapes");

        stock.removeItem("apples");
        assertEquals(2, stock.count(), "ch: count after remove = 2");
        assertFalse(stock.hasItem("apples"), "ch: apples gone");

        assertThrows(RuntimeException.class, () -> stock.getItem("apples"));
    }
}
