package model;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Tobias Mauritzon & Joachim Antfolk
 * @version 1.0
 * @since 2020-09-24
 */
public class UserProfile implements Serializable{
	//used for saving the profile
	private static final long serialVersionUID = 1L;
	
	// user name
	private String name;
	
	// all problems stored as they come in
	private LinkedList<Problem> history;
	
	//mapybe <recordType(as String), Problem>
	private HashMap<String,Integer> records;

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
	
	public String getName() {
		return name;
	}
	
	public LinkedList<Problem> getHistory(){
		return history;
	}
	
	public HashMap<String,Integer> getRecords(){
		return records;
	}
	
	
	
	/**Loads a UserProfile with the specified name given to the method
	 * if no saved UserProfile matches that name we return your current 
	 * UserProfile.
	 * 
	 * @param name of the UserProfile you want to load
	 * @param current is your current UserProfile
	 * @return
	 */
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
	
	
	
	/**Saved the current UserProfile using the getName method and a 
	 * predetermined String as the name of the file
	 * 
	 * @return True if file was saved, False if file failed to save
	 * 		as of right now there is no way for this method to fail.
	 */
	public boolean saveProfile(){
		try {
			saveFile(this, this.name+"SavedProfile.Save");
			return true;
		}catch (IOException e) {
			System.out.println("Could not save file");
			e.printStackTrace();
			return false;
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
	
	/**Method for removal of specified UserProfile save
	 * 
	 * @param name is the name of the UserProfile you want to remove
	 * @return "Ok" if the file was removed "Fail, no such file" if file could not be found
	 */
	public String deleteFile(String name){
		File myFile = new File(name+"SavedProfile.Save"); 
		if(myFile.delete()) {
			return "Ok";
		}else {
			return "Fail, no such file";
		}
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
