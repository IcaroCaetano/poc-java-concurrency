package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ExecutorServiceDemo {
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void runTasks() {
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Running task " + taskId + " in thread " + Thread.currentThread().getName());
            });
        }
    }
}