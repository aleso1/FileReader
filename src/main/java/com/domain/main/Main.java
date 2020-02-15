/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.main;

import com.domain.pojo.Record;
import com.domain.util.FileUtil;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Utente
 */
public class Main {

    private static final String BASEDIR = "C:/";
    private static final String DIR = "Users/Utente/Desktop/file/";
    private static final String FILENAME = "dump.csv";
    private static final String FILENAME2 = "prova.txt";
    private static final String FILENAME3 = "videoteca.json";
    private static final String FILENAME4 = "biblioteca.json";
    private static final String FILENAME5 = "discografia.json";
    private static final String FILENAME6 = "unsorted.json";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        printMenu();
        switch (scan.nextInt()) {
            case 1:
                System.out.println("------- VIDEOTECA -------");
                printSubMenu();
                break;
            case 2:
                System.out.println("------- BIBLIOTECA -------");
                printSubMenu();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.println("Fine");
                System.exit(0);
                break;

        }

    }

    public static void printMenu() {
        System.out.println("Inserire il numero:");
        System.out.println("1. Videoteca");
        System.out.println("2. Biblioteca");
        System.out.println("3. Discografia");
        System.out.println("4. Unsorted");
        System.out.println("5. Esci");
        System.out.println("\n");
    }

    public static void printSubMenu() {
        System.out.println("Inserire il numero:");
        System.out.println("1. Cerca");
        System.out.println("2. Visualizza per categoria");
        System.out.println("3. Visualizza tutto");
        System.out.println("4. Cerca");
        System.out.println("5. Torna indietro");
        System.out.println("6. Esci");
    }

    public static void videoteca() {
        System.out.println("Categorie:");
        System.out.println("");
    }

    public static void search(String section) {
        String value;
        List<Record> jsonRecords;
        List<Record> results;
        Scanner scan = new Scanner(System.in);
        FileUtil fu = new FileUtil(section);

        System.out.println("------- CERCA -------");
        System.out.println("Inserire una valore da cercare:");
        value = scan.nextLine();

        jsonRecords = fu.readLinesJson();        
        results = fu.search(jsonRecords, value);
        fu.printFromRecord(results);
    }

}
