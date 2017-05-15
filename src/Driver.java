import java.io.IOException;

import Adapter.CreateAuto;
import Adapter.PrintAuto;
import Adapter.UpdateAuto;
import Adapter.kbbFacade;
import Model.Automobile;
import Util.FileIO;

public class Driver {
	public static void main(String[] args) {
		/** Test Code **/
		String fileName = "FordWagonZTW.auto";
		//Testing with incorrect file name:
		/*
		System.out.println("Start Test");
		CreateAuto carBuilder = new kbbFacade();
		carBuilder.buildAuto(fileName);
		PrintAuto carPrint = new kbbFacade();
		UpdateAuto carUpdate = new kbbFacade();
		carPrint.printAllSelectedOpts("Ford Wagon ZTW");
		carUpdate.updateSelectedOption("Ford Wagon ZTW", "Transmission", "Manual");
		carPrint.printAllSelectedOpts("Ford Wagon ZTW");
		*/
		
		//Testing Chosen Options
		
		FileIO file = new FileIO();
		try {
			Automobile car = file.buildAutoObject(fileName);
			System.out.println("Testing Getter and Setter:");
			car.setSelectedOption("Transmission", "manual");
			System.out.println(car.strRepSelectedOps());
			car.updateOptionSetName("Transmission", "trans");
			System.out.println(car.getSelectedOptionName("trans"));
			System.out.println("Testing Update methods:\n" + car.strRepSelectedOps());
		} catch(Exception e) {}
		
		
	}
}
