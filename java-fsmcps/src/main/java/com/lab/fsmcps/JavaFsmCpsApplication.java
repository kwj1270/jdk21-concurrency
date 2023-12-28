package com.lab.fsmcps;

public class JavaFsmCpsApplication {

    public static void main(String[] args) {
        final FsmCpsCalculator fsmCpsCalculator = new FsmCpsCalculator();
        fsmCpsCalculator.calculate(5, new FinalContinuation());
    }
}
