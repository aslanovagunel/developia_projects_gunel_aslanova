package ders12.Polymorphism;

public class Main {

	public static void main(String[] args) {
		Animal c = new Cat();
		Animal d = new Dog();
		Animal l = new Lion();

		Animal[] animals = { c, d, l };

		for (Animal a : animals) {
			a.makeNoise();
		}
	}

}
