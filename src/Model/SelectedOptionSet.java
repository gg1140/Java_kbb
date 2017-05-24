package Model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SelectedOptionSet extends OptionSet implements SelectedOpSet{
	private ArrayList<String> optSetLabels;
	
	public SelectedOptionSet() {
		super("Selected Option");
		optSetLabels = new ArrayList<String>();
		setDuplicationAllowance(true);
	}
	
	public boolean addSelectedOp(OptionSet opSet) {
		boolean output = addToCollection(opSet.getSelectedOption());
		if (output)
			optSetLabels.add(opSet.getName());
		return output;
	}
	
	public boolean removeSelectedOp(String opSet) {
		int index = optSetLabels.indexOf(opSet);
		boolean output = removeFromCollection(index);
		if (output) {
			optSetLabels.remove(index);
		}
		return output;
	}
	
	public void removeAllSelectedOp() {
		removeAllOption();
	}
	
	public void setOpSetLabel(String currentName, String newName) {
		int index = optSetLabels.indexOf(currentName);
		optSetLabels.set(index, newName);
	}
	
	public Option getSelectedOp(String opSet) {
		int index = optSetLabels.indexOf(opSet);
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
		//System.out.println(collection.size());
		for (int i = 0; i < collection.size(); i++) {
			if (!collection.get(i).getName().equalsIgnoreCase("Unknown")) {
				output.append("\t" + optSetLabels.get(i) + ": " + 
						collection.get(i).strRep() + "\n");
			}
		}
		return output.toString();
	}
	
}
