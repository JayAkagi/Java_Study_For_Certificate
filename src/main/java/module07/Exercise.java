package module07;

import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;

/**
 * MODULE 07 — Optional & Date/Time API
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each method stub — each returns a value so it can be tested
 *   3. Run: javac *.java && java Test
 */
public class Exercise {

    // -------------------------------------------------------------------------
    // EXERCISE 1 — Optional Basics
    // -------------------------------------------------------------------------

    static Optional<String> ex1a_present() {
        return Optional.empty(); // placeholder
    }

    static Optional<Integer> ex1b_empty() {
        return Optional.of(0); // placeholder
    }

    static Optional<String> ex1c_nullOptional() {
        return Optional.of("not null"); // placeholder
    }

    static String ex1d_defaultGuest() {
        return ""; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 2 — Optional Transform (map, filter)
    // -------------------------------------------------------------------------

    static String ex2a_trimUppercase() {
        return ""; // placeholder
    }

    static String ex2d_chainedOptional() {
        return ""; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 4 — LocalDate
    // -------------------------------------------------------------------------

    static LocalDate ex4a_today() {
        return LocalDate.of(1970, 1, 1); // placeholder
    }

    static LocalDate ex4b_birthday() {
        return LocalDate.of(1970, 1, 1); // placeholder
    }

    static LocalDate ex4c_hundredDaysFromNow() {
        return LocalDate.of(1970, 1, 1); // placeholder
    }

    static boolean ex4d_jan1BeforeDec31() {
        return false; // placeholder
    }

    static int ex4e_daysInMarch2024() {
        return 0; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 5 — LocalTime & LocalDateTime
    // -------------------------------------------------------------------------

    static LocalTime ex5a_addTwoAndHalfHours() {
        return LocalTime.MIDNIGHT; // placeholder
    }

    static LocalDateTime ex5b_addTime() {
        return LocalDateTime.of(2024, 1, 1, 0, 0); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 6 — Formatting & Parsing
    // -------------------------------------------------------------------------

    static String ex6a_formatSlash() {
        return ""; // placeholder
    }

    static String ex6b_formatLong() {
        return ""; // placeholder
    }

    static LocalDate ex6d_parseChristmas() {
        return LocalDate.of(1970, 1, 1); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 7 — Duration
    // -------------------------------------------------------------------------

    static long ex7c_durationHours() {
        return 0; // placeholder
    }

    static long ex7c_durationMinutes() {
        return 0; // placeholder
    }

    // -------------------------------------------------------------------------
    // Helper (keep as-is)
    // -------------------------------------------------------------------------
    static String getUserName(int id) {
        Map<Integer, String> users = Map.of(1, "Alice", 2, "Bob", 3, "Charlie");
        return users.get(id); // returns null if not found
    }

    public static void main(String[] args) {
        System.out.println("Run: javac *.java && java Test");
    }
}
