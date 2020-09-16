package model;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Tobias & Joachim
 * Class used to generate random expression depending on different modifiers
 */

enum Operator{
	ADD, SUB, DIV, MUL, POW;
}

public class calculator {
		
	private LinkedList<String> uniqueness; 
	public calculator() {
		this.uniqueness = new LinkedList<String>();
	}
	
	/**
	 * guarantees the generated number has not appeared in the last 10 numbers
	 * 
	 * @param numbers the number of numbers in the expression
	 * @param numberSize allowed number size {small, big}
	 * @param modifiers if array contains 1 = addition, 2 = subtraction, 3 = division, 4 = multiplication, 
	 * 			5 = power of 2. array can be any size
	 * @return String array. array[0] = expression, array[1] = awnser
	 */
	public String[] uniqueGeneration(int numbers, int[] numberSize, Operator[] modifiers) {
		boolean unique = false;
		String[] returnString = new String[2];
	
		while(!unique) {
			if(uniqueness.isEmpty()) unique = true;
			returnString = generate(numbers, numberSize, new String[2], modifiers);
			for(String s : uniqueness) {
				if(s.equals(returnString[0])) {
					unique = false;
					break;
				}
				unique = true;
			}
		}
		
		if(uniqueness.size() >= 10) {
			uniqueness.removeFirst();
		}
		uniqueness.addLast(returnString[0]);
		
		return returnString;
	}
	
	
	public static void main(String[] args) 
	{
		//number size
		int[] size = {1,10};
		// allowed modifiers
		Operator[] mod = {Operator.ADD, Operator.SUB};
		String[] s = generate(3, size,new String[2], mod);
		
		System.out.println(s[0]);
		System.out.println(s[1]);
	}
	
	/**
	 * Generates a expression and and answer for that expression
	 * 
	 * @param numbers the number of numbers in the expression
	 * @param numberSize allowed number size {small, big}
	 * @param expressionAndAnswer new String[2]
	 * @param modifiers array of enum Operators that can be used to generate expressions 
	 * @return String array. array[0] = expression, array[1] = answer
	 */
	public static String[] generate(int numbers, int[] numberSize, String[] expressionAndAnswer, Operator[] modifiers) {
		Random rand = new Random();

		return gen(numbers, numberSize, modifiers, rand);
	}
	
	/**
	 * Recursively generates mathematical expressions and calculates their value
	 * @param numbers the number of numbers in the expression
	 * @param numberRange allowed number range {small, big}
	 * @param modifiers array of enum Operators that can be used to generate expressions
	 * @param rand random number generator to be used
	 * @return String array. array[0] = expression, array[1] = answer
	 */
	private static String[] gen(int numbers, int[] numberRange, Operator[] modifiers, Random rand) {
		String[] returnVal = new String[2];
		if(numbers == 1) {
			int number =  rand.nextInt((numberRange[1] - (numberRange[0]))+1) + (numberRange[0]);
			returnVal[0] = "" + number;
			returnVal[1] = "" + number;
		}
		else {
			int right = numbers/2;
			int left = numbers - right;
			String[] rightRet = gen(right, numberRange, modifiers, rand); 
			String[] leftRet = gen(left, numberRange, modifiers, rand); 
			
			switch(modifiers[rand.nextInt(modifiers.length)]) {
			case ADD:
				returnVal[0] = "(" + rightRet[0] + " + " + leftRet[0] + ")";
				returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) + Double.parseDouble(leftRet[1]));
				break;
			case SUB:
				returnVal[0] = "(" + rightRet[0] + " - " + leftRet[0] + ")";
				returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) - Double.parseDouble(leftRet[1]));
				break;
			case DIV:
				returnVal[0] = "(" + rightRet[0] + " / " + leftRet[0] + ")";
				returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) / Double.parseDouble(leftRet[1]));
				break;
			case MUL:
				returnVal[0] = "(" + rightRet[0] + " * " + leftRet[0] + ")";
				returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) * Double.parseDouble(leftRet[1]));
				break;
			case POW:
				returnVal[0] = "(" + rightRet[0] + " ^ " + leftRet[0] + ")";
				returnVal[1] = Double.toString(Math.pow(Double.parseDouble(rightRet[1]), Double.parseDouble(leftRet[1])));
				break;
			}
		}				
		return returnVal;
	}
}
