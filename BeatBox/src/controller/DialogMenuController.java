package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.SceneHandler;
import view.DialogMenuGUI;

import java.util.Optional;

/**
 * The controller class for the dialog menu that is used by the UserController.
 * @author Philip
 * @version 1.0
 * @since 2020-10-17
 *
 */
public class DialogMenuController implements ControllerInterface{

    private final DialogMenuGUI dialogMenuGUI;
    private final SceneHandler sceneHandler;
    private Delegate delegate;

    EventHandler<ActionEvent> newProfile;
    EventHandler<ActionEvent> switchProfile;
    EventHandler<ActionEvent> deleteProfile;

    /**
     * Constructor for the DialogMenuController.
     *
     * @param dialogMenuGUI the view class of dialogMenu.
     * @param sceneHandler the model class scenehandler.
     */
    public DialogMenuController(DialogMenuGUI dialogMenuGUI, SceneHandler sceneHandler){
        this.dialogMenuGUI = dialogMenuGUI;
        this.sceneHandler = sceneHandler;
        setActions();
        setEvents();
    }

    /** Setter for delegate.
     * @param delegate The delegate to set.
     */
    public void setDelegate(Delegate delegate){
        this.delegate = delegate;
    }

    /**
     * A delegate so this class can make profile operations.
     */
    public interface Delegate {
        boolean newProfile(Optional<String> name);
        boolean switchProfile(Optional<String> name);
        boolean deleteProfile(Optional<String> name);
    }

    /**
     * Opens the dialogMenu with customization according to the operation.
     * @param operation The type of profile operation the dialog menu should adapt to.
     */
    public void open(String operation){
        dialogMenuGUI.resetInfoLabel();
        switch (operation) {
            case "New" -> {
                dialogMenuGUI.setTitle("New Profile");
                dialogMenuGUI.setButtonText("Add");
                setOperationAction(newProfile);
            }
            case "Switch" -> {
                dialogMenuGUI.setTitle("Switch Profile");
                dialogMenuGUI.setButtonText(operation);
                setOperationAction(switchProfile);
            }
            case "Delete" -> {
                dialogMenuGUI.setTitle("Delete Profile");
                dialogMenuGUI.setButtonText(operation);
                setOperationAction(deleteProfile);
            }
        }
        setEvents();
        setActions();
        sceneHandler.applyScene(5);
    }

    /**
     * Sets the events for the different profile operations.
     * This method also handles errors.
     */
    private void setEvents(){
        newProfile = e -> {
            if (delegate.newProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                dialogMenuGUI.printInfoText(dialogMenuGUI.getTextField().getText() + " was added.",false);
                sceneHandler.closeScene(800);
            } else if(dialogMenuGUI.getTextField().getText().isEmpty()){
                dialogMenuGUI.printInfoText("Error: no name entered.",true);
            } else{
                dialogMenuGUI.printInfoText("Error: " + dialogMenuGUI.getTextField().getText()+" already exists.",true);
            }
        };

        switchProfile = e -> {
            if (delegate.switchProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                dialogMenuGUI.printInfoText("Switched to: " + dialogMenuGUI.getTextField().getText(),false);
                sceneHandler.closeScene(800);
            } else if(dialogMenuGUI.getTextField().getText().isEmpty()){
                dialogMenuGUI.printInfoText("Error: no name entered.",true);
            } else {
                dialogMenuGUI.printInfoText("Error: " + dialogMenuGUI.getTextField().getText()+" does not exist.",true);
            }
        };

        deleteProfile = e -> {
            if (delegate.deleteProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                dialogMenuGUI.printInfoText(dialogMenuGUI.getTextField().getText() + " was deleted.",false);
                sceneHandler.closeScene(800);
            } else if(dialogMenuGUI.getTextField().getText().isEmpty()) {
                dialogMenuGUI.printInfoText("Error: no name entered.", true);
            } else {
                dialogMenuGUI.printInfoText("Error: " + dialogMenuGUI.getTextField().getText()+" does not exist.",true);
            }
        };
    }

    @Override
    public void setActions() {
        dialogMenuGUI.getBackButton().setOnAction(e-> sceneHandler.closeScene(400));
    }

    /**
     * Sets the specific operation action for textfield and button on the dialog menu.
     * @param event The event that should be set on the textfield and button.
     */
    private void setOperationAction( EventHandler<ActionEvent> event){
        dialogMenuGUI.getTextField().setOnAction(event);
        dialogMenuGUI.getConfirmButton().setOnAction(event);
    }
}
