package fileReader.model;

import java.util.ArrayList;

public class Formula {
	/* Arguments */
	private String rawFormula;
	private String component = "";
	private ArrayList<Formula> subFormulas = new ArrayList<Formula>();
	
	/* Utility */
	private char[] operators = {
			// Path Quantifier operator (T)
			'A', // For all
			'E', // There exist
			
			// Linear-time operator (T)
			'X', // Next
			'G', // Globally (always in the future)
			'F' // Future (in the future)
	};
	
	
	/* Constructor */
	public Formula(String formula) throws Exception {
		if (formula.length() == 0 && formula != "")
			throw new Exception("No formula");
		
		this.rawFormula = formula;
		
		// Decompose the parenthesis
		if (!decomposeParenthesis())
			// If no parenthesis, decompose the operators
			if (!decomposeOperators())
				// If no operators, stock the component
				this.component = this.rawFormula;
	}
	
	
	/* Decompose the formula if composed with operators */
	private boolean decomposeOperators() throws Exception {
		boolean foundOperator = false;
		
		// Decompose the operators
		while (checkOperator())
			foundOperator = true;
		
		// Remove the parenthesis if there were operators
		if (foundOperator)
			if (!checkParenthesis())
				throw new Exception("Invalid synthax in the formula at : " + this.component);
		
		return foundOperator;
	}
	
	private boolean checkOperator() {
		for (int i = 0; i < this.operators.length; i++)
			if (this.rawFormula.charAt(0) == this.operators[i]) {
				// Stock the operator then erase it from the formula
				this.component += this.rawFormula.charAt(0);
				this.rawFormula = this.rawFormula.substring(1);
				
				return true;
			}
		
		return false;
	}
	
	
	/* Decompose the formula if composed with parenthesis */
	private boolean decomposeParenthesis() throws Exception {
		// Decompose the first formula
		if (checkParenthesis()) {
			// Stock the operator
			this.component += this.rawFormula.charAt(0);
			this.rawFormula = this.rawFormula.substring(1);
			
			// Decompose the second formula
			if (!checkParenthesis())
				throw new Exception("Invalid synthax in the formula at : " + this.component);
			
			return true;
		}
		
		return false;
	}
	
	private boolean checkParenthesis() throws Exception {
		// Check if there is a parenthesis
		if (this.rawFormula.charAt(0) != '(')
			return false;
		
		// Get the closing parenthesis index
		int closingParenthesisIndex = getClosingParenthesisIndex(this.rawFormula);
		
		// Erase the parenthesis
		this.rawFormula = this.rawFormula.substring(1);
		this.rawFormula = this.rawFormula.substring(0, closingParenthesisIndex)
							+ this.rawFormula.substring(closingParenthesisIndex);
		
		// Add the sub formula
		this.subFormulas.add(new Formula(this.rawFormula.substring(0, closingParenthesisIndex - 1)));
		
		// Erase the sub formula
		this.rawFormula = this.rawFormula.substring(closingParenthesisIndex);
		
		return true;
	}
	
	private int getClosingParenthesisIndex(String formula) throws Exception {
		if (formula.charAt(0) != '(')
			throw new Exception("1st index is not a parenthesis");
		
		int parenthesisCount = 0;
		int i = 0;
		
		do {
			if (formula.charAt(i) == '(')
				parenthesisCount++;
			else if (formula.charAt(i) == ')')
				parenthesisCount--;
			
			if (parenthesisCount != 0)
				i++;
		} while (parenthesisCount != 0);
		
		return i;
	}
	
	
	/* Serialize */
	@Override
	public String toString() {
		String serialized = "";
		
		if (this.subFormulas.size() == 2) {
			serialized += "(" + this.subFormulas.get(0).toString() + ")";
			serialized += this.component;
			serialized += "(" + this.subFormulas.get(1).toString() + ")";
		} 
		else if (this.subFormulas.size() == 1) {
			serialized += this.component;
			serialized += "(" + this.subFormulas.get(0).toString() + ")";
		}
		else
			serialized += this.component;
		
		return serialized;
	}
	// Serialize

}
