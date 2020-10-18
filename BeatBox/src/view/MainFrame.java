package view;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedList;
/**
 * The view class that gets all objects from the main frame.
 * Main frame is a custom made frame that is used instead of the default frame.
 * Contains getters for each object and a function to add a scene to the application.
 * @author Philip
 * @version 1.0
 * @since 2020-10-16
 */
public class MainFrame implements GUIHandler{

    private Button closeButton;
    private Button fullScreenButton;
    private Button minimizeButton;
    private LinkedList<Button> buttons;

    private AnchorPane closePane;
    private AnchorPane fullScreenPane;
    private AnchorPane minimizePane;
    private LinkedList<AnchorPane> panes;

    private AnchorPane root;
    private AnchorPane scenePane;

    /**
     * The constructor of the MainFrame class
     * Gets the root of the MainFrame fxml-file.
     * Calls the method getGUIObjects.
     * @param root the root of the MainFrame fxml-file.
     *
     */
    public MainFrame(AnchorPane root) {
        this.root = root;
        getGUIObjects();
    }

    @Override
    public void getGUIObjects() {
        closeButton = (Button) root.lookup("#closeButton");
        fullScreenButton = (Button) root.lookup("#fullScreenButton");
        minimizeButton = (Button) root.lookup("#minimizeButton");
        buttons = new LinkedList<Button>();
        buttons.add(closeButton);
        buttons.add(fullScreenButton);
        buttons.add(minimizeButton);

        panes = new LinkedList<AnchorPane>();
        closePane = (AnchorPane) root.lookup("#closePane");
        fullScreenPane = (AnchorPane) root.lookup("#fullScreenPane");
        minimizePane = (AnchorPane) root.lookup("#minimizePane");
        panes.add(closePane);
        panes.add(fullScreenPane);
        panes.add(minimizePane);

        scenePane = (AnchorPane) root.lookup("#scenePane");
    }

    public LinkedList<Button> getButtons() {
        return buttons;
    }

    public LinkedList<AnchorPane> getPanes() {
        return panes;
    }

    /**
     * The constructor of the MainFrame class
     * Gets the root of the MainFrame fxml-file.
     * Calls the method getGUIObjects.
     * @param pane the pane to be added to the application.
     *
     */
    public void addScene(AnchorPane pane) {
        if(pane!=null) {
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setBottomAnchor(pane, 0.0);

            scenePane.getChildren().clear();
            scenePane.getChildren().add(pane);

            pane.applyCss();
        }
    }
}
