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
    private String operation;

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
        this.operation = operation;

        switch (operation){
            case "New": dialogMenuGUI.setTitle("New Profile"); dialogMenuGUI.setButtonText("Add"); setOperationAction(newProfile); break;
            case "Switch":dialogMenuGUI.setTitle("Switch Profile"); dialogMenuGUI.setButtonText(operation); setOperationAction(switchProfile); break;
            case "Delete": dialogMenuGUI.setTitle("Delete Profile"); dialogMenuGUI.setButtonText(operation); setOperationAction(deleteProfile); break;
        }
        setActions();
        sceneHandler.applyScene(5);
    }


    @Override
    public void setActions() {
        dialogMenuGUI.getBackButton().setOnAction(e->{
            sceneHandler.closeScene();
        });

        newProfile = e -> {
            if (delegate.newProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                System.out.println("new profile added");
            } else {
                dialogMenuGUI.showErrorMessage("newProfileError");
            }
        };

        switchProfile = e -> {
            if (delegate.switchProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                sceneHandler.closeScene();
            } else {
                dialogMenuGUI.showErrorMessage("switchError");
            }
        };

        deleteProfile = e -> {
            if (delegate.deleteProfile(Optional.ofNullable(dialogMenuGUI.getTextField().getText()))) {
                System.out.println("profile deleted");
            } else {
                dialogMenuGUI.showErrorMessage("deleteError");
            }
        };
    }

    private void setOperationAction( EventHandler<ActionEvent> e){
        dialogMenuGUI.getTextField().setOnAction(e);
        dialogMenuGUI.getConfirmButton().setOnAction(e);
    }


}
