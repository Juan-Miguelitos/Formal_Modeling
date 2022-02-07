package main;
import fileReader.service.FileReader;
import modelChecking.model.CTLChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import fileReader.model.Formula;
import fileReader.model.KripkeStructure;
import fileReader.model.State;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("=== FORMAL MODELING PROJECT ===");
		System.out.println("made by AUSSAGE Nicolas, VIEHWEGER Ryan, BANNIEL Antoine");
		
		try {
			menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void menu() throws Exception {
		
		
		String choice = "";
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
		
		System.out.println();
		System.out.println("-- Menu --");
		//System.out.println("1. See available Kripke Structures");
		System.out.println("1. Model Checking");
		System.out.println("2. Quit");
		
		try {
			choice = reader.readLine();
			switch (choice) {
			/*case "1":
				try {
					KSList.listKS();
				} catch (Exception e) {
					e.printStackTrace();
				}
				menu();
				break;*/
			case "1":
				FormulaCheck.startCheck();
				menu();
				break;
			case "2":
				return;
			default:
				menu();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*String[] content = FileReader.readFile("src/fileReader/data/ks.txt");
		String[] contentCTL = FileReader.readFile("src/fileReader/data/ctl0_phi.txt");
		
		if(content == null) {
			System.out.println("No KS file");
			return;
		}
		if(contentCTL == null) {
			System.out.println("No CTL file");
			return;
		}
		
		
		/*for(int i = 0; i < content.length; i++)
			System.out.println(content[i]);*/
		/*
		try {
			KripkeStructure structure = new KripkeStructure(content);
			//System.out.println(structure.toString());
			
			Formula formula = new Formula(contentCTL[0]);
			System.out.println(formula.toString());
			
			State[] reachable = CTLChecker.goThroughPaths(structure.getInitialState(), new ArrayList<State>());
			State[] checking = formula.modelChecking(reachable);
			
			if (checking != null)
				for (State s : checking)
					System.out.println(s);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}*/
		
		
		
	}
}
