package Adapter;

import Model.Automobile;
import Util.FileIO;

public abstract class proxyAutomobile {
	public static Automobile car;
	
	public proxyAutomobile() {
		car = null;
	}
	
	/* BuildAuto Interface */
	public void BuildAuto(String fileName) {
		FileIO file = new FileIO();
		car = file.buildAutoObject(fileName);
	}
	
	/* UpdateAuto Interface */
	public void updateOptionSetName(String currentName, String newName) {
		if (avaliable()) {
			car.updateOptionSetName(currentName, newName);
		}
	}
	
	public void updateOptionPrice(String optSet, String opt, Double newPrice) {
		if (avaliable()) {
			car.updateOptionPrice(optSet, opt, newPrice);
		}
	}
	
	/* PrintAuto Interface */
	public void printAuto() {
		System.out.println(car.toString());
	}
	
	public String strRepresentation(String model) {
		if (avaliable()) {
			return car.toString();
		}
		else {
			return "";
		}
	}
	
	/* Helper Methods */
	private boolean avaliable() {
		boolean flag = false;
		if (car != null) {
			flag = true;
		}
		return flag;
	}
}
