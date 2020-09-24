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
public class NavigationMenu extends GUIHandler {
	
	private AnchorPane root;
	private AnchorPane pane;
	public AnchorPane[] leftPanes;
	public AnchorPane[] rightPanes;
	private int amountOfPanes;
	private VBox sideBox;
	public Button menuButton;
	public AnchorPane menuButtonPane;
	private Button[] leftSideButtons;
	private Button[] rightSideButtons;
	
	public String enteredColor = "#505050";
    public String exitedColor = " #303030";
    
    private TranslateTransition openNav;
    private TranslateTransition closeNav;
	private String[] sideButtonNames;
    
	public NavigationMenu(AnchorPane root, int amountOfPanes) {
		this.root = root;
		this.amountOfPanes = amountOfPanes;
		setSideButtonNames();
		getGUIObjects();
		initSlideEffect();     
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

	public AnchorPane getBasePane() {
		return pane;
	}
	/**
	 * initializes the translate-transition.
	 */
	private void initSlideEffect() {
		openNav=new TranslateTransition(new Duration(350), sideBox);
        openNav.setToX(0.0);
        closeNav=new TranslateTransition(new Duration(350), sideBox);
        closeNav.setToX(-(sideBox.getWidth()));
	}
	
	/**
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
	
	/**
	 * Sets the color on the left- and right panes to a lighter color
	 * @param lPane the left pane to highlight.
	 * @param rPane the right pane to highlight.
	 */
	public void focusOn(AnchorPane lPane, AnchorPane rPane) {
		lPane.setStyle("-fx-background-color:" + enteredColor);
		rPane.setStyle("-fx-background-color:" + enteredColor);
	}
	
	/**
	 * Sets the color on the left- and right panes to default color
	 * @param lPane the left pane to set to default color.
	 * @param rPane the right pane to set to default color.
	 */
	public void focusOff(AnchorPane lPane, AnchorPane rPane) {
		lPane.setStyle("-fx-background-color:" + exitedColor);
		rPane.setStyle("-fx-background-color:" + exitedColor);
	}
	
	/**
	 * Initialize the GUI-objects.
	 */
	@Override
	protected void getGUIObjects() {
		
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
		pane = (AnchorPane) root.lookup("#rootPane");
	}
	
	
}