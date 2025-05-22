package ders46;

import java.util.Scanner;

public class BoyukKicikEded {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int[] ededler = new int[n];

		for (int i = 0; i < n; i++) {
			ededler[i] = scanner.nextInt();
		}

		int max = ededler[0];
		int min = ededler[0];

		for (int i = 1; i < n; i++) {
			if (ededler[i] > max) {
				max = ededler[i];
			}
			if (ededler[i] < min) {
				min = ededler[i];
			}
		}

		System.out.println("Ən böyük ədəd: " + max);
		System.out.println("Ən kiçik ədəd: " + min);

	}

}
