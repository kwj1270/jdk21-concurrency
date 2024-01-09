package com.lab.sc.prev;

import com.lab.sc.prev.cancellation.LongRunningTask3;

import java.util.concurrent.StructuredTaskScope;

public class CompleteAllTasks {
    public static void main(String[] args) {
        try (final var scope = new StructuredTaskScope<TaskResponse>()) {
            final var wooziTask = new LongRunningTask3("woozi", 3, "100$", true);
            final var woozaeTask = new LongRunningTask3("woozae", 10, "110$", false);

            final var wooziTaskSubtask = scope.fork(wooziTask);
            final var woozaeTaskSubtask = scope.fork(woozaeTask);

            scope.join();
            final var wooziState = wooziTaskSubtask.state();
            final var woozaeState = woozaeTaskSubtask.state();

            if (wooziState == StructuredTaskScope.Subtask.State.SUCCESS) {
                System.out.println(wooziTaskSubtask.get());
            } else if (wooziState == StructuredTaskScope.Subtask.State.FAILED) {
                System.out.println(wooziTaskSubtask.exception());
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
