package model;


/***
 * Stores the answer of the current problem and checks if the user inputed answer is the same
 * 
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class Grading {

	/***
	 * A simple delegate to communicate the main class with less connection in the code
	 */
	public interface Delegate
	{
		public void setHistory(String p, String a, String u);
	}

	public Delegate delegate;
	private String answer;
	private String problem;


	/***
	 * Sets the value of the answer string to the parameters value
	 * @param a the new value for answer
	 */
	public void setAnswer(String a) {
		answer = a;
	}

	/***
	 * Sets the value of the problem string to the parameters value
	 * @param p the new value for problem
	 */
	public void setProblem(String p) { problem = p; }

	/***
	 * Compares userInput with answer and returns false if they are the same and false otherwsie
	 * @param userInput the userInputed String
	 */
	public Boolean grade(String userInput) {
		if(userInput.isEmpty()) {
			return false;
		}
		else {

			Double userDouble = Double.parseDouble(userInput);
			Double answerDouble = Double.parseDouble(answer);
			// Rounds to two decimals
			answerDouble = Math.round(answerDouble * 100.0) / 100.0;
			userDouble = Math.round(userDouble * 100.0) / 100.0;
			setHistory(problem, answer, userInput);
			return (answerDouble.equals(userDouble));
		}
	}

	/***
	 * Calls the function setHistory from the delegate if it exists with the same parameters.
	 * @param a the correct answer string for the problem
	 * @param p the the problem string
	 * @param u the user inputted answer string.
	 */
	public void setHistory(String p, String a, String u) {

		if(delegate != null) {
				delegate.setHistory(p,a,u);
		}
	}
}
