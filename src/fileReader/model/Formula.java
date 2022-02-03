package fileReader.model;

import java.util.ArrayList;

public class Formula {
	private char Q = 0;
	private char T = 0;
	private String arg = null;
	private State[] validState = {};
	
	private Formula[] restFormula = null;
	
	
	// Sample to identify a formula element
	private char[] sampleQ = {
			'A', // For all
			'E' // There exist
	};
	private char[] sampleT = {
		'X', // Next
		'U', // Until
		'G', // Globally (always in the future)
		'F' // Future (in the future)
	};
	private char[] sampleLogical = {
		'|', // Or
		'&', // And
		'!', // Not
		't', // True
		'f' // False
	};
	
	
	// Constructor
	public Formula(String formula) throws Exception {
		if (formula.length() == 0)
			return;
		
		
		
		char buffer = formula.charAt(0);
		checkElement(buffer);
		formula = formula.replaceFirst("" + buffer, "");
		
		if (formula.length() == 0)
			return;
		buffer = formula.charAt(0);
		checkElement(buffer);
		formula = formula.replaceFirst("" + buffer, "");
			
		ArrayList<Formula> restFormulaList = new ArrayList<Formula>();
		restFormulaList.add(new Formula(formula));
		restFormula = restFormulaList.toArray(restFormula);
	}
	
	
	// Check an element of the formula
	private void checkElement(char e) {
		if (checkQ(e))
			this.Q = e;
		else if (checkT(e))
			this.T = e;
		else
			this.arg = "" + e;
	}
	
	
	private boolean checkLogical(String formula) {
		for (int i = 0; i < this.sampleLogical.length; i++)
			if (formula.contains("" + this.sampleLogical[i]))
				return true;
		
		return false;
	}
	private boolean checkQ(char Q) {
		for (int i = 0; i < this.sampleQ.length; i++)
			if (Q == this.sampleQ[i])
				return true;
		
		return false;
	}
	private boolean checkT(char T) {
		for (int i = 0; i < this.sampleT.length; i++)
			if (T == this.sampleT[i])
				return true;
		
		return false;
	}
	
	// Serialize
	@Override
	public String toString() {
		String serialized = "";
		if (this.Q != 0)
			serialized += this.Q;
		if (this.T != 0)
			serialized += this.T;
		if (this.arg != null)
			serialized += this.arg;
		if (this.restFormula != null)
			if (this.restFormula.length == 1)
				serialized += this.restFormula[0].toString();
			else {
				serialized += this.restFormula[0].toString();
				serialized += this.restFormula[1].toString();
			}
		
		return serialized;
	}
}
