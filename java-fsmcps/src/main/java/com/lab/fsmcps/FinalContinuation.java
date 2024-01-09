package com.lab.fsmcps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinalContinuation implements CustomContinuations<Integer> {
    private static final Logger log = LoggerFactory.getLogger(FinalContinuation.class);

    @Override
    public CoroutineContext context() {
        return new EmptyCoroutineContext();
    }

    @Override
    public void resumeWith(final Result<Integer> result) {
        log.info("Finish = {}", result);
    }
}

