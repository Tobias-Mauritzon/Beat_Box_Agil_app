package view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuGUI {
	public Button addEasy;
	public Button addInzane;
	public Button basEasy;
	public Button basInzane;
	public Button secButton;
	
	private VBox root;
	
	public MenuGUI(VBox root) {
		this.root = root;
		initGUIObjects();
	}
	
	private void initGUIObjects() {
		addEasy = (Button) root.lookup("#additioneasy");
		addInzane = (Button) root.lookup("#additioninzane");
		basEasy = (Button) root.lookup("#basicseasy");
		basInzane = (Button) root.lookup("#basicsinzane");
		secButton = (Button) root.lookup("#secretbutton");
	}
}
