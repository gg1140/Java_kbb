package Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Option implements Serializable {
	private String name;
	private Double price;
	
	/* Constructors */
	protected Option() {
		this.name = "Unknown";
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
	protected String strRep() {
		StringBuilder output = new StringBuilder("Option: " + name + " $" + String.valueOf(price));
		return output.toString();
	}
}
