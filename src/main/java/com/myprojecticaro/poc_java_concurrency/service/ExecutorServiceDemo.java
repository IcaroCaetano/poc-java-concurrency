package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Service that demonstrates basic usage of {@link ExecutorService} with a fixed thread pool.
 * <p>
 * This example submits multiple tasks to a thread pool executor, each of which prints the task ID
 * and the thread name that is executing it.
 * </p>
 *
 * <p>
 * It showcases how to:
 * <ul>
 *   <li>Use {@link Executors#newFixedThreadPool(int)} to create a fixed number of threads</li>
 *   <li>Submit multiple tasks using {@link ExecutorService#submit(Runnable)}</li>
 *   <li>Share threads across tasks to improve performance and resource management</li>
 * </ul>
 * </p>
 *
 * <p><strong>Note:</strong> The executor is not shut down in this example, as it is intended for demonstration purposes only.
 * In a production application, remember to call {@link ExecutorService#shutdown()} when the executor is no longer needed.</p>
 *
 * @author Icaro Caetano
 */
@Service
public class ExecutorServiceDemo {
    private final ExecutorService executor = Executors.newFixedThreadPool(3);


    /**
     * Submits 5 tasks to the executor. Each task prints its ID and the name of the executing thread.
     */
    public void runTasks() {
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Running task " + taskId + " in thread " + Thread.currentThread().getName());
            });
        }
    }
}