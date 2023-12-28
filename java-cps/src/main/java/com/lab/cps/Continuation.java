package com.lab.cps;

public interface Continuation<T> {
    CoroutineContext context();

    void resumeWith(Result<T> result);
}

class Result<T> {
    T t;

    public Result(final T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Result{" +
                "t=" + t +
                '}';
    }
}

interface CoroutineContext {

}

class EmptyCoroutineContext implements CoroutineContext {

}
