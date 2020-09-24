package model;


import java.io.IOException;
import java.util.LinkedList;

import controller.Controller;
import controller.MenuController;
import controller.SampleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.GUIHandler;
import view.MenuGUI;
import view.NavigationMenu;
import view.ProblemGUI;
import view.ProblemGUI.Delegate;

/***
 * the Main class of the application, creates most classes and communicates between them using delegates.
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class Main extends Application{
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	private AnchorPane root;
	private testGenerator gen;
	private grading grade;
	private SceneHandler sh;
	@Override
    public void start(Stage primaryStage) {
        try {
//        	LinkedList<Scene> sceneList = new LinkedList<Scene>();
//        	sceneList.add(createScene("/view/buttontest.fxml","/view/application.css"));
//        	sceneList.add(createScene("/view/MenuGUI.fxml","/view/TabFix.css"));
//        	sceneList.add(createScene("/view/HomeMenu.fxml","/view/application.css"));
//        	sceneList.add(createScene("/view/SettingsMenu.fxml","/view/application.css"));
//        	sceneList.add(createScene("/view/StartWindow.fxml","/view/application.css"));
//			primaryStage.setScene(sceneList.get(1));
        	Scene mainScene = createScene("/view/StartWindow.fxml","/view/application.css");
        	primaryStage.setScene(mainScene);
        	primaryStage.getIcons().add(new Image("/frameIcons/app-icon2.png"));
	        primaryStage.setTitle("Mathematics");
        	LinkedList<Node> nodeList = new LinkedList<Node>();
        	nodeList.add(createSubScene("/view/HomeMenu.fxml"));
        	nodeList.add(createSubScene("/view/buttontest.fxml"));
        	nodeList.add(createSubScene("/view/SettingsMenu.fxml"));
        	
			primaryStage.show();
			primaryStage.setMinWidth(600);
	        primaryStage.setMinHeight(600);
	        primaryStage.setWidth(600);
	        primaryStage.setHeight(600);
	        gen = new testGenerator();
	        grade = new grading();
	        
	        //sh = new SceneHandler(sceneList, primaryStage, 800, 800);

	        NavigationMenu nm = new NavigationMenu(root, 6);
	        sh = new SceneHandler(nodeList, nm.getBasePane());
	        //MenuGUI mg = new MenuGUI((AnchorPane) sceneList.get(1).getRoot());
//	        mg.delegate =  new MenuGUI.Delegate() 
//	        {
//
//				@Override
//				public void changeScene(int i) {
//					sh.changeScene(i);
//				}
//	        	
//	        };
	        //ProblemGUI pg = new ProblemGUI((AnchorPane) sceneList.get(0).getRoot());
	        ProblemGUI pg = new ProblemGUI((AnchorPane) nodeList.get(1));
			LinkedList<GUIHandler> GUIHandlers = new LinkedList<GUIHandler>();
			GUIHandlers.add(nm);
			GUIHandlers.add(pg);

	        pg.delegate =  new ProblemGUI.Delegate() 
	        {
				/***
				 * calls the grade method from the instance of grading
				 * @parameter String s the string to be compared by the grading class
				 */
				@Override
				public Boolean grade(String s) 
				{
					return grade.grade(s);
				}
				
				/***
				 * Generates a new problem through the problem generator and sends the answer to grading and then updates
				 * the problemGUI:s problem text.
				 */
				@Override
				public String getProblem() 
				{
					String[] problemString = gen.getNextProblem();
					grade.setAnswer(problemString[1]);
					return problemString[0];
				}
	        };

	        pg.initProblem();
	        new Controller(GUIHandlers, sh);
	        //new SampleController(pg);
	        //new MenuController(mg);
	        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	/***
	 * Creates a new Scene with a FXML document and a CSS file path as parameters.
	 * @param stringFXML
	 * @param stringCSS
	 * @return returns the created scene
	 */
	private Scene createScene(String stringFXML, String stringCSS) 
	{
    	try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource(stringFXML));
			Scene scene = new Scene(root,root.getWidth(),root.getHeight());
			scene.getStylesheets().add(getClass().getResource(stringCSS).toExternalForm());
			return scene;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
	
	/***
	 * Creates a subscene using the FXML doccuemnt specified by a filepath string
	 * @param stringFXML the filepath for the FXML file
	 * @return returns the subscene as a node
	 */
	private Node createSubScene(String stringFXML) 
	{
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource(stringFXML));
			return pane;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
