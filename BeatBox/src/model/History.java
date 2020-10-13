package model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The internal class Problem was created to enable easy sorting and
 * categorization of problems
 * 
 * @author Tobias Mauritzon, Joachim Antfolk
 * @since 2020-10-07
 */
public class History implements Serializable{
	
	private static final long serialVersionUID = 2L;
	
	private final String problem;
	private final double userAnswer;
	private final double correctAnswer;
	private final String date;
	private final int points; // placeholder
	private final int timeRequired; // placeholder
	private final Operator[] modifiers;
	
	/**Initialises the History class with required values
	 * 
	 * @param problem as a Stirng
	 * @param awnser to the problem as a String
	 * @param points for the solve or failiure
	 * @param timeRequired to solve the problem
	 * @param modifiers used to create the problem, modifiers can 
	 * 		be null and problem will be used as refrence
	 */
	public History(String problem, String userAnswer, String correctAnswer, int points, int timeRequired, Operator[] modifiers) {
		this.problem = problem;
		this.userAnswer = Double.parseDouble(userAnswer);
		this.correctAnswer = Double.parseDouble(correctAnswer);
		LocalDateTime now = LocalDateTime.now();
		
		
		this.date =  now.getYear()+"-"+String.format("%02d", now.getMonthValue())+"-"+String.format("%02d", now.getDayOfMonth())+" "+now.getHour()+":"+now.getMinute()+":"+now.getSecond();
		this.points = points;
		this.timeRequired = timeRequired;
		
		// if modifiers is null we look for what modifiers are used in the problem and add them to the list
		if(modifiers == null) {
			modifiers = new Operator[5];
			if(problem.contains("+")) {
				modifiers[0] = Operator.ADD;
			}
			if(problem.contains("-")) {
				modifiers[1] = Operator.SUB;
			}
			if(problem.contains("*")) {
				modifiers[2] = Operator.MUL;
			}
			if(problem.contains("/")) {
				modifiers[3] = Operator.DIV;
			}
			if(problem.contains("^")) {
				modifiers[4] = Operator.EXP;
			}
		}
		this.modifiers = modifiers;
	}
	
	/**
	 * Gets the problem 
	 * @return String problem
	 */
	public String getProblem() {
		return problem;
	}
	
	/**
	 * Gets user answer
	 * @return String users answer
	 */
	public double getUserAnswer() {
		return userAnswer;
	}
	
	/**
	 * Gets the correct answer
	 * @return String answer
	 */
	public double getCorrectAnswer() {
		return correctAnswer;
	}
	 
	/**
	 * Gets date of the problem
	 * @return Date of problem
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Gets points
	 * @return points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Gets time required
	 * @return time required
	 */
	public int getTimeRequired() {
		return timeRequired;
	}
	
	/**
	 * Gets problem modifiers
	 * @return List of Operators
	 */
	public Operator[] getModifiers() {
		return modifiers;
	}
}
