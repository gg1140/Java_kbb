import java.io.IOException;

import Adapter.CreateAuto;
import Adapter.PrintAuto;
import Adapter.UpdateAuto;
import Adapter.kbbFacade;
import Util.AutoException;

public class Driver {
	public static void main(String[] args) {
		/** Test Code **/
		//Testing with incorrect file name:
		String fileName = "FordWagonZT.auto";
		System.out.println("Start of Loop");
		CreateAuto carBuilder = new kbbFacade();
		carBuilder.buildAuto(fileName);
		PrintAuto carPrint = new kbbFacade();
		carPrint.printAuto();

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
