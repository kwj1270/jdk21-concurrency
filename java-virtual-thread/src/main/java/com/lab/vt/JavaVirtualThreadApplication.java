package com.lab.vt;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class JavaVirtualThreadApplication {

    private static final JavaLangAccess JLA = SharedSecrets.getJavaLangAccess();
    private static final Logger log = LoggerFactory.getLogger(JavaVirtualThreadApplication.class);
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static void main(String[] args) {
        final AtomicLong atomicLong = new AtomicLong(1);
        try (final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    try {
                        final long andIncrement = atomicLong.getAndIncrement();
                        final Thread currentCarrierThread = JLA.currentCarrierThread();
                        log.info("[Task-{}. {}]", andIncrement, Thread.currentThread());
                        log.info("[Task-{}. {}]", andIncrement, currentCarrierThread);
                        Thread.sleep(1000);
                        final Thread currentCarrierThread2 = JLA.currentCarrierThread();
                        log.info("[Task-{}. {}]", andIncrement, Thread.currentThread());
                        log.info("[Task-{}. {}]", andIncrement, currentCarrierThread2);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            log.info("end");
        }
    }
}