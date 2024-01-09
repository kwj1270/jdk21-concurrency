package com.lab.cps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaCustomContinuation<T> implements CustomContinuation<T> {
    private static final Logger log = LoggerFactory.getLogger(JavaCustomContinuation.class);

    private boolean visited = false;

    @Override
    public CoroutineContext context() {
        return new EmptyCoroutineContext();
    }

    @Override
    public void resumeWith(final Result<T> result) {
        if (visited) {
            log.info(result.toString());
            return;
        }
        log.info("visit now");
        visited = true;
    }
}
