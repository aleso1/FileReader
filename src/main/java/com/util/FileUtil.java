/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.util;

import com.domain.Record;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Utente
 */
public class FileUtil {
    
    private final String baseDir, dir, fileName, fullPath;
    
    private List<String> lines;
    private List<Record> records;
    
    public FileUtil(String baseDir, String dir, String fileName) {
        this.baseDir = baseDir;
        this.dir = dir;
        this.fileName = fileName;
        fullPath = baseDir + "/" + dir + "/" + fileName;
    }
    
    public List<String> readLines() {
        System.out.println("Da readLines() di FileManipulator: ");
        System.out.println("-----------------------------------");
        
        File file = new File(fullPath);
        lines = new ArrayList();
        
        if (file.exists()) {
            System.out.println("Il file esiste");
            System.out.println("-----------------------------------");
            try {
                
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                
                String line = br.readLine();
                System.out.println("Linea letta: " + line);
                System.out.println("1-----------------------------------");
                while (line != null) {
                    lines.add(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return lines;
    }
    
    public void writeLines(List<String> lines) throws IOException {
        
        File file = new File(fullPath);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for (String a : lines) {
                bw.write(a + "\n");
            }
            
            bw.flush();
            bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public boolean removeAll() {
        String pathTemp = baseDir + "/" + dir + "/" + "temp" + fileName;
        boolean successful = false;
        File inputFile = new File(fullPath);
        
        if (inputFile.exists()) {
            
            File tempFile = new File(pathTemp);
            
            successful = inputFile.delete();
            successful = tempFile.renameTo(inputFile);
            
        }
        return successful;
    }
    
    public void removeByCategory(String value) {
        
        String pathTemp = baseDir + "/" + dir + "/" + "temp" + fileName;
        
        File inputFile = new File(fullPath);
        
        if (inputFile.exists()) {
            try {
                File tempFile = new File(pathTemp);
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile, true));
                String currentLine = br.readLine();
                
                List<Record> records = new ArrayList();
                Record rec;
                Gson gs = new Gson();
                
                while (currentLine != null) {
                    rec = gs.fromJson(currentLine, Record.class);
                    if (rec.getCategory().equals(value)) {
                        currentLine = br.readLine();
                        continue;
                    }
                    
                    bw.write(currentLine + "\n");
                    currentLine = br.readLine();
                }
                
                bw.close();
                br.close();
                
                boolean successful = inputFile.delete();
                successful = tempFile.renameTo(inputFile);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    public void printLines(List<String> lines) {
        for (String x : lines) {
            System.out.println(x);
        }
    }
    
    public List<Record> searchByCategory(List<Record> records, String value) {
        List<Record> exit = new ArrayList();
        System.out.println("Metodo di ricerca avviato...");
        
        for (Record x : records) {
            if (x.getCategory().equals(value)) {
                exit.add(x);
            }
        }
        System.out.println("Record trovati: " + exit.size());
        System.out.println("Metodo di ricerca terminato...");
        return exit;
    }
    
    public List<Record> stringsToRecords(List<String> lines, String SEPARATOR) {
        int i;
        Record rec;
        records = new ArrayList();
        
        for (String x : lines) {
            rec = new Record();
            i = 0;
            
            for (String y : x.split(SEPARATOR)) {
                
                switch (i) {
                    case 0:
                        rec.setDate(y);
                        break;
                    case 1:
                        rec.setHash(y);
                        break;
                    case 5:
                        rec.setTitle(y);
                        break;
                    case 6:
                        rec.setDescription(y);
                        break;
                    case 8:
                        rec.setCategory(y);
                        break;
                    default:
                        break;
                }
                i++;
            }
            records.add(rec);
        }
        return records;
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
    
    public boolean createJsonFile() {
        boolean exit = false;
        try {
            File fileT = new File(fullPath);
            
            if (!fileT.exists()) {
                fileT.createNewFile();
                exit = true;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exit;
    }
    
}
