package model;

import java.util.ArrayList;
import java.util.List;

public class DifficultyPresets {

    Operator operator;

    public DifficultyPresets(Operator operator) {

        this.operator = operator;

    }

    public void easyProblem(Operator op){

        List<Operator> operator = new ArrayList<>();
        operator.add(op);

        int[] range = {1, 10};
        int termAmount = 2;
        ProblemParameters pb = new ProblemParameters(operator, range, termAmount, false);
    }

    public void mediumProblem(Operator op){

        List<Operator> operator = new ArrayList<>();
        operator.add(op);

        int[] range = {20, 50};
        int termAmount = 3;
        ProblemParameters pb = new ProblemParameters(operator, range, termAmount, false);

    }

    public void hardProblem(Operator op)
    {
        List<Operator> operator = new ArrayList<>();
        operator.add(op);

        int[] range = {50, 100};
        int termAmount = 4;
        ProblemParameters pb = new ProblemParameters(operator, range, termAmount, false);
    }

}
