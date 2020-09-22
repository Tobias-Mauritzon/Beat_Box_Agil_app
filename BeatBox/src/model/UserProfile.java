package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class UserProfile {
	
	// user name
	private String name;
	
	// all problems stored as they come in
	private LinkedList<Problem> history;
	
	//mapybe <recordType(as String), Problem>
	private HashMap<String,Integer> records;

	public UserProfile(String name) {
		this.name = name;
		this.history = new LinkedList<Problem>();
		this.records = new HashMap<String,Integer>();
	}
	
	public void loadProfile(String name) {
		/* TODO
		 * method needs proper implementation
		 * should load from a sereilised save if possible
		*/
		this.history = new LinkedList<Problem>();
		this.records = new HashMap<String,Integer>();
	}
	
	public void saveProfile() {
		
	}
	
	/*
	+ saveProfile() : void
	+ loadProfile(name : String) : UserProfile
	+ getName() : String
	+ getHistory() : LinkedList<String>
	+ getRecords() : HashMap<String, int> 
	+ setName(name : String) : void
	+ setHistory(history : LinkedList<String>) : void
	+ setRecords(record : HashMap<String, int>) : void

	*/
	
	/**Private problem class created to enable easy sorting 
	 * and categorization
	 * 
	 * @author Tobias
	 *
	 */
	private class Problem{
		private final String problem;
		private final String answer;
		private final String correctAnswer;
		private final Date date;
		private final int points;
		private final int timeRequierd;
		private final Operator[] modifiers;
		
		/**Initialises the Problem class with requierd values
		 * 
		 * @param problem as a Stirng
		 * @param awnser to the problem as a String
		 * @param points for the solve or failiure
		 * @param timeRequierd to solve the problem
		 * @param modifiers used to create the problem
		 */
		public Problem(String problem, String answer, String correctAnswer, int points, int timeRequierd, Operator[] modifiers) {
			this.problem = problem;
			this.answer = answer;
			this.correctAnswer = correctAnswer;
			this.date =  new Date();
			this.points = points;
			this.timeRequierd = timeRequierd;
			this.modifiers = modifiers;
		}

		public String getProblem() {
			return problem;
		}
		public String getAwnser() {
			return answer;
		}
		public String getcCrrectAnswer() {
			return correctAnswer;
		}
		public Date getDate() {
			return date;
		}
		public int getPoints() {
			return points;
		}
		public int getTimeRequierd() {
			return timeRequierd;
		}
		public Operator[] getModifiers() {
			return modifiers;
		}

	}
}
