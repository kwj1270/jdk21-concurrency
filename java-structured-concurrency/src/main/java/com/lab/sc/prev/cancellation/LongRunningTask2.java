package com.lab.sc.prev.cancellation;

import com.lab.sc.prev.TaskResponse;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LongRunningTask2 implements Callable<TaskResponse> {

    private final String name;
    private final int time;
    private final String output;
    private final Boolean fail;

    public LongRunningTask2(String name, int time, String output, Boolean fail) {
        this.name = name;
        this.time = time;
        this.output = output;
        this.fail = fail;
    }

    @Override
    public TaskResponse call() throws Exception {
        print("Running Task is Started");
        Thread.sleep(Duration.ofSeconds(this.time));
        if(fail) {
            throwExceptionOnFailure();
        }
        print("Running Task is Completed");
        return new TaskResponse(this.output);
    }

    /***
     * @param args
     * @throws Exception
     * @description 1초만에 종료가 되는 것을 알 수 있음 -> cancel 시키는 법
     */
    public static void main(String[] args) throws Exception {
        final long start = System.currentTimeMillis();
        System.out.println("> Main Start");
        final LongRunningTask2 longRunningTask = new LongRunningTask2(
                "woozi", 10, "task-result", false
        );
        try (final ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            final Future<TaskResponse> taskResponseFuture = executorService.submit(longRunningTask);
            Thread.sleep(Duration.ofSeconds(5));
            taskResponseFuture.cancel(true);
        }
        System.out.println("< Main End");
        final long end = System.currentTimeMillis();
        System.out.println("time = " + (end - start));
    }

    private void print(String message) {
        System.out.printf("> %s : %s\n", name, message);
    }

    private void throwExceptionOnFailure() {
        print("Failed");
        throw new RuntimeException(name + ": Failed");
    }

    private void throwInterruptExceptionOnFailure() {
        print("Interrupt");
        throw new RuntimeException(name + ": Interrupt");
    }
}
