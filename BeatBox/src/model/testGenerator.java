package model;

import java.util.Random;


/**
 * A placeholder generator that is mainly used for testing.
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class testGenerator {
	//Placeholder arrays that contains problems and answers for testing purposes
		String pArray[] = {"5 + 5 =", "4 + 2 =", "3 + 5 =", "3 + 9 =", "5 - 5 =", "4 - 2 =", "3 - 5 =", "3 - 9 ="};
		String aArray[] = {"10", "6", "8", "12", "0", "2", "-2", "-6"};
		String pString = "5 + 5 =";
		String pAnswer = "10";
		
		
		/***
	     * Get a the next math problem and updates the label containing the current problem and clear the answerField.
	     * @return returns a String array containing the problem in the first index and the answer to the problem in the second index.
	     */
	    public String[] getNextProblem() {	
	    	Random rand = new Random();
	    	int num = rand.nextInt(8);
	    	pAnswer = aArray[num];
	    	pString = pArray[num]; 	
	    	String[] s = {pString,pAnswer};  
	    	
	    	return s;
	    }

}
