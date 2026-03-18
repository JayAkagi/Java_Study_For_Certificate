# Module 01 — Advanced Collections

You already know `HashMap` and basic arrays. This module covers the full Java Collections Framework — the tools you'll use every single day as a developer.

---

## The Collections Hierarchy (Big Picture)

```
java.util.Collection
    ├── List          → ordered, allows duplicates
    │     ├── ArrayList
    │     └── LinkedList
    ├── Set           → no duplicates
    │     ├── HashSet
    │     └── TreeSet  (sorted)
    └── Queue
          └── LinkedList (also a Queue)

java.util.Map         → key-value pairs (NOT a Collection)
      ├── HashMap
      ├── TreeMap      (sorted by key)
      └── LinkedHashMap (insertion order)
```

---

## 1. ArrayList

The go-to list. Backed by a resizable array. Fast random access, slow insert/delete in the middle.

```java
import java.util.ArrayList;
import java.util.Collections;

ArrayList<String> names = new ArrayList<>();

// Adding
names.add("Alice");
names.add("Bob");
names.add(0, "Zara");   // insert at index 0

// Accessing
System.out.println(names.get(0));   // "Zara"
System.out.println(names.size());   // 3

// Removing
names.remove("Bob");          // by value
names.remove(0);              // by index

// Checking
names.contains("Alice");      // true/false
names.isEmpty();              // true/false

// Iterating (3 ways)
for (String name : names) { System.out.println(name); }

for (int i = 0; i < names.size(); i++) { System.out.println(names.get(i)); }

names.forEach(name -> System.out.println(name));  // lambda (Module 05)

// Sorting
Collections.sort(names);                          // alphabetical
Collections.sort(names, Collections.reverseOrder()); // reverse
```

---

## 2. LinkedList

Use when you frequently insert/delete at the start or middle. Slightly slower random access than ArrayList.

```java
import java.util.LinkedList;

LinkedList<Integer> nums = new LinkedList<>();
nums.add(10);
nums.add(20);
nums.addFirst(5);   // [5, 10, 20]
nums.addLast(30);   // [5, 10, 20, 30]

nums.removeFirst();  // removes 5
nums.removeLast();   // removes 30
nums.peek();         // look at first without removing
nums.poll();         // remove and return first
```

> **When to use what?**
> - Default list? → `ArrayList`
> - Lots of add/remove at front? → `LinkedList`

---

## 3. HashSet

No duplicates, no guaranteed order. Backed by a HashMap internally. O(1) add/contains/remove.

```java
import java.util.HashSet;

HashSet<String> fruits = new HashSet<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Apple");   // ignored! already exists

System.out.println(fruits.size());          // 2
System.out.println(fruits.contains("Banana")); // true

fruits.remove("Banana");

// Iterating
for (String fruit : fruits) { System.out.println(fruit); }
```

---

## 4. TreeSet

Like HashSet, but **sorted automatically** (natural order or custom comparator).

```java
import java.util.TreeSet;

TreeSet<Integer> scores = new TreeSet<>();
scores.add(50);
scores.add(10);
scores.add(30);
// Internally sorted: [10, 30, 50]

scores.first();   // 10 (smallest)
scores.last();    // 50 (largest)
scores.headSet(30); // elements < 30: [10]
scores.tailSet(30); // elements >= 30: [30, 50]
```

---

## 5. HashMap (Review + Deeper)

You know the basics. Here's what to add:

```java
import java.util.HashMap;
import java.util.Map;

HashMap<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.put("Bob", 87);
scores.put("Alice", 99);  // overwrites previous value

// Safe retrieval with default
scores.getOrDefault("Charlie", 0);  // returns 0 if key missing

// Check key/value
scores.containsKey("Bob");    // true
scores.containsValue(99);     // true

// Iterating over entries
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// Iterating over keys only
for (String key : scores.keySet()) { ... }

// Iterating over values only
for (int val : scores.values()) { ... }

// putIfAbsent — only adds if key doesn't exist
scores.putIfAbsent("Alice", 50);  // does nothing, Alice exists

// merge — combine values
scores.merge("Alice", 5, Integer::sum);  // Alice = 99 + 5 = 104
```

---

## 6. TreeMap

Like HashMap, but **sorted by key** automatically.

```java
import java.util.TreeMap;

TreeMap<String, Integer> map = new TreeMap<>();
map.put("Banana", 2);
map.put("Apple", 5);
map.put("Cherry", 1);
// Internally sorted by key: Apple, Banana, Cherry

map.firstKey();   // "Apple"
map.lastKey();    // "Cherry"
```

---

## 7. LinkedHashMap

Maintains **insertion order** — useful when order matters but you need fast lookup.

```java
import java.util.LinkedHashMap;

LinkedHashMap<String, Integer> ordered = new LinkedHashMap<>();
ordered.put("first", 1);
ordered.put("second", 2);
ordered.put("third", 3);
// Iterates in insertion order: first, second, third
```

---

## 8. Iterator

A way to traverse and safely remove elements during iteration.

```java
import java.util.Iterator;

ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    int val = it.next();
    if (val % 2 == 0) {
        it.remove();  // safe removal during iteration
    }
}
// list is now [1, 3, 5]
```

> **Never use `list.remove()` inside a for-each loop** — it throws `ConcurrentModificationException`. Always use `Iterator` if you need to remove while iterating.

---

## 9. Useful Utility Methods (Collections class)

```java
import java.util.Collections;

ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));

Collections.sort(nums);              // [1, 1, 3, 4, 5]
Collections.reverse(nums);           // [5, 4, 3, 1, 1]
Collections.shuffle(nums);           // random order
Collections.min(nums);               // 1
Collections.max(nums);               // 5
Collections.frequency(nums, 1);      // 2 (count of 1s)
Collections.swap(nums, 0, 1);        // swap index 0 and 1
```

---

## Key Takeaways

| Need | Use |
|------|-----|
| Ordered list, fast access by index | `ArrayList` |
| Fast add/remove at front | `LinkedList` |
| Unique values, don't care about order | `HashSet` |
| Unique values, sorted | `TreeSet` |
| Key-value, fast lookup | `HashMap` |
| Key-value, sorted by key | `TreeMap` |
| Key-value, insertion order | `LinkedHashMap` |

---

## Next Step

Open `Exercise.java` and complete all the TODOs. Come back to these notes whenever you're stuck.
