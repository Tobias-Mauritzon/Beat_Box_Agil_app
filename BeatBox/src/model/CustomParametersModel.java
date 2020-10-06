package model;

import java.util.List;


/**
* Class for custom problem parameters. 
*
* @see ProblemParameters class
* @see Operator class. 
* 
* @author Seif Eddine Bourogaa
* @author Andreas Palmqvist 
*/
public class CustomParametersModel {
    private static final String ILLEGAL_STATE_ERROR_MSG = "Class was uninitialized. Call updateModel at least once before use.";

    /** Data structures used to hold all the problem parameters.
	*
	* @see operators   List holding all the seeked Operators.
	* @see range 	   Array holding the range of numbers to create.
	* @see termAmount  variable holding the number of terms in a problem.
	* @see timed       variable holding whether or not the session should be timed. 
	*/
    private List<Operator> operators;
    private int[] range;
    private int termAmount;
    private boolean timed;
    private boolean initialized;
    private Delegate delegate;

    public CustomParametersModel() {
        initialized = false;
    }

    /** Setter for delegate.
     *
     * @param delegate   The delegate to set.
     */
    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

	/** Method to update the model. Must be called at least once.
	*
	* @param operators   List holding all the seeked Operators.
	* @param range 	   Array holding the range of numbers to create.
	* @param termAmount  variable holding the number of terms in a problem.
	* @param timed       variable holding whether or not the session should be timed. 
	*/
    public void updateModel(List<Operator> operators, int[] range, int termAmount, boolean timed) {
        if (!initialized) {
            initialized = true;
        }
        this.operators = operators;
        this.range = range;
        this.termAmount = termAmount;
        this.timed = timed;
    }

    /**
    * Method to generate Problem parameters based on the current state of this model. This ProblemParameters instance
    * is then transmitted out of this class.
    *
    * @see checkInitialization()
    */
    public void generateProblemParameters() {
        checkInitialization();
        delegate.transmitProblemParameters(new ProblemParameters(operators, range, termAmount, timed));
    }

     /**
    * Method to check if the Operators are valid. 
    *
    * @see checkInitialization()
    * @see Operator class. 
    */
    public boolean operatorsIsValid() {
        checkInitialization();
        return (operators.size() > 0);
    }

  	/**
    * Method to check if the range is valid. 
    *
    * @see checkInitialization()
    */
    public boolean rangeIsValid() {
        checkInitialization();
        return (range[0] < range[1]);
    }

    /**
    * Method to check if it has been initialized. 
    */
    private void checkInitialization() {
        if (!initialized) {
            throw new IllegalStateException(ILLEGAL_STATE_ERROR_MSG);
        }
    }

    /**
     * A delegate for transmitting problem parameters out of this class.
     */
    public interface Delegate {
        void transmitProblemParameters(ProblemParameters p);
    }
}
