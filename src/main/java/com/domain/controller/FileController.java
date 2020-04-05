package com.domain.controller;

import java.util.ArrayList;
import java.util.List;

import com.domain.model.pojo.Record;
import com.domain.model.util.FileUtil;
import com.google.gson.Gson;

public class FileController {

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

	public void print(List<String> lines) {
		lines.forEach(line -> System.out.println(line));
	}

	public void printFromRecord(List<Record> records) {
		print(recordsToJson(records));
	}

	public void printRecords(List<Record> records) {
		records.forEach(rec -> System.out.println(rec.toString()));
	}

	public void readAndPrint() {
		print(fu.readLinesString());
	}

	public List<String> searchOnFile(String strToSearch) {
		return search(fu.readLinesString(), strToSearch);
	}

	public List<String> search(List<String> recordsStr, String strToSearch) {

		List<String> exit = new ArrayList();

		for (String s : recordsStr) {
			if (s.toLowerCase().contains(strToSearch.toLowerCase())) {
				exit.add(s);
			}
		}
		return exit;
	}

	public List<Record> searchByCategory(List<Record> records, String value) {
		List<Record> exit = new ArrayList();

		for (Record x : records) {
			if (x.getCategory().equals(value)) {
				exit.add(x);
			}
		}
		return exit;
	}

	public List<Record> searchByTitle(List<Record> records, String value) {
		List<Record> exit = new ArrayList();

		for (Record x : records) {
			if (x.getTitle().contains(value)) {
				exit.add(x);
			}
		}

		return exit;
	}

	public List<String> recordsToJson(List<Record> records) {
		List<String> strRecords = new ArrayList();
		Gson gs = new Gson();

		for (Record x : records) {
			strRecords.add(gs.toJson(x));
		}

		return strRecords;
	}

	public List<Record> jsonToRecords(List<String> jsons) {
		List<Record> records = new ArrayList();
		Gson gs = new Gson();

		for (String x : jsons) {
			records.add(gs.fromJson(x, Record.class));
		}

		return records;
	}

	public List<Record> readRecords() {
		return jsonToRecords(fu.readLinesString());
	}

	public void search(String strToSearch) {
		List<String> lines = searchOnFile(strToSearch);

		System.out.println("\nResult found: " + lines.size() + "\n");

		if (!lines.isEmpty()) {
			printRecords(jsonToRecords(lines));
		}
	}
}
