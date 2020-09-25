package model;


import controller.SampleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ProblemGUI;
import view.ProblemGUI.Delegate;

/***
 * the Main class of the application, creates most classes and communicates between them using delegates.
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class Main extends Application{
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	private AnchorPane root;
	private testGenerator gen;
	private grading grade;
	@Override
    public void start(Stage primaryStage) {
        try {
        	root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/buttontest.fxml"));
			Scene scene = new Scene(root,root.getWidth(),root.getHeight());
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMinWidth(600);
	        primaryStage.setMinHeight(600);
	        primaryStage.setWidth(600);
	        primaryStage.setHeight(600);
	        gen = new testGenerator();
	        grade = new grading();
	        
	        ProblemGUI pg = new ProblemGUI(root);
	        pg.delegate =  new ProblemGUI.Delegate() 
	        {
				/***
				 * calls the grade method from the instance of grading
				 * @parameter String s the string to be compared by the grading class
				 */
				@Override
				public Boolean grade(String s) 
				{
					return grade.grade(s);
				}
				
				/***
				 * Generates a new problem through the problem generator and sends the answer to grading and then updates
				 * the problemGUI:s problem text.
				 */
				@Override
				public String getProblem() 
				{
					String[] problemString = gen.getNextProblem();
					grade.setAnswer(problemString[1]);
					return problemString[0];
				}
	        };
	        
	        pg.initProblem();
	        new SampleController(pg);
	        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}