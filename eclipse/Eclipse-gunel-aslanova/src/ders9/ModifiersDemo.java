package ders9;

public class ModifiersDemo {

	public static void main(String[] args) {

		Person person = new Person(4);
		Person person1 = new Person("gunel");
		Person person3 = new Person(4.2);
		Person.deyisen = 6;

		person.edediOrta = person.edediOrta(1, 2, 3, 4, 5, 6, 7, 8);
		person.sum = person.sum(4, 6);
		person.word = person.letters('r', 'i', 'y', 'f');
		String word = person.letters('r', 'i', 'y', 'f');
		person.setLetter('k');
		person1.finalSoz();

		System.out.println(word);
		System.out.println("ededi orta: " + person.edediOrta);
		System.out.println("cem : " + person.sum);
		System.out.println(person.word);
		System.out.println("private olan herf : " + person.getLetter());
		System.out.println("static deyisen : " + Person.deyisen);


	}
}
