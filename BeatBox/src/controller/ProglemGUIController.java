package controller;

import model.grading;
import model.testGenerator;
import view.ProblemGUI;
import view.ShakeTransition;

public class ProglemGUIController implements ControllerInterface{
	
	private ProblemGUI pg;
	private grading g;
	private testGenerator gen;
	private String[] problemString;
	
	public ProglemGUIController(ProblemGUI pg, grading g,testGenerator gen) {
		this.pg = pg;
		this.g = g;
		this.gen = gen;
		setActions();
		nextProblem();
	}
	
	/***
	 * Sets up the actions for the Problem GUI to connect it the rest of the program.
	 */
	@Override
	public void setActions() {
		pg.getAnswerButton().setOnAction(e -> {
			answer();
			pg.clearAnswerText();
			pg.getAnswerText().requestFocus();
		});
		pg.getAnswerText().setOnAction(e -> {
			answer();
			pg.clearAnswerText();
			pg.getAnswerText().requestFocus();
		});
	}
	
	
	private void answer() {
		String answer = pg.getAnswerText().getText();
		if(g.grade(answer)){
			pg.showResponse(true);
			nextProblem();
		}else {
			pg.showResponse(false);
			ShakeTransition anim = new ShakeTransition(pg.getAnswerText(), t -> System.out.println("test"));
			anim.playFromStart();
		}
	}
	
	private void nextProblem() {
		problemString = gen.getNextProblem();
		g.setAnswer(problemString[1]);
		pg.setProblemText(problemString[0]);
	}
}
