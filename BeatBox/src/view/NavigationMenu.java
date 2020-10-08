package view;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * This class initializes and manipulates GUI objects from the StartWindow.fxml
 * file.
 * 
 * @author Philip
 * @version 1.0
 * @since 2020-09-28
 */
public class NavigationMenu implements GUIHandler {

	/***
	 * All GUI Objects used to build up the NavigationMenu GUI.
	 * 
	 * @see root Root of the main scene
	 * @see pane Root of the subscenes
	 * @see leftPanes Pane containin the symbol for the buttons (always visible)
	 * @see rightPanes Pane containing the text for the buttons (can be closed)
	 * @see amountOfPanes Int containg the amount of sub scenes
	 * @see sideBox the Sidebox which is the base for the navigation menu.
	 * @see menuButton The Button for closing and Opening the sideMenu
	 * @see menuButtonPane The Pane Containg all the menu Buttons
	 * @see leftSideButtons Array Containing all the Button icons
	 * @see rightSideButtons Array Contianing all the Button text
	 * @see enteredColor String ,Color when SideMenu is opened
	 * @see exitedColor String ,Color when SideMenu is closed
	 * @see openNav TranslateTransition for when the SideMenu is Opened
	 * @see closeNav TranslateTransition for when the SideMenu is Closed
	 * @see sideButtonNames Array containing strings with name of the Buttons
	 */
	private AnchorPane root;
	private AnchorPane sideScenepane;
	private AnchorPane backPane;
	private AnchorPane frontPane;
	private LinkedList<AnchorPane> leftPanes;
	private LinkedList<AnchorPane> rightPanes;
	private StackPane sidePane;
	private LinkedList<Button> leftSideButtons;
	private LinkedList<Button> rightSideButtons;

	private TranslateTransition openNav;
	private TranslateTransition closeNav;

	private final Effect frostEffect = new GaussianBlur();

	private AnchorPane slideMenuBackground;
	

	private double focusOnOpacity = 0.08;
	/***
	 * Constructor for NavigationMenu
	 * 
	 * @param root          the main scenes root
	 * @param amountOfPanes the amount of subscenes NavigationMenu has.
	 */
	public NavigationMenu(AnchorPane root) {
		this.root = root;
		getGUIObjects();
		initSlideEffect();
		sidePane.setTranslateX(-sidePane.getWidth()); // start with closed sidebox
		sidePane.setPrefWidth(0.0);
		setVisualEffects();
//		System.out.println( "TEST " + slideMenuBackground.getCssMetaData().toString());
	}

	/***
	 * initializes the translate-transition.
	 */
	private void initSlideEffect() {
		openNav = new TranslateTransition(new Duration(350), sidePane);
		openNav.setToX(0.0);
		closeNav = new TranslateTransition(new Duration(350), sidePane);
		closeNav.setToX(-(sidePane.getWidth()));
	}

	/***
	 * Starts the translate-transition which makes the sideBox slide.
	 */
	public void slidePanel() {
		if (sidePane.getTranslateX() != 0.0) {
			openNav.play();
			sidePane.setPrefWidth(150.0);

		} else {
			closeNav.play();
			closeNav.setOnFinished(e -> {
				sidePane.setPrefWidth(0.0);
			});

		}
	}

	/***
	 * Sets the color on the left- and right panes to a lighter color
	 * 
	 * @param lPane the left pane to highlight.
	 * @param rPane the right pane to highlight.
	 */
	public void focusOn(AnchorPane lPane, AnchorPane rPane) {
		lPane.setOpacity(focusOnOpacity);
		lPane.setStyle("-fx-background-color: white");
		if (rPane != null) {
			rPane.setOpacity(focusOnOpacity);
			rPane.setStyle("-fx-background-color: white");
		}

	}

