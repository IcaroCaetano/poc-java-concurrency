package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Service that demonstrates the use of Java's {@link ExecutorService} to execute tasks concurrently
 * using a fixed-size thread pool.
 *
 * <p>
 * This class submits multiple tasks to a thread pool with a fixed number of threads (3).
 * Each task logs its own identifier and the thread name/ID that executed it.
 * </p>
 *
 * <p>
 * The result of all task executions is collected and returned as a single formatted string.
 * </p>
 *
 * <p>
 * This example is useful for understanding basic concurrent task execution, thread reuse in a pool,
 * and retrieving results from asynchronous computations using {@link Future}.
 * </p>
 *
 * <p><strong>Note:</strong> The order of task completion may vary depending on thread scheduling.</p>
 *
 * @author Icaro Caetano
 */
@Service
public class ExecutorServiceDemo {


    /**
     * Fixed-size thread pool with 3 threads for concurrent task execution.
     */
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    /**
     * Submits 5 tasks to the executor and returns detailed logs of each execution.
     * Each task reports its task ID and the thread that executed it.
     *
     * @return A string containing formatted information about each task's execution.
     * @throws InterruptedException if the thread executing this method is interrupted while waiting.
     * @throws ExecutionException if any submitted task throws an exception during execution.
     */
    public String runTasks() throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int taskId = i;
            futures.add(executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                long threadId = Thread.currentThread().getId();
                return String.format("Task %d executed by thread %s (ID: %d)%n", taskId, threadName, threadId);
            }));
        }

        StringBuilder result = new StringBuilder("ExecutorService results:\n");
        for (Future<String> future : futures) {
            result.append(future.get());
        }

        return result.toString();
    }
}