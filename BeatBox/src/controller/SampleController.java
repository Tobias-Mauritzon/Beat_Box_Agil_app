package controller;


import view.ProblemGUI;


/***
 * A simple controller that handles the input from the user and communicates with the GUI.
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class SampleController  {

	
	ProblemGUI problemGUI;
	
	/***
	 * Constructor of the SampleController
	 * @param problemGUI reference to the problemGUI instance so it can interact with it.
	 */
	public SampleController(ProblemGUI problemGUI) {
		this.problemGUI = problemGUI;	
		setActions();
	}
	
	/***
	 * Binds actions of the GUI to the controller
	 */
	private void setActions() {
		problemGUI.button.setOnAction(e->{problemGUI.answer();});
		problemGUI.answerText.setOnAction(e->{problemGUI.answer();});
	}

}
