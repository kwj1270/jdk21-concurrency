package com.lab.fsmcps;

public interface Continuation<T> {
    CoroutineContext context();

    void resumeWith(Result<T> result);

    default void resume(T value) {
        this.resumeWith(new Result<>(value));
    }
}

