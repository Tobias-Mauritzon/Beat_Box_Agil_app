package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// HEJ
/**
 * Stores the users information. Such as name, previous problems, and records
 * for different categories
 * 
 * @author Tobias Mauritzon, Joachim Antfolk
 * @version 1.0
 * @since 2020-10-09
 */
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L; // used for saving the profile
	private String name;
	private List<History> history; // all problems stored as they come in

	/**
	 * Constructor for UserProfile. A UserProfile is initialized with the given
	 * name, an empty List History and an empty HashMap records
	 * 
	 * @param name
	 */
	public UserProfile(String name) {
		this.name = name;
		this.history = FXCollections.observableArrayList();
	}

	/**
	 * Gets profile name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets history
	 * 
	 * @return List of problem history
	 */
	public ObservableList<History> getHistory() {
		return (ObservableList<History>) history;
	}
	
	/**
	 * Adds a Problem last in UserProfile local LinkedList history
	 * 
	 * @param problem
	 * @param userAnswer
	 * @param correctAnswer
	 */
	public void addProblemToHistory(String problem, String userAnswer, String correctAnswer) {
		history.add(new History(problem, userAnswer, correctAnswer));
	}

	/**
	 * Makes the list serializable
	 */
	public void toArrayList() {
		ArrayList<History> temp = new ArrayList<History>();
		for (History h : history) {
			temp.add(h);
		}
		this.history = temp;
	}

	/**
	 * Makes the list observable
	 */
	public void toObservableList() {
		ObservableList<History> temp = FXCollections.observableArrayList(history);
		this.history = temp;
	}

}