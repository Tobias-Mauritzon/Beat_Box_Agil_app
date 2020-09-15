package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberGeneratorTest {
	
	public static boolean contains(String s, char value){
	    return s != null && s.indexOf(value) > -1;
	}

	@Test
	public void testGenerate() {
		// addition is working
		String[] output = NumberGenerator.generate(2, new int[]{1, 10},new String[2], new int[]{1});
		assertTrue(contains(output[0],'+'));
		assertTrue(Double.parseDouble(output[1]) > 1 && Double.parseDouble(output[1]) < 21);
		// subtraction is working
		output = NumberGenerator.generate(2, new int[]{1, 10},new String[2], new int[]{2});
		assertTrue(contains(output[0],'-'));
		output = NumberGenerator.generate(2, new int[]{1, 10},new String[2], new int[]{3});
		assertTrue(contains(output[0],'/'));
		output = NumberGenerator.generate(2, new int[]{1, 10},new String[2], new int[]{4});
		assertTrue(contains(output[0],'*'));
	}
	
	@Test
	public void testUniqueGeneration() {
		NumberGenerator numG = new NumberGenerator();
		// addition is working
		String[] output = numG.uniqueGeneration(2, new int[]{1, 10}, new int[]{1});
		assertTrue(contains(output[0],'+'));
		// subtraction is working
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new int[]{2});
		assertTrue(contains(output[0],'-'));
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new int[]{3});
		assertTrue(contains(output[0],'/'));
		output = numG.uniqueGeneration(2, new int[]{1, 10}, new int[]{4});
		assertTrue(contains(output[0],'*'));
	}

}
