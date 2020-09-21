package model;
/**
 * Class used to generate random expression depending on different modifiers
 * @author Tobias Mauritzon, Joachim Antfolk
 * @version 1.0
 * @since 2020-09-17
 */

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NumberGeneratorTest{
	private NumberGenerator numG;
	
	public static boolean contains(String s, char value){
	    return s != null && s.indexOf(value) > -1;
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	

	/**
	 * 	Before each test reinitialize generator object
	 */
	@Before
	public void setup() {
		numG = new NumberGenerator();
	}
	
	/**
	 * Test that Generator can generate addition
	 */
	@Test
	public void testGenerateAdd() {
		String[] output = NumberGenerator.generate(2, new int[]{1, 10}, new Operator[]{Operator.ADD});
		assertTrue(contains(output[0],'+'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new Operator[]{Operator.ADD});
		assertTrue(contains(output[0],'+'));
	}
	
	/**
	 * Test that Generator can generate subtraction
	 */
	@Test
	public void testGenerateSub() {
		String[] output = NumberGenerator.generate(2, new int[]{1, 10}, new Operator[]{Operator.SUB});
		assertTrue(contains(output[0],'-'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new Operator[] {Operator.SUB});
		assertTrue(contains(output[0],'-'));
	}
	
	/**
	 * Test that Generator can generate division
	 */
	@Test
	public void testGenerateDiv() {
		String[] output = NumberGenerator.generate(2, new int[]{1, 10}, new Operator[]{Operator.DIV});
		assertTrue(contains(output[0],'/'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new Operator[] {Operator.DIV});
		assertTrue(contains(output[0],'/'));
	}
	
	/**
	 * Test that Generator can generate multiplication
	 */
	@Test
	public void testGenerateMul() {
		String[] output = NumberGenerator.generate(2, new int[]{1, 10}, new Operator[]{Operator.MUL});
		assertTrue(contains(output[0],'*'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new Operator[] {Operator.MUL});
		assertTrue(contains(output[0],'*'));
	}
	
	/**
	 * Test that Generator can generate Exponentiation
	 */
	@Test
	public void testGenerateExp() {
		String[] output = NumberGenerator.generate(2, new int[]{1, 10}, new Operator[]{Operator.EXP});
		assertTrue(contains(output[0],'^'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new Operator[] {Operator.EXP});
		assertTrue(contains(output[0],'^'));
	}
	
	/**
	 * Test that number range in form bigger, smaller throws error, with positive numbers
	 */
	@Test
	public void testCorrectSortingPositive() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("The real interval has to in the format [smaller, bigger]!");
		NumberGenerator.generate(2, new int[]{10, 2}, new Operator[]{Operator.ADD});
	}

	/**
	 * Test that number range in form bigger, smaller throws error, with negative numbers
	 */
	@Test
	public void testCorrectSortingNegativ() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("The real interval has to in the format [smaller, bigger]!");
		NumberGenerator.generate(2, new int[]{-2, -6}, new Operator[]{Operator.ADD});
	}
	
	/**
	 * Test that number range in form bigger, smaller throws error, with negative numbers
	 */
	@Test
	public void testCorrectSortingBoth() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("The real interval has to in the format [smaller, bigger]!");
		NumberGenerator.generate(2, new int[]{10, -6}, new Operator[]{Operator.ADD});
	}
	
	/**
	 * Test that negative term number throws IllegalArgumentException
	 */
	@Test
	public void testNoNegativTermNumber() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("Number of terms must be grater than 2!");
		NumberGenerator.generate(-2, new int[]{2, 6}, new Operator[]{Operator.ADD});
	}
	
	/**
	 * Test that unique generation happens when parameters allow at least 11 different problems 
	 */
	@Test
	public void testUniqueGeneration() {
		LinkedList<String> list = new LinkedList<String>();
		for(int i = 0; i < 15; i++) {
			numG.uniqueGeneration(2, new int[]{1, 4}, new Operator[]{Operator.ADD});
		}
		list = numG.getList();
		assertTrue(list.size() == 10);
	}
	
	/**
	 * Test that unique generation does not happen when parameters don't allow for at least 11 different problems 
	 */
	@Test
	public void testUniqueGenerationFail() {
		for(int i = 0; i < 10; i++) {
			numG.uniqueGeneration(2, new int[]{0, 2}, new Operator[]{Operator.ADD});
		}
		LinkedList<String> list = numG.getList();
		assertTrue(list.isEmpty()); //Doesn't touch list if parameters are to narrow
	}
	
	@Test
	public void testDivisionByZero() {
		
		String output[] = numG.uniqueGeneration(100, new int[]{0, 3}, new Operator[]{Operator.DIV});
		
		assertTrue(Double.parseDouble(output[1]) >= 0);
	}
	
	@Test
	public void testDivisionByZeroManyOperators() {
		
		String output[] = numG.uniqueGeneration(1000, new int[]{0, 3}, new Operator[]{Operator.DIV, Operator.ADD, Operator.EXP, Operator.MUL, Operator.SUB});
	}
}