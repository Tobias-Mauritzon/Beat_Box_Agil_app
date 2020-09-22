package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class UserProfile implements Serializable{
	//used for saving the profile
	private static final long serialVersionUID = 1L;
	
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
	
	public String getName() {
		return name;
	}
	
	public LinkedList<Problem>getHistory(){
		return history;
	}
	
	public HashMap<String,Integer> getRecords(){
		return records;
	}
	
	
	
	public UserProfile loadProfile(String name, UserProfile current ) {
		try {
			current = (UserProfile) loadFile(current.name+"SavedProfile.Save");
		}catch(IOException | ClassNotFoundException e){
			System.out.println("Could not load save-file");
			e.printStackTrace();
			return current;
		}
		return current;
	}
	
	/**
	 * Reads the data from the selected file and loads it
	 * 
	 * @param fileName the name of file that the data is saved in
	 * @return input stream
	 * @throws ClassNotFoundException when the loaded file is wrong format.
	 * @throws IOException            when file is not found
	 */
	private Object loadFile(String fileName) throws ClassNotFoundException, IOException {
		try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
			return inputStream.readObject();
		}
	}
	
	/**
	 * Saves the data to a file with the parameter filename as the files name.
	 * 
	 * @param data     the that is saved
	 * @param fileName the name of file that the data is saved in
	 * @throws IOException when the file can't be saved.
	 */
	private void saveFile(Serializable data, String fileName) throws IOException {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
			outputStream.writeObject(data);
		}
	}
	
	public boolean saveProfile(UserProfile current){
		try {
			saveFile(current, current.name+"SavedProfile.Save");
			return true;
		}catch (IOException e) {
			//JOptionPane.showMessageDialog(null, "Could not save game");
			System.out.println("Could not save file");
			e.printStackTrace();
			return false;
		}
	}
	
	public void saveProfile2(){
		try {
			saveFile(this, this.name+"saveFile.Save");
		}catch (IOException e) {
			//JOptionPane.showMessageDialog(null, "Could not save game");
			System.out.println("Could not save file");
		}
	}
	
	public void addProblemToHistory(String problem, String userAnswer, String correctAnswer, int points, int timeRequierd, Operator[] modifiers) {
		history.addLast(new Problem(problem, userAnswer, correctAnswer, points, timeRequierd, modifiers));
	}
	
	/*
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
	public class Problem implements Serializable{
		
		private static final long serialVersionUID = 2L;
		
		private final String problem;
		private final String userAnswer;
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
		 * @param modifiers used to create the problem, modifiers can 
		 * 		be null and problem will be used as refrence
		 */
		public Problem(String problem, String userAnswer, String correctAnswer, int points, int timeRequierd, Operator[] modifiers) {
			this.problem = problem;
			this.userAnswer = userAnswer;
			this.correctAnswer = correctAnswer;
			this.date =  new Date();
			this.points = points;
			this.timeRequierd = timeRequierd;
			
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

		public String getProblem() {
			return problem;
		}
		public String getUserAnswer() {
			return userAnswer;
		}
		public String getCorrectAnswer() {
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
