package ders46;

import java.util.Scanner;

public class SozunTersi {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Söz daxil edin: ");
		String soz = scanner.nextLine();

		String ters = "";
		for (int i = soz.length() - 1; i >= 0; i--) {
			ters += soz.charAt(i);
		}

		System.out.println("Sözün tərsi: " + ters);

	}

}
