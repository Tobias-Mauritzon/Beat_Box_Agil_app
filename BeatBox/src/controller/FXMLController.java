package controller;

import java.io.IOException;
import java.util.LinkedList;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.NavigationMenu;

public class FXMLController {

	private LinkedList<Node> panes = new LinkedList<Node>();
	public NavigationMenu nm;
	
	public FXMLController(NavigationMenu nm, LinkedList<Node> panes) {
		this.nm = nm;
		this.panes = panes;
		setActions();
	}
	
	private void setActions() {
		// action event 
        EventHandler<ActionEvent> toHome = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {loadHomeMenu();}};
        EventHandler<ActionEvent> toAddition = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {loadPlusTest();}};
        EventHandler<ActionEvent> toSettings = new EventHandler<ActionEvent>() { public void handle(ActionEvent e) {loadSettingsMenu();}};

        nm.getLeftSideButtons()[0].setOnAction(toHome);
        nm.getRightSideButtons()[0].setOnAction(toHome);
        
        nm.getLeftSideButtons()[1].setOnAction(toAddition);
        nm.getRightSideButtons()[1].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[2].setOnAction(toAddition);
        nm.getRightSideButtons()[2].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[3].setOnAction(toAddition);
        nm.getRightSideButtons()[3].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[4].setOnAction(toAddition);
        nm.getRightSideButtons()[4].setOnAction(toAddition);
        
        nm.getLeftSideButtons()[5].setOnAction(toSettings);
        nm.getRightSideButtons()[5].setOnAction(toSettings);
		
	
	
	}
	
	private void loadHomeMenu() {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), nm.getRoot());
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
		Node pane = panes.get(0);
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
		
		

		// container child clear
		nm.getRoot().getChildren().clear();

		// new container add
		nm.getRoot().getChildren().add(pane);
	}
	
	private void loadPlusTest() {
		
		FadeTransition ft = new FadeTransition(Duration.millis(1000), nm.getRoot());
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
		Node pane = panes.get(1);
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
		
		// container child clear
		nm.getRoot().getChildren().clear();

		// new container add
		nm.getRoot().getChildren().add(pane);
	}
	
	private void loadSettingsMenu() {
		Node pane = panes.get(2);
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
		FadeTransition ft = new FadeTransition(Duration.millis(1500), nm.getRoot());
		   ft.setFromValue(0);
		   ft.setToValue(1);
		   ft.play();
		// container child clear
		nm.getRoot().getChildren().clear();

		// new container add
		nm.getRoot().getChildren().add(pane);
	}
	
}
