package ders12;

public class Restangle {
	protected double witdh;
	protected double height;

	public Restangle(double witdh, double height) {
		super();
		this.witdh = witdh;
		this.height = height;
	}

	public Restangle() {
		super();
	}

	public Restangle(double witdh) {
		super();
		this.witdh = witdh;
	}

	public double getWitdh() {
		return witdh;
	}

	public void setWitdh(double witdh) {
		this.witdh = witdh;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
