import java.io.IOException;

import Adapter.CreateAuto;
import Adapter.PrintAuto;
import Adapter.UpdateAuto;
import Adapter.buildAuto;
import Util.AutoException;

public class Driver {
	public static void main(String[] args) {
		/** Test Code **/
		//Testing API in adapter package:
		String fileName = "FordWagonZTW.auto";
		try {
			CreateAuto carBuilder = new buildAuto();
			carBuilder.buildAuto(fileName);
			PrintAuto carPrint = new buildAuto();
			carPrint.printAuto();
			UpdateAuto carUpdate = new buildAuto();
			carUpdate.updateOptionSetName("Brakes/Traction Control", "B/TC");
			carUpdate.updateOptionPrice("B/TC", "Standard", 50.0);
			carPrint.printAuto();
		} catch (AutoException e) {
			fileName = (String) e.fix();
		}
		
		//Testing with incorrect file name:
		/*String fileName = "FordWagonZT.auto";
		boolean flag = true;
		while (flag) {
			try {
				System.out.println("Start of Loop");
				CreateAuto carBuilder = new buildAuto();
				carBuilder.buildAuto(fileName);
				PrintAuto carPrint = new buildAuto();
				carPrint.printAuto();
				flag = false;
			} catch (AutoException e) {
				fileName = (String) e.fix();
			}
		}*/
		
		//Testing with incorrect string conversion to number:
		/*String fileName = "FordWagonZTW_Incorrect.auto";
		boolean flag = true;
		while (flag) {
			try {
				System.out.println("Start of Loop");
				CreateAuto carBuilder = new buildAuto();
				carBuilder.buildAuto(fileName);
				PrintAuto carPrint = new buildAuto();
				carPrint.printAuto();
				flag = false;
			} catch (AutoException e) {
				fileName = (String) e.fix();
			}
		}*/
	}
}
