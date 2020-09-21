package model;


/***
 * Stores the answer of the current problem and checks if the user inputed answer is the same
 * 
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class grading {

	String answer;
	
	/***
	 * Sets the value of the answer string to the parameters value
	 * @param a the new value for answer
	 */
	public void setAnswer(String a) {
		answer = a;
		System.out.println("Answer set;" + a);
	}
	
	/***
	 * Compares userInput with answer and returns false if they are the same and false otherwsie
	 * @param userInput the userInputed String
	 */
	public Boolean grade(String userInput) {
		return answer.equals(userInput);
	}
}
