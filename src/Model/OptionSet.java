package Model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class OptionSet extends ModelColle<Option, String> implements Serializable{
	private String name;
	private Option selectedOp;
	
	/* Constructors */
	public OptionSet() {
		super();
		this.name = null;
		//this.selectedOp = null;
		this.selectedOp = new Option();
	}
	
	public OptionSet(String name) {
		super();
		this.name = name;
		//this.selectedOp = null;
		this.selectedOp = new Option();
	}
	
	public OptionSet(String name, int size) {
		super(size);
		this.name = name;
		//this.selectedOp = null;
		this.selectedOp = new Option();
	}
	
	/* Abstract Methods from Collection */
	protected void initializeCollection() {
		//for(Option element : collection) {
	}
	
	protected String getKey(Option item) {
		return item.getName();
	}
	
	protected boolean matchKey(String k1, String k2) {
		if (k1.equalsIgnoreCase(k2)) {
			return true;
		} else {
			return false;
		}
	}
	
	/* Basic Getter, Setter & strRepresentation Methods */
	protected void setName(String name) {
		this.name = name;
	}
	
	protected String getName() {
		return this.name;	
	}
	
	protected String strRep() {
		StringBuilder output = new StringBuilder("OptionSet: " + name + "\n");
		for (Option element : collection) {
			if (element != null) 
				output.append("\t" + element.strRep() + "\n");
		}
		return output.toString();
	}
	
	/* Create & Delete Option */
	protected boolean addOption(String name, Double price) {
		Option newOpt = new Option(name, price);
		boolean confirmation = addToCollection(newOpt);
		return confirmation;
	}
	
	protected boolean removeOption(String name) {
		boolean confirmation = removeFromCollection(name);
		return confirmation;
	}
	
	protected void removeAllOption() {
		collection.clear();
	}
	
	/* Update Option */
	protected boolean updateOption(String currentName, String newName, Double newPrice) {
		Option requestedOption = searchCollection(currentName);
		if (requestedOption != null) {
			requestedOption.setName(newName);
			requestedOption.setPrice(newPrice);
			return true;
		}
		return false;
	}
	
	protected boolean updateOptionName(String currentName, String newName) {
		Option requestedOption = searchCollection(currentName);
		if (requestedOption != null) {
			requestedOption.setName(newName);
			return true;
		}
		return false;
	}
	
	protected boolean updateOptionPrice(String currentName, double newPrice) {
		Option requestedOption = searchCollection(currentName);
		if (requestedOption != null) {
			requestedOption.setPrice(newPrice);
			return true;
		}
		return false;
	}
	
	protected boolean setSelectedOption(String name) {
		Option requestedOption = searchCollection(name);
		if (requestedOption != null) {
			selectedOp = requestedOption;
			//System.out.println(selectedOp.strRep());
			return true;
		} else {
			return false;
		}
	}
	/* Read Single Option */
	protected Double getOptionPrice(String name) {
		Option requestedOption = searchCollection(name);
		if (requestedOption != null) {
			return requestedOption.getPrice();
		}
		return -3.1415;
	}
	
	protected String getOptionStrRepresentation(String name) {
		Option requestedOption = searchCollection(name);
		if (requestedOption != null) {
			return requestedOption.strRep();
		}
		return "";
	}
	
	protected Option getSelectedOption() {
		return selectedOp;
	}
	
	/* Read Multiple Options */
	protected String[] getAllOptionName() {
		ArrayList<String> nameCollection = new ArrayList<String>();
		for (Option element : collection) {
			if (element != null) {
				nameCollection.add(element.getName());
				
			}
		}
		String[] output = new String[nameCollection.size()];
		nameCollection.toArray(output);
		return output;
	}
	
	protected Double[] getAllOptionPrice() {
		ArrayList<Double> priceCollection = new ArrayList<Double>();
		for (Option element : collection) {
			if (element != null) {
				priceCollection.add(element.getPrice());
			}
		}
		Double[] output = new Double[priceCollection.size()];
		priceCollection.toArray(output);
		return output;
	}

	//protected String[][] getAllOptionAsStrArray() {
		/*
		 * This Function return a 2D String Array of nx2 holding the name and price of all option
		 * Return:
		 * String[][]: [n][0] hold Option Names and [n][1] hold Option Price.
		 */
	
	//protected String[][] getOptionsFromPriceRange(Double upperLimit, Double lowerLimit) //Will be implemented in later lab
}
