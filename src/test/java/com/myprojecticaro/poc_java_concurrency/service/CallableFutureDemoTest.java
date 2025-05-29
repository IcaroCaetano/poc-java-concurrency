package com.myprojecticaro.poc_java_concurrency.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CallableFutureDemoTest {

    @Test
    void testRunCallable_shouldPrintResult() throws Exception {
        CallableFutureDemo demo = new CallableFutureDemo();
        String result = demo.runCallable();

        System.out.println("Resultado do Callable: " + result);

        assertThat(result).isNotNull();
        assertThat(result).startsWith("Callable result from thread");
        assertThat(result).matches("Callable result from thread .*");
    }
}
