package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProblemGUI extends Application{
	
    @Override
    public void start(Stage primaryStage) {
        try {
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("buttontest.fxml"));
			Scene scene = new Scene(root,root.getWidth(),root.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMinWidth(600);
	        primaryStage.setMinHeight(600);
	        primaryStage.setWidth(600);
	        primaryStage.setHeight(600);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
