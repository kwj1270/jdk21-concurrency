package com.lab.sc.clients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.StructuredTaskScope;

public class AverageWeatherTaskScope extends StructuredTaskScope<Object> {
    private final List<Subtask<?>> subtaskList = Collections.synchronizedList(new ArrayList<>());

    @Override
    protected void handleComplete(final Subtask<?> subtask) {
        if(subtask.state() == Subtask.State.SUCCESS) {
            add(subtask);
        }
    }

    private void add(Subtask<?> subtask) {
        int numSuccessFul = 0;
        synchronized (subtaskList) {
            subtaskList.add(subtask);
            numSuccessFul = subtaskList.size();
        }
        if(numSuccessFul == 2) {
            this.shutdown();
        }
    }

    public AverageWeatherTaskScope join() throws InterruptedException {
        super.join();
        return this;
    }

    public Object response() {
        super.ensureOwnerAndJoined();
        if(subtaskList.size() != 2) {
            throw new RuntimeException("Atleast two subtasks must be successful");
        }
        final Object o1 = subtaskList.get(1).get();
        final Object o2 = subtaskList.get(1).get();
        return o1;
    }
}
