/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.domain.model.util;

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
public class FileUtil implements Log {

	private final String fileName;

	private List<String> lines;

	public FileUtil(String fileName) {
		this.fileName = fileName;
	}

	public List<String> readLinesString() {
		LOG.debug("Reading from file..");

		File file = new File(fileName);

		if (file.exists()) {

			lines = new ArrayList();
			try {

				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);

				String line = br.readLine();
				while (line != null) {
					lines.add(line);
					line = br.readLine();
				}
				br.close();
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		} else {
			LOG.debug("File doesn't exixt");
		}

		return lines;
	}

	public void writeLines(List<String> lines) throws IOException {
		LOG.debug("Writing on file..");

		File file = new File(fileName);
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
					LOG.error(e.getMessage());
				}
			}
		}
	}

	public boolean replaceAll(List<String> newLines) {
		LOG.debug("Deleting file..");

		boolean result = false;

		File inputFile = new File(fileName);

		if (inputFile.exists()) {
			boolean isDeleted = inputFile.delete();
			if (isDeleted && createFile()) {
				try {
					writeLines(newLines);
					result = true;
				} catch (IOException e) {
					LOG.error(e.getMessage());
				}
			}
		}
		return result;
	}

	public boolean createFile() {
		LOG.debug("Creating new file..");

		boolean result = false;
		try {
			File fileT = new File(fileName);

			if (!fileT.exists()) {
				fileT.createNewFile();
				result = true;
			}

		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		return result;
	}

}
