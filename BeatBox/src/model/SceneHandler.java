package model;

import java.util.LinkedList;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.NavigationMenu;

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
	private AnchorPane backPane;
	private AnchorPane frontPane;
	private AnchorPane sideScenePane;

	/***
	 * a Constructor for sceneHandler when the scenes are sepearate windows
	 * 
	 * @param scenelist the list of scenes the program has
	 * @param stage     reference to the main stage of the applcation
	 */
	public SceneHandler(LinkedList<Scene> scenelist, NavigationMenu navMenu) {
		this.sceneList = scenelist;
		this.backPane = navMenu.getBackPane();
		this.frontPane = navMenu.getFrontPane();
		this.sideScenePane = navMenu.getSideScenePane();
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

			FadeTransition ft = new FadeTransition(Duration.millis(800), sideScenePane);
			ft.setFromValue(0.0);
			ft.setToValue(1);
			ft.play();

			sideScenePane.getChildren().clear();
			sideScenePane.getChildren().add(currentPane);
			currentPane.applyCss();
		}
	}

	/***
	 * Sets the scene on top of the whole application.
	 * 
	 * @param i the index of the scene we want to change to in the sceneList
	 */
	public void applyScene(int i) {
		currentScene = sceneList.get(i);
		if (currentScene != null) {
			// stage.hide();
			AnchorPane currentPane = (AnchorPane) currentScene.getRoot();

			AnchorPane.setTopAnchor(currentPane, 0.0);
			AnchorPane.setRightAnchor(currentPane, 0.0);
			AnchorPane.setLeftAnchor(currentPane, 0.0);
			AnchorPane.setBottomAnchor(currentPane, 0.0);

			FadeTransition ft = new FadeTransition(Duration.millis(800), frontPane);
			ft.setFromValue(0.0);
			ft.setToValue(1);
			ft.play();

			backPane.setEffect(new GaussianBlur(8));
			frontPane.getChildren().clear();
			frontPane.getChildren().add(currentPane);
			backPane.setDisable(true);
			frontPane.setDisable(false);
			currentPane.applyCss();
		}

	}
}
