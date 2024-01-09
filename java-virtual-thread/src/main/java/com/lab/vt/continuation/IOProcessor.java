package com.lab.vt.continuation;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOProcessor implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ContinuationApplication.class);
    private static final ContinuationScope SCOPE2 = new ContinuationScope("scope2");

    @Override
    public void run() {
        method();
    }

    private void method() {
        log.info(">> method : enter");
        final var continuation = new Continuation(SCOPE2, this::method1);
        while (!continuation.isDone()) {
            log.info("$$ IOProcessor.method loop start $$");
            continuation.run();
            log.info("$$ IOProcessor.method loop end $$");
        }
        log.info("<< method : exit");
    }

    private void method1() {
        log.info(">>> method1 : enter");
        int part = 0;
        part++;

        log.info("method1 : execute part " + part);
        Continuation.yield(SCOPE2);

        part++;
        log.info("method1 : execute part " + part);
        Continuation.yield(SCOPE2);

        part++;
        log.info("method1 : execute part " + part);
        Continuation.yield(SCOPE2);

        log.info("<<< method1 : exit");
    }
}
