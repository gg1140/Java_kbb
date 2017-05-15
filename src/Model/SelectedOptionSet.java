package Model;

import java.util.ArrayList;

public class SelectedOptionSet extends OptionSet{
	private ArrayList<String> optSetLabels;
	
	public SelectedOptionSet() {
		super("Selected Option");
		optSetLabels = new ArrayList<String>();
	}
	
	public boolean addSelectedOption(String optSet, Option opt) {
		if (opt == null)
			opt = new Option();
		
		boolean output = addToCollection(opt);
		if (output)
			optSetLabels.add(optSet);
		return output;
	}
	
	public boolean removeSelectedOption(String optSet) {
		int index = optSetLabels.indexOf(optSet);
		boolean output = removeFromCollection(index);
		if (output) {
			optSetLabels.remove(index);
		}
		return output;
	}
	
	public void removeAllSelectedOptions() {
		removeAllOption();
	}
	
	public void setOptSetLabel(String currentName, String newName) {
		int index = optSetLabels.indexOf(currentName);
		optSetLabels.set(index, newName);
	}
	
	public void setSelectedOption(String optSet, Option opt) {
		int index = optSetLabels.indexOf(optSet);
		if (index >= 0) {
			collection.set(index, opt);
		}
	}
	
	public Option getSelectedOption(String optSet) {
		int index = optSetLabels.indexOf(optSet);
		return collection.get(index);
	}
	
	public String[] getAllSelectedOpName() {
		return getAllOptionName();
	}

	public Double[] getAllSelectedOpPrice() {
		return getAllOptionPrice();
	}
	
	public Double sumOfAllSelectedOpPrice() {
		Double[] allOpPrice = getAllOptionPrice();
		Double output = 0.0;
		for (Double price : allOpPrice) {
			output += price;
		}
		return output;
	}
	
	public String strRep() {
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
