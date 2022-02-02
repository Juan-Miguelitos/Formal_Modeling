package fileReader.model;

public class CTLFormulas {
	private String[] decomposedFormula;
	
	public CTLFormulas(String[] fileContent) throws Exception {
		if (fileContent.length > 1 || fileContent.length == 0)
			throw new Exception("Wrong CTL Formula");
		this.decomposedFormula = fileContent[0].split(" ");
	}

	public String[] getDecomposedFormula() {
		return decomposedFormula;
	}
	
}
