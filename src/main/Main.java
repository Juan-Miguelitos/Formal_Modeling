package main;
import fileReader.service.FileReader;
import fileReader.model.Formula;
import fileReader.model.KripkeStructure;

public class Main {
	public static void main(String[] args) {
		String[] content = FileReader.readFile("src/fileReader/data/ctl.txt");
		
		if(content == null) {
			System.out.println("No file");
			return;
		}
		
		/*for(int i = 0; i < content.length; i++)
			System.out.println(content[i]);*/
		
		try {
			//KripkeStructure structure = new KripkeStructure(content);
			
			//System.out.println(structure.toString());
			
			Formula formula = new Formula(content[0]);
			System.out.println(formula.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
