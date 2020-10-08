package model;

import java.util.*;

/**
 * Class used to generate random expression depending on different modifiers
 * 
 * @author Tobias Mauritzon, Joachim Antfolk
 * @version 2.0
 * @since 2020-10-08
 */

public class NumberGenerator implements ProblemGenerator {
	private LinkedList<String> uniqueness;
	private ProblemParameters settings;
	private Random rand;

	public NumberGenerator() {
		uniqueness = new LinkedList<String>();
		settings = new ProblemParameters();
		rand = new Random();
	}

	/**
	 * Change the settings of the generator.
	 *
	 * @see ProblemParameters
	 * @param settings - the new settings to use in the generation.
	 */
	public void setSettings(ProblemParameters settings) {
		this.settings = settings;
	}

	/**
	 * Used to generate unique problems if there are more than ten possible outcomes
	 * with current settings. Otherwise ignore uniqueness.
	 *
	 * @see ProblemParameters
	 * @return String array. array[0] = expression, array[1] = answer
	 */
	public String[] uniqueGeneration() {
		boolean unique = false;
		String[] returnString = new String[2];

		// Get settings
		List<Operator> modifiers = settings.getOperators();
		int[] numberRange = settings.getRange();
		int terms = settings.getTermAmount();

		try {
			if (10 < (modifiers.size() * Math.pow((numberRange[1] - numberRange[0] + 1), terms))) {
				while (!unique) {
					if (uniqueness.isEmpty())
						unique = true;
					returnString = generate();
					for (String s : uniqueness) {
						if (s.equals(returnString[0])) {
							unique = false;
							break;
						}
						unique = true;
					}
				}
				if (uniqueness.size() >= 10) {
					uniqueness.removeFirst();
				}
				uniqueness.addLast(returnString[0]);

				return returnString;

			} else {
				return generate();
			}
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}

	/**
	 * Generates an expression and and answer for that expression based on the
	 * current settings.
	 *
	 * @see ProblemParameters
	 * @return String array. array[0] = expression, array[1] = answer
	 */
	public String[] generate() {
		// Get settings
		List<Operator> modifiers = settings.getOperators();
		int[] numberRange = settings.getRange();
		int terms = settings.getTermAmount();

		if (modifiers.contains(null)) {
			throw new NullPointerException("The operator array can not contain null values!");
		}

		if (numberRange.length != 2) {
			throw new IllegalArgumentException("The real interval has to be composed of two elements!");
		}

		if (terms < 2) {
			throw new IllegalArgumentException("Number of terms must be grater than 2!");
		}

		if (numberRange[0] >= numberRange[1]) {
			throw new IllegalArgumentException("The real interval has to in the format [smaller, bigger]!");
		}

		return gen(terms, numberRange, modifiers);
	}

	/**
	 * Recursively generates mathematical expressions and calculates their value.
	 * Only to be called by generate(). The expressions are in LaTex string format.
	 *
	 * @param numbers     the amount of terms in the expression
	 * @param numberRange allowed number range {min, max}
	 * @param modifiers   array of enum Operators that can be used to generate
	 *                    expressions
	 * @return String array. array[0] = expression, array[1] = answer
	 */
	private String[] gen(int numbers, int[] numberRange, List<Operator> modifiers) {
		String[] returnVal = new String[2];
		if (numbers == 1) {
			int number = rand.nextInt((numberRange[1] - (numberRange[0])) + 1) + (numberRange[0]);
			returnVal[0] = "" + number;
			returnVal[1] = "" + number;
		} else {
			int right = numbers / 2; // Number of number to generate in the right branch
			int left = numbers - right; // Number of number to generate in the left branch
			String[] rightRet = gen(right, numberRange, modifiers);
			String[] leftRet = gen(left, numberRange, modifiers);

			switch (modifiers.get(rand.nextInt(modifiers.size()))) {
			case ADD:
				returnVal[0] = rightRet[0] + " + " + leftRet[0]; // Combines the branches to one string with the Add
																	// modifier
				returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) + Double.parseDouble(leftRet[1])); // Calculates
																													// the
																													// final
																													// answer
				break;

			case SUB:
				if (leftRet[0].contains(" ")) {
					returnVal[0] = rightRet[0] + " - \\left(" + leftRet[0] + "\\right)"; // Nicer looking parentheses
				} else {
					returnVal[0] = rightRet[0] + " - " + leftRet[0];
				}
				returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) - Double.parseDouble(leftRet[1]));
				break;

			case DIV:
				boolean div = true;
				while (div) {
					if (Double.parseDouble(leftRet[1]) != 0.0) {// Division by zero
						returnVal[0] = String.format("\\frac{%s}{%s}", rightRet[0], leftRet[0]); // LaTex string format
																									// for division
						returnVal[1] = Double
								.toString(Double.parseDouble(rightRet[1]) / Double.parseDouble(leftRet[1]));
						div = false;
					} else {
						leftRet = gen(left, numberRange, modifiers); // if division by zero occur we generate again with
																		// the same number
					}
				}
				break;

			case MUL:
				if (leftRet[0].contains(" ")) {
					leftRet[0] = "\\left(" + leftRet[0] + "\\right)";
				}
				if (rightRet[0].contains(" ")) {
					rightRet[0] = "\\left(" + rightRet[0] + "\\right)";
				}
				returnVal[0] = rightRet[0] + " \\times " + leftRet[0]; // LaTex string format multiplication
				returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) * Double.parseDouble(leftRet[1]));
				break;

			case EXP:
				if (leftRet[0].contains(" ")) {
					leftRet[0] = "\\left(" + leftRet[0] + "\\right)";
				}
				if (rightRet[0].contains(" ")) {
					rightRet[0] = "\\left(" + rightRet[0] + "\\right)";
				}
				returnVal[0] = rightRet[0] + " ^ " + leftRet[0];
				returnVal[1] = Double
						.toString(Math.pow(Double.parseDouble(rightRet[1]), Double.parseDouble(leftRet[1])));
				break;
			}
		}
		return returnVal;
	}

	/**
	 * Returns the list of the latest ten uniquely generated problems
	 *
	 * @return List of math problems
	 */
	public LinkedList<String> getList() {
		return uniqueness;
	}
}
