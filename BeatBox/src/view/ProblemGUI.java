package view;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.testGenerator;

public class ProblemGUI{
	
	
    public VBox responseBox;
	public Text problemText;
    public TextField answerText;
    public Button button;
    public Text responseText;
    
    private testGenerator testGen;
    private String text;
    private String answer;
    
    private AnchorPane root;
    
    public ProblemGUI(AnchorPane root, testGenerator g) {
    	this.root=root;
    	initGUI();
    	this.testGen = g;
		String[] a = testGen.getNextProblem();
		text = a[0];
		answer = a[1];
		answerText.clear();
		problemText.setText(text);
		problemText.setFocusTraversable(false);
    }
    
 
   public void answer() {
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
	
   
    
    private void initGUI() {
    	responseBox =  (VBox) root.lookup("#responseBox");
    	problemText = (Text) root.lookup("#problemText");
    	answerText = (TextField) root.lookup("#answerText");
    	button = (Button) root.lookup("#button");
    	responseText = (Text) root.lookup("#responseText");
    }
   
}
