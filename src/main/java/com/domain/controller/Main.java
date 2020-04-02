/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.controller;

import com.domain.model.pojo.Record;
import com.domain.model.util.FileUtil;
import com.domain.view.Menu;
import com.domain.view.ViewFactory;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Utente
 */
public class Main {

	private static final String BASEDIR = "C:/";
	private static final String DIR = "Users/Utente/Desktop/file/";
	private static final String FILENAME = "dump.csv";
	private static final String FILENAME2 = "prova.txt";
	private static final String FILENAME3 = "videoteca.json";
	private static final String FILENAME4 = "biblioteca.json";
	private static final String FILENAME5 = "discografia.json";
	private static final String FILENAME6 = "unsorted.json";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Boolean exit = false;
		Boolean exitSubMenu = false;

		ViewFactory view = ViewFactory.getInstance();
		Menu mainMenu = view.getMainMenu();
		Menu subMenu = null;

		int index = -1;
		String strToSearch = null;

		while (!exit) {

			index = mainMenu.printAndChoose();
			subMenu = mainMenu.getOption(index);

			FileUtil fileUtil = new FileUtil(FILENAME);
			
			index = subMenu.printAndChoose();
			while (!exitSubMenu) {

				switch (index) {

				case 1:
					// cerca
					strToSearch = subMenu.search();

					break;
				case 2:
					// visualizza tutto
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
					index = subMenu.choose();
					break;
					
				}
			}
		}

	}

	public static void search(String section) {
		String value;
		List<Record> jsonRecords;
		List<Record> results;
		Scanner scan = new Scanner(System.in);
		FileUtil fu = new FileUtil(section);

		System.out.println("------- CERCA -------");
		System.out.println("Inserire una valore da cercare:");
		value = scan.nextLine();

		jsonRecords = fu.readLinesJson();
		results = fu.search(jsonRecords, value);
		fu.printFromRecord(results);
	}

}
