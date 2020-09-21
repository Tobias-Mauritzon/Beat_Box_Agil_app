package model;
/**
 * Interface for what a math generator needs
 * @author Joachim Antfolk
 * @version 1.0
 * @since 2020-09-17
 */

public interface ProblemGenerator {

	public static String[] generate() {
		return new String[] {"1 + 2","3"}; //Template for String array structure
	}
}
