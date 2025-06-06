package com.myprojecticaro.poc_java_concurrency.controller;


import com.myprojecticaro.poc_java_concurrency.service.CallableFutureDemo;
import com.myprojecticaro.poc_java_concurrency.service.ExecutorServiceDemo;
import com.myprojecticaro.poc_java_concurrency.service.LockAtomicDemo;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/concurrency")
public class ConcurrencyController {

    private final ExecutorServiceDemo executorServiceDemo;
    private final CallableFutureDemo callableFutureDemo;
    private final LockAtomicDemo lockAtomicDemo;

    public ConcurrencyController(
            ExecutorServiceDemo executorServiceDemo,
            CallableFutureDemo callableFutureDemo,
            LockAtomicDemo lockAtomicDemo) {
        this.executorServiceDemo = executorServiceDemo;
        this.callableFutureDemo = callableFutureDemo;
        this.lockAtomicDemo = lockAtomicDemo;
    }

    @GetMapping("/executor")
    public String testExecutorService() throws Exception {
        return executorServiceDemo.runTasks();
    }

    @GetMapping("/callable")
    public String testCallableFuture() throws Exception {
        return callableFutureDemo.runCallable();
    }

    @GetMapping("/atomic")
    public String testAtomicAndLock() {
        return lockAtomicDemo.demo();
    }

    @GetMapping("/atomic-multithread")
    public String testAtomicAndLockMultithread(@RequestParam(defaultValue = "5") int threadCount) {
        try {
            return lockAtomicDemo.demoMultithread(threadCount);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            return "Error during concurrent execution: " + e.getMessage();
        }
    }
}