package ders10;

import java.util.Arrays;

public class StringDersPraktikasi1 {

	public static void main(String[] args) {
		String name = "Cavid";
		String message = "Java dili güclü və effektli bir proqramlaşdırma dilidir";

		int uzunluq = message.length();
		System.out.println(uzunluq);
		
		char secondSymbol = message.charAt(1);
		System.out.println(secondSymbol);
		
		int index=message.indexOf("i");
		System.out.println(index);
		
		String testLower = message.toLowerCase();
		System.out.println(testLower);
		
		String testUpper = message.toUpperCase();
		System.out.println(testUpper);
		
		boolean check=message.contains("a");
		System.out.println(check);
		
		boolean testEnd=message.endsWith("var");
		System.out.println(testEnd);
		
		boolean testStart=message.startsWith("Java");
		System.out.println(testStart);
		
		name = name.replace(name, "\tAdil\t");
		System.out.println(name);

		name = name.trim();
		System.out.println(name);
		
		name = name.replace(name, "Bextiyar");
		System.out.println(name);

		name = name.substring(0, 4);
		System.out.println(name);

		name = name.replace(name, "Eli Hesenov");
		System.out.println(name);

		String[] words = name.split(" ");
		String result = Arrays.toString(words);
		System.out.println(result);


	}

}
