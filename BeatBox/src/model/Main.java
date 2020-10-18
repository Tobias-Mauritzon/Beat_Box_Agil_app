package model;

import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import view.*;

import java.io.IOException;
import java.util.LinkedList;

/***
 * The Main class of the application, creates most classes and communicates
 * between them using delegates.
 *
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class Main extends Application {

	private Scene mainScene;
	private Stage mainStage;
	private LinkedList<Scene> sceneList;

	// View
	private NavigationMenu navigationMenu;
	private ProblemGUI problemGUI;
	private UserProfileGUI userProfileGUI;
	private CustomParametersGUI customParametersGUI;
	private DifficultyGUI diffGUI;
	private MainFrame mainFrame;
	private SettingsGUI settingsGUI;
	private ThemeHandler themeHandler;
	private DialogMenuGUI dialogMenuGUI;

	// Model
	private CustomParametersModel customParameters;
	private UserProfile userProfile;
	private SceneHandler sceneHandler;
	private ProfileHandler profileHandler;
	private NumberGenerator generator;
	private Grading grade;
	private DifficultyPresets diffPresets;

	// Controller
	private ProblemGUIController problemController;
	private NavigationMenuController navigationMenuController;
	private DifficultyPresetsController diffPresetsController;
	private DialogMenuController dialogMenuController;
	private MainFrameController mainFrameController;
	private SettingsController settingsController;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Setup for main scene
		AnchorPane root=null;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/mainFrame.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainScene = new Scene(root, root.getWidth(), root.getHeight());
		primaryStage.setScene(mainScene);
		primaryStage.getIcons().add(new Image("/frameIcons/app-icon2.png"));
		primaryStage.setTitle("Mathematics");
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(550);
		primaryStage.setWidth(900);
		primaryStage.setHeight(550);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();

		mainStage = primaryStage;
		// Create a list of scenes that is used in the sceneHandler
		sceneList = new LinkedList<Scene>();
		sceneList.add(createScene("/FXML/NavigationMenu.fxml")); // [0]
		sceneList.add(createScene("/FXML/UserProfile.fxml")); // [1]
		sceneList.add(createScene("/FXML/ProblemGUI.fxml")); // [2]
		sceneList.add(createScene("/FXML/CustomParametersGUI.fxml")); // [3]
		sceneList.add(createScene("/FXML/SettingsMenu.fxml")); // [4]
		sceneList.add(createScene("/FXML/Difficulty.fxml")); // [5]
		sceneList.add(createScene("/FXML/DialogMenu.fxml")); // [6]

		// Instantiate all objects for the application
		createViewObjects();
		createModelObjects();
		createControllerObjects();
		setDelegates();


		mainFrame.addScene((AnchorPane)sceneList.get(0).getRoot());
	}

	/**
	 * Creates instances of view classes.
	 */
	private void createViewObjects() {
		mainFrame = new MainFrame((AnchorPane)mainScene.getRoot());
		navigationMenu = new NavigationMenu((AnchorPane) sceneList.get(0).getRoot());
		userProfileGUI = new UserProfileGUI((AnchorPane) sceneList.get(1).getRoot());
		problemGUI = new ProblemGUI((AnchorPane) sceneList.get(2).getRoot());
		customParametersGUI = new CustomParametersGUI((AnchorPane) sceneList.get(3).getRoot());
		settingsGUI = new SettingsGUI((AnchorPane) sceneList.get(4).getRoot());
		diffGUI = new DifficultyGUI((AnchorPane) sceneList.get(5).getRoot());
		dialogMenuGUI = new DialogMenuGUI((AnchorPane) sceneList.get(6).getRoot());
	}

	/**
	 * Creates instances of model classes.
	 */
	private void createModelObjects() {
		customParameters = new CustomParametersModel();
		sceneHandler = new SceneHandler(sceneList, navigationMenu);
		generator = new NumberGenerator();
		grade = new Grading();
		profileHandler = new ProfileHandler("test profile");
		diffPresets = new DifficultyPresets();
	}

	/**
	 * Creates instances of controller classes.
	 */
	private void createControllerObjects() {
		mainFrameController = new MainFrameController(mainStage,mainFrame);
		dialogMenuController = new DialogMenuController(dialogMenuGUI,sceneHandler);
		new UserController(userProfileGUI, profileHandler, dialogMenuController);
		navigationMenuController = new NavigationMenuController(navigationMenu, sceneHandler);
		problemController = new ProblemGUIController(problemGUI,grade,generator);
		new CustomParametersController(customParametersGUI,customParameters);
		diffPresetsController = new DifficultyPresetsController(diffPresets, diffGUI);
		themeHandler = new ThemeHandler(mainScene);
		new SettingsController(settingsGUI,themeHandler,sceneHandler,mainStage);

	}

	/**
	 * Sets delegates.
	 */
	private void setDelegates() {
		customParameters.setDelegate(new CustomParametersModel.Delegate() {
			@Override
			public void transmitProblemParameters(ProblemParameters p) {
				generator.setSettings(p);
				sceneHandler.changeScene(2);
				problemController.ResetGUI();
			}
		});

		diffPresets.setDelegate(new DifficultyPresets.Delegate() {
			@Override
			public void transmitProblemParameters(ProblemParameters problemParameters) {
				generator.setSettings(problemParameters);
				sceneHandler.changeScene(2);
				problemController.ResetGUI();
			}
		});

		navigationMenuController.delegate = new NavigationMenuController.Delegate() {
			@Override
			public void setCategory(Operator op) {
				diffPresetsController.setCategory(op);
			}
		};

		grade.delegate = new Grading.Delegate() {
			@Override
			public void setHistory(String p, String a, String u) {
				profileHandler.getCurrentProfile().addProblemToHistory(p, u, a,4,3, null );
			}
		};

	}

	/**
	 * Creates a new scene with a FXML document as parameter.
	 *
	 * @param stringFXML
	 * @return returns the created scene
	 */
	private Scene createScene(String stringFXML) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(stringFXML));
			Scene scene = new Scene(root, root.getWidth(), root.getHeight());
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void stop()
	{
		profileHandler.saveCurrentProfile();
	}


}
