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
		menuGUI.addEasy.setOnAction(e->{menuGUI.changeScene(0); System.out.println("addition easy");});
		menuGUI.addInzane.setOnAction(e->{menuGUI.changeScene(2);System.out.println("addition inzane");});
		menuGUI.basEasy.setOnAction(e->{menuGUI.changeScene(3);System.out.println("basic algebra easy");});
		menuGUI.basInzane.setOnAction(e->{menuGUI.changeScene(4);System.out.println("basic algebra inzane");});
		menuGUI.secButton.setOnAction(e->{menuGUI.changeScene(0);System.out.println("GET REKT N00B");});
	}
}
