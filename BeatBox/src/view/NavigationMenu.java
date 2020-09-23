package view;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * This class initializes and manipulates GUI objects from the StartWindow.fxml file.
 * @author Philip
 * @version 1.0
 * @since 2020-09-19
 */
public class NavigationMenu {
	
	private AnchorPane root;
	private AnchorPane rootPane;
	private AnchorPane[] leftPanes;
	private AnchorPane[] rightPanes;
	private int amountOfPanes;
	private VBox sideBox;
	private Button menuButton;
	private AnchorPane menuButtonPane;
	private Button[] leftSideButtons;
	private Button[] rightSideButtons;
	
	private String enteredColor = "#505050";
    private String exitedColor = " #303030";
    
    private TranslateTransition openNav;
    private TranslateTransition closeNav;
	private String[] sideButtonNames;
    
	public NavigationMenu(AnchorPane root, int amountOfPanes) {
		this.root = root;
		this.amountOfPanes = amountOfPanes;
		setSideButtonNames();
		getGUIObjects();
		initSlideEffect();
		setActionsOnObjects();        
	}
	
	private void setSideButtonNames() {
		sideButtonNames = new String[6];
		sideButtonNames[0] = ("#homeButton");
		sideButtonNames[1] = ("#plusButton");
		sideButtonNames[2] = ("#minusButton");
		sideButtonNames[3] = ("#multButton");
		sideButtonNames[4] = ("#divButton");
		sideButtonNames[5] = ("#settingsButton");
	}
	
	public Button[] getLeftSideButtons() {
		return leftSideButtons;
	}
	public Button[] getRightSideButtons() {
		return rightSideButtons;
	}

	public AnchorPane getRoot() {
		return rootPane;
	}
	/**
	 * Starts the translate-transition which makes the sideBox slide.
	 */
	private void initSlideEffect() {
		openNav=new TranslateTransition(new Duration(350), sideBox);
        openNav.setToX(0);
        closeNav=new TranslateTransition(new Duration(350), sideBox);
        closeNav.setToX(-(sideBox.getWidth()));
	}
	
	/**
	 * Sets all actions for the GUI-objects
	 */
	private void setActionsOnObjects() {
		menuButton.setOnMouseClicked(e->{slidePanel();});
        menuButtonPane.setOnMouseEntered(e->{menuButtonPane.setStyle("-fx-background-color:" + enteredColor);});
        menuButtonPane.setOnMouseExited(e->{menuButtonPane.setStyle("-fx-background-color:" + exitedColor);});

        
        //set actions on sidepane objects
		for(int i = 0; i<leftPanes.length; i++) {
			AnchorPane lPane = leftPanes[i];
			AnchorPane rPane = rightPanes[i];
			lPane.setOnMouseEntered(e->{focusOn(lPane,rPane);});
			lPane.setOnMouseExited(e->{focusOff(lPane,rPane);});
			rPane.setOnMouseEntered(e->{focusOn(lPane,rPane);});
			rPane.setOnMouseExited(e->{focusOff(lPane,rPane);});
		}		
	}
	
	
	
	/**
	 * Starts the translate-transition which makes the sideBox slide.
	 */
	private void slidePanel() {
		if(sideBox.getTranslateX()!=0){
			sideBox.setPrefWidth(150);
            openNav.play();
      
        }else{
            closeNav.play();
            closeNav.setOnFinished(e->{sideBox.setPrefWidth(0);});
            
        }
	}
	
	/**
	 * Sets the color on the left- and right panes to a lighter color
	 * @param lPane the left pane to highlight.
	 * @param rPane the right pane to highlight.
	 */
	private void focusOn(AnchorPane lPane, AnchorPane rPane) {
		lPane.setStyle("-fx-background-color:" + enteredColor);
		rPane.setStyle("-fx-background-color:" + enteredColor);
	}
	
	/**
	 * Sets the color on the left- and right panes to default color
	 * @param lPane the left pane to set to default color.
	 * @param rPane the right pane to set to default color.
	 */
	private void focusOff(AnchorPane lPane, AnchorPane rPane) {
		lPane.setStyle("-fx-background-color:" + exitedColor);
		rPane.setStyle("-fx-background-color:" + exitedColor);
	}
	
	/**
	 * Initialize the GUI-objects.
	 */
	private void getGUIObjects() {
		
		leftPanes = new AnchorPane[amountOfPanes];
		leftPanes[0] = (AnchorPane) root.lookup("#homeButtonPane");
		leftPanes[1] = (AnchorPane) root.lookup("#plusButtonPane");
		leftPanes[2] = (AnchorPane) root.lookup("#minusButtonPane");
		leftPanes[3] = (AnchorPane) root.lookup("#multButtonPane");
		leftPanes[4] = (AnchorPane) root.lookup("#divButtonPane");
		leftPanes[5] = (AnchorPane) root.lookup("#settingsButtonPane");
		
		rightPanes = new AnchorPane[amountOfPanes];
		rightPanes[0] = (AnchorPane) root.lookup("#homeButtonPane1");
		rightPanes[1] = (AnchorPane) root.lookup("#plusButtonPane1");
		rightPanes[2] = (AnchorPane) root.lookup("#minusButtonPane1");
		rightPanes[3] = (AnchorPane) root.lookup("#multButtonPane1");
		rightPanes[4] = (AnchorPane) root.lookup("#divButtonPane1");
		rightPanes[5] = (AnchorPane) root.lookup("#settingsButtonPane1");
		
		leftSideButtons = new Button[amountOfPanes];
		rightSideButtons = new Button[amountOfPanes];
		for(int i = 0; i< leftSideButtons.length; i++) {
			leftSideButtons[i] = (Button) root.lookup(sideButtonNames[i]);
			rightSideButtons[i] = (Button) root.lookup(sideButtonNames[i]+"1");
		}
		
		sideBox = (VBox) root.lookup("#sideBox");
		menuButtonPane = (AnchorPane) root.lookup("#menuButtonPane");
		menuButton = (Button) root.lookup("#menuButton");
		rootPane = (AnchorPane) root.lookup("#rootPane");
	}
}