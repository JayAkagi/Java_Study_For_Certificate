package module09;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;

/**
 * MODULE 09 — Multithreading & Concurrency
 *
 * Instructions:
 *   1. Read notes.md first
 *   2. Implement each class stub and method stub below
 *   3. Run: javac *.java && java Test
 *
 * NOTE: Thread output order is non-deterministic — that is normal!
 *       What the tests check are final counts and return values, not print order.
 */

// ============================================================================
// TO DO: PrinterThread extending Thread
// ============================================================================
class PrinterThread extends Thread {
    PrinterThread(String name) {
        setName(name);
    }

    @Override
    public void run() {
        // TO DO: loop i from 0 to 2 (inclusive), print getName() + ": iteration " + i
    }
}

// ============================================================================
// TO DO: SafeCounter with synchronized methods
// ============================================================================
class SafeCounter {
    private int count = 0;

    synchronized void increment() {
        // TO DO: count++
    }

    synchronized int getCount() {
        return 0; // TO DO: return count
    }
}

// ============================================================================
// MAIN — static exercise methods used by Test.java
// ============================================================================
public class Exercise {

    // TO DO: Start 10 threads, each calling counter.increment() 1000 times.
    static int safeConcurrentCount() throws InterruptedException {
        SafeCounter counter = new SafeCounter();
        return 0; // placeholder
    }

    // TO DO: Use AtomicInteger.
    static int atomicConcurrentCount() throws InterruptedException {
        AtomicInteger atomicCounter = new AtomicInteger(0);
        return 0; // placeholder
    }

    // TO DO: Submit 3 Callable<Integer> tasks to a fixed thread pool of 3.
    static int callableFutureSum() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.shutdown();
        return 0; // placeholder
    }

    // TO DO: Demonstrate compareAndSet
    static boolean[] compareAndSetDemo() {
        return new boolean[]{false, false}; // placeholder
    }

    public static void main(String[] args) {
        System.out.println("Run: javac *.java && java Test");
    }
}
