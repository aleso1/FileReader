package com.domain.view;

public class ViewFactory {

	private static ViewFactory instance = null;
	private static Menu mainMenu = null;
	private static Menu subMenu = null;

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
			Menu menu = new Menu(0, "Main Menu");
			menu.addOption(1, subMenuBuilder(1, "Biblioteca"));
			menu.addOption(2, subMenuBuilder(2, "Videoteca"));
			menu.addOption(3, subMenuBuilder(3, "Discografia"));
			menu.addOption(4, subMenuBuilder(4, "Altro"));
			menu.addOption(5, subMenuBuilder(4, "Esci"));
		}

		return mainMenu;
	}

	public Menu subMenuBuilder(int index, String name) {

		Menu menu = new Menu(index, name);
		menu.addOption(1, getSubMenu());
		menu.addOption(2, new Menu(2, "Torna indietro"));
		menu.addOption(3, new Menu(3, "Esci"));

		return menu;
	}

	// -------- TO FINISH
	public static Menu getSubMenu() {

		if (subMenu == null) {
			subMenu = new Menu(1, "Submenu");
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
		}

		return subMenu;
	}
}
