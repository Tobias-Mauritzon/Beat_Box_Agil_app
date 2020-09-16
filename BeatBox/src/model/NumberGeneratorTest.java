package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class NumberGeneratorTest {
	private NumberGenerator numG;
	
	public static boolean contains(String s, char value){
	    return s != null && s.indexOf(value) > -1;
	}

	/*
	 * 	
	 */
	@Before
	public void setup() {
		numG = new NumberGenerator();
	}
	/*
	 * 
	 */
	@Test
	public void testGenerateAdd() {
		// addition is working
		String[] output = NumberGenerator.generate(2, new int[]{1, 10},new String[2], new int[]{1});
		assertTrue(contains(output[0],'+'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new int[]{1});
		assertTrue(contains(output[0],'+'));
	}
	
	/*
	 * 
	 */
	@Test
	public void testGenerateSub() {
		// subtraction is working
		String[] output = NumberGenerator.generate(2, new int[]{1, 10},new String[2], new int[]{2});
		assertTrue(contains(output[0],'-'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new int[]{2});
		assertTrue(contains(output[0],'-'));
	}
	
	/*
	 * 
	 */
	@Test
	public void testGenerateDiv() {
		// division is working
		String[] output = NumberGenerator.generate(2, new int[]{1, 10},new String[2], new int[]{3});
		assertTrue(contains(output[0],'/'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new int[]{3});
		assertTrue(contains(output[0],'/'));
	}
	
	/*
	 * 
	 */
	@Test
	public void testGenerateMul() {
		// multiplication is working
		String[] output = NumberGenerator.generate(2, new int[]{1, 10},new String[2], new int[]{4});
		assertTrue(contains(output[0],'*'));
		
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new int[]{4});
		assertTrue(contains(output[0],'*'));
	}
}