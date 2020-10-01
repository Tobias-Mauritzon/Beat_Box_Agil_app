package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.SceneHandler;
import view.NavigationMenu;

public class NavigationMenuController implements ControllerInterface{
	
	
	private NavigationMenu navigationMenu;
	private SceneHandler sceneHandler;
	
	
	public NavigationMenuController(NavigationMenu navigationMenu, SceneHandler sceneHandler) {
		this.navigationMenu = navigationMenu;
		this.sceneHandler = sceneHandler;
		setActions();
	}

	/***
	 * Sets up the actions for the navigation Menu
	 */
	@Override
	public void setActions() {
		visualEffectsActions();
		navigationActions();		
	}
	
	/***
	 * Sets up visual effect actions for the navigation Menu
	 */
	private void visualEffectsActions() {
		// set actions on sidepane objects
		for (int i = 0; i < navigationMenu.getLeftPanes().size(); i++) {
			AnchorPane lPane = navigationMenu.getLeftPanes().get(i);
			AnchorPane rPane = navigationMenu.getRightPanes().get(i);
			lPane.setOnMouseEntered(e -> {
				navigationMenu.focusOn(lPane, rPane);
			});
			lPane.setOnMouseExited(e -> {
				navigationMenu.focusOff(lPane, rPane);
			});
			if(rPane!=null) {
				rPane.setOnMouseEntered(e -> {
					navigationMenu.focusOn(lPane, rPane);
				});
				
				rPane.setOnMouseExited(e -> {
					navigationMenu.focusOff(lPane, rPane);
				});
			}
		}
	}
	
	/***
	 * Sets up navigation actions for the navigation Menu
	 */
	private void navigationActions() {
		
		// Create action events that is used for each button on the navigationMenu
		EventHandler<ActionEvent> slidePanel = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {navigationMenu.slidePanel();}};
		EventHandler<ActionEvent> toHome = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(0);}};
		EventHandler<ActionEvent> toAddition = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(1);}};
		EventHandler<ActionEvent> toSettings = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(2);}};
		EventHandler<ActionEvent> toUser = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(3);}};	//test
		
		// Set actions on all buttons on the navigationMenu
		navigationMenu.getLeftSideButtons().get(0).setOnAction(slidePanel);
		
		navigationMenu.getLeftSideButtons().get(1).setOnAction(toHome);
		navigationMenu.getRightSideButtons().get(1).setOnAction(toHome);
		
		navigationMenu.getLeftSideButtons().get(2).setOnAction(toAddition);
		navigationMenu.getRightSideButtons().get(2).setOnAction(toAddition);
		
		navigationMenu.getLeftSideButtons().get(3).setOnAction(toUser);
		navigationMenu.getRightSideButtons().get(3).setOnAction(toUser);
		
		navigationMenu.getLeftSideButtons().get(4).setOnAction(toAddition);
		navigationMenu.getRightSideButtons().get(4).setOnAction(toAddition);
		
		navigationMenu.getLeftSideButtons().get(5).setOnAction(toAddition);
		navigationMenu.getRightSideButtons().get(5).setOnAction(toAddition);
		
		navigationMenu.getLeftSideButtons().get(6).setOnAction(toSettings);
		navigationMenu.getRightSideButtons().get(6).setOnAction(toSettings);
	}
	
}
