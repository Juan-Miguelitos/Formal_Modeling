package fileReader.model;

public class Formula {
	private String Q = null;
	private String T = null;
	private String arg = null;
	private State[] validState = {};
	
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
	private char[] sampleLogic = {
		'|', // Or
		'&', // And
		'!', // Not
		't', // True
		'f' // False
	};
	
	public Formula(String[] fileContent) throws Exception {
		if (fileContent == null || fileContent.length == 0)
			throw new Exception("No formula");
		
		String formula = fileContent[0];
		char first = formula.charAt(0);
		
		
		
	}
	
	public Formula(String Q, String T) {
		
	}
	
	public Formula(String arg) {
		
	}
	
	
	private boolean checkQ(char Q) {
		for (int i = 0; i < sampleQ.length; i++) {
			//if (Q == sampleQ[i])
		}
		
		return false;
	}
}
