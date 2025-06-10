package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

/**
 * Demonstrates the use of Semaphore to control access to a limited number of resources.
 * Only a specified number of threads can access a critical section simultaneously.
 */
@Service
public class SemaphoreDemo {

    /**
     * Runs multiple tasks where only a limited number are allowed to execute concurrently.
     *
     * @return A string describing how each task acquired and released a permit.
     * @throws InterruptedException if the current thread is interrupted while sleeping.
     */
    public String run() throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        StringBuilder result = new StringBuilder("Semaphore Example:\n");

        for (int i = 0; i < 5; i++) {
            int taskId = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    result.append("Task ").append(taskId).append(" acquired permit\n");
                    Thread.sleep(1000);
                    result.append("Task ").append(taskId).append(" released permit\n");
                } catch (InterruptedException e) {
                    result.append("Task ").append(taskId).append(" interrupted\n");
                } finally {
                    semaphore.release();
                }
            }).start();
        }

        Thread.sleep(4000);
        result.append("All tasks completed.\n");
        return result.toString();
    }
}
