package module07;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class ExerciseTest {

    @Test
    void testEx1_OptionalBasics() {
        Optional<String> present = Exercise.ex1a_present();
        assertTrue(present != null && present.isPresent(), "1a: Optional is present");
        assertEquals("Hello, Optional!", present != null && present.isPresent() ? present.get() : "", "1a: value = 'Hello, Optional!'");

        Optional<Integer> empty = Exercise.ex1b_empty();
        assertTrue(empty != null && !empty.isPresent(), "1b: Optional is empty");

        Optional<String> nullOpt = Exercise.ex1c_nullOptional();
        assertTrue(nullOpt != null && !nullOpt.isPresent(), "1c: ofNullable(null) is empty");

        assertEquals("Guest", Exercise.ex1d_defaultGuest(), "1d: orElse Guest");
    }

    @Test
    void testEx2_OptionalTransform() {
        assertEquals("HELLO WORLD", Exercise.ex2a_trimUppercase(), "2a: trim + uppercase");
        assertEquals("Number: 42", Exercise.ex2d_chainedOptional(), "2d: chained optional");
    }

    @Test
    void testEx4_LocalDate() {
        LocalDate today = Exercise.ex4a_today();
        assertEquals(LocalDate.now(), today, "4a: today = LocalDate.now()");

        LocalDate bday = Exercise.ex4b_birthday();
        assertEquals(1999, bday != null ? bday.getYear() : -1, "4b: birthday year");
        assertEquals(7, bday != null ? bday.getMonthValue() : -1, "4b: birthday month");
        assertEquals(20, bday != null ? bday.getDayOfMonth() : -1, "4b: birthday day");

        LocalDate hundredDays = Exercise.ex4c_hundredDaysFromNow();
        long diff = hundredDays != null ? java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), hundredDays) : -1;
        assertEquals(100L, diff, "4c: 100 days from now diff = 100");

        assertTrue(Exercise.ex4d_jan1BeforeDec31(), "4d: 2024-01-01 is before 2024-12-31");
        assertEquals(31, Exercise.ex4e_daysInMarch2024(), "4e: days in March 2024 = 31");
    }

    @Test
    void testEx5_LocalDateTime() {
        LocalTime result5a = Exercise.ex5a_addTwoAndHalfHours();
        assertEquals(LocalTime.of(12, 0, 0), result5a, "5a: 09:30 + 2.5h = 12:00");

        LocalDateTime result5b = Exercise.ex5b_addTime();
        assertEquals(LocalDateTime.of(2024, 6, 15, 13, 30), result5b, "5b: 2024-06-15T10:00 + 3h30m = 2024-06-15T13:30");
    }

    @Test
    void testEx6_Formatting() {
        assertEquals("15/06/2024", Exercise.ex6a_formatSlash(), "6a: format as dd/MM/yyyy");
        assertEquals("June 15, 2024", Exercise.ex6b_formatLong(), "6b: format as MMMM dd, yyyy");

        LocalDate christmas = Exercise.ex6d_parseChristmas();
        assertEquals(2024, christmas != null ? christmas.getYear() : -1, "6d: parse 25-12-2024 year");
        assertEquals(12, christmas != null ? christmas.getMonthValue() : -1, "6d: parse month = 12");
        assertEquals(25, christmas != null ? christmas.getDayOfMonth() : -1, "6d: parse day = 25");
    }

    @Test
    void testEx7_Duration() {
        assertEquals(8L, Exercise.ex7c_durationHours(), "7c: duration hours = 8");
        assertEquals(510L, Exercise.ex7c_durationMinutes(), "7c: duration minutes = 510");
    }
}
