package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.SceneHandler;
import view.DialogMenuGUI;

import java.util.Optional;

public class DialogMenuController implements ControllerInterface{

    private final DialogMenuGUI dialogMenuGUI;
    private final SceneHandler sceneHandler;
    private Delegate delegate;

    EventHandler<ActionEvent> newProfile;
    EventHandler<ActionEvent> switchProfile;
    EventHandler<ActionEvent> deleteProfile;

    public DialogMenuController(DialogMenuGUI dialogMenuGUI, SceneHandler sceneHandler){
        this.dialogMenuGUI = dialogMenuGUI;
        this.sceneHandler = sceneHandler;
        setActions();
    }

    public void setDelegate(Delegate delegate){
        this.delegate = delegate;
    }

    public interface Delegate {
        boolean newProfile(Optional<String> name);
        boolean switchProfile(Optional<String> name);
        boolean deleteProfile(Optional<String> name);
    }

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
        setActions();
        sceneHandler.applyScene(5);
    }

    @Override
    public void setActions() {
        dialogMenuGUI.getBackButton().setOnAction(e-> sceneHandler.closeScene(400));

        newProfile = e -> {
            if (delegate.newProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                dialogMenuGUI.printInfoText(dialogMenuGUI.getTextField().getText() + " was added.",false);
                sceneHandler.closeScene(800);
            } else if(dialogMenuGUI.getTextField().getText().isEmpty()){
                dialogMenuGUI.printInfoText("Error: no name entered.",true);
            }else{

            }
        };

        switchProfile = e -> {
            if (delegate.switchProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                dialogMenuGUI.printInfoText("Switched to: " + dialogMenuGUI.getTextField().getText(),false);
                sceneHandler.closeScene(800);
            } else if(dialogMenuGUI.getTextField().getText().isEmpty()){
                dialogMenuGUI.printInfoText("Error: no name entered.",true);
            } else {
                dialogMenuGUI.printInfoText(dialogMenuGUI.getTextField().getText()+" does not exist.",true);
            }
        };

        deleteProfile = e -> {
            if (delegate.deleteProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                dialogMenuGUI.printInfoText(dialogMenuGUI.getTextField().getText() + " was deleted.",false);
                sceneHandler.closeScene(800);
            } else if(dialogMenuGUI.getTextField().getText().isEmpty()) {
                dialogMenuGUI.printInfoText("Error: no name entered.", true);
            } else {
                dialogMenuGUI.printInfoText(dialogMenuGUI.getTextField().getText()+" does not exist.",true);
            }
        };
    }

    private void setOperationAction( EventHandler<ActionEvent> e){
        dialogMenuGUI.getTextField().setOnAction(e);
        dialogMenuGUI.getConfirmButton().setOnAction(e);
    }
}
