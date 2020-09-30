package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import model.SaveManager;
import model.UserProfile;
import view.UserProfileGUI;

public class UserController implements ControllerInterface {

	private UserProfileGUI up;
	private UserProfile user;

	
	public UserController(UserProfileGUI up, UserProfile user) {
		this.up = up;
		this.user = user;
	}
	
	@Override
	public void setActions() {
		
		   up.setUserNameLabel(user.getName());
		   
		   up.getPSwitchButton().setOnAction((event) -> {
			   try {
				   Optional<String> name = up.getInput("Switch Profile");
				   
				   if(name.isPresent() && name.get().length() > 0){
					   SaveManager.saveFile(user, user.getName() + "Profile.Save");
					   user = (UserProfile) SaveManager.loadFile(name.get() + "Profile.Save");
					   
					   up.setUserNameLabel(user.getName());
				   }
			   }catch(IOException | ClassNotFoundException e) {
				   up.errorMessage("Could not switch profile!");
			   }
		   });
		   
		   
		   up.getPDeleteButton().setOnAction((event) -> {
			   try {
				   Optional<String> name = up.getInput("Delete Profile");
				   
				   if(name.isPresent() && name.get().length() > 0){
					   SaveManager.deleteFile(name.get() + "Profile.Save");
				   }
			   }catch(IOException e) {
				   up.errorMessage("Could not delete profile: " + user.getName() + "!");
			   }
		   });
		
	}

}
