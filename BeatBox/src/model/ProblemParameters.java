package model;

import java.util.ArrayList;
import java.util.List;

public class ProblemParameters {


	private List<Operator> operators = new ArrayList<>();
	private int[] range;
	private int termAmount;
	private boolean timed;


	public ProblemParameters(List<Operator> operators, int[] range, int termAmount, boolean timed){

		this.operators = operators;
		this.range = range;
		this.termAmount = termAmount; 
		this.timed = timed;
	}


	/*
		Get acceptable operators. 
	*/
	public List<Operator> getOperators(){

		return (operators);

	}

	
	/*
		Get acceptable range for numbers to generate, from a -> b. 
	*/
	public int[] getRange(){
		return range;
	}

	/*
		Maximum number of terms a problem should have.
	*/
	public int getTermAmount(){
		return termAmount;
	}

	/*
		Return whether or not a session should be timed. 
	*/
	public boolean getTimed(){
		return timed;
	}

	
}