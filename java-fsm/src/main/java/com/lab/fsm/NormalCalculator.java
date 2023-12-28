package com.lab.fsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NormalCalculator {
    private final static Logger log = LoggerFactory.getLogger(NormalCalculator.class);

    public void calculate(final int initialValue) {
        var result = initialValue;
        result += 1;
        result *= 2;
        log.info("result = {}", result);
    }
}
