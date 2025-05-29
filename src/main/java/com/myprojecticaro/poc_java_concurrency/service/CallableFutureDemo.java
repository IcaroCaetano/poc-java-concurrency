package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Service that demonstrates the use of Java Concurrency with {@link Callable} and {@link Future}.
 * <p>
 * This example creates a single-threaded executor, submits a task that returns a result, and waits for its completion.
 * </p>
 *
 * <p>
 * The task simulates some processing by sleeping for 500 milliseconds and returns a string including
 * the name of the executing thread.
 * </p>
 *
 * <p>
 * It showcases the basic usage of:
 * <ul>
 *   <li>{@link Callable} - to define a task that returns a result</li>
 *   <li>{@link Future} - to retrieve the result of an asynchronous computation</li>
 *   <li>{@link ExecutorService} - to manage thread execution</li>
 * </ul>
 * </p>
 *
 * @author Icaro Caetano
 */
@Service
public class CallableFutureDemo {

    /**
     * Runs a {@link Callable} task asynchronously using an {@link ExecutorService}.
     *
     * @return the result returned by the {@link Callable} task
     * @throws Exception if interrupted while waiting or during execution
     */
    public String runCallable() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(5000);
            String threadName = Thread.currentThread().getName();
            System.out.println("Result of Callable: " + threadName);
            return "Callable result from thread " + threadName;
        };

        Future<String> future = executor.submit(task);
        String result = future.get();
        executor.shutdown();
        return result;
    }
}