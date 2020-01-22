/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.test;


import com.util.FileUtil;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Utente
 */
public class FileReaderTest {

    private static final String BASEDIR = "C:/";
    private static final String DIR = "Users/Utente/Desktop/file/";
    private static final String FILENAME1 = "videoteca.json";
    private static final String FILENAME2 = "biblioteca.json";
    private static final String FILENAME3 = "discografia.json";
    private static final String FILENAME4 = "unsorted.json";

    @Test
    public void FileReaderTest() {
        System.out.println("Quale file vuoi leggere?");
        
        FileUtil fu = new FileUtil(BASEDIR, DIR, FILENAME1);
      

        List<String> str = fu.readLinesString();
        fu.printFromString(str);

    }

}
