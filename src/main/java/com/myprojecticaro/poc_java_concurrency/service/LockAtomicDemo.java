package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class LockAtomicDemo {

    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    private final ReentrantLock lock = new ReentrantLock();

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
