package Adapter;

import Model.Automobile;
import Util.AutoException;
import Util.FileIO;

public abstract class proxyAutomobile {
	private static Automobile car;
	
	public proxyAutomobile() {}
	
	/* BuildAuto Interface */
	public void buildAuto(String fileName) throws AutoException{
		FileIO file = new FileIO();
		try {
			car = file.buildAutoObject(fileName);
		} catch (AutoException e) {
			throw e;
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
	
	public String strRepresentation(String model) {
		if (available()) {
			return car.toString();
		}
		else {
			return "";
		}
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
