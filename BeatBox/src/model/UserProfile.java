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

import javax.swing.JOptionPane;

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
	
	public UserProfile loadProfile(String name, UserProfile current ) {
		/* TODO
		 * method needs proper implementation
		 * should load from a sereilised save if possible
		*/
		try {
			current = (UserProfile) loadFile(current.name+"SavedProfile.Save");
		}catch(IOException | ClassNotFoundException e){
			System.out.println("Could not load save-file");
			return current;
		}
		return current;
	}
	
	public static Object loadFile(String fileName) throws ClassNotFoundException, IOException {
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
	public static void saveProfile(Serializable data, UserProfile current) throws IOException {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(current.name+"SavedProfile.Save")))) {
			outputStream.writeObject(data);
		}catch (IOException e) {
			//JOptionPane.showMessageDialog(null, "Could not save game");
			System.out.println("Could not save game");
		}
	}
	
	/*
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
