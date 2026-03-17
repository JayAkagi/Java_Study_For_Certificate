package module08;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * MODULE 08 — File I/O
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each method stub — each returns a value so it can be tested
 *   3. Run: javac *.java && java Test
 *
 * All file methods receive a filePath argument so tests can control the location.
 * The static TEMP_DIR is created automatically if it doesn't exist.
 */
public class Exercise {

    static final String TEMP_DIR = System.getProperty("java.io.tmpdir") + "/jl_fileio";

    // -------------------------------------------------------------------------
    // EXERCISE 1 — BufferedWriter + BufferedReader
    // -------------------------------------------------------------------------

    static List<String> writeAndReadLines(String filePath, List<String> lines) throws IOException {
        return new ArrayList<>(); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 2 — NIO Files
    // -------------------------------------------------------------------------

    static List<String> nioWriteAndRead(String filePath, List<String> lines) throws IOException {
        return new ArrayList<>(); // placeholder
    }

    static int readStringLength(String filePath, List<String> lines) throws IOException {
        return 0; // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 3 — Append to File
    // -------------------------------------------------------------------------

    static List<String> appendToLog(String logFile, String initialLine, List<String> appendLines) throws IOException {
        return new ArrayList<>(); // placeholder
    }

    // -------------------------------------------------------------------------
    // EXERCISE 5 — CSV Processing
    // -------------------------------------------------------------------------

    static int countGradeA(String csvFile, List<String> csvLines) throws IOException {
        return 0; // placeholder
    }

    static double averageAge(String csvFile, List<String> csvLines) throws IOException {
        return 0.0; // placeholder
    }

    // -------------------------------------------------------------------------
    // CHALLENGE — Log File Processor
    // -------------------------------------------------------------------------

    static int countErrors(String logFile, List<String> logLines) throws IOException {
        return 0; // placeholder
    }

    // -------------------------------------------------------------------------
    // Helper — ensures TEMP_DIR exists (called by Test)
    // -------------------------------------------------------------------------
    static void ensureTempDir() throws IOException {
        Files.createDirectories(Path.of(TEMP_DIR));
    }

    public static void main(String[] args) throws IOException {
        ensureTempDir();
        System.out.println("Run: javac *.java && java Test");
    }
}
