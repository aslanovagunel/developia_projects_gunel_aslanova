package ders12.Solid;

public class OrderProcessing {
	private Product product;
	private DiscountedProduct discountedProduct;
	private int count;
	private Shipping shipping;

	public OrderProcessing(Product product, int count, DiscountedProduct discountedProduct, Shipping shipping) {
		super();
		this.product = product;
		this.count = count;
		this.discountedProduct = discountedProduct;
		this.shipping = shipping;
	}

	public OrderProcessing() {
		super();
	}

	double hesablama(Product product, int count, DiscountedProduct discountedProduct, Shipping shipping) {
		double total = product.getPrice() - discountedProduct.discountStrategy(product.getPrice());
		System.out.println("total " + total);
		return count * total + shipping.shipping(total);
	}
}
