package model;

public class grading {

	String answer;
	
	public void setAnswer(String a) {
		answer = a;
	}
	
	public Boolean grade(String userInput) {
		return answer.equals(userInput);
	}
}
