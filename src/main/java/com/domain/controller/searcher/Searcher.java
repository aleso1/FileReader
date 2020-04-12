package com.domain.controller.searcher;

import java.util.ArrayList;
import java.util.List;

import com.domain.model.pojo.Record;

public abstract class Searcher {

	private Searcher() {
		throw new IllegalStateException("Utility class");
	}

	private interface Predicate<T> {

		boolean check(T t);
	}

	public static List<String> search(List<String> recordsStr, String strToSearch) {
		List<String> exit = new ArrayList();

		for (String s : recordsStr) {
			if (s.toLowerCase().contains(strToSearch.toLowerCase())) {
				exit.add(s);
			}
		}

		return exit;
	}

	public static List<Record> searchByPredicate(List<Record> records, Predicate<Record> searcher) {
		List<Record> exit = new ArrayList();

		for (Record r : records) {
			if (searcher.check(r)) {
				exit.add(r);
			}
		}

		return exit;
	}

}
