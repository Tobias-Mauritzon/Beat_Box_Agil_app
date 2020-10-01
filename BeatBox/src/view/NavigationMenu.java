package view;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * This class initializes and manipulates GUI objects from the StartWindow.fxml file.
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
	private AnchorPane pane;
	private LinkedList<AnchorPane> leftPanes;
	private LinkedList<AnchorPane> rightPanes;
	private VBox sideBox;
//	private Button menuButton;
//	private AnchorPane menuButtonPane;
	private LinkedList<Button> leftSideButtons;
	private LinkedList<Button> rightSideButtons;
	
	private String enteredColor = "#505050";
	private String exitedColor = " #303030";
    
    private TranslateTransition openNav;
    private TranslateTransition closeNav;
//	private String[] sideButtonNames;
	
	/***
	 * Constructor for NavigationMenu
	 * 
	 * @param root the main scenes root
	 * @param amountOfPanes the amount of subscenes NavigationMenu has.
	 */
	public NavigationMenu(AnchorPane root) {
		this.root = root;
//		setSideButtonNames();
		getGUIObjects();
		initSlideEffect();   
		sideBox.setTranslateX(-sideBox.getWidth()); //start with closed sidebox
		sideBox.setPrefWidth(0.0);
	}
	
	/***
	 * Sets the name of the sideButton in an array called sideButtonsNames
	 */
//	private void setSideButtonNames() {
//		sideButtonNames = new String[7];
//		sideButtonNames[0] = ("#slideButton");
//		sideButtonNames[1] = ("#homeButton");
//		sideButtonNames[2] = ("#plusButton");
//		sideButtonNames[3] = ("#minusButton");
//		sideButtonNames[4] = ("#multButton");
//		sideButtonNames[5] = ("#divButton");
//		sideButtonNames[6] = ("#settingsButton");
//	}
	
	
	/***
	 * initializes the translate-transition.
	 */
	private void initSlideEffect() {
		openNav=new TranslateTransition(new Duration(350), sideBox);
        openNav.setToX(0.0);
        closeNav=new TranslateTransition(new Duration(350), sideBox);
        closeNav.setToX(-(sideBox.getWidth()));
	}
	
	/***
	 * Starts the translate-transition which makes the sideBox slide.
	 */
	public void slidePanel() {
		if(sideBox.getTranslateX()!=0.0){
			openNav.play();
			sideBox.setPrefWidth(150.0);
			
        }else{
            closeNav.play();
            closeNav.setOnFinished(e->{sideBox.setPrefWidth(0.0);});
            
        }
	}
	
	/***
	 * Sets the color on the left- and right panes to a lighter color
	 * @param lPane the left pane to highlight.
	 * @param rPane the right pane to highlight.
	 */
	public void focusOn(AnchorPane lPane, AnchorPane rPane) {
		lPane.setStyle("-fx-background-color:" + enteredColor);
		if(rPane!=null) {
			rPane.setStyle("-fx-background-color:" + enteredColor);
		}
		
	}
	
	/***
	 * Sets the color on the left- and right panes to default color
	 * @param lPane the left pane to set to default color.
	 * @param rPane the right pane to set to default color.
	 */
	public void focusOff(AnchorPane lPane, AnchorPane rPane) {
		lPane.setStyle("-fx-background-color:" + exitedColor);
		if(rPane!=null) {
			rPane.setStyle("-fx-background-color:" + exitedColor);
		}
		
	}
	
	/***
	 * Initialize the GUI elements
	 * Sets up the Panes and the Buttons with the corresponding text.
	 */
	@Override
	public void getGUIObjects() {
		
		leftPanes = new LinkedList<AnchorPane>();
		leftPanes.add((AnchorPane) root.lookup("#slideButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#homeButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#plusButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#minusButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#multButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#divButtonPane"));
		leftPanes.add((AnchorPane) root.lookup("#settingsButtonPane"));
		
		leftSideButtons = new LinkedList<Button>();
		leftSideButtons.add((Button) root.lookup("#slideButton"));
		leftSideButtons.add((Button) root.lookup("#homeButton"));
		leftSideButtons.add((Button) root.lookup("#plusButton"));
		leftSideButtons.add((Button) root.lookup("#minusButton"));
		leftSideButtons.add((Button) root.lookup("#multButton"));
		leftSideButtons.add((Button) root.lookup("#divButton"));
		leftSideButtons.add((Button) root.lookup("#settingsButton"));
		
		
		rightPanes = new LinkedList<AnchorPane>();
		rightPanes.add(null);
		rightPanes.add((AnchorPane) root.lookup("#homeButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#plusButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#minusButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#multButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#divButtonPane1"));
		rightPanes.add((AnchorPane) root.lookup("#settingsButtonPane1"));

		rightSideButtons = new LinkedList<Button>();
		rightSideButtons.add(null);
		rightSideButtons.add((Button) root.lookup("#homeButton1"));
		rightSideButtons.add((Button) root.lookup("#plusButton1"));
		rightSideButtons.add((Button) root.lookup("#minusButton1"));
		rightSideButtons.add((Button) root.lookup("#multButton1"));
		rightSideButtons.add((Button) root.lookup("#divButton1"));
		rightSideButtons.add((Button) root.lookup("#settingsButton1"));
//		for(int i = 0; i< sideButtonNames.length; i++) {
//			leftSideButtons.add((Button) root.lookup(sideButtonNames[i]));
//			rightSideButtons.add((Button) root.lookup(sideButtonNames[i]+"1"));
//		}
		
		sideBox = (VBox) root.lookup("#sideBox");
		pane = (AnchorPane) root.lookup("#rootPane");
	}

	
	/***
	 *  gets the menu button 
	 * @return returns menubutton 
	 */
	
	/***
	 *  gets the array of leftPanes
	 * @return returns leftPanes array
	 */
	public LinkedList<AnchorPane> getLeftPanes() {
		return leftPanes;
	}
	/***
	 *  gets the array of rightPanes
	 * @return returns rightPanes array
	 */
	public LinkedList<AnchorPane> getRightPanes() {
		return rightPanes;
	}
	/***
	 *  gets the enter color
	 * @return returns color string
	 */
	public String getEnteredColor() {
		return enteredColor;
	}
	/***
	 *  gets the exited color
	 * @return returns color string
	 */
	public String getExitedColor() {
		return exitedColor;
	}
	
	/***
	 *  gets the LeftSideButtons array
	 * @return returns the LeftSideButtons array
	 */
	public LinkedList<Button> getLeftSideButtons() {
		return leftSideButtons;
	}
	
	/***
	 *  gets the RightSideButtons array
	 * @return returns the RightSideButtons array
	 */
	public LinkedList<Button> getRightSideButtons() {
		return rightSideButtons;
	}
	
	/***
	 *  gets the root pane of the sub scene
	 * @return returns the AnchorPane which is the root for the sub scene
	 */
	public AnchorPane getBasePane() {
		return pane;
	}
	

}