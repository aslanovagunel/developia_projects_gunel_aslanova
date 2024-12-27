package ders8;

public class ArraysDemo {

	public static void main(String[] args) {

		int[] numbers = { 32, 15, 7, 9, 45 };
		int sum = 0;
		for (int i : numbers) {
			sum += i;
		}
		System.out.println("cem: " + sum);

		double edediOrta = (double) sum / numbers.length;
		System.out.println("ededi orta :" + edediOrta);

		int min = numbers[0];
		for (int i = 0; i < numbers.length; i++) {
			if (min >= numbers[i]) {
				min = numbers[i];
			}
		}
		System.out.println("min eded :" + min);

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] % 2 == 1) {
				System.out.print(numbers[i] + " ");
			}
		}
	}

}
