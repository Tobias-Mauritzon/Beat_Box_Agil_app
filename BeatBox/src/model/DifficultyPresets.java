package model;

//Varför har du importerat denna?
//import view.DifficultyGUI;

import java.util.ArrayList;
import java.util.List;

/***
 * Class for handling difficulty on various operators.
 *
 * @version 1.0
 * @since 2020-10-11
 * @author Andreas Palmqvist
 * @author Seif Bourogaa
 */
public class DifficultyPresets {
    private Operator operator;
    private Delegate delegate;

    /**
     * Constructor for DifficultyPresets.
     *
     * @param operator the Operator we are using.
     *
     * @see DifficultyGUI
     * @see controller.DifficultyPresetsController
     */
    public DifficultyPresets() {

        this.operator = Operator.ADD;
    }

    /**
     * Method to generate problems of easy difficulty.
     *
     * @param op Operator we are generating problems with.
     */
    public void easyProblem() {
        List<Operator> operators = new ArrayList<>();
        operators.add(operator);

        int[] range = new int[]{1, 10};
        int termAmount = 2;
        delegate.transmitProblemParameters(new ProblemParameters(operators, range, termAmount, false));
    }

    /**
     * Method to generate problems of medium difficulty.
     *
     * @param op Operator we are generating problems with.
     */
    public void mediumProblem() {
        List<Operator> operators = new ArrayList<>();
        operators.add(operator);

        int[] range = new int[]{10, 20};
        int termAmount = 3;
        delegate.transmitProblemParameters(new ProblemParameters(operators, range, termAmount, false));
    }

    /**
     * Method to generate problems of hard difficulty.
     *
     * @param op Operator we are generating problems with.
     */
    public void hardProblem() {
        List<Operator> operators = new ArrayList<>();
        operators.add(operator);

        int[] range = new int[]{20, 30};
        int termAmount = 4;
        delegate.transmitProblemParameters(new ProblemParameters(operators, range, termAmount, false));
    }

    /** Setter for delegate.
     *
     * @param delegate   The delegate to set.
     */
    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    /** Setter for operator.
     *
     * @param operator   The operator to set.
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * A delegate for transmitting problem parameters out of this class.
     */
    public interface Delegate {
        void transmitProblemParameters(ProblemParameters problemParameters);
    }
}
