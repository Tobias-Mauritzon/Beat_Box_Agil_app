package model;

import java.util.List;

public class CustomParametersModel {
    private static final String ILLEGAL_STATE_ERROR_MSG = "Class was uninitialized. Call updateModel at least once before use.";

    private List<Operator> operators;
    private int[] range;
    private int termAmount;
    private boolean timed;

    private boolean initialized;

    public CustomParametersModel() {
        initialized = false;
    }

    public void updateModel(List<Operator> operators, int[] range, int termAmount, boolean timed) {
        if (!initialized) {
            initialized = true;
        }
        this.operators = operators;
        this.range = range;
        this.termAmount = termAmount;
        this.timed = timed;
    }

    public void generateProblemParameters() {
        checkInitialization();
        ProblemParameters p = new ProblemParameters(operators, range, termAmount, timed);
        debugPrint(p);
    }

    public boolean operatorsIsValid() {
        checkInitialization();
        return (operators.size() > 0);
    }

    public boolean rangeIsValid() {
        checkInitialization();
        return (range[0] <= range[1]);
    }

    private void checkInitialization() {
        if (!initialized) {
            throw new IllegalStateException(ILLEGAL_STATE_ERROR_MSG);
        }
    }

    private void debugPrint(ProblemParameters p) {
        System.out.println("-----------------------------------------------");
        for (Operator o : p.getOperators()) {
            System.out.println(o.toString());
        }
        System.out.println(p.getRange()[0]);
        System.out.println(p.getRange()[1]);
        System.out.println(p.getTermAmount());
        System.out.println(p.getTimed());
    }
}
