package com.lab.fsm;

public class FsmApplication {

    public static void main(String[] args) {
        final FsmExample fsmExample = new FsmExample();
        fsmExample.execute();

        final int initialValue = 5;
        final NormalCalculator normalCalculator = new NormalCalculator();
        normalCalculator.calculate(initialValue);

        final FsmCalculator fsmCalculator = new FsmCalculator();
        fsmCalculator.calculate(5);
    }
}