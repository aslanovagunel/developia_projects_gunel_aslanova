package ders7;

import java.util.Random;

public class CalismaEv {

	public static void main(String[] args) {

		Random r = new Random();
		int eded = (r.nextInt(20) + 30);
		System.out.println(eded);

		int num = 64;
		double kokaltiEded = Math.sqrt(num);
		System.out.println(kokaltiEded);

		double quvvetUstuEded = Math.pow(4, 3);
		System.out.println(quvvetUstuEded);

		double num2 = 6.154;
		double yeniEded = num2 * 100;
		double n1 = yeniEded % 10;
		if (n1 >= 5) {
			yeniEded += 10;
		}
		int m = (int) yeniEded / 10;
		double yuvarlaqEded = (double) m / 10;

		System.out.println(yuvarlaqEded);

	}

}
