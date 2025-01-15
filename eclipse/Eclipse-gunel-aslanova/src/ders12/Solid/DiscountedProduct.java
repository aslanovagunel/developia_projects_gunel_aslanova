package ders12.Solid;

public class DiscountedProduct implements Discounting{
	private double discount;
	private Product product;

	public DiscountedProduct(Product product, double discount) {
		super();
		this.product = product;
		this.discount = discount;
	}

	@Override
	public double discountStrategy(double discount) {
		
		return (product.getPrice() * discount) / 100;
	}

}
