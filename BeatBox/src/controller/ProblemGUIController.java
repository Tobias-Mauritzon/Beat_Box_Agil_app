package controller;

import model.NumberGenerator;
import model.Grading;
import view.ProblemGUI;
import view.ShakeTransition;

/***
 * The controller for the problem GUI connects the Problem GUI to both the grading and Number Generator.
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-10-03
 *
 */
public class ProblemGUIController implements ControllerInterface{
	
	private ProblemGUI pg;
	private Grading g;
	private NumberGenerator gen;
	private String[] problemString;
	
	public ProblemGUIController(ProblemGUI pg, Grading g, NumberGenerator gen) {
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
	
	/***
	 * The function called when the user inputs an answer. 
	 * uses grading to check if it's correct and shows a response, if it's correct it uses problem Generator to generate a new problem and displays it.
	 */
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
	
	/***
	 * Gets a new problem form the generator and updates the GUI.
	 */
	private void nextProblem() {
		problemString = gen.generate();
		g.setAnswer(problemString[1]);
		//pg.setProblemText(problemString[0] + " = ");
		pg.setProblemText(problemString[0]);
	}
	
	/***
	 * a public version of the nextProblem Function.
	 */
	public void ResetGUI() 
	{
		nextProblem();
	}
}
