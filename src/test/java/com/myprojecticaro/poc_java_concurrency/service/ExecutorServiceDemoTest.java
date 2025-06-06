package com.myprojecticaro.poc_java_concurrency.service;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorServiceDemoTest {

    @Test
    void testRunTasks_returnsExpectedOutput() throws InterruptedException, ExecutionException {
        ExecutorServiceDemo demo = new ExecutorServiceDemo();

        String result = demo.runTasks();

        assertNotNull(result);
        assertTrue(result.contains("ExecutorService results:"), "Result should contain the header");
        for (int i = 0; i < 5; i++) {
            assertTrue(result.contains("Task " + i), "Result should contain Task " + i);
        }
        assertTrue(result.contains("thread"), "Result should mention thread name");
    }
}
