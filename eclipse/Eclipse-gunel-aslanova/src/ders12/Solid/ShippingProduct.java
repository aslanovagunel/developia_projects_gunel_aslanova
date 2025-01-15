package ders12.Solid;

public class ShippingProduct implements Shipping {
	private Product baseProduct;

	public ShippingProduct(Product baseProduct) {
		super();
		this.baseProduct = baseProduct;
	}

	@Override
	public double shipping(double price) {
		return price / 10;
	}

}
