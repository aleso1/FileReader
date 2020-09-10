package com.domain.view;

import java.util.List;

public class ConsoleAppender {

	public static <T> void print(Iterable<T> source) {
		source.forEach(t -> System.out.println(t.toString()));
	}

	public static <T> void print(List<T> source, int start, int stop) {

		for (int i = start; i < stop; i++) {
			System.out.println(source.get(i));
		}
	}

}
