package ders9;

public class Person {
	public int sum;
	private char letter;
	String word;
	static int deyisen;
	protected double edediOrta;
	
	void finalSoz() {
		final int b = 7;
		System.out.println("final ededimiz : " + b);
	}

	public Person(int a) {
		System.out.println("sizin yazdiginiz reqem: " + a);
	}

	private Person(char b) {
		System.out.println("sizin yazdiginiz herf: " + b);
	}

	Person(String soz) {
		System.out.println("sizin yazdiginiz soz: " + soz);
	}

	protected Person(double number) {
		System.out.println("sizin yazdiginiz kesrli eded: " + number);
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int sum(int num1, int num2) {
		int cem = num1 + num2;
		return cem;
	}

	private char letter(String word) {
		char letter = ' ';
		System.out.print("sozun herf formasi :");
		for (int i = 0; i < word.length(); i++) {
			letter += word.charAt(i);
			System.out.print(letter + " ");
		}
		return letter;

	}

	String letters(char... letters) {
		String word = "";
		for (int i = 0; i < letters.length; i++) {
			word += letters[i];
		}
		return "sozumuz: " + word;
	}

	protected double edediOrta(int... nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		double edediOrta = sum / nums.length;

		return edediOrta;
	}

}
