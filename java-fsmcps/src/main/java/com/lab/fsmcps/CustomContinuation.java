package com.lab.fsmcps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomContinuation<T> implements Continuation<Integer> {
    private static final Logger log = LoggerFactory.getLogger(CustomContinuation.class);

    private final FsmCpsCalculator fsmCpsCalculator;
    private final Continuation<Integer> continuation;
    private Integer resultObj;
    private Integer label;

    public CustomContinuation(final FsmCpsCalculator fsmCpsCalculator,
                              final Continuation<Integer> continuation) {
        this.fsmCpsCalculator = fsmCpsCalculator;
        this.continuation = continuation;
        this.label = 0;
    }

    public void firstStep(final Integer initialValue, Continuation<Integer> continuation) {
        label = 1;
        log.info("label update = {}", label);
        continuation.resume(initialValue);
    }

    public void secondStep(Continuation<Integer> continuation) {
        label = 2;
        log.info("label update = {}", label);
        continuation.resume(Math.addExact(resultObj, 1));
    }

    public void thirdStep(Continuation<Integer> continuation) {
        label = 3;
        log.info("label update = {}", label);
        continuation.resume(Math.multiplyExact(resultObj, 2));
    }

    public void complete() {
        continuation.resumeWith(new Result<>(resultObj));
    }

    public int getLabel() {
        return label;
    }

    @Override
    public CoroutineContext context() {
        return new EmptyCoroutineContext();
    }

    @Override
    public void resumeWith(final Result<Integer> result) {
        resultObj = result.getOrThrow();
        log.info("resultObj update = {}", resultObj);
        fsmCpsCalculator.calculate(resultObj, this);
    }
}
