package ders12.Solid;

public class BaseProduct extends Product {
	private DiscountedProduct discountedProduct;

	public BaseProduct(int id, String name, int price, DiscountedProduct discountedProduct) {
		super(id, name, price);
		this.discountedProduct = discountedProduct;
	}

	void discountPrice() {

	}
}
