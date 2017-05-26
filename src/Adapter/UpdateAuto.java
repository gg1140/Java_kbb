package Adapter;

public interface UpdateAuto {
	void updateModelName(String model, String newName);
	void updateMaker(String model, String newMaker);
	void updateBasePrice(String model, Double newPrice);
	void updateOptionSetName(String model, String currentName, String newName);
	void updateOptionName(String model, String opSet, String currentName, String newName);
	void updateOptionPrice(String model, String opSet, String op, Double newPrice);
	void updateSelectedOption(String model, String opSet, String op);
}
