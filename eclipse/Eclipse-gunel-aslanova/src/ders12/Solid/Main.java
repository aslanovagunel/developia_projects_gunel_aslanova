package ders12.Solid;

public class Main {
	public static void main(String[] args) {
		Product pr = new Product(1, "Telefon", 10);
		DiscountedProduct s = new DiscountedProduct(pr, 10);
		ShippingProduct sh = new ShippingProduct(pr);

		OrderProcessing o = new OrderProcessing();
		double totalprice = o.hesablama(pr, 1, s, sh);

		System.out.println(totalprice);
	}
}
