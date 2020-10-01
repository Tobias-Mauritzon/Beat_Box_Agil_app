import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Operator;
import model.ProblemParameters;

public class ProblemParameterTest {

    public static void main(String[] args) {

        List<Operator> operators = new ArrayList<>();
        operators.add(Operator.ADD);
        operators.add(Operator.MUL);
        int[] range = {0, 200};

        ProblemParameters problemParameters = new ProblemParameters(operators, range, 20, false);

        System.out.println(problemParameters.getOperators());
        System.out.println(Arrays.toString(problemParameters.getRange()));
        System.out.println(problemParameters.getTermAmount());
        System.out.println(problemParameters.getTimed());

    }
}
