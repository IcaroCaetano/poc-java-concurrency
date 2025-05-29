package com.myprojecticaro.poc_java_concurrency.service;

import org.junit.jupiter.api.Test;

public class ExecutorServiceDemoTest {

    @Test
    void testRunTasks_printsResults() throws InterruptedException {
        ExecutorServiceDemo demo = new ExecutorServiceDemo();

        System.out.println("Starting tasks...");
        demo.runTasks();

        Thread.sleep(1000);

        System.out.println("Tasks completed.");
    }
}
