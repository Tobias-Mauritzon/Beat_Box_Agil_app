package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// HEJ
/**
 * Stores the users information. 
 * Such as name, previous problems, and records for different categories
 * @author Tobias Mauritzon, Joachim Antfolk
 * @version 1.0
 * @since 2020-09-28
 */
public class UserProfile implements Serializable{

	private static final long serialVersionUID = 1L; //used for saving the profile
	private String name;
	private LinkedList<Problem> history; // all problems stored as they come in
	private HashMap<String,Integer> records; //maybe <recordType(as String), Problem>

	/**Constructor for UserProfile. A UserProfile is initialized with 
	 * the given name, an empty List History and an empty HashMap records
	 * 
	 * @param name
	 */
	public UserProfile(String name) {
		this.name = name;
		this.history = new LinkedList<Problem>();
		this.records = new HashMap<String,Integer>();
	}
	
	/**
	 * Gets profile name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets history
	 * @return List of problem history
	 */
	public LinkedList<Problem> getHistory(){
		return history;
	}
	
	/**
	 * Gets records
	 * @return hashmap of records
	 */
	public HashMap<String,Integer> getRecords(){
		return records;
	}
	
	/**Adds a Problem last in UserProfile local LinkedList history
	 * 
	 * @param problem
	 * @param userAnswer
	 * @param correctAnswer
	 * @param points
	 * @param timeRequierd
	 * @param modifiers
	 */
	public void addProblemToHistory(String problem, String userAnswer, String correctAnswer, int points, int timeRequierd, Operator[] modifiers) {
		history.addLast(new Problem(problem, userAnswer, correctAnswer, points, timeRequierd, modifiers));
		
	}
	
	public class History{
		private final String problem;
		private final String userAnswer;
		
		public History(String problem, String userAnswer) {
			this.problem = problem;
			this.userAnswer = userAnswer;
		}
		

		public String getProblem() {
			return problem;
		}
		
		public String getUserAnswer() {
			return userAnswer;
		}
		
	}
	
	
	public LinkedList<History> getDataForHistory(){
		LinkedList<History> history = new LinkedList<History>();
		for(Problem p : this.history) {
			history.add(new History(p.getProblem(), p.getUserAnswer()));
		}
		
		return history;
	}
	
	
	/**The internal class Problem was created to enable easy sorting 
	 * and categorization of problems
	 * 
	 * @author Tobias
	 *
	 */
	public class Problem implements Serializable{
		
		private static final long serialVersionUID = 2L;
		
		private final String problem;
		private final String userAnswer;
		private final String correctAnswer;
		private final Date date;
		private final int points;
		private final int timeRequired;
		private final Operator[] modifiers;
		
		/**Initialises the Problem class with requierd values
		 * 
		 * @param problem as a Stirng
		 * @param awnser to the problem as a String
		 * @param points for the solve or failiure
		 * @param timeRequired to solve the problem
		 * @param modifiers used to create the problem, modifiers can 
		 * 		be null and problem will be used as refrence
		 */
		public Problem(String problem, String userAnswer, String correctAnswer, int points, int timeRequired, Operator[] modifiers) {
			this.problem = problem;
			this.userAnswer = userAnswer;
			this.correctAnswer = correctAnswer;
			this.date =  new Date();
			this.points = points;
			this.timeRequired = timeRequired;
			
			// if modifiers is null we look for what modifiers are used and add them to the list
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
		public String getUserAnswer() {
			return userAnswer;
		}
		
		/**
		 * Gets the correct answer
		 * @return String answer
		 */
		public String getCorrectAnswer() {
			return correctAnswer;
		}
		
		/**
		 * Gets date of the problem
		 * @return Date of problem
		 */
		public Date getDate() {
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
}