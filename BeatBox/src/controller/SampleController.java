package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.testGenerator;

public class SampleController implements Initializable{
	
	@FXML
    private VBox responseBox;
	
	@FXML
    private Text problemText;

    @FXML
    private TextField answerText;

    @FXML
    private Button button;
    
    @FXML
    private Text responseText;
    
    private testGenerator testGen;

    private String text;
    private String answer;
    
    
    
    @FXML
    void AnswerText(ActionEvent event) {
    	answer();
    }
    
   @FXML
    void ButtonClicked(ActionEvent event) {	   
	   answer();
    }
 
   private void answer() {
	   if(answerText.getText().equals(answer)) {
		   String[] a = testGen.getNextProblem();
		   text = a[0];
		   answer = a[1];
		   problemText.setText(text);
		   answerText.clear();
		   answerText.requestFocus();
		   showResponse(true);
	   }else {
		   answerText.clear();
		   answerText.requestFocus();
		   showResponse(false);
	   }

   }

   
   private void showResponse(boolean correct) {
	   if(correct) {
		   responseText.setText("CORRECT!");
		   responseText.setFill(Color.LIMEGREEN);
		   responseBox.setVisible(true); 
		   
		   FadeTransition ft = new FadeTransition(Duration.millis(1500), responseBox);
		   ft.setFromValue(1);
		   ft.setToValue(0);
		   ft.play();
		  
	   }else {
		   responseText.setText("WRONG!");
		   responseText.setFill(Color.RED);
		   responseBox.setVisible(true);
		   
		   FadeTransition ft = new FadeTransition(Duration.millis(1500), responseBox);
		   ft.setFromValue(1);
		   ft.setToValue(0);
		   ft.play();
		   
		   
	   }
   }
	   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		testGen = new testGenerator();
		String[] a = testGen.getNextProblem();
		text = a[0];
		answer = a[1];
		answerText.clear();
		problemText.setText(text);
		problemText.setFocusTraversable(false);
	}
}
