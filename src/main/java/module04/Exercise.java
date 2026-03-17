package module04;

import java.util.*;

/**
 * MODULE 04 — Generics
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each generic class and method stub below
 *   3. Run: javac *.java && java Test
 */

// ============================================================================
// TO DO 1: Generic class Container<T>
// ============================================================================
class Container<T> {
    private T content;

    Container(T content) {
        // TO DO: store content
    }

    T getContent() {
        return null; // TO DO: return content
    }

    void setContent(T content) {
        // TO DO: update this.content
    }

    boolean isEmpty() {
        return true; // TO DO: return content == null
    }

    @Override
    public String toString() {
        return "Container[empty]"; // TO DO: "Container[content]" or "Container[empty]"
    }
}

// ============================================================================
// TO DO 2: Generic class Pair<K, V>
// ============================================================================
class Pair<K, V> {
    K first;
    V second;

    Pair(K first, V second) {
        // TO DO: store first and second
    }

    K getFirst() {
        return null; // TO DO: return first
    }

    V getSecond() {
        return null; // TO DO: return second
    }

    Pair<V, K> swap() {
        return new Pair<>(null, null); // TO DO: return new Pair<>(second, first)
    }

    @Override
    public String toString() {
        return ""; // TO DO: return "(" + first + ", " + second + ")"
    }
}

// ============================================================================
// TO DO 3: Generic class Stack<T>
// ============================================================================
class Stack<T> {
    private ArrayList<T> items = new ArrayList<>();

    void push(T item) {
        // TO DO: add item to items
    }

    T pop() {
        // TO DO: check isEmpty(), then remove and return last element
        throw new RuntimeException("Stack is empty"); // placeholder
    }

    T peek() {
        // TO DO: check isEmpty(), then return last element without removing
        throw new RuntimeException("Stack is empty"); // placeholder
    }

    boolean isEmpty() {
        return true; // TO DO: return items.isEmpty()
    }

    int size() {
        return 0; // TO DO: return items.size()
    }

    @Override
    public String toString() {
        return items.toString();
    }
}

// ============================================================================
// TO DO 4: Generic class NumberBox<T extends Number>
// ============================================================================
class NumberBox<T extends Number> {
    T value;

    NumberBox(T value) {
        // TO DO: store value
    }

    double doubleValue() {
        return 0.0; // TO DO: return value.doubleValue()
    }

    boolean isPositive() {
        return false; // TO DO: return doubleValue() > 0
    }

    @Override
    public String toString() {
        return "NumberBox[" + value + "]";
    }
}

// ============================================================================
// TO DO 5: GenericUtils static methods
// ============================================================================
class GenericUtils {

    static <T> void swap(T[] arr, int i, int j) {
        // TO DO: swap arr[i] and arr[j]
    }

    static <T> T getFirst(List<T> list) {
        throw new RuntimeException("List is empty"); // placeholder
    }

    static <T extends Comparable<T>> T findMax(List<T> list) {
        throw new RuntimeException("List is empty"); // placeholder
    }

    static <T> List<T> repeat(T item, int times) {
        return new ArrayList<>(); // placeholder
    }
}

// ============================================================================
// TO DO CHALLENGE: Generic Inventory<T>
// ============================================================================
class Inventory<T> {
    private Map<String, T> items = new HashMap<>();

    void addItem(String name, T item) {
        // TO DO: put into items map
    }

    T getItem(String name) {
        // TO DO: return items.get(name), throw RuntimeException if not found
        throw new RuntimeException("Item not found: " + name); // placeholder
    }

    boolean hasItem(String name) {
        return false; // TO DO: return items.containsKey(name)
    }

    void removeItem(String name) {
        // TO DO: items.remove(name)
    }

    int count() {
        return 0; // TO DO: return items.size()
    }
}

// ============================================================================
// MAIN (leave as-is, students use Test.java)
// ============================================================================
public class Exercise {
    public static void main(String[] args) {
        System.out.println("Run: javac *.java && java Test");
    }
}
