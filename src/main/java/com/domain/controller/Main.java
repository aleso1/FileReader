/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.controller;

import java.util.List;
import java.util.Scanner;

import com.domain.model.util.FileUtil;
import com.domain.model.util.Log;
import com.domain.view.Menu;
import com.domain.view.ViewFactory;

/**
 *
 * @author Utente
 */
public class Main implements Log {

	public static void main(String[] args) {
		Boolean exit = false;
		Boolean exitSubMenu;

		FileController fc = new FileController();

		ViewFactory view = ViewFactory.getInstance();
		Menu mainMenu = view.getMainMenu();
		Menu subMenu = null;

		int index = -1;
		String strToSearch = null;

		while (!exit) {
			exitSubMenu = false;

			index = mainMenu.printAndChoose();

			if (index != -1) {

				subMenu = mainMenu.getOption(index);

				if (index != FileController.END) {

					fc.setFile(index);

					int subMenuIndex = subMenu.printAndChoose();

					while (!exitSubMenu) {

						switch (subMenuIndex) {

						case 1:
							// cerca
							strToSearch = subMenu.search();
							fc.search(strToSearch);
							exitSubMenu = !waitInput();
							break;
						case 2:
							// visualizza tutto
							fc.readAndPrint();
							exitSubMenu = !waitInput();
							break;
						case 3:
							// torna indietro
							exitSubMenu = true;
							break;
						case 4:
							// esci
							exitSubMenu = true;
							exit = true;
							break;

						default:
							subMenuIndex = subMenu.choose();
							break;

						}
					}

				} else {
					LOG.info("Terminating program...");
					exit = true;
				}
			}

		}

	}

	public static boolean waitInput() {
		System.out.println("\nContinue (y/n)");

		Scanner scan = new Scanner(System.in);
		return scan.nextLine() == "y" ? true : false;
	}

}
