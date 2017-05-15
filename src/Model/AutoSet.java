package Model;

import java.util.LinkedHashMap;
import java.util.Map;

public class AutoSet<X extends Automobile> {
	private LinkedHashMap<String, X> AutoColle;
	
	public AutoSet() {
		AutoColle = new LinkedHashMap<String, X>();
	}
	
	public boolean addAutomobile(X car) {
		boolean output = false;
		String key = car.getModel();
		if (!AutoColle.containsKey(key)) {
			AutoColle.put(key, car);
			output = true;
		}
		return output;
	}
	
	public void removeAutomobile(String key) {
		AutoColle.remove(key);
	}
	
	public void clear() {
		AutoColle.clear();
	}
	
	public X getAutomobile(String key) {
		return AutoColle.get(key);
	}
	
	public String getStrRepOf(String key) {
		Automobile requestedCar = AutoColle.get(key);
		if (requestedCar != null) {
			return requestedCar.toString();
		}
		return "";
	}
	public String strRep() {
		StringBuilder output = new StringBuilder();
		for (Map.Entry<String, X> entry : AutoColle.entrySet()) {
			output.append(entry.getValue().toString() + "\n");
		}
		
		return output.toString();
	}
}
