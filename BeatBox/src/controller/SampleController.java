package controller;


import view.ProblemGUI;

public class SampleController  {

	
	ProblemGUI problemGUI;
	
	public SampleController(ProblemGUI problemGUI) {
		this.problemGUI = problemGUI;	
		setActions();
	}
	
	private void setActions() {
		problemGUI.button.setOnAction(e->{problemGUI.answer();});
		problemGUI.answerText.setOnAction(e->{problemGUI.answer();});
	}

}
