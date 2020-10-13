package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.MainFrame;

import java.util.HashMap;
import java.util.LinkedList;

public class MainFrameController implements ControllerInterface {

    private final MainFrame MAINFRAME;
    private final Stage STAGE;
    private final Scene SCENE;

    // old frame values
    private double oldX = 0.0;
    private double oldY = 0.0;
    private double oldWidth = 0.0;
    private double oldHeight = 0.0;

    // resize
    private final HashMap<Cursor, EventHandler<MouseEvent>> LISTENER = new HashMap<>();
    private final int resizeArea;
    private final int dragArea;

    private double mPresSceneX, mPresSceneY;
    private double mPresScreeX, mPresScreeY;
    private double mPresStageW, mPresStageH;

    private FadeTransition closeTransition;
    private FadeTransition minimizeTransition;
    private ScaleTransition shrinkTransition;

    public MainFrameController(Stage stage, MainFrame mainFrame) {
        this.STAGE = stage;
        this.SCENE = stage.getScene();
        this.MAINFRAME = mainFrame;
        this.resizeArea = 5;
        this.dragArea = 30;

        SCENE.setFill(Color.TRANSPARENT);
        updateOldStageSize();
        initCloseFadeTransition();
        initMinimizeFadeTransition();
        initShrinkTransition();
        setActions();
        createListener();
        launch();
    }

    private void initCloseFadeTransition() {
        closeTransition = new FadeTransition(Duration.millis(300), STAGE.getScene().getRoot());
        closeTransition.setFromValue(1);
        closeTransition.setToValue(0.0);
        closeTransition.setOnFinished(event -> {
            System.exit(0);
        });
    }

    private void initMinimizeFadeTransition() {
        minimizeTransition = new FadeTransition(Duration.millis(250), STAGE.getScene().getRoot());
        minimizeTransition.setFromValue(1);
        minimizeTransition.setToValue(0.0);
        minimizeTransition.setOnFinished(event -> {
            STAGE.setIconified(true);
            STAGE.getScene().getRoot().setOpacity(1);
        });
    }

    private void initShrinkTransition() {
        shrinkTransition = new ScaleTransition(new Duration(250), STAGE.getScene().getRoot());
        shrinkTransition.setToX(0);
        shrinkTransition.setToY(0);
        shrinkTransition.setOnFinished(event -> {
            STAGE.getScene().getRoot().setScaleX(1);
            STAGE.getScene().getRoot().setScaleY(1);
        });
    }

