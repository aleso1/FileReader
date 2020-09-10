package com.domain.controller;

import java.util.ArrayList;
import java.util.List;

import com.domain.controller.searcher.Searcher;
import com.domain.model.pojo.Record;
import com.domain.model.util.FileUtil;
import com.domain.model.util.Log;
import com.domain.view.ConsoleAppender;
import com.domain.view.Menu;
import com.google.gson.Gson;

public class FileController implements Log {

	private static final String ROOT = "src/main/java/com/domain/data/";
	private static final String VIDEOTECA = "videoteca.json";
	private static final String BIBLIOTECA = "biblioteca.json";
	private static final String DISCOGRAFIA = "discografia.json";
	private static final String UNSORTED = "unsorted.json";
	private static final String[] files = { BIBLIOTECA, VIDEOTECA, DISCOGRAFIA, UNSORTED };
	public static final int END = (files.length) + 1;

	private FileUtil fu = null;

	public FileController() {
	}

	public void setFile(int fileNameIndex) {
		fu = new FileUtil(ROOT + files[--fileNameIndex]);
	}

	public void readAndPrint() {
		ConsoleAppender.print(fu.readLinesString());
	}

	public void search(String strToSearch) {
		List<String> lines = searchOnFile(strToSearch);

		if (!lines.isEmpty()) {
			Menu.pagination(deserialize(lines));
		}

		System.out.println("\nResults found: " + lines.size() + "\n");
	}

	public List<String> searchOnFile(String strToSearch) {
		return Searcher.search(fu.readLinesString(), strToSearch);
	}

	public static List<String> serialize(List<Record> records) {
		List<String> strRecords = new ArrayList<>();
		Gson gs = new Gson();

		for (Record x : records) {
			strRecords.add(gs.toJson(x));
		}

		return strRecords;
	}

	public static List<Record> deserialize(List<String> jsons) {
		List<Record> records = new ArrayList<>();
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
