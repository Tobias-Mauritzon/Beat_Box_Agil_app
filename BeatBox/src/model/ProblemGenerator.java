package model;
/**
 * Interface for what a math generator needs
 * @author Joachim
 */

public interface ProblemGenerator {

	public static String[] generate() {
		return new String[] {"1 + 1","2"};
	}
}
