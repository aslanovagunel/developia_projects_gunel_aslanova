package ders11;

public class SinifTap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double edediOrta =method(1, 2, 3, 4, 5, 5);
		System.out.println(edediOrta);
	}

	static double method(int... is) {
		double sum = 0;

		for (int i = 0; i < is.length; i++) {
			sum += is[i];
		}

		return (double) sum / is.length;
	}


}
