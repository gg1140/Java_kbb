package Adapter;

import Model.AutoSet;
import Model.Automobile;
import Util.AutoException;
import Util.FileIO;

public abstract class proxyAutomobile {
	private static final AutoSet<Automobile> cars = new AutoSet<Automobile>();;
	
	public proxyAutomobile() {}
	
	/* BuildAuto Interface */
	public void buildAuto(String fileName) {
		FileIO file = new FileIO();
		Automobile newCar;
		while (true) {
			try {		
				newCar = file.buildAutoObject(fileName);
				cars.addAutomobile(newCar);
				break;
			} catch (AutoException e) {
				fileName = (String) e.fix();
			}
		}
	}
	
	/* UpdateAuto Interface */
	public void updateOptionSetName(String model, String currentName, String newName) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			requestedCar.updateOptionSetName(currentName, newName);
		}
	}
	
	public void updateOptionPrice(String model, String optSet, String opt, Double newPrice) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			requestedCar.updateOptionPrice(optSet, opt, newPrice);
		}
	}
	
	public void updateSelectedOption(String model, String optSet, String opt) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			requestedCar.setSelectedOption(optSet, opt);
		}
	}
	
	/* PrintAuto Interface */
	public void printAuto(String model) {
		System.out.println(cars.getStrRepOf(model));
	}
	
	public void printAllAuto() {
		System.out.println(cars.strRep());
	}
	
	public void printAllSelectedOpts(String model) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			System.out.println(requestedCar.strRepSelectedOps());
		}
	}
	
	/* Helper Methods */
	/*private boolean available() {
		boolean flag = false;
		if (car != null) {
			flag = true;
		}
		return flag;
	}*/
}
