# Module 07 — Optional & Date/Time API

Two important Java 8+ features: `Optional` for null-safe code, and the new `java.time` package to replace the old, broken `Date`/`Calendar` classes.

---

## PART A — Optional

### 1. The Null Problem

`NullPointerException` is one of the most common Java errors. `Optional<T>` is a container that may or may not hold a value — it forces you to handle the missing case.

```java
// Without Optional — can throw NullPointerException
String name = getUserName(userId);   // might return null
System.out.println(name.toUpperCase());  // BOOM if name is null

// With Optional — explicit about the missing case
Optional<String> name = getUserName(userId);
name.ifPresent(n -> System.out.println(n.toUpperCase()));  // safe
```

---

### 2. Creating Optionals

```java
// Definitely has a value
Optional<String> present = Optional.of("Hello");

// Empty optional (no value)
Optional<String> empty = Optional.empty();

// Might be null — use ofNullable
String mightBeNull = getValueThatMightBeNull();
Optional<String> maybe = Optional.ofNullable(mightBeNull);
```

---

### 3. Using Optionals

```java
Optional<String> opt = Optional.of("Java");

// Check and get
opt.isPresent()   // true if value present
opt.isEmpty()     // true if no value (Java 11+)
opt.get()         // get value — throws NoSuchElementException if empty!

// Safe access
opt.ifPresent(val -> System.out.println(val));   // only runs if present

// Get with default
opt.orElse("default")                        // return value or default
opt.orElseGet(() -> computeDefault())        // lazy default (lambda)
opt.orElseThrow(() -> new RuntimeException("Missing!"))  // or throw

// Transform
opt.map(String::toUpperCase)           // transform if present, empty if not
opt.filter(s -> s.length() > 3)        // return empty if condition fails
opt.flatMap(s -> Optional.of(s + "!")) // like map but for Optional-returning functions
```

---

### 4. Real Example — Safe Chaining

```java
class User {
    String name;
    Address address;  // might be null
}

class Address {
    String city;      // might be null
}

// Without Optional — nested null checks
String city = "Unknown";
if (user != null && user.address != null && user.address.city != null) {
    city = user.address.city;
}

// With Optional — clean chain
String city = Optional.ofNullable(user)
    .map(u -> u.address)
    .map(a -> a.city)
    .orElse("Unknown");
```

---

### 5. What NOT to do with Optional

```java
// BAD: Don't use Optional as a field or method parameter
class User {
    Optional<String> name;  // Don't do this
}

// BAD: Don't call get() without checking
Optional<String> opt = Optional.empty();
opt.get();  // throws NoSuchElementException!

// GOOD: Always use orElse, orElseGet, ifPresent, or check isPresent first
```

---

## PART B — Date/Time API (java.time)

### Why New API?

Old `java.util.Date` and `Calendar` were terrible:
- Not thread-safe
- Months were 0-indexed (January = 0!)
- No clean separation of date vs time
- Confusing API

New `java.time` (Java 8+) is immutable, thread-safe, and sensible.

---

### 6. Core Classes

| Class | What it represents |
|-------|--------------------|
| `LocalDate` | Date only (2024-01-15) |
| `LocalTime` | Time only (14:30:00) |
| `LocalDateTime` | Date + Time (no timezone) |
| `ZonedDateTime` | Date + Time + Timezone |
| `Instant` | Point in time (UTC epoch) |
| `Duration` | Amount of time (seconds, minutes) |
| `Period` | Amount of time in date units (days, months, years) |

---

### 7. LocalDate

