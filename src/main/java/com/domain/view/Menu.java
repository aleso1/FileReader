package com.domain.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.domain.model.pojo.Record;
import com.domain.model.util.Log;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Menu implements Log {

	private int index;
	private String name;
	private Map<Integer, Menu> menuOption = new HashMap();
	private static final int MAXRESULTS = 100;

	public Menu(int index, String name) {
		this.index = index;
		this.name = name;
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

	public static void pagination(List<Record> records) {
		int size = records.size();
		int numumberOfPages = 0;
		int start = 0;
		int stop = MAXRESULTS;

		boolean next = true;

		System.out.println("\nResults found: " + size + "\n");

		if (size < MAXRESULTS) {
			LOG.debug("Results found are lesser then MAXRESULT");
			ConsoleAppender.print(records);
		} else {

			numumberOfPages = size / MAXRESULTS;
			int currentPage = 1;

			if (numumberOfPages < size / MAXRESULTS) {
				numumberOfPages++;
			}

			while (next) {

				if (currentPage <= numumberOfPages) {

					ConsoleAppender.print(records, start, stop);

					System.out.println("Start: " + start + " Stop: " + stop);
					System.out.println("\nPage " + currentPage + " of " + numumberOfPages + "\n");

					currentPage++;
				}

				LOG.debug("Waiting for user input..");

				switch (getUserInput()) {
				case 1:
					LOG.debug("Selected: 1. Go Forward");

					if (currentPage <= numumberOfPages) {
						LOG.debug("End not reached..");
						start = stop;
						stop = currentPage == numumberOfPages ? size : stop + MAXRESULTS;
					} else {
						LOG.debug("Reached end..");
					}

					break;
				case 2:
					LOG.debug("Selected: 2. Go Back");

					if (currentPage > 1) {
						LOG.debug("Going back..");
						currentPage -= 2;
						stop = start;
						start -= MAXRESULTS;
					} else {
						LOG.debug("Attempted to go before the first page..");
					}

					break;

				case 3:
					LOG.debug("Selected: 3. Exit");
					return;

				default:
					LOG.debug("Invalid input..");
					break;
				}
			}
		}
	}

	private static int getUserInput() {
		Scanner scan = new Scanner(System.in);

		System.out.println("1. Go Forward");
		System.out.println("2. Go Back");
		System.out.println("3. Exit");

		System.out.println("\nEnter your choise:");

		return scan.nextInt();
	}

}
