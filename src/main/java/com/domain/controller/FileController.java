package com.domain.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.domain.controller.searcher.Searcher;
import com.domain.model.pojo.Record;
import com.domain.model.util.FileUtil;
import com.domain.model.util.Log;
import com.domain.view.ConsoleAppender;
import com.google.gson.Gson;

public class FileController implements Log {

	private static final String ROOT = "src/main/java/com/domain/data/";
	private static final String VIDEOTECA = "videoteca.json";
	private static final String BIBLIOTECA = "biblioteca.json";
	private static final String DISCOGRAFIA = "discografia.json";
	private static final String UNSORTED = "unsorted.json";
	private static final String[] files = { BIBLIOTECA, VIDEOTECA, DISCOGRAFIA, UNSORTED };
	public static final int END = (files.length) + 1;

	private static final int MAXRESULTS = 100;

	private FileUtil fu = null;

	public FileController() {
	}

	public void setFile(int fileNameIndex) {
		fu = new FileUtil(ROOT + files[--fileNameIndex]);
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

					for (int i = start; i < stop; i++) {
						System.out.println(records.get(i));
					}

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

	public void readAndPrint() {
		ConsoleAppender.print(fu.readLinesString());
	}

	public void search(String strToSearch) {
		List<String> lines = searchOnFile(strToSearch);

		if (!lines.isEmpty()) {
			pagination(deserialize(lines));
		}

		System.out.println("\nResults found: " + lines.size() + "\n");
	}

	public List<String> searchOnFile(String strToSearch) {
		return Searcher.search(fu.readLinesString(), strToSearch);
	}

	public static List<String> serialize(List<Record> records) {
		List<String> strRecords = new ArrayList();
		Gson gs = new Gson();

		for (Record x : records) {
			strRecords.add(gs.toJson(x));
		}

		return strRecords;
	}

	public static List<Record> deserialize(List<String> jsons) {
		List<Record> records = new ArrayList();
		Gson gs = new Gson();

		for (String x : jsons) {
			records.add(gs.fromJson(x, Record.class));
		}

		return records;
	}

	public List<Record> readRecords() {
		return deserialize(fu.readLinesString());
	}

}
