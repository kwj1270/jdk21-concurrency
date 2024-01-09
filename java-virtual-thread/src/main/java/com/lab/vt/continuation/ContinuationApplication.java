package com.lab.vt.continuation;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ContinuationApplication {
    private static final Logger log = LoggerFactory.getLogger(ContinuationApplication.class);
    private static final ContinuationScope SCOPE1 = new ContinuationScope("scope1");

    public static void main(String[] args) throws Exception {
        log.info("> Start Main");

        final var continuation = new Continuation(SCOPE1, new IOProcessor());
        while (!continuation.isDone()) {
            log.info("$ ContinuationApplication.main loop start $");
            continuation.run();
            log.info("$ ContinuationApplication.main loop end $");
            Thread.sleep(Duration.ofSeconds(3));
        }
        log.info("< End Main");
    }
}
