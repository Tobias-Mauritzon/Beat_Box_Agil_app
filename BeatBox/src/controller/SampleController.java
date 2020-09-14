package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.testGenerator;

public class SampleController implements Initializable{
		@FXML
	    private TextField problemText;

	    @FXML
	    private TextField answerText;

	    @FXML
	    private Button button;
	    
	    private testGenerator testGen;

	    private String text;
	    private String answer;
	    
	    @FXML
	    void AnswerText(ActionEvent event) {
	    	
	    }
	    
	   @FXML
	    void ButtonClicked(ActionEvent event) {	   
		   
		   if(answerText.getText().equals(answer)) {
			   System.out.println("CORRECT");
			   String[] a = testGen.getNextProblem();
			   text = a[0];
			   answer = a[1];
			   problemText.setText(text);
		   }else {
			   System.out.println("WRONG");
		   }
		   
		   System.out.println("Clicked button");
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		testGen = new testGenerator();
		String[] a = testGen.getNextProblem();
		text = a[0];
		answer = a[1];
		
		problemText.setText(text);
	}
}
