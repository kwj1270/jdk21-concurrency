package com.lab.cps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaCpsApplication {
    private final static Logger log = LoggerFactory.getLogger(JavaCpsApplication.class);

    public static void main(String[] args) {
        final NormalSeparatedCalculator normalSeparatedCalculator = new NormalSeparatedCalculator();
        normalSeparatedCalculator.calculate(5);

        final CpsCalculator cpsCalculator = new CpsCalculator();
        cpsCalculator.calculate(5, result -> log.info("result = {}", result));

        final JavaContinuation javaContinuation = new JavaContinuation();
        javaContinuation.resumeWith(new Result<>(10));
        javaContinuation.resumeWith(new Result<>(10));
        javaContinuation.resumeWith(new Result<>(new RuntimeException("hell world")));
    }
}
