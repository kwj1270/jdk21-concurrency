package com.lab.fsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class FsmExample {
    private final static Logger log = LoggerFactory.getLogger(FsmExample.class);

    public void execute() {
        execute(Integer.valueOf(0));
    }

    private void execute(final Integer label) {
        final Integer nextLabel = switch (label) {
            case 0 -> {
                log.info("initial");
                yield Integer.valueOf(1);
            }
            case 1 -> {
                log.info("State 1");
                yield Integer.valueOf(2);
            }
            case 2 -> {
                log.info("State 2");
                yield Integer.valueOf(3);
            }
            case 3 -> {
                log.info("end");
                yield null;
            }
            default -> throw new IllegalStateException("Unexpected value: " + label);
        };
        if (Objects.nonNull(nextLabel)) {
            this.execute(nextLabel);
        }
    }
}
