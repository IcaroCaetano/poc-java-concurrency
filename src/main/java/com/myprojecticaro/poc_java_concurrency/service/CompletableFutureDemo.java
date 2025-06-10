package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Demonstrates the use of CompletableFuture for asynchronous programming.
 * This class runs two asynchronous tasks and combines their results.
 */
@Service
public class CompletableFutureDemo {

    /**
     * Runs two asynchronous tasks and combines their results.
     *
     * @return A combined result string from both tasks.
     * @throws ExecutionException if the computation threw an exception.
     * @throws InterruptedException if the current thread was interrupted while waiting.
     */
    public String run() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Result from task 1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Result from task 2");

        CompletableFuture<String> combined = future1.thenCombine(future2, (r1, r2) -> r1 + " + " + r2);

        return "CompletableFuture Example:\n" + combined.get();
    }
}