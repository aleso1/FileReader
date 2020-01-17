/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.test;

import com.domain.Record;
import com.util.FileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Utente
 */
public class FileManipulatorTest {

    private static final String BASEDIR = "C:/";
    private static final String DIR = "Users/Utente/Desktop/file/";
    private static final String FILENAME = "dump.csv";
    private static final String FILENAME2 = "prova.txt";
    private static final String FILENAME3 = "videoteca.json";
    private static final String FILENAME4 = "biblioteca.json";
    private static final String FILENAME5 = "discografia.json";
    private static final String FILENAME6 = "unsorted.json";
    private static final String SEPARATOR = ",";

    //C:\Users\Utente\Desktop\file
    public FileManipulatorTest() {
    }

//    @Test
    public void readAndPrint() {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME3);
        List<String> str = fm.readLines();
        fm.printLines(str);
    }

//    @Test
    public void modLines() {
        System.out.println("----------------------------");
        System.out.println("Dall'unità di test:");
        System.out.println("-");
        System.out.println("Istanzio FileManipulator");
        System.out.println("-");

        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME);

        System.out.println("leggo le linee del file");
        List<String> str = fm.readLines();

        List<Record> rec = fm.stringsToRecords(str, SEPARATOR);

        for (Record x : rec) {
            System.out.println(x.toString());
        }
    }

//    @Test
    public void writeFile() throws IOException {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME);
        List<String> str = fm.readLines();

        List<Record> rec = fm.stringsToRecords(str, SEPARATOR);

        FileUtil fm2 = new FileUtil(BASEDIR, DIR, "prova.txt");

        str = fm2.recordsToJson(rec);
        fm2.writeLines(str);
    }

//    @Test
    public void readJson() {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME2);
        List<String> str = fm.readLines();

        List<Record> rec = fm.jsonToRecords(str);

        for (Record x : rec) {
            System.out.println(x.toString());
        }

        System.out.println("Numero di record: " + rec.size());

    }

//    1  = Film TV e programmi
//    2  = Musica
//    3  = E Books
//    4  = Film
//    8  = Cartoni
//    13 = Students Releases
//    14 = Documentari
//    23 = Teatro
//    29 = Serie TV
//    30 = Fumetteria
//    34 = A Book
//    36 = Edicola
//    @Test
    public void printByCategory() {
        FileUtil fu = new FileUtil(BASEDIR, DIR, FILENAME2);
        List<String> str = fu.readLines();
        List<Record> rec = fu.jsonToRecords(str);

        List<Record> recResults = fu.searchByCategory(rec, "34");

        str = fu.recordsToJson(recResults);
        fu.printLines(str);
        System.out.println("numero di record per categoria 2: " + str.size());

    }

//    @Test
    public void removeByCategory() {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME2);

        fm.removeByCategory("2");
    }

//    @Test
    public void removeByCategory2() {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME2);
        for (int i = 0; i <= 36; i++) {
            fm.removeByCategory(i + "");
        }
    }

//    1  = Film TV e programmi
//    4  = Film
//    8  = Cartoni
//    14 = Documentari
//    23 = Teatro
//    29 = Serie TV
//    @Test
    public void writeByCategory() throws IOException {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME2);
        List<String> str = fm.readLines();
        List<Record> rec = fm.jsonToRecords(str);

        List<Record> recResults = fm.searchByCategory(rec, "29");

        str = fm.recordsToJson(recResults);

        FileUtil fu = new FileUtil(BASEDIR, DIR, FILENAME3);

        fu.writeLines(str);

    }

//    3  = E Books
//    13 = Students Releases
//    30 = Fumetteria
//    34 = A Book
//    36 = Edicola
//    @Test
    public void writeByCategory2() throws IOException {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME2);
        List<String> str = fm.readLines();
        List<Record> rec = fm.jsonToRecords(str);

        List<Record> recResults = fm.searchByCategory(rec, "36");

        str = fm.recordsToJson(recResults);

        FileUtil fu = new FileUtil(BASEDIR, DIR, FILENAME4);
        fu.writeLines(str);

    }

    //    2  = Musica
