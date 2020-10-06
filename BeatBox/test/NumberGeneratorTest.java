package model;
/**
 * Class used to generate random expression depending on different modifiers
 * @author Tobias Mauritzon, Joachim Antfolk
 * @version 1.0
 * @since 2020-09-17
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
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
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{1, 10},
				2,
				false)
		);

		String[] output = numG.generate();
		assertTrue(contains(output[0],'+'));
		
		output = numG.uniqueGeneration();
		assertTrue(contains(output[0],'+'));
	}
	
	/**
	 * Test that Generator can generate subtraction
	 */
	@Test
	public void testGenerateSub() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.SUB); }},
				new int[]{1, 10},
				2,
				false)
		);

		String[] output = numG.generate();
		assertTrue(contains(output[0],'-'));
		
		output = numG.generate();
		assertTrue(contains(output[0],'-'));
	}
	
	/**
	 * Test that Generator can generate division
	 */
	@Test
	public void testGenerateDiv() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.DIV); }},
				new int[]{1, 10},
				2,
				false)
		);

		String[] output = numG.generate();
		assertTrue(contains(output[0],'/'));
		
		output = numG.uniqueGeneration();
		assertTrue(contains(output[0],'/'));
	}
	
	/**
	 * Test that Generator can generate multiplication
	 */
	@Test
	public void testGenerateMul() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.MUL); }},
				new int[]{1, 10},
				2,
				false)
		);

		String[] output = numG.generate();
		assertTrue(contains(output[0],'*'));
		
		output = numG.uniqueGeneration();
		assertTrue(contains(output[0],'*'));
	}
	
	/**
	 * Test that Generator can generate Exponentiation
	 */
	@Test
	public void testGenerateExp() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.EXP); }},
				new int[]{1, 10},
				2,
				false)
		);

		String[] output = numG.generate();
		assertTrue(contains(output[0],'^'));
		
		output = numG.uniqueGeneration();
		assertTrue(contains(output[0],'^'));
	}

	/**
	 * Test that number range in form bigger, smaller throws error, with positive numbers
	 */
	@Test
	public void testCorrectSortingPositive() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{10, 2},
				2,
				false)
		);

		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("The real interval has to in the format [smaller, bigger]!");
		numG.generate();
	}

	/**
	 * Test that number range in form bigger, smaller throws error, with negative numbers
	 */
	@Test
	public void testCorrectSortingNegativ() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{-2, -6},
				2,
				false)
		);

		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("The real interval has to in the format [smaller, bigger]!");
		numG.generate();
	}

	/**
	 * Test that number range in form bigger, smaller throws error, with negative numbers
	 */
	@Test
	public void testCorrectSortingBoth() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{10, -6},
				2,
				false)
		);

		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("The real interval has to in the format [smaller, bigger]!");
		numG.generate();
	}

	/**
	 * Test that negative term number throws IllegalArgumentException
	 */
	@Test
	public void testNoNegativTermNumber() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{2, 6},
				-2,
				false)
		);

		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("Number of terms must be grater than 2!");
		numG.generate();
	}

	/**
	 * Test that array of number range can not be longer than two elements
	 */
	@Test
	public void testToLongNumberRange() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{2, 3, 6},
				2,
				false)
		);

		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("The real interval has to be composed of two elements!");
		numG.generate();
	}

	/**
	 * Test that array of number range can not be shorter than two elements
	 */
	@Test
	public void testToShortNumberRange() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{6},
				2,
				false)
		);

		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("The real interval has to be composed of two elements!");
		numG.generate();
	}

	/**
	 * Test that array of modifiers can not contain a null value
	 */
	@Test
	public void testNoNullOperator() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); add(null); }},
				new int[]{2, 6},
				2,
				false)
		);

		exceptionRule.expect(NullPointerException.class);
	    exceptionRule.expectMessage("The operator array can not contain null values!");
		numG.generate();
	}


	/**
	 * Test that unique generation happens when parameters allow at least 11 different problems
	 */
	@Test
	public void testUniqueGeneration() {
		LinkedList<String> list = new LinkedList<String>();

		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{1, 4},
				2,
				false)
		);

		for(int i = 0; i < 15; i++) {
			numG.uniqueGeneration();
		}
		list = numG.getList();
		assertTrue(list.size() == 10);
	}

	/**
	 * Test that unique generation does not happen when parameters don't allow for at least 11 different problems
	 */
	@Test
	public void testUniqueGenerationFail() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); }},
				new int[]{0, 2},
				2,
				false)
		);

		for(int i = 0; i < 10; i++) {
			numG.uniqueGeneration();
		}
		LinkedList<String> list = numG.getList();
		assertTrue(list.isEmpty()); //Doesn't touch list if parameters are to narrow
	}

	@Test
	public void testDivisionByZero() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.DIV); }},
				new int[]{0, 3},
				100,
				false)
		);

		String output[] = numG.uniqueGeneration();

		assertTrue(Double.parseDouble(output[1]) >= 0);
	}

	@Test
	public void testDivisionByZeroManyOperators() {
		numG.setSettings(new ProblemParameters(
				new ArrayList<Operator>() {{ add(Operator.ADD); add(Operator.SUB); add(Operator.MUL); add(Operator.DIV); add(Operator.EXP); }},
				new int[]{0, 3},
				1000,
				false)
		);

		String output[] = numG.uniqueGeneration();
	}
}