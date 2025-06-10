package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Service that demonstrates the use of both {@link AtomicInteger} and {@link ReentrantLock}
 * for concurrency control in Java.
 *
 * <p>
 * This class includes two types of thread-safe mechanisms:
 * <ul>
 *   <li><strong>Atomic Operations:</strong> Uses {@link AtomicInteger} to increment a counter atomically.</li>
 *   <li><strong>Lock-based Synchronization:</strong> Uses {@link ReentrantLock} to manually control access
 *   to a critical section.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The {@code demo()} method demonstrates both of these features:
 * it atomically increments a counter and then enters a locked section to append thread-specific output.
 * </p>
 *
 * <p>
 * This example is useful for understanding how low-level concurrency primitives can be combined to
 * ensure data integrity and safe access across threads.
 * </p>
 *
 * <p><strong>Note:</strong> Always ensure {@code lock.unlock()} is called inside a {@code finally} block
 * to prevent deadlocks in case of exceptions.</p>
 *
 * @author Icaro Caetano
 */
@Service
public class LockAtomicDemo {

    /**
     * Thread-safe counter using atomic operations.
     */
    private final AtomicInteger atomicCounter = new AtomicInteger(0);

    /**
     * Reentrant lock to control access to a critical section.
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Demonstrates the use of atomic operations and lock-based synchronization.
     *
     * @return a string containing the atomic counter value and thread information
     */
    public String demo() {
        StringBuilder sb = new StringBuilder();

        int atomic = atomicCounter.incrementAndGet();
        sb.append("Atomic counter: ").append(atomic).append("\n");

        lock.lock();
        try {
            sb.append("Locked section accessed by thread ").append(Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }

        return sb.toString();
    }

    /**
     * Executes the demonstration of atomic and lock-based operations concurrently using multiple threads.
     *
     * <p>This method creates a thread pool with the specified number of threads and runs
     * the {@link #demo()} operation on each thread. The results from each execution are
     * collected and concatenated into a single string.</p>
     *
     * <p>It uses an {@link ExecutorService} to manage the threads and waits for all tasks
     * to complete before returning the result.</p>
     *
     * @param threadCount the number of threads to run concurrently
     * @return a string containing the concatenated results from all threads
     * @throws InterruptedException if the execution is interrupted while waiting for threads to finish
     * @throws ExecutionException if any task throws an exception during execution
     */
    public String demoMultithread(int threadCount) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executor.submit(this::demo));
        }

        StringBuilder result = new StringBuilder();
        for (Future<String> future : futures) {

            result.append(future.get()).append("\n");
        }

        executor.shutdown();
        return result.toString();
    }
}
