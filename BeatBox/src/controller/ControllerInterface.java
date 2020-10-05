package controller;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.SceneHandler;
import view.GUIHandler;
import view.NavigationMenu;
import view.ProblemGUI;

/**
 * A simple controller that handles the input from the user and communicates
 * with the GUI.
 * 
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-10-01
 *
 */
public interface ControllerInterface {
	/***
	 * A prototype functions for interfaces is used to set all their actions.
	 */
	public void setActions();
	
}
