package com.lab.cps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class CpsCalculator {
    private final static Logger log = LoggerFactory.getLogger(CpsCalculator.class);

    public void calculate(final int initialValue, Consumer<Integer> continuation) {
        initialize(initialValue,
                initial -> addOne(initial,
                        added -> multiPlyTwo(added,
                                multiPlied -> continuation.accept(multiPlied)
                        )
                )
        );
    }

    private void initialize(final int value, Consumer<Integer> continuation) {
        log.info("initialize value = {}", value);
        continuation.accept(value);
    }

    private void addOne(final int value, Consumer<Integer> continuation) {
        log.info("addOne value = {}", value);
        continuation.accept(Math.addExact(value, 1));
    }

    private void multiPlyTwo(final int value, Consumer<Integer> continuation) {
        log.info("multiPlyTwo value = {}", value);
        continuation.accept(Math.multiplyExact(value, 2));
    }
}
