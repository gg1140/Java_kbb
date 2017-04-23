package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import Model.OptionSet.Option;

public class Automobile extends Collection<OptionSet, String> implements Serializable{
	private String /*maker,*/ model;
	private double basePrice;
	
	/*Constructors*/
	public Automobile() {
		super();
		this.model = null;
		this.basePrice = 0.0;
	}
	
	public Automobile(String model, double basePrice, int numOfOptionSet) {
		super(OptionSet.class, numOfOptionSet);
		this.model = model;
		this.basePrice = basePrice;
	}
	
	/* Abstract Methods from Collection */
	protected void initializeCollection() {}
	
	protected String getKey(OptionSet item) {
		return item.getName();
	}
	
	protected boolean matchKey(String k1, String k2) {
		if(k1.equalsIgnoreCase(k2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* Basic Getter, Setter & toString Methods */
	public String getModel() {
		return this.model;
	}
	
	public double getBasePrice() {
		return this.basePrice;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder("Model: " + model + "\nbasePrice: " + basePrice + "\n");
		
		for (OptionSet element : collection) {
			if (element != null) 
				output.append(element.strRepresentation());
		}
		return output.toString();
	}
	
	/* Create & Delete OptionSet */
	public boolean addOptionSet(String name, int size) {
		OptionSet newOptSet = new OptionSet(name, size);
		return addToCollection(newOptSet);
	}
	
	public boolean removeOptionSet(String name) {
		return removeFromCollection(name);
	}
	
	public void removeAllOptionSet() {
		int size = getCollectionSize();
		collection = null;
		collection = new OptionSet[size]; //Allocate a new array with the same length
	}
	
	/* Update OptionSet */
	public boolean updateOptionSetName(String currentName, String newName) {
		OptionSet requestedOption = searchCollection(currentName);
		if(requestedOption != null) {
			requestedOption.setName(newName);
			return true;
		}
		return false;
	}
	
	/* Read Single OptionSet */
	public int getNumOfOptionSet() {
		return getCollectionSize();
	}
	
	public int getOptionSetSize(String name) {
		OptionSet requestedOption = searchCollection(name);
		if(requestedOption != null) {
			return requestedOption.getCollectionSize();
		}
		return -1;
	}
	
	public String getOptionSetStrRepresentation(String name) {
		OptionSet requestedOption = searchCollection(name);
		if(requestedOption != null) {
			return requestedOption.strRepresentation();
		}
		return "";
	}
	
	/* Read Multiple OptionSets */
	public String[] getAllOptionSetName() {
		ArrayList<String> nameCollection = new ArrayList<String>();
		for (OptionSet element : collection) {
			if (element != null) {
				nameCollection.add(element.getName());
			}
		}
		String[] output = new String[nameCollection.size()];
		nameCollection.toArray(output);
		return output;
	}
	
	public Integer[] getAllOptionSetSize() {
		ArrayList<Integer> sizeCollection = new ArrayList<Integer>();
		for (OptionSet element : collection) {
			if (element != null) {
				sizeCollection.add(element.getCollectionSize());
			}
		}
		Integer[] output = new Integer[sizeCollection.size()];
		sizeCollection.toArray(output);
		return output;
	}

	/** Create & Delete Option */
	public boolean addOption(String optSetName, String optName, Double optPrice) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.addOption(optName, optPrice);
		}
		else {
			return false;
		}
	}
	
	public boolean removeOption(String optSetName, String optName) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.removeOption(optName); //Could also use method from collection
		}
		else {
			return false;
		}
	}
	
	public void removeAllOption(String optSetName) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			selectedOptSet.removeAllOption();
		}
	}
	
	/* Update Option */
	public boolean updateOption(String optSetName, String currentName, String newName, Double newPrice) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.updateOption(currentName, newName, newPrice);
		}
		else {
			return false;
		}
	}
	
	public boolean updateOptionName(String optSetName, String currentName, String newName) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.updateOptionName(currentName, newName);
		}
		else {
			return false;
		}
	}
	
	public boolean updateOptionPrice(String optSetName, String currentName, double newPrice) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.updateOptionPrice(currentName, newPrice);
		}
		else {
			return false;
		}
	}
	
	/* Read Single Option */
	public Double getOptionPrice(String optSetName, String optName) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.getOptionPrice(optName);
		}
		return -3.1415;
	}
	
	public String getOptionStrRepresentation(String optSetName, String optName) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.getOptionStrRepresentation(optName);
		}
		return "";
	}
	
	/* Read Multiple Options */
	public String[] getAllOptionName(String optSetName) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.getAllOptionName();
		}
		String[] output = null;
		return  output;
	}
	
	public Double[] getAllOptionPrice(String optSetName) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.getAllOptionPrice();
		}
		Double[] output = null;
		return  output;
	}

	/*public String[][] getAllOptionAsStrArray(String optSetName) {
		OptionSet selectedOptSet = searchCollection(optSetName);
		if(selectedOptSet != null) {
			return selectedOptSet.getAllOptionAsStrArray();
		}
		String[][] output = null;
		return  output;
	}*/
}
