package model;

import model.ProblemParameters;

import java.util.ArrayList;
import java.util.List;

public class CustomParametersModel {
    private List<Operator> operators;
    private int[] range;
    private int termAmount;
    private boolean timed;

    private boolean initialized;

    public CustomParametersModel() {
        initialized = false;
    }

    public void initModel(List<Operator> operators, int[] range, int termAmount, boolean timed) {
        if (!initialized) {
            this.operators = operators;
            this.range = range;
            this.termAmount = termAmount;
            this.timed = timed;
        }
    }

    private boolean rangeIsValid(int min, int max) {
        return (min <= max);
    }
}
