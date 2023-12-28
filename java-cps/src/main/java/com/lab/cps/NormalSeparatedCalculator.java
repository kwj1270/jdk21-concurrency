package com.lab.cps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NormalSeparatedCalculator {
    private final static Logger log = LoggerFactory.getLogger(NormalSeparatedCalculator.class);

    public void calculate(final int initialValue) {
        int result = initialize(initialValue);
        result = addOne(result);
        result = multiPlyTwo(result);
        log.info("Result {}", result);
    }


    private int initialize(final int initialValue) {
        return initialValue;
    }

    private int addOne(final int result) {
        return Math.addExact(result, 1);
    }

    private int multiPlyTwo(final int result) {
        return Math.multiplyExact(result, 2);
    }
}
