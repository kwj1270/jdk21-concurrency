package com.lab.sc.prev.cancellation;

import com.lab.sc.prev.TaskResponse;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LongRunningTask implements Callable<TaskResponse> {

    @Override
    public TaskResponse call() throws Exception {
        Thread.sleep(Duration.ofSeconds(5));
        System.out.println("Running Task is Completed");
        return new TaskResponse("Done");
    }

    /***
     * @param args
     * @throws Exception
     * @description 1초만에 종료가 되는 것을 알 수 있음 -> cancel 시키는 법
     */
    public static void main(String[] args) throws Exception {
        final long start = System.currentTimeMillis();
        System.out.println("> Main Start");
        final LongRunningTask longRunningTask = new LongRunningTask();
        try (final ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            final Future<TaskResponse> taskResponseFuture = executorService.submit(longRunningTask);
            Thread.sleep(Duration.ofSeconds(1));
            taskResponseFuture.cancel(true);
        }
        System.out.println("< Main End");
        final long end = System.currentTimeMillis();
        System.out.println("time = " + (end - start));
    }
}
