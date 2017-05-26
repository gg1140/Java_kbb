package Adapter;

import Model.AutoSet;
import Model.Automobile;
import Scale.AutoEditor;
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
	/**
	 * For Testing only. Will be removed later
	 * @param model
	 */
	public void updateModelName(String model, String newName) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			String[] param = new String[]{newName};
			editOps(requestedCar, 0, param);
			
		}
	}
	
	public void updateMaker(String model, String newMaker) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			String[] param = new String[]{newMaker};
			editOps(requestedCar, 1, param);
			
		}
	}
	
	public void updateBasePrice(String model, Double newPrice) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			String[] param = new String[]{Double.toString(newPrice)};
			editOps(requestedCar, 2, param);
			
		}
	}
	
	public void updateOptionSetName(String model, String currentName, String newName) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			String[] param = new String[]{currentName, newName};
			editOps(requestedCar, 3, param);
			
		}
	}
	
	public void updateOptionName(String model, String opSet, String currentName, String newName) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			String[] param = new String[]{opSet, currentName, newName};
			editOps(requestedCar, 4, param);
		}
	}
	
	public void updateOptionPrice(String model, String opSet, String op, Double newPrice) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			String[] param = new String[]{opSet, op, Double.toString(newPrice)};
			editOps(requestedCar, 5, param);
		}
	}
	
	public void updateSelectedOption(String model, String opSet, String op) {
		Automobile requestedCar = cars.getAutomobile(model);
		if (requestedCar != null) {
			String[] param = new String[]{opSet, op};
			editOps(requestedCar, 6, param);
		}
	}
	
	public void editOps(Automobile car, int functNo, String[] param) {
		AutoEditor editor = new AutoEditor(car, functNo, param);
		Thread t = new Thread(editor);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {}
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
}
