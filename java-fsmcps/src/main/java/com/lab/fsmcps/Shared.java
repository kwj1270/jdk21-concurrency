package com.lab.fsmcps;

public class Shared {
    private Integer result;
    private Integer label;

    public Shared(final Integer result, final Integer label) {
        this.result = result;
        this.label = label;
    }

    public Shared firstStep() {
        return new Shared(this.result, 1);
    }

    public Shared secondStep() {
        return new Shared(Math.addExact(this.result, 1), 2);
    }

    public Shared thirdStep() {
        return new Shared(Math.multiplyExact(this.result, 2), 3);
    }

    public Shared updateResult(final Integer result) {
        return new Shared(result, label);
    }

    public int getLabel() {
        return label;
    }

    public Integer getResult() {
        return result;
    }
}
