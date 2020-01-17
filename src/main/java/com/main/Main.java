/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.util.Scanner;

/**
 *
 * @author Utente
 */
public class Main {

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
        System.out.println("1. Visualizza tutto");
        System.out.println("2. Visualizza per categoria");
        System.out.println("3. Cerca");
        System.out.println("4. Torna indietro");
        System.out.println("5. Esci");
    }

    public static void videoteca() {
        System.out.println("Categorie:");
        System.out.println("");
    }
    
    public static void search(String value){
        
    }

}
