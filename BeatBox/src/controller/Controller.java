package controller;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.SceneHandler;
import view.GUIHandler;
import view.NavigationMenu;
import view.ProblemGUI;

/**
 * A simple controller that handles the input from the user and communicates
 * with the GUI.
 * 
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class Controller {

	private LinkedList<GUIHandler> GUIHandlers;

	private SceneHandler sceneHandler;
	
	/**
	 * Constructor of the SampleController
	 * 
	 * @param GUIHandlers a list of all GUIHandlers so this class can set actions to GUI-objects
	 */
	public Controller(LinkedList<GUIHandler> GUIHandlers, SceneHandler sceneHandler) {
		this.GUIHandlers = GUIHandlers;
		this.sceneHandler = sceneHandler;
		setActionsNavigationMenu();
		setActionsProblemGUI();
	}

	private void setActionsNavigationMenu() {
		NavigationMenu navigationMenu = (NavigationMenu) GUIHandlers.get(0);

		navigationMenu.menuButton.setOnMouseClicked(e -> {
			navigationMenu.slidePanel();
		});
		navigationMenu.menuButtonPane.setOnMouseEntered(e -> {
			navigationMenu.menuButtonPane.setStyle("-fx-background-color:" + navigationMenu.enteredColor);
		});
		navigationMenu.menuButtonPane.setOnMouseExited(e -> {
			navigationMenu.menuButtonPane.setStyle("-fx-background-color:" + navigationMenu.exitedColor);
		});

		// set actions on sidepane objects
		for (int i = 0; i < navigationMenu.leftPanes.length; i++) {
			AnchorPane lPane = navigationMenu.leftPanes[i];
			AnchorPane rPane = navigationMenu.rightPanes[i];
			lPane.setOnMouseEntered(e -> {
				navigationMenu.focusOn(lPane, rPane);
			});
			lPane.setOnMouseExited(e -> {
				navigationMenu.focusOff(lPane, rPane);
			});
			rPane.setOnMouseEntered(e -> {
				navigationMenu.focusOn(lPane, rPane);
			});
			rPane.setOnMouseExited(e -> {
				navigationMenu.focusOff(lPane, rPane);
			});
		}
		
		// Action events 
        EventHandler<ActionEvent> toHome = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(0);}};
        EventHandler<ActionEvent> toAddition = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(1);}};
        EventHandler<ActionEvent> toSettings = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(2);}};

        navigationMenu.getLeftSideButtons()[0].setOnAction(toHome);
        navigationMenu.getRightSideButtons()[0].setOnAction(toHome);
        
        navigationMenu.getLeftSideButtons()[1].setOnAction(toAddition);
        navigationMenu.getRightSideButtons()[1].setOnAction(toAddition);
        
        navigationMenu.getLeftSideButtons()[2].setOnAction(toAddition);
        navigationMenu.getRightSideButtons()[2].setOnAction(toAddition);
        
        navigationMenu.getLeftSideButtons()[3].setOnAction(toAddition);
        navigationMenu.getRightSideButtons()[3].setOnAction(toAddition);
        
        navigationMenu.getLeftSideButtons()[4].setOnAction(toAddition);
        navigationMenu.getRightSideButtons()[4].setOnAction(toAddition);
        
        navigationMenu.getLeftSideButtons()[5].setOnAction(toSettings);
        navigationMenu.getRightSideButtons()[5].setOnAction(toSettings);
		
	}

	private void setActionsProblemGUI() {

		ProblemGUI problemGUI = (ProblemGUI) GUIHandlers.get(1);
//		HomeMenu homeMenu = (ProblemGUI) GUIHandlers.get(1);

		problemGUI.button.setOnAction(e -> {
			problemGUI.answer();
		});
		problemGUI.answerText.setOnAction(e -> {
			problemGUI.answer();
		});
	}

}
