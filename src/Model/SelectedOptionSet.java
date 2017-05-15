package Model;

import java.util.ArrayList;

public class SelectedOptionSet extends OptionSet{
	private ArrayList<String> optSetLabels;
	
	public SelectedOptionSet() {
		super("Selected Option");
		optSetLabels = new ArrayList<String>();
	}
	
	protected boolean addSelectedOption(String optSet, Option opt) {
		if (opt == null)
			opt = new Option();
		
		boolean output = addToCollection(opt);
		if (output)
			optSetLabels.add(optSet);
		return output;
	}
	
	protected boolean removeSelectedOption(String optSet) {
		int index = optSetLabels.indexOf(optSet);
		boolean output = removeFromCollection(index);
		if (output) {
			optSetLabels.remove(index);
		}
		return output;
	}
	
	protected void removeAllSelectedOptions() {
		removeAllOption();
	}
	
	protected void setOptSetLabel(String currentName, String newName) {
		int index = optSetLabels.indexOf(currentName);
		optSetLabels.set(index, newName);
	}
	
	protected void setSelectedOption(String optSet, Option opt) {
		int index = optSetLabels.indexOf(optSet);
		if (index >= 0) {
			collection.set(index, opt);
		}
	}
	
	protected Option getSelectedOption(String optSet) {
		int index = optSetLabels.indexOf(optSet);
		return collection.get(index);
	}
	
	protected String[] getAllSelectedOpName() {
		return getAllOptionName();
	}

	protected Double[] getAllSelectedOpPrice() {
		return getAllOptionPrice();
	}
	
	protected Double sumOfAllSelectedOpPrice() {
		Double[] allOpPrice = getAllOptionPrice();
		Double output = 0.0;
		for (Double price : allOpPrice) {
			output += price;
		}
		return output;
	}
	
	protected String strRep() {
		StringBuilder output = new StringBuilder(getName() + ":\n");
		for (int i = 0; i < collection.size(); i++) {
			if (collection.get(i).getName() != "Unknown") {
				output.append("\t" + optSetLabels.get(i) + ": " + 
						collection.get(i).strRep() + "\n");
			}
		}
		return output.toString();
	}
	
}
