package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

		nm.menuButton.setOnMouseClicked(e -> {
			nm.slidePanel();
		});
		nm.menuButtonPane.setOnMouseEntered(e -> {
			nm.menuButtonPane.setStyle("-fx-background-color:" + nm.enteredColor);
		});
		nm.menuButtonPane.setOnMouseExited(e -> {
			nm.menuButtonPane.setStyle("-fx-background-color:" + nm.exitedColor);
		});

		// set actions on sidepane objects
		for (int i = 0; i < nm.leftPanes.length; i++) {
			AnchorPane lPane = nm.leftPanes[i];
			AnchorPane rPane = nm.rightPanes[i];
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

        nm.getLeftSideButtons()[0].setOnAction(toHome);
        nm.getRightSideButtons()[0].setOnAction(toHome);
        
        nm.getLeftSideButtons()[1].setOnAction(toAddition);
        nm.getRightSideButtons()[1].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[2].setOnAction(toAddition);
        nm.getRightSideButtons()[2].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[3].setOnAction(toAddition);
        nm.getRightSideButtons()[3].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[4].setOnAction(toAddition);
        nm.getRightSideButtons()[4].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[5].setOnAction(toSettings);
        nm.getRightSideButtons()[5].setOnAction(toSettings);
		
	}
}
