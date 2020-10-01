package view;
/**
 * Class that creates a a prototype for the user profile gui
 * @author Joachim Antfolk, Tobias Mauritzon
 * @since 2020-09-25
 */
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.UserProfile2;
import model.History;
import model.Operator;
import model.SaveManager;


public class UserProfileGui2 extends Application{
	
	private Label userName;
	private Button profileNew;
    private Button profileSwitch;
    private Button profileDelete;
    private TabPane root;
    private UserProfile2 profile;
    private TableView<History> history;
    
    public static void main(String[] args) {
    	Locale.setDefault(Locale.ENGLISH); //Sets button text to english
        launch();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	try {
    		root = (TabPane)FXMLLoader.load(getClass().getResource("/FXML/UserProfile.fxml"));
    	    
    		Scene scene = new Scene(root, 600, 600);
    		scene.getStylesheets().add(getClass().getResource("/CSS/userprofile.css").toExternalForm());
    		stage.setTitle("User profile test");
    		stage.setScene(scene);
    		stage.show();
    		
    		getGUIObjects();
    		setup();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    
    }
    
   /**
    *  Initializes the GUI elements so the users can interact with them.
    */
   private void getGUIObjects() {
	   	userName  = (Label) root.lookup("#profileName");
		profileNew  = (Button) root.lookup("#profileNew");
	    profileSwitch = (Button) root.lookup("#profileSwitch");
	    profileDelete = (Button) root.lookup("#profileDelete");
	    history = (TableView<History>) root.lookup("#history");
	    
    }
   
   /**
    * Creates a user profile, updates user name text and programs buttons 
    */
   private void setup() {
	   profile = new UserProfile2("Generic");
	   userName.setText(profile.getName());
	   profile.addProblemToHistory("1 + 2", "3", "3", 4, 3, new Operator[] {Operator.ADD});
	   profile.addProblemToHistory("1 + 3", "4", "4", 4, 3, new Operator[] {Operator.ADD});
	   profile.addProblemToHistory("1 + 4", "5", "5", 4, 3, new Operator[] {Operator.ADD});
	   profile.addProblemToHistory("1 - 4", "10", "-3", 4, 3, new Operator[] {Operator.ADD});
	   profile.addProblemToHistory("5 * 10", "0", "50", 4, 3, new Operator[] {Operator.ADD});
	  
	   TableColumn dateCol = new TableColumn("Date");
	   TableColumn problemCol = new TableColumn("Problem");
       TableColumn userSolutionCol = new TableColumn("User Solution");
       TableColumn rightSolutionCol = new TableColumn("Correct Solution");
       history.getColumns().setAll(dateCol, problemCol, userSolutionCol, rightSolutionCol);
       
       
       dateCol.setCellValueFactory(
			    new PropertyValueFactory<History,String>("date")
			);
	   problemCol.setCellValueFactory(
			    new PropertyValueFactory<History,String>("problem")
			);
	   userSolutionCol.setCellValueFactory(
			    new PropertyValueFactory<History,Double>("userAnswer")
	   );
	   
	   rightSolutionCol.setCellValueFactory(
			    new PropertyValueFactory<History,Double>("correctAnswer")
	   );
	   
	   history.setItems(profile.getHistory());
	   
	   profileNew.setOnAction((event) -> {
		   try {
			   Optional<String> name = getInput("New Profile");
			   
			   if(name.isPresent() && name.get().length() > 0){
				   profile.toArrayList();
				   
				   SaveManager.saveFile(profile, profile.getName() + "Profile.Save");
				   
				   profile = new UserProfile2(name.get());
				   userName.setText(name.get());
				   history.setItems(FXCollections.observableArrayList(profile.getHistory()));
				   profile.addProblemToHistory("5 * 10", "0", "50", 4, 3, new Operator[] {Operator.ADD});
				   
			   }
		   }catch(IOException e) {
			   e.printStackTrace();
			   //errorMessage("Could not save profile: " + profile.getName() + "!");
		   }
	   });
	   
	   profileSwitch.setOnAction((event) -> {
		   try {
			   Optional<String> name = getInput("Switch Profile");
			   
			   if(name.isPresent() && name.get().length() > 0){
				   profile.toArrayList();
				   SaveManager.saveFile(profile, profile.getName() + "Profile.Save");
				   
				   profile = (UserProfile2) SaveManager.loadFile(name.get() + "Profile.Save");
				   profile.toObservableList();
				   
				   userName.setText(profile.getName());
				   history.setItems(FXCollections.observableArrayList(profile.getHistory()));
				   profile.addProblemToHistory("5 * 10", "0", "50", 4, 3, new Operator[] {Operator.ADD});
			   }
		   }catch(IOException | ClassNotFoundException e) {
			   e.printStackTrace();
			   //errorMessage("Could not switch profile!");
		   }
	   });

	   profileDelete.setOnAction((event) -> {
		   try {
			   Optional<String> name = getInput("Delete Profile");
			   
			   if(name.isPresent() && name.get().length() > 0){
				   SaveManager.deleteFile(name.get() + "Profile.Save");
			   }
		   }catch(IOException e) {
			   e.printStackTrace();
			   //errorMessage("Could not delete profile: " + profile.getName() + "!");
		   }
	   });
   }
   
   /**
    * Opens an error dialog with an error message
    * @param error message to display
    */
   private void errorMessage(String error) {
	   Alert alert = new Alert(AlertType.ERROR);
	   alert.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/dialog.css").toExternalForm());
	   alert.setTitle("Error Detected");
	   alert.setHeaderText("");
	   alert.setContentText(error);
	   alert.showAndWait();
   }
   
   /**
    * Opens a dialog that gets user input
    * @param title title for the dialog box window
    * @return profile name as Optional<String>
    */
   private Optional<String> getInput(String title) {
	   	TextInputDialog dialog = new TextInputDialog("");
		dialog.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/dialog.css").toExternalForm());
		dialog.setTitle(title);
		dialog.setGraphic(null);
		dialog.setHeaderText("");
		dialog.setContentText("Profile name:");
		
		return  dialog.showAndWait();
   }
}
