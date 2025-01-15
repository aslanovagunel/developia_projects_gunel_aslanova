package ders12.Solid;

public class NotDiscountedProduct implements Discounting {

	@Override
	public double discountStrategy(double price) {
		return price;
	}

}