	/***
	 * Sets the color on the left- and right panes to default color
	 * 
	 * @param lPane the left pane to set to default color.
	 * @param rPane the right pane to set to default color.
	 */
	public void focusOff(AnchorPane lPane, AnchorPane rPane) {
		lPane.setOpacity(0);
		lPane.setStyle("-fx-background-color: transparent");
		if (rPane != null) {
			rPane.setOpacity(0);
			rPane.setStyle("-fx-background-color: transparent");
		}

	}

	/***
	 * Initialize the GUI elements Sets up the Panes and the Buttons with the
	 * corresponding text.
	 */
	@Override
	public void getGUIObjects() {

		leftPanes = new LinkedList<AnchorPane>();
		leftPanes.add((AnchorPane) root.lookup("#slideButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#userButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#homeButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#plusButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#minusButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#multButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#divButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#customParButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#settingsButtonPane"));

		leftSideButtons = new LinkedList<Button>();
		leftSideButtons.add((Button) root.lookup("#slideButton"));
		leftSideButtons.add((Button) root.lookup("#userButton"));
		leftSideButtons.add((Button) root.lookup("#homeButton"));
		leftSideButtons.add((Button) root.lookup("#plusButton"));
		leftSideButtons.add((Button) root.lookup("#minusButton"));
		leftSideButtons.add((Button) root.lookup("#multButton"));
		leftSideButtons.add((Button) root.lookup("#divButton"));
		leftSideButtons.add((Button) root.lookup("#customParButton"));
		leftSideButtons.add((Button) root.lookup("#settingsButton"));

		rightPanes = new LinkedList<AnchorPane>();
		rightPanes.add(null);
		rightPanes.add((AnchorPane) root.lookup("#userButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#homeButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#plusButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#minusButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#multButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#divButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#customParButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#settingsButtonPane1"));

		rightSideButtons = new LinkedList<Button>();
		rightSideButtons.add(null);
		rightSideButtons.add((Button) root.lookup("#userButton1"));
		rightSideButtons.add((Button) root.lookup("#homeButton1"));
		rightSideButtons.add((Button) root.lookup("#plusButton1"));
		rightSideButtons.add((Button) root.lookup("#minusButton1"));
		rightSideButtons.add((Button) root.lookup("#multButton1"));
		rightSideButtons.add((Button) root.lookup("#divButton1"));
		rightSideButtons.add((Button) root.lookup("#customParButton1"));
		rightSideButtons.add((Button) root.lookup("#settingsButton1"));

		sidePane = (StackPane) root.lookup("#sidePane");
		slideMenuBackground = (AnchorPane) root.lookup("slideMenuBackground");
		sideScenepane = (AnchorPane) root.lookup("#scenePane");
		backPane = (AnchorPane) root.lookup("#backPane");
		frontPane = (AnchorPane) root.lookup("#frontPane");
	}

	private void setVisualEffects() {
//		sideBoxBackground.setEffect(frostEffect);
	}

	/***
	 * gets the array of leftPanes
	 * 
	 * @return returns leftPanes array
	 */
	public LinkedList<AnchorPane> getLeftPanes() {
		return leftPanes;
	}

	/***
	 * gets the array of rightPanes
	 * 
	 * @return returns rightPanes array
	 */
	public LinkedList<AnchorPane> getRightPanes() {
		return rightPanes;
	}

	
	/***
	 * gets the LeftSideButtons array
	 * 
	 * @return returns the LeftSideButtons array
	 */
	public LinkedList<Button> getLeftSideButtons() {
		return leftSideButtons;
	}

	/***
	 * gets the RightSideButtons array
	 * 
	 * @return returns the RightSideButtons array
	 */
	public LinkedList<Button> getRightSideButtons() {
		return rightSideButtons;
	}

	/***
	 * gets the root pane of the sub scene
	 * 
	 * @return returns the AnchorPane which is the root for the sub scene
	 */
	public AnchorPane getSideScenePane() {
		return sideScenepane;
	}

	public AnchorPane getBackPane() {
		return backPane;
	}
	
	public AnchorPane getFrontPane() {
		return frontPane;
	}

}