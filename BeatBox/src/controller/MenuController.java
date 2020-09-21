package controller;

import view.MenuGUI;
/**
*	Controller Class for MenuGUI. 
*/
public class MenuController {
	private MenuGUI menuGUI;
	
	public MenuController(MenuGUI menuGUI) {
		this.menuGUI = menuGUI;
		initActions();
	}
	
/**
*	Set behaviour for buttons in MenuGUI. 
*/	
	private void initActions() {
		menuGUI.addEasy.setOnAction(e->{System.out.println("addition easy");});
		menuGUI.addInzane.setOnAction(e->{System.out.println("addition inzane");});
		menuGUI.basEasy.setOnAction(e->{System.out.println("basic algebra easy");});
		menuGUI.basInzane.setOnAction(e->{System.out.println("basic algebra inzane");});
		menuGUI.secButton.setOnAction(e->{System.out.println("GET REKT N00B");});
	}
}
