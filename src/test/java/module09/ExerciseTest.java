package module09;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.concurrent.*;

public class ExerciseTest {

    @Test
    @Timeout(10)
    void testPrinterThread() throws InterruptedException {
        PrinterThread t1 = new PrinterThread("ThreadA");
        PrinterThread t2 = new PrinterThread("ThreadB");
        t1.start();
        t2.start();
        t1.join(2000);
        t2.join(2000);
        assertFalse(t1.isAlive(), "PrinterThread: t1 completes without exception");
        assertFalse(t2.isAlive(), "PrinterThread: t2 completes without exception");
    }

    @Test
    @Timeout(10)
    void testSafeCounter() throws InterruptedException {
        int result = Exercise.safeConcurrentCount();
        assertEquals(10000, result, "SafeCounter: 10 threads * 1000 = 10000");
    }

    @Test
    @Timeout(10)
    void testAtomicCounter() throws InterruptedException {
        int result = Exercise.atomicConcurrentCount();
        assertEquals(10000, result, "AtomicCounter: 10 threads * 1000 = 10000");
    }

    @Test
    @Timeout(10)
    void testCallableFuture() throws InterruptedException, ExecutionException {
        int sum = Exercise.callableFutureSum();
        assertEquals(600, sum, "Callable: sum of 100+200+300 = 600");
    }

    @Test
    void testCompareAndSet() {
        boolean[] results = Exercise.compareAndSetDemo();
        assertTrue(results != null && results.length == 2, "CAS: result array not null and length 2");
        assertTrue(results != null && results[0], "CAS: first CAS (0→1) succeeds = true");
        assertTrue(results != null && !results[1], "CAS: second CAS (0→2) fails = false");
    }
}
