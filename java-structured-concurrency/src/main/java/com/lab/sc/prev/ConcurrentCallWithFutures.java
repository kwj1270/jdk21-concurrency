package com.lab.sc.prev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentCallWithFutures {
    public static void main(String[] args) throws Exception {
        try (final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            final long start = System.currentTimeMillis();
            final Future<String> dbFuture = executorService.submit(ConcurrentCallWithFutures::dbCall);
            final Future<String> restFuture = executorService.submit(ConcurrentCallWithFutures::restCall);

            final String result = String.format("[%s, %s]", dbFuture.get(), restFuture.get());
            final long end = System.currentTimeMillis();
            System.out.println("time = " + (end - start));
            System.out.println(result);
        }
    }

    public static String dbCall() throws InterruptedException {
        Thread.sleep(100L);
        return "dbResult";
    }

    public static String restCall() throws InterruptedException {
        Thread.sleep(200L);
        return "restResult";
    }
}
