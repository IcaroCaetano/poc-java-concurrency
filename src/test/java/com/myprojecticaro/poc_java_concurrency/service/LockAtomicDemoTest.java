package com.myprojecticaro.poc_java_concurrency.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LockAtomicDemoTest {

    @Test
    void testDemo_shouldReturnAtomicAndLockMessage() {
        LockAtomicDemo demoService = new LockAtomicDemo();
        
        String result = demoService.demo();
        System.out.println("Demo output:\n" + result);

        assertThat(result).isNotNull();
        assertThat(result).containsPattern("Atomic counter: \\d+");
        assertThat(result).contains("Locked section accessed by thread");
    }
}
