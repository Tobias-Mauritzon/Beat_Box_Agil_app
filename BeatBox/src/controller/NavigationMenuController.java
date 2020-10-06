package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.Operator;
import model.SceneHandler;
import view.NavigationMenu;
import model.ProblemParameters;

import java.util.ArrayList;
/***
 * The controller for the navigation menu sets the actions for all the buttons so the user can interact with the navigation menu.
* @author Greppe
* @author Philip
* @version 1.0
* @since 2020-10-03
*
*/
public class NavigationMenuController implements ControllerInterface{
	
	
	private NavigationMenu navigationMenu;
	private SceneHandler sceneHandler;

	// TEMP
	public Delegate delegate;
	
	
	/**
	 * The constructor for the navigationMenuController takes a navigationMenu, SceneHandler
	 * @param navigationMenu the navgiationMenu model
	 * @param sceneHandler the sceneHandler instance.
	 */
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
		EventHandler<ActionEvent> toUser = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(0);}};
		EventHandler<ActionEvent> toCustomParameters = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {sceneHandler.changeScene(2);}};//test

		//TEMP
		EventHandler<ActionEvent> toAddition = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				delegate.transmitProblemParameters(new ProblemParameters(
						new ArrayList<Operator>() {{ add(Operator.ADD); }},
						new int[]{0, 10},
						3,
						false)
				);
				sceneHandler.changeScene(1);
			}
		};

		EventHandler<ActionEvent> toSubtraction = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				delegate.transmitProblemParameters(new ProblemParameters(
						new ArrayList<Operator>() {{ add(Operator.SUB); }},
						new int[]{0, 10},
						3,
						false)
				);
				sceneHandler.changeScene(1);
			}
		};

		EventHandler<ActionEvent> toMultiplication = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				delegate.transmitProblemParameters(new ProblemParameters(
						new ArrayList<Operator>() {{ add(Operator.MUL); }},
						new int[]{0, 10},
						3,
						false)
				);
				sceneHandler.changeScene(1);
			}
		};

		EventHandler<ActionEvent> toDivision = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				delegate.transmitProblemParameters(new ProblemParameters(
						new ArrayList<Operator>() {{ add(Operator.DIV); }},
						new int[]{0, 10},
						3,
						false)
				);
				sceneHandler.changeScene(1);
			}
		};
		// END TEMP
		
		// Set actions on all buttons on the navigationMenu
		navigationMenu.getLeftSideButtons().get(0).setOnAction(slidePanel);
		
		navigationMenu.getLeftSideButtons().get(1).setOnAction(toUser);
		navigationMenu.getRightSideButtons().get(1).setOnAction(toUser);
		
		navigationMenu.getLeftSideButtons().get(3).setOnAction(toAddition);
		navigationMenu.getRightSideButtons().get(3).setOnAction(toAddition);
		
		navigationMenu.getLeftSideButtons().get(4).setOnAction(toSubtraction);
		navigationMenu.getRightSideButtons().get(4).setOnAction(toSubtraction);
		
		navigationMenu.getLeftSideButtons().get(5).setOnAction(toMultiplication);
		navigationMenu.getRightSideButtons().get(5).setOnAction(toMultiplication);
		
		navigationMenu.getLeftSideButtons().get(6).setOnAction(toDivision);
		navigationMenu.getRightSideButtons().get(6).setOnAction(toDivision);
		
		navigationMenu.getLeftSideButtons().get(7).setOnAction(toCustomParameters);
		navigationMenu.getRightSideButtons().get(7).setOnAction(toCustomParameters);
	}

	// TEMP
	public interface Delegate {
		void transmitProblemParameters(ProblemParameters p);
	}
	
}
