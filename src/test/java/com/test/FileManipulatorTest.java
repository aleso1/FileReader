/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.test;

import com.domain.model.pojo.Record;
import com.domain.model.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Utente
 */
public class FileManipulatorTest {

	private static final String ROOT = "src/main/java/com/domain/data/";
	private static final String ROOT1 = "src/main/java/com/domain/data/master/";
	private static final String FILENAME = "dump.csv";
	private static final String FILENAME2 = "prova.txt";
	private static final String VIDEOTECA = "videoteca.json";
	private static final String BIBLIOTECA = "biblioteca.json";
	private static final String DISCOGRAFIA = "discografia.json";
	private static final String UNSORTED = "unsorted.json";
	private static final String UNSORTED_VIDEO = "unsortedVideo.json";
	private static final String SEPARATOR = ",";

////    @Test
//	public void smistaUnsorded() throws IOException {
//		FileUtil fm = new FileUtil(ROOT + UNSORTED);
//		List<Record> rec = fm.readRecords();
//
//		List<Record> unsorted = new ArrayList();
//
//		for (Record r : rec) {
//			if (r.getDescription().toLowerCase().contains("sub")) {
//				r.setCategory("99");
//				unsorted.add(r);
////                fm.removeByHash(r.getHash());
//			}
//		}
//
//		FileUtil fm2 = new FileUtil(ROOT + "unsortedVideo.json");
//		if (fm2.createFile()) {
//			fm2.writeLines(fm2.recordsToJson(unsorted));
//		}
//
//		fm2.print(fm.readLinesString());
//	}
//
////	@Test
//	public void deleteDuplicate() {
//		FileUtil fm = new FileUtil(ROOT + UNSORTED_VIDEO);
//		List<Record> unsVideo = fm.readRecords();
//
//		FileUtil fm2 = new FileUtil(ROOT + UNSORTED);
//		List<Record> uns = fm2.readRecords();
//
//		List<Record> unsTemp = new ArrayList();
//		unsTemp.addAll(uns);
//
//		for (Record r : unsVideo) {
//			if (uns.contains(r)) {
//				System.out.println("aa");
//				unsTemp.remove(r);
//			}
//		}
//
//		System.out.println("unsVideo: " + unsVideo.size());
//		System.out.println("uns: " + uns.size());
//		System.out.println("unsTemp: " + unsTemp.size());
//	}
//
//	@Test
//	public void deleteCose() {
//		FileUtil fm = new FileUtil(ROOT + BIBLIOTECA);
//		List<String> rec = fm.readLinesString();
//		List<String> temp = new ArrayList<String>();
//		temp.addAll(rec);
//
//		List<String> asd = fm.search(rec, " hot");
//
//		for (String s : asd) {
//			if (rec.contains(s)) {
//				temp.remove(s);
//			}
//		}
//
//		fm.print(asd);
//
//		System.out.println("rec: " + rec.size());
//		System.out.println("temp: " + temp.size());
//		System.out.println("asd: " + asd.size());
////		if (!temp.containsAll(asd)) {
////			System.out.println("deleted: " +fm.replaceAll(temp));
////		}
//	}

}
