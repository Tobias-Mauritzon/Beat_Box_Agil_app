package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.SceneHandler;
import view.NavigationMenu;

public class NavigationMenuController implements ControllerInterface{
	
	
	private NavigationMenu nm;
	private SceneHandler sh;
	
	
	public NavigationMenuController(NavigationMenu nm, SceneHandler sh) {
		this.nm = nm;
		this.sh = sh;
		setActions();
	}

	/***
	 * Sets up the actions for the navigation Menu
	 */
	@Override
	public void setActions() {
		
		AnchorPane menuButtonPane = nm.getMenuButtonPane();
		Button menuButton = nm.getMenuButton();

		menuButton.setOnMouseClicked(e -> {
			nm.slidePanel();
		});
		menuButtonPane.setOnMouseEntered(e -> {
			menuButtonPane.setStyle("-fx-background-color:" + nm.getEnteredColor());
		});
		menuButtonPane.setOnMouseExited(e -> {
			menuButtonPane.setStyle("-fx-background-color:" + nm.getExitedColor());
		});

		// set actions on sidepane objects
		for (int i = 0; i < nm.getLeftPanes().length; i++) {
			AnchorPane lPane = nm.getLeftPanes()[i];
			AnchorPane rPane = nm.getRightPanes()[i];
			lPane.setOnMouseEntered(e -> {
				nm.focusOn(lPane, rPane);
			});
			lPane.setOnMouseExited(e -> {
				nm.focusOff(lPane, rPane);
			});
			rPane.setOnMouseEntered(e -> {
				nm.focusOn(lPane, rPane);
			});
			rPane.setOnMouseExited(e -> {
				nm.focusOff(lPane, rPane);
			});
		}
		
		// Action events 
        EventHandler<ActionEvent> toHome = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sh.changeScene(0);}};
        EventHandler<ActionEvent> toAddition = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sh.changeScene(1);}};
        EventHandler<ActionEvent> toSettings = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sh.changeScene(2);}};
        EventHandler<ActionEvent> toUser = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sh.changeScene(3);}};	//test

        nm.getLeftSideButtons()[0].setOnAction(toHome);
        nm.getRightSideButtons()[0].setOnAction(toHome);
        
        nm.getLeftSideButtons()[1].setOnAction(toAddition);
        nm.getRightSideButtons()[1].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[2].setOnAction(toUser);
        nm.getRightSideButtons()[2].setOnAction(toUser);
        
        nm.getLeftSideButtons()[3].setOnAction(toAddition);
        nm.getRightSideButtons()[3].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[4].setOnAction(toAddition);
        nm.getRightSideButtons()[4].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[5].setOnAction(toSettings);
        nm.getRightSideButtons()[5].setOnAction(toSettings);
		
	}
}
