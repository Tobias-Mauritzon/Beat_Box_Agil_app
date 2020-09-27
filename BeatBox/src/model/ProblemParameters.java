package model;

import java.util.ArrayList;
import java.util.List;


/**
* A data structure holding all possible parameters for the problem generator. 
*
* @see Operator class. 
* 
* @author Seif Eddine Bourogaa
* @author Andreas Palmqvist 
*/
public class ProblemParameters {
<<<<<<< HEAD


	/**
	* Data structures used to hold all the problem parameters.
	*
	* @see operators   List holding all the seeked Operators.
	* @see range 	   Array holding the range of numbers to create.
	* @see termAmount  variable holding the number of terms in a problem.
	* @see timed       variable holding whether or not the session should be timed. 
	*/
=======
>>>>>>> 50be9502b210a1dff416fed193fe3baa2f6b06f2
	private List<Operator> operators = new ArrayList<>();
	private int[] range;
	private int termAmount;
	private boolean timed;

<<<<<<< HEAD

	/**
	* Constructor for the ProblemParameter class. 
	*
	* @param operators  List of operators this session should hold
	* @param range 	    Range of numbers to create in the problem generator. 
	* @param termAmount How many terms each problem should have. 
	* @param timed      If the session should be timed or not. 
	*/
=======
>>>>>>> 50be9502b210a1dff416fed193fe3baa2f6b06f2
	public ProblemParameters(List<Operator> operators, int[] range, int termAmount, boolean timed){
		this.operators = operators;
		this.range = range;
		this.termAmount = termAmount; 
		this.timed = timed;
	}

<<<<<<< HEAD

	/**
	* Get which operators that should be used. 
	*
	* @return List of operators.
	*
	* @see Operator enum/class.  
=======
	/*
		Get acceptable operators. 
>>>>>>> 50be9502b210a1dff416fed193fe3baa2f6b06f2
	*/
	public List<Operator> getOperators(){
		return (operators);
	}
<<<<<<< HEAD

	/**
	* Get acceptable range for numbers to generate, from a -> b.
	*
	* @return Array of minimum and maximum number to generate terms from. 
=======
	
	/*
		Get acceptable range for numbers to generate, from a -> b. 
>>>>>>> 50be9502b210a1dff416fed193fe3baa2f6b06f2
	*/
	public int[] getRange(){
		return range;
	}

	/**
	* Get maximum number of terms a generated problem should hold.
	*
	* @return number of terms a problem should hold. 
	*/
	public int getTermAmount(){
		return termAmount;
	}

	/**
	* Get wheter or not a session should be timed. 
	*
	* @return false -> session not timed, true -> session timed. 
	*/
	public boolean getTimed(){
		return timed;
	}
}