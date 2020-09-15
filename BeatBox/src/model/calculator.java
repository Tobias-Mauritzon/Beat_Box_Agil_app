package model;

import java.util.LinkedList;
import java.util.Random;

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
	public String[] uniqueGeneration(int numbers, int[] numberSize, int[] modifiers) {
		boolean unique = false;
		String[] returnString = new String[2];
		while(!unique) {
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
		int[] size = {1,100};
		// allowed modifiers
		int[] mod = {1,2,3,4,0};
		String[] s = generate(5, size,new String[2], mod);
		
		System.out.println(s[0]);
		System.out.println(s[1]);
	}
	
	/**
	 * Generates a expression and and answer for that expression
	 * 
	 * @param numbers the number of numbers in the expression
	 * @param numberSize allowed number size {small, big}
	 * @param expressionAndAwnser new String[2]
	 * @param modifiers if array contains 1 = addition, 2 = subtraction, 3 = division, 4 = multiplication, 
	 * 			5 = power of 2. array can be any size
	 * @return String array. array[0] = expression, array[1] = answer
	 */
	public static String[] generate(int numbers, int[] numberSize, String[] expressionAndAwnser, int[] modifiers) {
		Random rand = new Random();
		if(numbers > 0) {
			numbers--;
			
			// generates the next number for the expression
			int genNumber = rand.nextInt((numberSize[1] - (numberSize[0]))+1) + (numberSize[0]);
			double newAwnser;
			if(expressionAndAwnser[1] != null) {				
				
				boolean allowed = false;
				int whichMod = 0;
				
				while(!allowed) {
					// generates a number 1 - 4 randomly for modifier
					whichMod = rand.nextInt(5)+1;					
					for(int m : modifiers) {
						if(m == whichMod) {
							allowed = true;
						}
					}
				}
				
				if(whichMod == 1) {					
					expressionAndAwnser[0] = expressionAndAwnser[0]+" + "+genNumber;
					newAwnser = Double.parseDouble(expressionAndAwnser[1]) + genNumber;
					expressionAndAwnser[1] = Double.toString(newAwnser);					
				}else if(whichMod == 2){					
					expressionAndAwnser[0] = expressionAndAwnser[0]+" - "+genNumber;
					newAwnser = Double.parseDouble(expressionAndAwnser[1]) - genNumber;
					expressionAndAwnser[1] = Double.toString(newAwnser);					
				}else if(whichMod == 3){
					boolean run = true;
					while(run) {						
						try {
							expressionAndAwnser[0] = "("+expressionAndAwnser[0]+")/"+genNumber;
							newAwnser = Double.parseDouble(expressionAndAwnser[1]) / genNumber;
							expressionAndAwnser[1] = Double.toString(newAwnser);
							run = false;
						}catch(Exception e) {
							genNumber = rand.nextInt((numberSize[1] - (numberSize[0]))) + (numberSize[0]);
						}
					}															
				}else if(whichMod == 4){
					// the parentheses are a feature
					expressionAndAwnser[0] = "("+expressionAndAwnser[0]+") * "+genNumber;
					newAwnser = Double.parseDouble(expressionAndAwnser[1]) * genNumber;
					expressionAndAwnser[1] = Double.toString(newAwnser);					
				}else if(whichMod == 5){
					// the parentheses are a feature
					expressionAndAwnser[0] = "("+expressionAndAwnser[0]+") ^ "+2;
					newAwnser = Math.pow(Double.parseDouble(expressionAndAwnser[1]), 2);
					expressionAndAwnser[1] = Double.toString(newAwnser);					
				}
			}else {								
				expressionAndAwnser[0] = Integer.toString(genNumber);
				expressionAndAwnser[1] = Integer.toString(genNumber);
			}			
			expressionAndAwnser = generate(numbers, numberSize, expressionAndAwnser, modifiers);
		}		
		return expressionAndAwnser;
	}
}
