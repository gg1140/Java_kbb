import java.io.IOException;

import Model.Automobile;
import Util.FileIO;

public class Driver {
	public static void main(String[] args) {
		/** Test Code **/
		/*
		String carAModel = "Jaguar F-Type";
		Double carAPrice = 61400.0;
		Automobile carA = new Automobile(carAModel, carAPrice, 2);
		
		// Testing key functionalities of Collection abstract class	
		carA.addOptionSet("Color", 2); //Adding to empty collection
		carA.addOptionSet("Trans", 1);
		carA.addOption("Color", "Red", 300.00); //Adding to empty collection
		carA.addOption("Color", "Red", 150.00); //Adding duplicated items
		carA.addOption("color", "Grey", 123.45); 
		carA.addOption("color", "White", 0.00); //Over capacity
		carA.removeOption("color", "white"); //Removing an non-existing item
		carA.removeOption("color", "red"); //Removing an existing item. Case-sensitivity of search methods
		if(!carA.removeOption("trans", "abc")) //Trying to remove from an empty collection
			System.out.println("remove from empty collection unsuccessful\n");
		
		System.out.println(carA.toString()); //Testing toString of Automobile, OptionSet, Option
		
		
		// Testing read functions
		System.out.println(carA.getOptionPrice("color", "grey"));
		System.out.println(carA.getNumOfOptionSet());
		String[] sArray = carA.getAllOptionName("Color");
		Double[] dArray = carA.getAllOptionPrice("color");
		for(int i = 0; i < sArray.length; i++) {
			System.out.println(sArray[i] + " ~ " + dArray[i]);
		}
		
		//Test update functions
		carA.updateOption("color", "Red", "Violet", 456.2);
		carA.updateOptionName("color", "Grey", "Space Grey");
		carA.updateOptionPrice("color", "Space Grey", 223.45);
		carA.updateOptionSetName("color", "Colour");

		System.out.println(carA.toString());
		*/
		//([^=\n\r]+)\s*=\s*([^=\n\r]+)
		/*Pattern assignRegex = Pattern.compile("\\**([^=\\n\\r]+)=\\s*([^=\\n\\r]+)");
		Pattern pairRegex = Pattern.compile("\\( *([^)]+)(?= *\\))");
		Pattern setRegex = Pattern.compile("\\{ *([^\\{\\}]+)(?= *\\})");
		
		String testStr1 = "Automobile = {(model), (base price), (collection size), (OptionSetCollection)}";
		String testStr2 = " (                   Twilight Blue Clearcoat Metallic                 ,                  0            )";
		String testStr3 = "Base Price=18445";
		String testStr4 = "(model), (base price), (collection size), (OptionSetCollection)";
		Matcher m = pairRegex.matcher(testStr4);	
		
		while(m.find()) {
			String find = m.group(1);
			System.out.println(find);
		}*/
		/*while(m.find()) {
			String find = m.group(1);
			System.out.println(find);
			String[] items = find.split(",");
			for(int i = 0; i < items.length; i++) {
				//System.out.println(items[i]);
				String trimmed = items[i].trim();
				System.out.println(trimmed);
			}
		}*/
		
		
		try {
			FileIO file = new FileIO();
			Automobile car = file.buildAutoObject("AutobileData.auto");
			System.out.println(car.toString());
			
			System.out.println("Deserialized Car _____________________");
			
			file.serializeObject(car, "Automobile.ser");
			Automobile Ford = (Automobile)file.deserializeObject("Automobile.ser");
			System.out.println(Ford.toString());
		} catch(IOException e) {
			System.out.print("Error:" + e);
		} catch (ClassNotFoundException e) {
			System.out.print("Error:" + e);
		}
	}
}
