package controller;

import view.ProblemGUI;

public class ProglemGUIController implements ControllerInterface{
	
	private ProblemGUI pg;
	
	public ProglemGUIController(ProblemGUI pg) {
		this.pg = pg;
		setActions();
	}
	
	/***
	 * Sets up the actions for the Problem GUI to connect it the rest of the program.
	 */
	@Override
	public void setActions() {
		pg.getAnswerButton().setOnAction(e -> {
			pg.answer();
		});
		pg.getAnswerText().setOnAction(e -> {
			pg.answer();
		});
	}
	
}
