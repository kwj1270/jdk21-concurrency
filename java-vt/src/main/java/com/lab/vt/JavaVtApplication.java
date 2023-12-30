package com.lab.vt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class JavaVtApplication {

    public static void main(String[] args) {
        try(final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 2; i++) {
                executorService.execute(() -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("[%s] hello World".formatted(Thread.currentThread()));
                });
            }
        }
        
        System.out.println("[%s] hello World2".formatted(Thread.currentThread()));
    }

}
