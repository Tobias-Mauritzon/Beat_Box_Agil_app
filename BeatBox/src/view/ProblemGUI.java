package view;

import javafx.scene.control.Button;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProblemGUI extends Application implements EventHandler<ActionEvent>{
	
	Button button;
	
    @Override
    public void start(Stage primaryStage) {
        try {
        	primaryStage.setTitle("MathGame");
        	button = new Button();
        	button.setText("Answer");
        	button.setOnAction(this);
        	StackPane layout = new StackPane();
        	layout.getChildren().add(button);
            Scene scene = new Scene(layout,400,400);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()== button) 
		{
			System.out.println("You Answered");
		}
		
	}
}
