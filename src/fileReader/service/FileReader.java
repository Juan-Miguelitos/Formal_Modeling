package fileReader.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	public static String[] readFile(String path) {
		try {
			File file = new File(path);
			Scanner reader = new Scanner(file);
			
			ArrayList<String> fileContent = new ArrayList<String>();
			
			for(int i = 0; reader.hasNextLine(); i++) {
				// Save the line
				fileContent.add(reader.nextLine());
				
				// Don't care if the line is a comment
				if(fileContent.get(i).charAt(0) == '#')
					i--;
			}
			
			reader.close();
			
			String[] contentAsStringArray = {};
			contentAsStringArray = fileContent.toArray(contentAsStringArray);
			
			return contentAsStringArray;
		} catch (FileNotFoundException e) {
			return null;
		}
		
	}
}
