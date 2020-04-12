package com.domain.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;

import com.domain.model.util.Log;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Menu implements Log {

	private int index;
	private String name;
	private Map<Integer, Menu> menuOption = new HashMap();

	public Menu(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public void addOption(int index, Menu submenuName) {
		menuOption.put(index, submenuName);
	}

	public Menu getOption(int index) {
		return menuOption.get(index);
	}

	public int printAndChoose() {
		printOptions();
		return choose();
	}

	public void printOptions() {
		LOG.info("Printing options...");
		for (int i : menuOption.keySet()) {
			System.out.println(i + ". " + menuOption.get(i).getName());
		}
	}

	// restituisce -1 se l'input non è valido
	public int choose() {
		LOG.info("Choosing..");

		Scanner scan = new Scanner(System.in);
		int choosen = -1;

		System.out.println("\nEnter your choise: ");

		try {

			choosen = Integer.valueOf(scan.nextLine());
			System.out.println(" \nSelected Option: " + menuOption.get(choosen).getName());

		} catch (NumberFormatException e) {
			LOG.error("Invalid input. Expected an Integer -> " + e.getMessage());
		}

		return choosen;
	}

	public static String search() {
		LOG.debug("Searcing..");

		Scanner scan = new Scanner(System.in);
		String strToSearch = null;

		System.out.println("\nSearch: ");
		strToSearch = scan.nextLine();

		return strToSearch;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setName(String name) {
		this.name = name;
	}

}
