package Adapter;

public interface UpdateAuto {
	void updateOptionSetName(String model, String currentName, String newName);
	void updateOptionPrice(String model, String optSet, String opt, Double newPrice);
	void updateSelectedOption(String model, String optSet, String opt);
}
