package model;

import java.util.LinkedList;
import java.util.Random;

/**
 * Class used to generate random expression depending on different modifiers
 * @author Tobias & Joachim
 * @version 1.0
 * @since 2020-09-17
 */

enum Operator{
	ADD, SUB, DIV, MUL, EXP;
}

public class NumberGenerator {
		
	private LinkedList<String> uniqueness; 
	
	public NumberGenerator() {
		this.uniqueness = new LinkedList<String>();
	}
	
	/**
	 * Used to generate unique problems if there are more than ten possible outcomes with current parameters.
	 * 		Otherwise ignore uniqueness.
	 * @param numbers the number of numbers in the expression
	 * @param numberSize allowed number size {small, big}
	 * @param modifiers array of enum Operators that can be used to generate expressions 
	 * @return String array. array[0] = expression, array[1] = awnser
	 */
	public String[] uniqueGeneration(int numbers, int[] numberRange, Operator[] modifiers) {
		boolean unique = false;
		String[] returnString = new String[2];
		try {
			if(10 < (modifiers.length * Math.pow((numberRange[1] - numberRange[0] + 1), numbers))) {
				while(!unique) {
					if(uniqueness.isEmpty()) unique = true;
					returnString = generate(numbers, numberRange, modifiers);
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
				
			}else {
				return generate(numbers, numberRange, modifiers);
			}
		}catch(IllegalArgumentException e) {
			throw e;
		}	
	}
	
	/**
	 * testing
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//number size
		int[] size = {0,100};
		// allowed modifiers
		Operator[] mod = {Operator.ADD};
		String[] s = new String[2];
		try {
			s = generate(3, size, mod);
			System.out.println(s[0]);
			System.out.println(s[1]);
		}catch(Exception e) {
			e.printStackTrace();
		}	
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
	public static String[] generate(int numbers, int[] numberSize, Operator[] modifiers) {
		Random rand = new Random();
		
		if(numbers < 2) {
			throw new IllegalArgumentException("Number of terms must be grater than 2!");
		}
		
		if(numberSize[0] > numberSize[1]) {
			throw new IllegalArgumentException("The real interval has to in the format [smaller, bigger]!");
		}

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
				boolean div = true;
				while(div) {						
					if(Double.parseDouble(leftRet[1]) != 0.0) {//Division by zero 
						returnVal[0] = "(" + rightRet[0] + " / " + leftRet[0] + ")";
						returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) / Double.parseDouble(leftRet[1]));
						div = false;
					}else {
						leftRet = gen(left, numberRange, modifiers, rand); 
					}
				}	
				break;
			case MUL:
				returnVal[0] = "(" + rightRet[0] + " * " + leftRet[0] + ")";
				returnVal[1] = Double.toString(Double.parseDouble(rightRet[1]) * Double.parseDouble(leftRet[1]));
				break;
			case EXP:
				returnVal[0] = "(" + rightRet[0] + " ^ " + leftRet[0] + ")";
				returnVal[1] = Double.toString(Math.pow(Double.parseDouble(rightRet[1]), Double.parseDouble(leftRet[1])));
				break;
			}
		}				
		return returnVal;
	}
	
	public LinkedList<String> getList(){
		return uniqueness;
	}
}
