package com.myprojecticaro.poc_java_concurrency.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class CallableFutureDemo {

    public String runCallable() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(500);
            return "Callable result from thread " + Thread.currentThread().getName();
        };

        Future<String> future = executor.submit(task);
        String result = future.get();
        executor.shutdown();
        return result;
    }
}