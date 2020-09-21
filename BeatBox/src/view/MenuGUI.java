package view;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
    * Class for our Menu GUI. Very basic model. Will be updated as progress is made. 
    * GUI holds x buttons to navigate around the app. 
*/
public class MenuGUI {
	public Button addEasy;
	public Button addInzane;
	public Button basEasy;
	public Button basInzane;
	public Button secButton;
	
	private VBox root;
	

/**
    * Class for our Menu GUI. Very basic model. Will be updated as progress is made. 
    * GUI holds x buttons to navigate around the app. 
*/
	public MenuGUI(VBox root) {
		this.root = root;
		initGUIObjects();
	}

	
/**
    * Method to init our very basic buttons. No logic connected since we have no backend yet.
*/	
	private void initGUIObjects() {
		addEasy = (Button) root.lookup("#additioneasy");
		addInzane = (Button) root.lookup("#additioninzane");
		basEasy = (Button) root.lookup("#basicseasy");
		basInzane = (Button) root.lookup("#basicsinzane");
		secButton = (Button) root.lookup("#secretbutton");
	}
}
