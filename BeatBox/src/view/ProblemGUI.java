package view;

import javafx.scene.control.Button;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProblemGUI extends Application{
    @Override
    public void start(Stage primaryStage) {
        try {
        	primaryStage.setTitle("MathGame");
        	Button button = new Button();
        	button.setText("ClickMe");
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
}
