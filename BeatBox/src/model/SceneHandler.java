package model;

import java.util.LinkedList;

import javafx.scene.Scene;
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
	 LinkedList<Scene> sceneList;
	 Scene currentScene;
	 Stage stage;
	 
	 public SceneHandler(LinkedList<Scene> scenelist, Stage stage) 
	 {		 
		 this.sceneList = scenelist;
		 this.stage = stage;
	 }
	 
	 
	 /***
	  * Changes the the scene to another scene  if the inputed index exists in sceneList
	  * @param i the index of the scene we want to change to in the sceneList
	  */
	 void changeScene(int i) 
	 {
		 currentScene =  sceneList.get(i);
		 if(currentScene != null) 
		 {
			 //stage.hide();
			 stage.setScene(currentScene);			
			 stage.show();
			 stage.setMinWidth(600);
			 stage.setMinHeight(600);
		     stage.setWidth(600);
		     stage.setHeight(600);
		 }

	 }
}
