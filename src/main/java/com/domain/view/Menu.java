package com.domain.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
		LOG.debug("Printing options...");
		for (int index : menuOption.keySet()) {
			System.out.println(index + ". " + menuOption.get(index));
		}
	}

	public int choose() {
		LOG.debug("Choosing..");

		Scanner scan = new Scanner(System.in);
		int choosen = 0;

		System.out.println("\nInsert a number: ");
		choosen = scan.nextInt();

		System.out.println(" \nOtpion selected: " + choosen + ". " + menuOption.get(choosen));

		scan.close();

		return choosen;
	}

	public String search() {
		LOG.debug("Searcing..");

		Scanner scan = new Scanner(System.in);
		String strToSearch = null;

		System.out.println("\nSearch: ");
		strToSearch = scan.nextLine();

		scan.close();

		return strToSearch;
	}

}
