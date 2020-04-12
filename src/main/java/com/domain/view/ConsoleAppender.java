package com.domain.view;

public class ConsoleAppender {

	public static <T> void print(Iterable<T> source) {
		source.forEach(t -> System.out.println(t.toString()));
	}

}
