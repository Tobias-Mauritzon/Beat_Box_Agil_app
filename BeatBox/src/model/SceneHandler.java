package model;

import java.util.LinkedList;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/***
 * The SceneHandler class, contains a list containing a the scenes of the
 * application and has a function to change between them.
 * 
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-28
 */
public class SceneHandler {
	
	private LinkedList<Scene> sceneList;
	private Scene currentScene;
	private AnchorPane mainPane;

	/***
	 * a Constructor for sceneHandler when the scenes are sepearate windows
	 * 
	 * @param scenelist the list of scenes the program has
	 * @param stage     reference to the main stage of the applcation
	 */
	public SceneHandler(LinkedList<Scene> scenelist, AnchorPane mainPane) {
		this.sceneList = scenelist;
		this.mainPane = mainPane;
	}

	/***
	 * Changes the the scene to another scene if the inputed index exists in
	 * sceneList
	 * 
	 * @param i the index of the scene we want to change to in the sceneList
	 */
	public void changeScene(int i) {
		currentScene = sceneList.get(i);
		if (currentScene != null) {
			// stage.hide();
			AnchorPane currentPane = (AnchorPane) currentScene.getRoot();
			
			AnchorPane.setTopAnchor(currentPane, 0.0);
			AnchorPane.setRightAnchor(currentPane, 0.0);
			AnchorPane.setLeftAnchor(currentPane, 0.0);
			AnchorPane.setBottomAnchor(currentPane, 0.0);
			
			mainPane.getChildren().clear();
			mainPane.getChildren().add(currentPane);
			mainPane.applyCss();
		}
	}
}
