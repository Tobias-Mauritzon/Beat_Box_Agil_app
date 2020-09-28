package view;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import view.ProblemGUI.Delegate;

/**
    * Class for our Menu GUI. Very basic model. Will be updated as progress is made. 
    * GUI holds x buttons to navigate around the app. 
*/
public class MenuGUI {
	
    public interface Delegate 
    {
    	void changeScene(int i);
    }
    
	public Button addEasy;
	public Button addInzane;
	public Button basEasy;
	public Button basInzane;
	public Button secButton;
	
	private AnchorPane root;
    public Delegate delegate;
    


/**
    * Class for our Menu GUI. Very basic model. Will be updated as progress is made. 
    * GUI holds x buttons to navigate around the app. 
*/
	public MenuGUI(AnchorPane root) {
		this.root = root;
		initGUIObjects();
	}

	
	public void changeScene(int i) 
	{
		if(delegate != null) 
		{
			delegate.changeScene(i);
		}
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
