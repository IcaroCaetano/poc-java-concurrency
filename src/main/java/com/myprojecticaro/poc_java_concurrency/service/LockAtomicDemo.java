package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

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
 * @author Your Name
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

        // Atomic operation
        int atomic = atomicCounter.incrementAndGet();
        sb.append("Atomic counter: ").append(atomic).append("\n");

        // Lock-based operation
        lock.lock();
        try {
            sb.append("Locked section accessed by thread ").append(Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }

        return sb.toString();
    }
}
