package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fileReader.model.Formula;
import fileReader.model.KripkeStructure;
import fileReader.model.State;
import fileReader.service.FileReader;
import modelChecking.model.CTLChecker;

public abstract class FormulaCheck {
	public static void startCheck() throws Exception {
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
		String choice;
		
		Util.clearConsole();
		System.out.println("Select a Kripke structure you want to work on: ");
		System.out.println("1. Structure 1");
/*		System.out.println("2. Structure 2");
		System.out.println("3. Structure 3");
		System.out.println("4. Structure 4");*/
		System.out.println("2. Go back");
		
		choice = reader.readLine();
		
		if (choice.equals("1")) checkFormula(choice);;
		
		
	}
	
	private static void checkFormula(String structure) throws Exception {
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
		String choice = "";
		String[] content = null;
		
		System.out.println("Select what you want to do");
		System.out.println("1. See the structure details");
		
		for (int i = 1; i < 7; i++) {
			content = FileReader.readFile("src/fileReader/data/ctl/ctl" + i + ".txt");
			
			if(content == null) {
				System.out.println("No CTL file");
				return;
			}
			
			System.out.println(i + 1 + ". " + content[0]);
		}
		
		System.out.println("8. Check your own formula");
		System.out.println("9. Go back");
		
		
		choice = reader.readLine();
		
		switch (choice) {
		case "1":
			KSList.detailStructure(structure);
			checkFormula(structure);
			break;
		case "2":
			check(findFormula("1"), structure);
			checkFormula(structure);
			break;
		case "3":
			check(findFormula("2"), structure);
			checkFormula(structure);
			break;
		case "4":
			check(findFormula("3"), structure);
			checkFormula(structure);
			break;
		case "5":
			check(findFormula("4"), structure);
			checkFormula(structure);
			break;
		case "6":
			check(findFormula("5"), structure);
			checkFormula(structure);
			break;
		case "7":
			check(findFormula("6"), structure);
			checkFormula(structure);
			break;
		case "8":
			customFormulaCheck(structure);
			checkFormula(structure);
			break;
		default:
			return;
		}
		
	}
	
	private static String findFormula(String formula) {
		String[] contentCTL = FileReader.readFile("src/fileReader/data/ctl/ctl" + formula + ".txt");
		
		return contentCTL[0];
	}
	
	private static void check(String formula, String structure) throws Exception {
		Formula toCheck = new Formula(formula);
		
		String[] contentKS = FileReader.readFile("src/fileReader/data/ks/ks" + structure + ".txt");
		KripkeStructure ks = new KripkeStructure(contentKS);
		
		State[] result = toCheck.modelChecking(ks.getStates());
		
		State[] reachableStates = CTLChecker.goThroughPaths(ks.getInitialState(), new ArrayList<State>());
		ArrayList<State> reachableStatesList = new ArrayList<State>();

		if (reachableStates == null) return;
		
		for (State state : reachableStates)
			reachableStatesList.add(state);
		
		if (result == null) {
			System.out.println("\n" + formula + " is not valid for this Kripke structure\n");;
			return;
		}
		for (State state : result)
			if (!reachableStatesList.contains(state)) {
				System.out.println("\n" + formula + " is not valid for this Kripke structure\n");
				return;
			}
		
		System.out.println("\n" + formula + " is valid for this Kripke structure\n");
	}

	private static void customFormulaCheck(String structure) throws Exception {
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
		String formula = "";
		
		System.out.println("Custom Formula");
		System.out.println("Notes:");
		System.out.println("- Operator U must be surrounded by parenthesis (ex: (…)U(…))");
		System.out.println("- Operators Q, QT, &, | and ! must be followed be parenthesis");
		System.out.println("- The syntax of your formula won't be verified, so the model checking could be altered in case of wrong syntax");
		System.out.println("Enter the custom formula: ");
		
		formula = reader.readLine();
		
		check(formula, structure);
	}
}
