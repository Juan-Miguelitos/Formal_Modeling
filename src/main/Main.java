package main;
import fileReader.service.FileReader;
import fileReader.model.KripkeStructure;

public class Main {
	public static void main(String[] args) {
		String[] content = FileReader.readFile("src/fileReader/data/ks.txt");
		
		if(content == null) {
			System.out.println("No file");
			return;
		}
		
		/*for(int i = 0; i < content.length; i++)
			System.out.println(content[i]);*/
		
		try {
			KripkeStructure structure = new KripkeStructure(content);
			
			try {
				System.out.println(structure.toString());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
