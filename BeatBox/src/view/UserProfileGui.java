package view;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.UserProfile;
import model.SaveManager;


public class UserProfileGui extends Application{
	
	private Label userName;
	private Button profileNew;
    private Button profileSwitch;
    private Button profileDelete;
    private TabPane root;
    private TextInputDialog dialog;
    private UserProfile profile = new UserProfile("Joachim");
    
    public static void main(String[] args) {
    	Locale.setDefault(Locale.ENGLISH);
        launch();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	try {
    		root = (TabPane)FXMLLoader.load(getClass().getResource("/view/UserProfile.fxml"));
    	    
    		Scene scene = new Scene(root, 600, 600);
    		scene.getStylesheets().add(getClass().getResource("/view/UserProfile.css").toExternalForm());
    		stage.setTitle("User profile test");
    		stage.setScene(scene);
    		stage.show();
    		
    		getGUIObjects();
    		setup();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    
    }
    
   /***
    *  Initializes the GUI elements so the users can interact with them.
    */
   private void getGUIObjects() {
	   	userName  = (Label) root.lookup("#profileName");
		profileNew  = (Button) root.lookup("#profileNew");
	    profileSwitch = (Button) root.lookup("#profileSwitch");
	    profileDelete = (Button) root.lookup("#profileDelete");
	    
	    dialog = new TextInputDialog(profile.getName());
		dialog.getDialogPane().getStylesheets().add(getClass().getResource("/view/dialog.css").toExternalForm());
		dialog.setGraphic(null);
		dialog.setHeaderText("");
		dialog.setContentText("Profile name:");
    }
   
   private void setup() {
	   userName.setText(profile.getName());
	   
	   profileNew.setOnAction((event) -> {
		   dialog.setTitle("New Profile");
		   
		   Optional<String> name = dialog.showAndWait();
		   try {
			   SaveManager.saveFile(profile, profile.getName() + "Profile.Save");
		   }catch(IOException e) {
			   errorMessage("Could not save profile: " + profile.getName() + "!");
		   }
		   profile = new UserProfile(name.get());
		   userName.setText(name.get());
	   });
	   
	   profileSwitch.setOnAction((event) -> {
		   dialog.setTitle("Switch Profile");
		   
		   Optional<String> name = dialog.showAndWait();
		   try {
			   SaveManager.saveFile(profile, profile.getName() + "Profile.Save");
			   
			   profile = (UserProfile) SaveManager.loadFile(name.get() + "Profile.Save");
		   }catch(IOException | ClassNotFoundException e) {
			   errorMessage("Could not switch profile!");
		   }
		   userName.setText(profile.getName());
	   });

	   profileDelete.setOnAction((event) -> {
		   try {
			   Optional<String> name = dialog.showAndWait();
			   SaveManager.deleteFile(name.get() + "Profile.Save");
		   }catch(IOException e) {
			   errorMessage("Could not delete profile: " + profile.getName() + "!");
		   }
		   userName.setText(profile.getName());
	   });
   }
   
   private void errorMessage(String error) {
	   Alert alert = new Alert(AlertType.ERROR);
	   alert.getDialogPane().getStylesheets().add(getClass().getResource("/view/dialog.css").toExternalForm());
	   alert.setTitle("Error Detected");
	   alert.setHeaderText("");
	   alert.setContentText(error);

	   alert.showAndWait();
   }
}
