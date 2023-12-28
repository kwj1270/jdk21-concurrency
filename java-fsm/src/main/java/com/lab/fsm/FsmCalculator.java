package com.lab.fsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class FsmCalculator {
    private final static Logger log = LoggerFactory.getLogger(FsmCalculator.class);


    public void calculate(final int initialValue) {
        this.calculate(initialValue, null);
    }

    public void calculate(final int initialValue, final Shared shared) {
        var current = shared;
        if (Objects.isNull(current)) {
            current = new Shared(initialValue, 0);
        }
        current = switch (current.label) {
            case 0 -> current.firstStep(current.result);
            case 1 -> current.secondStep(current.result);
            case 2 -> current.thirdStep(current.result);
            case 3 -> {
                log.info("shared result = {}, label = {}", shared.result, shared.label);
                yield null;
            }
            default -> throw new IllegalStateException("Unexpected value: " + initialValue);
        };
        if (Objects.isNull(current)) {
            return;
        }
        calculate(initialValue, current);
    }

    class Shared {
        private final Integer result;
        private final Integer label;

        public Shared(final Integer result, final Integer label) {
            this.result = result;
            this.label = label;
        }

        public Shared firstStep(final int result) {
            return new Shared(result, 1);
        }

        public Shared secondStep(final int result) {
            return new Shared(Math.addExact(result, 1), 2);
        }

        public Shared thirdStep(final int result) {
            return new Shared(Math.multiplyExact(result, 2), 3);
        }
    }
}
