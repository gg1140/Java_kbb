package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Model.OptionSet.Option;

public class OptionSet extends Collection<Option, String> implements Serializable{
	private String name;
	
	/* Constructors */
	public OptionSet() {
		super();
		this.name = null;
	}
	
	public OptionSet(String name, int size) {
		super(Option.class, size);
		this.name = name;
	}
	
	/* Abstract Methods from Collection */
	protected void initializeCollection() {
		//for(Option element : collection) {
	}
	
	
	protected String getKey(Option item) {
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
	
	/* Basic Getter, Setter & strRepresentation Methods */
	protected void setName(String name) {
		this.name = name;
	}
	
	protected String getName() {
		return this.name;	
	}
	
	protected String strRepresentation() {
		StringBuilder output = new StringBuilder("OptionSet: " + name + "\n");
		for (Option element : collection) {
			if (element != null) 
				output.append("\t" + element.strRepresentation() + "\n");
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
		int size = getCollectionSize();
		collection = null;
		collection = new Option[size]; //Allocate a new array with the same length
	}
	
	/* Update Option */
	protected boolean updateOption(String currentName, String newName, Double newPrice) {
		Option requestedOption = searchCollection(currentName);
		if(requestedOption != null) {
			requestedOption.setName(newName);
			requestedOption.setPrice(newPrice);
			return true;
		}
		return false;
	}
	
	protected boolean updateOptionName(String currentName, String newName) {
		Option requestedOption = searchCollection(currentName);
		if(requestedOption != null) {
			requestedOption.setName(newName);
			return true;
		}
		return false;
	}
	
	protected boolean updateOptionPrice(String currentName, double newPrice) {
		Option requestedOption = searchCollection(currentName);
		if(requestedOption != null) {
			requestedOption.setPrice(newPrice);
			return true;
		}
		return false;
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
			return requestedOption.strRepresentation();
		}
		return "";
	}
	
	/* Real Multiple Options */
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
	
	/**
	 ** Option Inner Class
	 **/
	protected class Option implements Serializable {
		private String name;
		private Double price;
		
		/* Constructors */
		protected Option() {
			this.name = null;
			this.price = 0.0;
		}
		
		public Option(String name, Double price) {
			//super();
			this.name = name;
			this.price = price;
		}

		/* Getter & Setter Functions */
		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected double getPrice() {
			return price;
		}

		protected void setPrice(Double price) {
			this.price = price;
		}
		
		/* Miscellaneous Functions */
		protected String strRepresentation() {
			StringBuilder output = new StringBuilder("Option: " + name + " $" + String.valueOf(price));
			return output.toString();
		}
	}
}
