package ders12;

public class Square extends Restangle {

	public Square(double witdh) {
		super(witdh);
	}

	public Square() {

	}

	void hesabla(double witdh) {
		double b = witdh * witdh;
		System.out.println(b);
	}

}
