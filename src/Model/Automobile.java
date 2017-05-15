package Model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Automobile extends ModelColle<OptionSet, String> implements Serializable{
	private String maker, model;
	private double basePrice;
	private SelectedOptionSet selectedOps;
	
	/*Constructors*/
	public Automobile() {
		super();
		this.maker = null;
		this.model = null;
		this.basePrice = 0.0;
		this.selectedOps = new SelectedOptionSet();
	}
	
	public Automobile(String model, double basePrice, int numOfOptionSet) {
		super(numOfOptionSet);
		this.maker = "Unknown";
		this.model = model;
		this.basePrice = basePrice;
		this.selectedOps = new SelectedOptionSet();
	}
	
	public Automobile(String maker, String model, double basePrice, int numOfOptionSet) {
		super(numOfOptionSet);
		this.maker = maker;
		this.model = model;
		this.basePrice = basePrice;
		this.selectedOps = new SelectedOptionSet();
	}
	
	/*public void test() {
		System.out.println(strRepSelectedOps());
		setSelectedOption("Transmission", "Manual");
		System.out.println(strRepSelectedOps());
	}*/
	
	public boolean setSelectedOption(String optSetName, String optName) {
		boolean output = false;
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			output = requestedOptSet.setSelectedOption(optName);
			selectedOps.setSelectedOption(optSetName, requestedOptSet.getSelectedOption());
		}
		return output;
	}
	
	public String getSelectedOptionName(String optSetName) {
		return selectedOps.getSelectedOption(optSetName).getName();
	}
	
	public Double getSelectedOptionPrice(String optSetName) {
		return selectedOps.getSelectedOption(optSetName).getPrice();
	}
	
	/* Abstract Methods from Collection */
	protected void initializeCollection() {}
	
	protected String getKey(OptionSet item) {
		return item.getName();
	}
	
	protected boolean matchKey(String k1, String k2) {
		if (k1.equalsIgnoreCase(k2)) {
			return true;
		} else {
			return false;
		}
	}
	
	/* Basic Getter, Setter & toString Methods */
	public String getMaker() {
		return this.maker;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public double getBasePrice() {
		return this.basePrice;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder("Maker: " + maker + 
				"\nModel: " + model + 
				"\nbasePrice: " + basePrice + "\n");
		
		for (OptionSet element : collection) {
			if (element != null) 
				output.append(element.strRep());
		}
		return output.toString();
	}
	
	public String strRepSelectedOps() {
		StringBuilder output = new StringBuilder("Maker: " + maker + 
				"\nModel: " + model + 
				"\nbasePrice: " + basePrice + "\n");
		output.append(selectedOps.strRep());
		return output.toString();
	}
	
	/* Create & Delete OptionSet */
	public boolean addOptionSet(String name, int size) {
		OptionSet newOptSet = new OptionSet(name, size);
		selectedOps.addSelectedOption(name, null);
		return addToCollection(newOptSet);
	}
	
	public boolean removeOptionSet(String name) {
		selectedOps.removeSelectedOption(name);
		return removeFromCollection(name);
	}
	
	public void removeAllOptionSet() {
		selectedOps.removeAllSelectedOptions();
		collection.clear();
	}
	
	/* Update OptionSet */
	public boolean updateOptionSetName(String currentName, String newName) {
		OptionSet requestedOption = searchCollection(currentName);
		if (requestedOption != null) {
			requestedOption.setName(newName);
			selectedOps.setOptSetLabel(currentName, newName);
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
		if (requestedOption != null) {
			return requestedOption.getCollectionSize();
		}
		return -1;
	}
	
	public String getOptionSetStrRep(String name) {
		OptionSet requestedOption = searchCollection(name);
		if (requestedOption != null) {
			return requestedOption.strRep();
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
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.addOption(optName, optPrice);
		} else {
			return false;
		}
	}
	
	public boolean removeOption(String optSetName, String optName) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.removeOption(optName); //Could also use method from collection
		} else {
			return false;
		}
	}
	
	public void removeAllOption(String optSetName) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			requestedOptSet.removeAllOption();
		}
	}
	
	/* Update Option */
	public boolean updateOption(String optSetName, String currentName, String newName, Double newPrice) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.updateOption(currentName, newName, newPrice);
		} else {
			return false;
		}
	}
	
	public boolean updateOptionName(String optSetName, String currentName, String newName) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.updateOptionName(currentName, newName);
		} else {
			return false;
		}
	}
	
	public boolean updateOptionPrice(String optSetName, String currentName, double newPrice) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.updateOptionPrice(currentName, newPrice);
		} else {
			return false;
		}
	}
	
	///////////////****************
	
	/* Read Single Option */
	public Double getOptionPrice(String optSetName, String optName) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.getOptionPrice(optName);
		}
		return -3.1415;
	}
	
	public String getOptionStrRep(String optSetName, String optName) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.getOptionStrRepresentation(optName);
		}
		return "";
	}
	
	///////////////**********
	////////////*******
	
	/* Read Multiple Options */
	public String[] getAllOptionName(String optSetName) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.getAllOptionName();
		}
		String[] output = null;
		return  output;
	}
	
	public Double[] getAllOptionPrice(String optSetName) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if (requestedOptSet != null) {
			return requestedOptSet.getAllOptionPrice();
		}
		Double[] output = null;
		return  output;
	}

	public String[] getAllSelectedOpName() {
		return selectedOps.getAllSelectedOpName();
	}
	
	public Double[] getAllSelectedOpPrice() {
		return selectedOps.getAllSelectedOpPrice();
	}
	
	public Double getTotalPrice() {
		Double output = basePrice + selectedOps.sumOfAllSelectedOpPrice();
		return output;
	}
	
	/*public String[][] getAllOptionAsStrArray(String optSetName) {
		OptionSet requestedOptSet = searchCollection(optSetName);
		if(requestedOptSet != null) {
			return requestedOptSet.getAllOptionAsStrArray();
		}
		String[][] output = null;
		return  output;
	}*/
}
