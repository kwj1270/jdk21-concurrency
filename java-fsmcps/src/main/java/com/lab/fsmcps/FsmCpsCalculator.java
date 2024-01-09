package com.lab.fsmcps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class FsmCpsCalculator {
    private final static AtomicInteger atomicInteger = new AtomicInteger();
    private final static Logger log = LoggerFactory.getLogger(FsmCpsCalculator.class);

    public void calculate(final int initialValue, final CustomContinuations<Integer> continuation) {
        final CustomContinuation<Integer> currentContinuation = crateContinuation(continuation);
        switch (currentContinuation.getLabel()) {
            case 0 -> currentContinuation.firstStep(initialValue, currentContinuation);
            case 1 -> currentContinuation.secondStep(currentContinuation);
            case 2 -> currentContinuation.thirdStep(currentContinuation);
            case 3 -> currentContinuation.complete();
        }
        log.info("loop count = {}", atomicInteger.incrementAndGet());
    }

    private CustomContinuation<Integer> crateContinuation(final CustomContinuations<Integer> continuation) {
        if (continuation instanceof CustomContinuations) {
            return (CustomContinuation) continuation;
        }
        return new CustomContinuation(this, continuation);
    }
}
