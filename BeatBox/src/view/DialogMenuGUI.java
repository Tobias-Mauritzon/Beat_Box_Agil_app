package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The view class that gets all objects from the dialog menu.
 * Contains getters for each object.
 * @author Philip
 * @version 1.0
 * @since 2020-10-16
 */
public class DialogMenuGUI implements GUIHandler{

    private final AnchorPane root;

    private Label title;
    private TextField textField;
    private Button confirmButton;
    private Button backButton;
    private Label infoLabel;

    /**
     * The constructor of the SettingsGUI class, gets the root of the SettingsGUI fxml-file.
     * @param root the root of the JavaFX application
     */
    public DialogMenuGUI(AnchorPane root){
        this.root = root;
        getGUIObjects();
    }

    @Override
    public void getGUIObjects() {
        title = (Label) root.lookup("#title");
        textField = (TextField) root.lookup("#textField");
        confirmButton = (Button) root.lookup("#confirmButton");
        backButton = (Button) root.lookup("#backButton");
        infoLabel = (Label) root.lookup("#infoLabel");
    }

    /**
     * Sets the title of the dialog menu
     * @param t title of the dialog menu
     */
    public void setTitle(String t){
        title.setText(t);
    }

    public void setButtonText(String t){
        confirmButton.setText(t);
    }


    public TextField getTextField() {
        return textField;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public Button getBackButton() {
        return backButton;
    }



    public void printInfoText(String s,boolean error) {
        infoLabel.setText(s);
        if(error){
            infoLabel.setStyle("-fx-text-fill: red;");
        }else{
            infoLabel.setStyle("-fx-text-fill: -color1;");
        }
        infoLabel.setOpacity(1);
    }


    public void resetInfoLabel(){
        infoLabel.setText("");
        infoLabel.setOpacity(0);
        infoLabel.setStyle("-fx-text-fill: -color1;");
    }
}