//    @Test
    public void writeByCategory3() throws IOException {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME4);
        List<String> str = fm.readLines();
        List<Record> rec = fm.jsonToRecords(str);

        List<Record> recResults = fm.searchByCategory(rec, "2");

        str = fm.recordsToJson(recResults);

        FileUtil fu = new FileUtil(BASEDIR, DIR, FILENAME5);
        fu.writeLines(str);

    }

//    @Test
    public void writeUnsorted() throws IOException {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME2);
        List<String> str = fm.readLines();

        FileUtil fu = new FileUtil(BASEDIR, DIR, FILENAME6);
        fu.writeLines(str);

    }

//    1  = Film TV e programmi
//    4  = Film
//    8  = Cartoni
//    14 = Documentari
//    23 = Teatro
//    29 = Serie TV
//    @Test
    public void modCategory1() throws IOException {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME3);
        List<String> str = fm.readLines();

        List<Record> rec = fm.jsonToRecords(str);
        String category;
        for (Record x : rec) {
            category = x.getCategory();
            switch (category) {
                case "4":
                    x.setCategory("2");
                    break;
                case "8":
                    x.setCategory("3");
                    break;
                case "14":
                    x.setCategory("4");
                    break;
                case "23":
                    x.setCategory("5");
                    break;
                case "29":
                    x.setCategory("6");
                    break;

            }
        }
        str = fm.recordsToJson(rec);
        fm.removeAll();
        fm.writeLines(str);
    }

//    3  = E Books
//    13 = Students Releases
//    30 = Fumetteria
//    34 = A Book
//    36 = Edicola
//    @Test
    public void modCategory2() throws IOException {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME4);
        List<String> str = fm.readLines();

        List<Record> rec = fm.jsonToRecords(str);
        String category;
        for (Record x : rec) {
            category = x.getCategory();
            switch (category) {
                case "3":
                    x.setCategory("1");
                    break;
                case "13":
                    x.setCategory("2");
                    break;
                case "30":
                    x.setCategory("3");
                    break;
                case "34":
                    x.setCategory("4");
                    break;
                case "36":
                    x.setCategory("5");
                    break;

            }
        }
        str = fm.recordsToJson(rec);
        fm.removeAll();
        fm.writeLines(str);
    }
    
//    2  = Musica
//    @Test
    public void modCategory3() throws IOException {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME5);
        List<String> str = fm.readLines();

        List<Record> rec = fm.jsonToRecords(str);
        String category;
        for (Record x : rec) {
            category = x.getCategory();
            if (category.equals("2")) {

                x.setCategory("1");

            }
        }
        str = fm.recordsToJson(rec);
        fm.removeAll();
        fm.writeLines(str);
    }
       

//    @Test
    public void categoryCounter() {
        FileUtil fm = new FileUtil(BASEDIR, DIR, FILENAME5);
        List<String> str = fm.readLines();
        int cat1 = 0, cat2 = 0, cat3 = 0, cat4 = 0, cat5 = 0, cat6 = 0;
        List<Record> rec = fm.jsonToRecords(str);
        String category;

        for (Record x : rec) {
            category = x.getCategory();
            switch (category) {
                case "1":
                    cat1++;
                    break;
                case "2":
                    cat2++;
                    break;
                case "3":
                    cat3++;
                    break;
                case "4":
                    cat4++;
                    break;
                case "5":
                    cat5++;
                    break;
                case "6":
                    cat6++;
                    break;

            }
        }

        System.out.println("ci sono " + cat1 + " occorrenze di 1");
        System.out.println("ci sono " + cat2 + " occorrenze di 2");
        System.out.println("ci sono " + cat3 + " occorrenze di 3");
        System.out.println("ci sono " + cat4 + " occorrenze di 4");
        System.out.println("ci sono " + cat5 + " occorrenze di 5");
        System.out.println("ci sono " + cat6 + " occorrenze di 6");
    }

}
