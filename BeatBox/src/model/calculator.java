package model;

import java.util.Random;

public class calculator {
	int num1;
	int num2;
	
	public static void main(String[] args) 
	{
		//number size
		int[] size = {1,100};
		// allowed modifiers
		int[] mod = {1,2,3,4};
		String[] s = generator(5, size,new String[2], mod);
		
		System.out.println(s[0]);
		System.out.println(s[1]);
	}
	
	/**
	 * @param numbers the number of numbers in the expression
	 * @param numberSize allowed number size {small, big}
	 * @param expressionAndAwnser new String[2]
	 * @param modifiers if list contains 1 = addition, 2 = subtraction, 3 = division, 4 = multiplication. list can be any size
	 * @return string array. array[0] = expression, array[1] = awnser
	 */
	private static String[] generator(int numbers, int[] numberSize, String[] expressionAndAwnser, int[] modifiers) {
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
					whichMod = rand.nextInt(4)+1;					
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
				}								
			}else {								
				expressionAndAwnser[0] = Integer.toString(genNumber);
				expressionAndAwnser[1] = Integer.toString(genNumber);
			}			
			expressionAndAwnser = generator(numbers, numberSize, expressionAndAwnser, modifiers);
		}		
		return expressionAndAwnser;
	}
}
