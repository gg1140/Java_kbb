package Model;

public interface SelectedOpSet {
	public boolean addSelectedOp(OptionSet opSet);
	public boolean removeSelectedOp(String opSet);
	public void removeAllSelectedOp();
	public void setOpSetLabel(String currentName, String newName);
	public Option getSelectedOp(String opSet);
	public String[] getAllSelectedOpName();
	public Double[] getAllSelectedOpPrice();
	public Double sumOfAllSelectedOpPrice();
	public String strRep();
}
