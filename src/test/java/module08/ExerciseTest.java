package module08;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class ExerciseTest {

    @BeforeEach
    void setup() throws Exception {
        Exercise.ensureTempDir();
    }

    @Test
    void testEx1_BufferedWriteRead() throws Exception {
        String path = Exercise.TEMP_DIR + "/test_ex1.txt";
        List<String> lines = Arrays.asList(
            "Java is awesome",
            "I am learning file I/O",
            "BufferedWriter is efficient",
            "Try-with-resources is important",
            "Line 5"
        );
        List<String> result = Exercise.writeAndReadLines(path, lines);
        assertEquals(5, result != null ? result.size() : -1, "1: line count = 5");
        assertEquals("Java is awesome", (result != null && result.size() > 0) ? result.get(0) : "", "1: line 0");
        assertEquals("BufferedWriter is efficient", (result != null && result.size() > 2) ? result.get(2) : "", "1: line 2");
        assertEquals("Line 5", (result != null && result.size() > 4) ? result.get(4) : "", "1: line 4");
        assertTrue(Files.exists(Path.of(path)), "1: file exists after write");
    }

    @Test
    void testEx2_NioFiles() throws Exception {
        String path = Exercise.TEMP_DIR + "/test_ex2.txt";
        List<String> lines = Arrays.asList(
            "NIO is the modern way",
            "It's cleaner than classic I/O",
            "Files.write takes a list of strings"
        );
        List<String> result = Exercise.nioWriteAndRead(path, lines);
        assertEquals(3, result != null ? result.size() : -1, "2a: line count = 3");
        assertEquals("NIO is the modern way", (result != null && result.size() > 0) ? result.get(0) : "", "2a: line 0");
        assertEquals("Files.write takes a list of strings", (result != null && result.size() > 2) ? result.get(2) : "", "2a: line 2");

        int charCount = Exercise.readStringLength(path, lines);
        assertTrue(charCount > 0, "2c: char count > 0");
        assertTrue(charCount > 50, "2c: char count reasonable (>50)");
    }

    @Test
    void testEx3_Append() throws Exception {
        String logFile = Exercise.TEMP_DIR + "/test_append.log";
        String initial = "[INFO] Application started";
        List<String> appendLines = Arrays.asList(
            "[INFO] Processing data...",
            "[WARN] Memory usage at 80%",
            "[INFO] Processing complete"
        );
        List<String> result = Exercise.appendToLog(logFile, initial, appendLines);
        assertEquals(4, result != null ? result.size() : -1, "3: total lines = 4");
        assertEquals("[INFO] Application started", (result != null && result.size() > 0) ? result.get(0) : "", "3: line 0 = initial");
        assertEquals("[INFO] Processing data...", (result != null && result.size() > 1) ? result.get(1) : "", "3: line 1 = first appended");
        assertEquals("[WARN] Memory usage at 80%", (result != null && result.size() > 2) ? result.get(2) : "", "3: line 2 = warn");
        assertEquals("[INFO] Processing complete", (result != null && result.size() > 3) ? result.get(3) : "", "3: line 3 = last");
    }

    @Test
    void testEx5_CSV() throws Exception {
        String csvFile = Exercise.TEMP_DIR + "/test_students.csv";
        List<String> csvLines = Arrays.asList(
            "Name,Age,Grade",
            "Alice,20,A",
            "Bob,22,B",
            "Charlie,21,A",
            "Dave,23,C",
            "Eve,20,B"
        );
        int gradeA = Exercise.countGradeA(csvFile, csvLines);
        assertEquals(2, gradeA, "5a: students with grade A = 2");

        double avgAge = Exercise.averageAge(csvFile, csvLines);
        assertEquals(21.2, avgAge, 0.01, "5d: average age ≈ 21.2");
    }

    @Test
    void testChallenge_LogProcessor() throws Exception {
        String logFile = Exercise.TEMP_DIR + "/test_challenge.log";
        List<String> logLines = Arrays.asList(
            "[INFO] 2024-03-01 Server started",
            "[ERROR] 2024-03-01 Database connection failed",
            "[INFO] 2024-03-02 Processing 1000 records",
            "[WARN] 2024-03-02 Memory usage high",
            "[ERROR] 2024-03-03 Null pointer exception in UserService",
            "[INFO] 2024-03-03 Processing complete",
            "[ERROR] 2024-03-04 Timeout connecting to API",
            "[INFO] 2024-03-04 Server shutting down"
        );
        int errorCount = Exercise.countErrors(logFile, logLines);
        assertEquals(3, errorCount, "challenge: error count = 3");
    }
}
