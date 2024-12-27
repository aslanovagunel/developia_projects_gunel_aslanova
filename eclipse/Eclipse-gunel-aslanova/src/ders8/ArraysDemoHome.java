package ders8;

import java.util.Arrays;

public class ArraysDemoHome {

	public static void main(String[] args) {
		int[] numbers = { 13, 7, 5, 8, 12 };
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		System.out.println("cem :" + sum);

		for (int num : numbers) {
			System.out.print(num + " ");
		}

		double edediOrta = (double) sum / numbers.length;
		System.out.println("edediOrta :" + edediOrta);

		int min = numbers[0];
		int max = numbers[0];

		for (int i = 0; i < numbers.length; i++) {
			if (min >= numbers[i]) {
				min = numbers[i];
			}
			if (max <= numbers[i]) {
				max = numbers[i];

			}
		}
		System.out.println("min eded :" + min);
		System.out.println("max eded :" + max);

		System.out.print("tek ededler :");
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] % 2 == 1) {
				System.out.print(numbers[i] + " ");
			}
		}

		System.out.println();
		System.out.print("cut ededler :");
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] % 2 == 0) {
				System.out.print(numbers[i] + " ");
			}
		}
		
		System.out.println();
		System.out.print("sade ededler :");
		for (int i = 0; i < numbers.length; i++) {
			int count = 0;
			for (int j = 1; j <= max; j++) {
				if (numbers[i] % j == 0) {
					count++;
				}
			}
			if (count == 2)
				System.out.print(numbers[i] + " ");

		}

		System.out.println();
		Arrays.sort(numbers);
		System.out.print("azalan sira :");
		for (int i = numbers.length - 1; i >= 0; i--) {
			System.out.print(numbers[i] + " ");
		}


	}

}
