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


/***
 * The view class that creates the GUI for the Problem solving scene of the application.
 * Handles Input through the SampleController.
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class ProblemGUI{
	
	/***
	* A Delegate used to communicate with the main class without and direct contact.
	* has two methods grade that take a string and compares it to the answer of the problem
	* and getProblem which generates a new problem and returns it.
	* @author Greppe
	* @author Philip
	* @version 1.0
	* @since 2020-09-17
	*/
    public interface Delegate 
    {
    	Boolean grade(String s);
    	String getProblem();
    }
	
    public VBox responseBox;
	public Text problemText;
    public TextField answerText;
    public Button button;
    public Text responseText;
    
    private String text;
    
    private AnchorPane root;
    
    public Delegate delegate;
    
    /***
     * The constructor of the ProblemGUI class, initializes the GUI elements with initGUI and sets the root of the main application to the problem gui:s root.
     * @param root the root of the JavaFX application
     * 
     */
    public ProblemGUI(AnchorPane root) {
    	this.root=root;
    	initGUI();
    }
    
    /***
     * Is called on start up to generate the first problem to solve
     */
    public void initProblem() 
    {
    	System.out.println("Delegate:" + delegate);
    	if(delegate != null)
    	{
    		text = delegate.getProblem();
    		answerText.clear();
    		problemText.setText(text);
    		problemText.setFocusTraversable(false);
    	}
    }
   
    /***
     * if the answer is correct a new problem is generated and the text field is cleared,
     * otherwise it stays on the same problem.
     */
    public void answer() {
	   if(delegate != null) {
		   if(delegate.grade(answerText.getText())) {
			   text = delegate.getProblem();
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
   }

    /***
     * shows a temporary message depending on the in parameter, 
     * if it's true it show the correct message otherwise it will show wrong.
     * @param correct a boolean indicated if the user answered correctly or not
     */
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
	
   /***
    *  Initalizes the GUI elements so the users can interact with them.
    */
   private void initGUI() {
    	responseBox =  (VBox) root.lookup("#responseBox");
    	problemText = (Text) root.lookup("#problemText");
    	answerText = (TextField) root.lookup("#answerText");
    	button = (Button) root.lookup("#button");
    	responseText = (Text) root.lookup("#responseText");
    }
   
}
