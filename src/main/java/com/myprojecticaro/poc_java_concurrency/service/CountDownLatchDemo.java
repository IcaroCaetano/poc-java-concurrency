package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Demonstrates the use of CountDownLatch to synchronize the completion of multiple threads.
 * This class starts several tasks in separate threads and uses a CountDownLatch to wait
 * until all tasks have completed before proceeding.
 */
@Service
public class CountDownLatchDemo {

    /**
     * Runs multiple tasks concurrently and waits for all of them to finish using CountDownLatch.
     *
     * @return A string containing the execution results of all tasks.
     * @throws InterruptedException if the current thread is interrupted while waiting.
     */
    public String run() throws InterruptedException {
        int taskCount = 3;
        CountDownLatch latch = new CountDownLatch(taskCount);
        StringBuilder result = new StringBuilder("CountDownLatch Example:\n");

        for (int i = 0; i < taskCount; i++) {
            int taskId = i;
            new Thread(() -> {
                try {
                    Thread.sleep(500 + taskId * 200L);
                    result.append("Task ").append(taskId).append(" finished\n");
                } catch (InterruptedException e) {
                    result.append("Task ").append(taskId).append(" interrupted\n");
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
        result.append("All tasks completed.\n");
        return result.toString();
    }
}