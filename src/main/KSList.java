package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fileReader.model.KripkeStructure;
import fileReader.service.FileReader;

public abstract class KSList {
	public static void listKS() throws Exception {
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
		String choice = "";
		
		Util.clearConsole();
		System.out.println("Select the structure you want the delails of:");
		System.out.println("1. Structure 1");
		System.out.println("2. Structure 2");
		System.out.println("3. Structure 3");
		System.out.println("4. Structure 4");
		System.out.println("5. Go back");
		
		choice = reader.readLine();
		
		if (choice.equals("5")) return;
		
		detailStructure(choice);
	}
	
	public static void detailStructure(String structure) throws Exception {
		String[] content = FileReader.readFile("src/fileReader/data/ks/ks" + structure + ".txt");
		
		if(content == null) {
			System.out.println("No KS file");
			return;
		}
		
		KripkeStructure ks = new KripkeStructure(content);
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
		
		System.out.println(ks);
		System.out.println();
		System.out.println("Press a key to continue");
		
		reader.readLine();
	}
}