    @Override
    public void setActions() {
        LinkedList<Button> buttons;
        buttons = MAINFRAME.getButtons();

        LinkedList<AnchorPane> panes;
        panes = MAINFRAME.getPanes();

        EventHandler<ActionEvent> close = e -> closeTransition.play();
        EventHandler<ActionEvent> fullScreen = e -> setFullScreen();
        EventHandler<ActionEvent> minimize = e -> {
            shrinkTransition.play();
            minimizeTransition.play();
        };

        buttons.get(0).setOnAction(close);
        buttons.get(1).setOnAction(fullScreen);
        buttons.get(2).setOnAction(minimize);

        AnchorPane p = panes.get(0);
        AnchorPane p1 = panes.get(1);
        AnchorPane p2 = panes.get(2);
        buttons.get(0).addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            p.setStyle("-fx-background-color: #BF1E0F");
            STAGE.getScene().setCursor(Cursor.DEFAULT);
        });
        buttons.get(0).setOnMouseExited(e -> {
            p.setStyle("-fx-background-color: transparent");
        });

        buttons.get(1).setOnMouseEntered(e -> {
            p1.setStyle("-fx-background-color: derive(-baseColor1, 40%)");
            STAGE.getScene().setCursor(Cursor.DEFAULT);
        });
        buttons.get(1).setOnMouseExited(e -> {
            p1.setStyle("-fx-background-color: transparent");
        });

        buttons.get(2).setOnMouseEntered(e -> {
            p2.setStyle("-fx-background-color: derive(-baseColor1, 40%)");
            STAGE.getScene().setCursor(Cursor.DEFAULT);
        });
        buttons.get(2).setOnMouseExited(e -> {
            p2.setStyle("-fx-background-color: transparent");
        });
    }

    private void updateOldStageSize() {
        oldWidth = STAGE.getWidth();
        oldHeight = STAGE.getHeight();
        oldX = STAGE.getX();
        oldY = STAGE.getY();
    }

    private void setFullScreen() {
        for (Screen screen : Screen.getScreens()) {
            Rectangle2D monitor = screen.getVisualBounds();
            Rectangle2D stageRec = new Rectangle2D(STAGE.getX(), STAGE.getY(), STAGE.getWidth(), STAGE.getHeight());
            Rectangle2D window = Screen.getScreensForRectangle(stageRec).get(0).getVisualBounds();

            if (window.equals(monitor)) {
                if (STAGE.getWidth() != window.getWidth() && STAGE.getHeight() != window.getHeight()) {
                    updateOldStageSize();
                    STAGE.setX(window.getMinX());
                    STAGE.setY(window.getMinY());
                    STAGE.setWidth(window.getWidth());
                    STAGE.setHeight(window.getHeight());
                } else {
                    STAGE.setX(oldX);
                    STAGE.setY(oldY);
                    STAGE.setWidth(oldWidth);
                    STAGE.setHeight(oldHeight);
                }
            }

        }
    }

    private void createListener() {
        LISTENER.put(Cursor.NW_RESIZE, event -> {
            double newWidth = mPresStageW - (event.getScreenX() - mPresScreeX);
            double newHeight = mPresStageH - (event.getScreenY() - mPresScreeY);
            if (newHeight > STAGE.getMinHeight()) {
                STAGE.setY(event.getScreenY() - mPresSceneY);
                STAGE.setHeight(newHeight);
            }
            if (newWidth > STAGE.getMinWidth()) {
                STAGE.setX(event.getScreenX() - mPresSceneX);
                STAGE.setWidth(newWidth);
            }
        });

        LISTENER.put(Cursor.NE_RESIZE, event -> {
            double newWidth = mPresStageW + (event.getScreenX() - mPresScreeX);
            double newHeight = mPresStageH - (event.getScreenY() - mPresScreeY);
            if (newHeight > STAGE.getMinHeight()) {
                STAGE.setHeight(newHeight);
                STAGE.setY(event.getScreenY() - mPresSceneY);
            }
            if (newWidth > STAGE.getMinWidth())
                STAGE.setWidth(newWidth);
        });

        LISTENER.put(Cursor.SW_RESIZE, event -> {
            double newWidth = mPresStageW - (event.getScreenX() - mPresScreeX);
            double newHeight = mPresStageH + (event.getScreenY() - mPresScreeY);
            if (newHeight > STAGE.getMinHeight())
                STAGE.setHeight(newHeight);
            if (newWidth > STAGE.getMinWidth()) {
                STAGE.setX(event.getScreenX() - mPresSceneX);
                STAGE.setWidth(newWidth);
            }
        });

        LISTENER.put(Cursor.SE_RESIZE, event -> {
            double newWidth = mPresStageW + (event.getScreenX() - mPresScreeX);
            double newHeight = mPresStageH + (event.getScreenY() - mPresScreeY);
            if (newHeight > STAGE.getMinHeight())
                STAGE.setHeight(newHeight);
            if (newWidth > STAGE.getMinWidth())
                STAGE.setWidth(newWidth);
        });

        LISTENER.put(Cursor.E_RESIZE, event -> {
            double newWidth = mPresStageW - (event.getScreenX() - mPresScreeX);
            if (newWidth > STAGE.getMinWidth()) {
                STAGE.setX(event.getScreenX() - mPresSceneX);
                STAGE.setWidth(newWidth);
            }
        });

        LISTENER.put(Cursor.W_RESIZE, event -> {
            double newWidth = mPresStageW + (event.getScreenX() - mPresScreeX);
            if (newWidth > STAGE.getMinWidth())
                STAGE.setWidth(newWidth);
        });

        LISTENER.put(Cursor.N_RESIZE, event -> {
            double newHeight = mPresStageH - (event.getScreenY() - mPresScreeY);
            if (newHeight > STAGE.getMinHeight()) {
                STAGE.setY(event.getScreenY() - mPresSceneY);
                STAGE.setHeight(newHeight);
            }
        });

        LISTENER.put(Cursor.S_RESIZE, event -> {
            double newHeight = mPresStageH + (event.getScreenY() - mPresScreeY);
            if (newHeight > STAGE.getMinHeight())
                STAGE.setHeight(newHeight);
        });

        LISTENER.put(Cursor.DEFAULT, event -> {
            for (Screen screen : Screen.getScreens()) {
                Rectangle2D monitor = screen.getVisualBounds();
                Rectangle2D stageRec = new Rectangle2D(STAGE.getX(), STAGE.getY(), STAGE.getWidth(), STAGE.getHeight());
                Rectangle2D window = Screen.getScreensForRectangle(stageRec).get(0).getVisualBounds();
                if (window.equals(monitor)) {
                    if (isFullScreen()) {
                        STAGE.setWidth(oldWidth);
                        STAGE.setHeight(oldHeight);
                        // basic maths
                        double xOffset = window.getMinX() * -1;
                        double a = STAGE.getWidth() / window.getWidth();
                        double b = (event.getScreenX() + xOffset) * (1 - a);
                        mPresSceneX = mPresSceneX - b;
                    } else {
                        STAGE.setX(event.getScreenX() - mPresSceneX);
                        STAGE.setY(event.getScreenY() - mPresSceneY);
                    }
                }
            }

        });
    }

    private boolean isFullScreen() {
        Rectangle2D stageRec = new Rectangle2D(STAGE.getX(), STAGE.getY(), STAGE.getWidth(), STAGE.getHeight());
        Rectangle2D window = Screen.getScreensForRectangle(stageRec).get(0).getVisualBounds();
        return STAGE.getWidth() == window.getWidth() && STAGE.getHeight() == window.getHeight();
    }

    private void launch() {

        SCENE.setOnMousePressed(event -> {
            mPresSceneX = event.getSceneX();
            mPresSceneY = event.getSceneY();

            mPresScreeX = event.getScreenX();
            mPresScreeY = event.getScreenY();

            mPresStageW = STAGE.getWidth();
            mPresStageH = STAGE.getHeight();
        });

        SCENE.setOnMouseClicked(event -> {
            double sy = event.getSceneY();
            boolean down = sy < SCENE.getHeight() && sy > SCENE.getHeight() - resizeArea;
            if ((sy < dragArea) && !down && (event.getClickCount() == 2)) {
                setFullScreen();
            }
        });

        SCENE.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            double sx = event.getSceneX();
            double sy = event.getSceneY();

            boolean left = sx > 0 && sx < resizeArea;
            boolean right = sx < SCENE.getWidth() && sx > SCENE.getWidth() - resizeArea;
            boolean down = sy < SCENE.getHeight() && sy > SCENE.getHeight() - resizeArea;
            boolean up = sy > 0 && sy < resizeArea;

            if (left && up && !isFullScreen())
                fireAction(Cursor.NW_RESIZE);
            else if (right && up && !isFullScreen())
                fireAction(Cursor.NE_RESIZE);
            else if (left && down && !isFullScreen())
                fireAction(Cursor.SW_RESIZE);
            else if (right && down && !isFullScreen())
                fireAction(Cursor.SE_RESIZE);
            else if (left && !isFullScreen())
                fireAction(Cursor.E_RESIZE);
            else if (right && !isFullScreen())
                fireAction(Cursor.W_RESIZE);
            else if (up && !isFullScreen())
                fireAction(Cursor.N_RESIZE);
            else if (down && !isFullScreen())
                fireAction(Cursor.S_RESIZE);
            else if (sy < dragArea && !down)
                fireAction(Cursor.DEFAULT);
            else
                fireAction(null);
        });
    }

    private void fireAction(Cursor c) {
        if (c == null) {
            SCENE.getRoot().setDisable(false);
            SCENE.getRoot().setOpacity(1);
            SCENE.setOnMouseDragged(null);
            SCENE.setCursor(Cursor.DEFAULT);
        } else if (c == Cursor.DEFAULT) {
            SCENE.getRoot().setDisable(false);
            SCENE.getRoot().setOpacity(1);
            SCENE.setCursor(c);
            SCENE.setOnMouseDragged(LISTENER.get(c));
        } else {
            SCENE.getRoot().setDisable(true);
            SCENE.getRoot().setOpacity(1);
            SCENE.setCursor(c);
            SCENE.setOnMouseDragged(LISTENER.get(c));
        }
    }
}