```java
import java.time.LocalDate;
import java.time.Month;

// Creating
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1995, 6, 15);         // 1995-06-15
LocalDate fromString = LocalDate.parse("2024-03-20");    // ISO format

// Reading
today.getYear()        // 2024
today.getMonth()       // MARCH (enum)
today.getMonthValue()  // 3
today.getDayOfMonth()  // 20
today.getDayOfWeek()   // WEDNESDAY

// Arithmetic (immutable — returns NEW object)
LocalDate nextWeek    = today.plusDays(7);
LocalDate lastMonth   = today.minusMonths(1);
LocalDate nextYear    = today.plusYears(1);

// Comparing
today.isAfter(birthday)       // true
today.isBefore(birthday)      // false
today.isEqual(today)          // true
today.compareTo(birthday)     // positive if today is after

// Checks
today.isLeapYear()            // true/false
today.lengthOfMonth()         // days in current month (e.g., 31)
```

---

### 8. LocalTime

```java
import java.time.LocalTime;

LocalTime now    = LocalTime.now();
LocalTime lunch  = LocalTime.of(12, 30);          // 12:30:00
LocalTime parsed = LocalTime.parse("08:45:00");

lunch.getHour()    // 12
lunch.getMinute()  // 30
lunch.getSecond()  // 0

LocalTime later = lunch.plusHours(2).plusMinutes(15);  // 14:45:00
```

---

### 9. LocalDateTime

```java
import java.time.LocalDateTime;

LocalDateTime now = LocalDateTime.now();
LocalDateTime meeting = LocalDateTime.of(2024, 3, 20, 14, 30, 0);

// Combining date and time
LocalDate date = LocalDate.of(2024, 3, 20);
LocalTime time = LocalTime.of(14, 30);
LocalDateTime combined = LocalDateTime.of(date, time);

// Extracting
combined.toLocalDate()   // LocalDate part
combined.toLocalTime()   // LocalTime part
```

---

### 10. Formatting and Parsing

```java
import java.time.format.DateTimeFormatter;

LocalDate date = LocalDate.of(2024, 3, 20);
LocalDateTime dt = LocalDateTime.of(2024, 3, 20, 14, 30);

// Predefined formatters
date.format(DateTimeFormatter.ISO_LOCAL_DATE);   // "2024-03-20"

// Custom patterns
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
System.out.println(date.format(fmt));   // "20/03/2024"

DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
System.out.println(dt.format(dtFmt));   // "20 Mar 2024 14:30"

// Parsing
LocalDate parsed = LocalDate.parse("20/03/2024", fmt);
```

---

### 11. Period and Duration

```java
import java.time.Period;
import java.time.Duration;

// Period — date-based (years, months, days)
LocalDate start = LocalDate.of(2020, 1, 1);
LocalDate end   = LocalDate.of(2024, 6, 15);

Period period = Period.between(start, end);
period.getYears()   // 4
period.getMonths()  // 5
period.getDays()    // 14

// Duration — time-based (hours, minutes, seconds, nanos)
LocalDateTime t1 = LocalDateTime.of(2024, 3, 20, 9, 0);
LocalDateTime t2 = LocalDateTime.of(2024, 3, 20, 17, 30);

Duration dur = Duration.between(t1, t2);
dur.toHours()    // 8
dur.toMinutes()  // 510
```

---

### 12. Common Patterns

```java
// Age calculator
LocalDate birthDate = LocalDate.of(1995, 6, 15);
LocalDate today = LocalDate.now();
int age = Period.between(birthDate, today).getYears();

// Days until an event
LocalDate event = LocalDate.of(2024, 12, 25);
long daysUntil = ChronoUnit.DAYS.between(LocalDate.now(), event);

// Start of day / End of day
LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
```

---

## Key Takeaways

**Optional:**
- Use `Optional.ofNullable()` when wrapping potentially null values
- Prefer `ifPresent`, `orElse`, `orElseGet` over `get()`
- Use `map` and `filter` to chain operations safely

**Date/Time:**
- Use `LocalDate` for dates, `LocalTime` for times, `LocalDateTime` for both
- All objects are immutable — operations return new objects
- Use `DateTimeFormatter` for parsing and formatting
- Use `Period` for date differences, `Duration` for time differences

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
