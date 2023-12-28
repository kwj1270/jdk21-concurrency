package com.lab.fsmcps;

import java.util.Objects;

public class Result<T> {
    T t;

    public Result(final T t) {
        this.t = t;
    }

    public T getOrThrow() {
        if (Objects.isNull(t)) {
            throw new RuntimeException("not found instance val");
        }
        return t;
    }

    @Override
    public String toString() {
        return "Result{" +
                "t=" + t +
                '}';
    }
}