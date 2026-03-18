# Module 09 — Multithreading & Concurrency Basics

Multithreading lets your program do multiple things at once. As a Jr. Dev, you need to understand this for background tasks, server handling, and performance-sensitive code.

---

## 1. What is a Thread?

A **thread** is an independent path of execution within a program. By default, Java programs run on one thread (the main thread). You can create additional threads to run code concurrently.

---

## 2. Creating Threads — Two Ways

### Way 1: Extend Thread class
```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running: " + getName());
    }
}

MyThread t = new MyThread();
t.start();   // starts the thread (calls run() in a new thread)
// t.run(); would be WRONG — it runs on the current thread, not a new one
```

### Way 2: Implement Runnable (preferred)
```java
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task running: " + Thread.currentThread().getName());
    }
}

Thread t = new Thread(new MyTask());
t.start();

// Even cleaner with a lambda:
Thread t2 = new Thread(() -> System.out.println("Lambda thread!"));
t2.start();
```

> **Prefer Runnable over extending Thread** — it separates the task from the thread mechanism, and lets you extend another class if needed.

---

## 3. Thread Lifecycle

```
NEW → RUNNABLE → RUNNING → (BLOCKED/WAITING) → TERMINATED
```

- `NEW`: Thread created, not started yet
- `RUNNABLE`: Ready to run, waiting for CPU
- `RUNNING`: Executing `run()`
- `BLOCKED/WAITING`: Waiting for a lock, I/O, or sleep
- `TERMINATED`: `run()` finished

---

## 4. Common Thread Methods

```java
Thread t = new Thread(() -> {
    System.out.println("Running...");
    try {
        Thread.sleep(1000);   // pause for 1 second (throws InterruptedException)
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();  // restore interrupted status
    }
    System.out.println("Done!");
});

t.start();                    // start the thread
t.getName()                   // "Thread-0" (auto-name)
t.setName("WorkerThread");    // set custom name
t.isAlive()                   // true while thread is running
t.join()                      // wait for this thread to finish
t.join(2000)                  // wait at most 2 seconds

Thread.currentThread()        // get reference to current thread
Thread.sleep(500)             // pause current thread for 500ms
Thread.yield()                // hint to scheduler to switch to another thread
```

---

## 5. Race Conditions & Synchronized

When multiple threads access shared data, results can be unpredictable.

```java
// UNSAFE — race condition!
class Counter {
    int count = 0;

    void increment() {
        count++;   // NOT atomic! read-modify-write can be interrupted
    }
}

Counter c = new Counter();
// If 1000 threads all call increment(), count might NOT be 1000!
```

**Fix with `synchronized`:**
```java
class SafeCounter {
    private int count = 0;

    // Only one thread can execute this at a time
    synchronized void increment() {
        count++;
    }

    synchronized int getCount() {
        return count;
    }
}
```

**Synchronized block** (lock only part of a method):
```java
class Counter {
    private int count = 0;
    private final Object lock = new Object();

    void increment() {
        synchronized (lock) {
            count++;
        }
        // other code here runs without lock
    }
}
```

---

## 6. Volatile

For simple flag variables shared between threads, `volatile` ensures visibility (each thread sees the latest value):

```java
class Worker implements Runnable {
    private volatile boolean running = true;   // volatile!

    @Override
    public void run() {
        while (running) {
            // do work
        }
        System.out.println("Worker stopped.");
    }

    void stop() {
        running = false;  // visible to the worker thread immediately
    }
}
```

---

## 7. ExecutorService — Modern Thread Management

Managing threads manually is error-prone. Use `ExecutorService` from `java.util.concurrent`:

```java
import java.util.concurrent.*;

// Fixed thread pool — reuses a pool of N threads
ExecutorService executor = Executors.newFixedThreadPool(4);

// Submit tasks
executor.submit(() -> System.out.println("Task 1 on: " + Thread.currentThread().getName()));
executor.submit(() -> System.out.println("Task 2 on: " + Thread.currentThread().getName()));
executor.submit(() -> System.out.println("Task 3 on: " + Thread.currentThread().getName()));

// Always shut down when done
executor.shutdown();                 // graceful — waits for tasks to finish
executor.awaitTermination(5, TimeUnit.SECONDS);  // wait up to 5s
```

### Common thread pool types:
```java
Executors.newFixedThreadPool(4)    // fixed number of threads
Executors.newSingleThreadExecutor()  // single thread, tasks run sequentially
Executors.newCachedThreadPool()    // creates threads as needed, reuses idle ones
Executors.newScheduledThreadPool(2) // for scheduled/recurring tasks
```

---

## 8. Callable and Future — Get Results from Threads

`Runnable` can't return a value. Use `Callable<T>` for tasks that return results:

```java
import java.util.concurrent.*;

ExecutorService executor = Executors.newFixedThreadPool(2);

Callable<Integer> task = () -> {
    Thread.sleep(100);  // simulate work
    return 42;
};

Future<Integer> future = executor.submit(task);

// Do other work while task runs...

// Get the result (blocks until task is done)
try {
    Integer result = future.get();            // blocking
    Integer result2 = future.get(2, TimeUnit.SECONDS);  // with timeout
    System.out.println("Result: " + result);  // 42
} catch (ExecutionException | InterruptedException | TimeoutException e) {
    e.printStackTrace();
}

executor.shutdown();
```

---

## 9. Atomic Classes

For simple counters/flags, `java.util.concurrent.atomic` provides thread-safe operations without `synchronized`:

```java
import java.util.concurrent.atomic.AtomicInteger;

AtomicInteger counter = new AtomicInteger(0);

counter.incrementAndGet()   // atomic ++
counter.decrementAndGet()   // atomic --
counter.addAndGet(5)        // atomic += 5
counter.get()               // read current value
counter.compareAndSet(5, 10)  // if current is 5, set to 10
```

---

## 10. Common Pitfalls

| Problem | What happens | Fix |
|---------|-------------|-----|
| Race condition | Inconsistent data | `synchronized` or atomic classes |
| Deadlock | Two threads wait for each other forever | Careful lock ordering, use timeouts |
| Not calling `start()` | Thread never runs | `t.start()`, not `t.run()` |
| Forgetting `shutdown()` | JVM won't exit | Always call `executor.shutdown()` |
| Swallowing `InterruptedException` | Thread can't be stopped | Call `Thread.currentThread().interrupt()` |

---

## Key Takeaways

- Create threads with `Runnable` (or lambda) + `Thread.start()`
- Use `synchronized` to protect shared state
- Prefer `ExecutorService` over raw threads
- Use `Callable<T>` + `Future<T>` when you need return values
- `AtomicInteger` for simple thread-safe counters

---

## Next Step

Open `Exercise.java` and complete all the TODOs.
