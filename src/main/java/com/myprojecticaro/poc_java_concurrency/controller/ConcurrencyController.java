package com.myprojecticaro.poc_java_concurrency.controller;


import com.myprojecticaro.poc_java_concurrency.service.CallableFutureDemo;
import com.myprojecticaro.poc_java_concurrency.service.ExecutorServiceDemo;
import com.myprojecticaro.poc_java_concurrency.service.LockAtomicDemo;
import org.springframework.web.bind.annotation.*;

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
    public String testExecutorService() {
        executorServiceDemo.runTasks();
        return "ExecutorService tasks submitted.";
    }

    @GetMapping("/callable")
    public String testCallableFuture() throws Exception {
        return callableFutureDemo.runCallable();
    }

    @GetMapping("/atomic")
    public String testAtomicAndLock() {
        return lockAtomicDemo.demo();
    }
}