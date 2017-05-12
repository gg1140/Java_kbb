package Adapter;

import Model.Automobile;
import Util.AutoException;
import Util.FileIO;

public abstract class proxyAutomobile {
	private static Automobile car;
	
	public proxyAutomobile() {}
	
	/* BuildAuto Interface */
	public void buildAuto(String fileName) {
		FileIO file = new FileIO();
		while (true) {
			try {		
				car = file.buildAutoObject(fileName);
				break;
			} catch (AutoException e) {
				fileName = (String) e.fix();
			}
		}
	}
	
	/* UpdateAuto Interface */
	public void updateOptionSetName(String currentName, String newName) {
		if (available()) {
			car.updateOptionSetName(currentName, newName);
		}
	}
	
	public void updateOptionPrice(String optSet, String opt, Double newPrice) {
		if (available()) {
			car.updateOptionPrice(optSet, opt, newPrice);
		}
	}
	
	/* PrintAuto Interface */
	public void printAuto() {
		System.out.println(car.toString());
	}
	
	/* Helper Methods */
	private boolean available() {
		boolean flag = false;
		if (car != null) {
			flag = true;
		}
		return flag;
	}
}
