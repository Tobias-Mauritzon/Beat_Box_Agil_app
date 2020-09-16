package model;


import controller.SampleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ProblemGUI;


public class Main extends Application{
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	private AnchorPane root;
	 
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
	        
	        ProblemGUI pg = new ProblemGUI(root,new testGenerator());
	        new SampleController(pg);
	        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
