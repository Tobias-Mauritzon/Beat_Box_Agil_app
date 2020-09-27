package model;

import java.util.LinkedList;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/***
 * The SceneHandler class, contains a list containing a the scenes of the application and has a function to change between them.
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-23
 */
public class SceneHandler 
{
	private LinkedList<Node> nodeList;
	private LinkedList<Scene> sceneList;
	private int sceneHeight;
	private int sceneWidth;
	private Scene currentScene;
	private AnchorPane currentPane;
	private Stage stage;
	private AnchorPane rootPane;
	private Boolean subScenes = false;
	
	/***
	 * a Constructor for sceneHandler when the scenes are sepearate windows
	 * @param scenelist the list of scenes the program has
	 * @param stage reference to the main stage of the applcation
	 * @param h the height of the window
	 * @param w the width of the window
	 */
	public SceneHandler(LinkedList<Scene> scenelist, Stage stage, int h, int w) 
	{		 
		this.sceneList = scenelist;
		this.stage = stage;
		sceneHeight = h;
		sceneWidth = w;
		subScenes = false;
	}
	/***
	 * a Constructor for sceneHandler when the scenes are subscenes of the navigation bar scene
	 * @param nodeList the list of the subscenes the main menu has.
	 * @param pane reference to the subscene pane
	 * @param h the height of the window
	 * @param w the width of the window
	 */
	public SceneHandler(LinkedList<Node> nodeList, AnchorPane rootPane) 
	{		 
		this.nodeList = nodeList;
		this.rootPane = rootPane;
		subScenes = true;
	}
	 
	 
	/***
	 * Changes the the scene to another scene  if the inputed index exists in sceneList
	 * @param i the index of the scene we want to change to in the sceneList
	 */
	public void changeScene(int i) 
	{
		if(subScenes) 
		{
			currentPane = (AnchorPane) nodeList.get(i);
			{
				if(currentPane != null) 
				{
					//pane.setStyle(currentPane.getStyle());
			        AnchorPane.setTopAnchor(currentPane, 0.0);
			        AnchorPane.setRightAnchor(currentPane, 0.0);
			        AnchorPane.setLeftAnchor(currentPane, 0.0);
			        AnchorPane.setBottomAnchor(currentPane, 0.0);
			        rootPane.getChildren().clear();
			        rootPane.getChildren().add(currentPane);
				}
			}
		}
		else 
		{
			currentScene =  sceneList.get(i);
			if(currentScene != null) 
			{
				//stage.hide();
				stage.setScene(currentScene);			
				stage.show();
				stage.setMinWidth(600);
				stage.setMinHeight(600);
			    stage.setWidth(sceneWidth);
			    stage.setHeight(sceneHeight);
			 }
		}

	 }
}
