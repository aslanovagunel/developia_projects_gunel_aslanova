package ders11;

public class SinifTap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SinifTap.method(1, 2, 3, 4, 5);

	}

	static void method(int... is) {
		int sum = 0;
//		for (int a : is) {
//			sum += is[a - 1];
//		}
		for (int i = 0; i < is.length; i++) {
			sum += is[i];
		}
		System.out.println(sum);
	}


}
