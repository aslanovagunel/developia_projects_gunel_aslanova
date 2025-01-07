package ders10;

public class StringDersPraktikasi2 {

	public static void main(String[] args) {

		char[] letters= {'c','o','m','p','u','t','e','r'};
		String word = String.copyValueOf(letters);
		System.out.println(word);

		String pattern = "[a-z]{3}";
		String test1 = "name";
		String test2 = "na1";
		String test3 = "try";
		System.out.println(test1.matches(pattern));
		System.out.println(test2.matches(pattern));
		System.out.println(test3.matches(pattern));

		String check = " ";
		if (check.isBlank())
			System.out.println("bosdur");
		else
			System.out.println("doludur");

		StringDersPraktikasi2 s = new StringDersPraktikasi2();
		s.test("sala", "la", "ra");

		s.yoxlama("kitab", "ab");

	}

	void test(String a, String b, String c) {
		boolean check = a.contains(b);
		if (check) {
			a = a.replace(b, c);
			System.out.println(a);
		}
		else {
			System.out.println("verdiyiniz 2 ci arqument 1 ci arqumentin daxilinde yoxdur");
		}
	}

	void yoxlama(String a, String b) {
		boolean isCheck = a.endsWith(b);
		System.out.println(isCheck);
	}

}
