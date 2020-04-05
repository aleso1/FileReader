package com.domain.view;

public class ViewFactory {

	private static ViewFactory instance = null;
	private Menu mainMenu = null;

	static {
		instance = new ViewFactory();
	}

	private ViewFactory() {
	}

	public static ViewFactory getInstance() {
		return instance;
	}

	public Menu getMainMenu() {

		if (mainMenu == null) {

			mainMenu = new Menu(0, "Main Menu");

			mainMenu.addOption(1, getSubMenu(1, "Biblioteca"));
			mainMenu.addOption(2, getSubMenu(2, "Videoteca"));
			mainMenu.addOption(3, getSubMenu(3, "Discografia"));
			mainMenu.addOption(4, getSubMenu(4, "Altro"));
			mainMenu.addOption(5, getSubMenu(5, "Esci"));
		}

		return mainMenu;
	}

	public Menu getSubMenu(int index, String menuName) {

		Menu subMenu = new Menu(index, menuName);
		Menu menu = null;

		Menu goBack = new Menu(1, "Torna indietro");
		Menu exit = new Menu(2, "Esci");

		menu = new Menu(1, "Cerca");
		menu.addOption(1, goBack);
		menu.addOption(2, exit);
		subMenu.addOption(1, menu);

		menu = new Menu(2, "Visualizza tutto");
		menu.addOption(1, goBack);
		menu.addOption(2, exit);
		subMenu.addOption(2, menu);

		subMenu.addOption(3, new Menu(4, "Torna indietro"));
		subMenu.addOption(4, new Menu(4, "Esci"));

		return subMenu;
	}
}
