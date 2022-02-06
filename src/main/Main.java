package main;
import fileReader.service.FileReader;
import modelChecking.model.CTLChecker;

import java.util.ArrayList;
import java.util.Arrays;

import fileReader.model.Formula;
import fileReader.model.KripkeStructure;
import fileReader.model.State;

public class Main {
	public static void main(String[] args) {
		String[] content = FileReader.readFile("src/fileReader/data/ks.txt");
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
		}
	}
}
