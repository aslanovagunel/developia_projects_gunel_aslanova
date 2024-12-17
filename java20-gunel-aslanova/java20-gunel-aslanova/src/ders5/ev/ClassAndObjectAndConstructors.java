package ders5.ev;

public class ClassAndObjectAndConstructors {
    public static void main(String[] args) {
        Computer computer=new Computer();
        computer.id=1;
        computer.model="HP";
        computer.color="Grey";

        Computer computer1=new Computer("Lenovo");
        computer1.id=2;
        computer1.color="White";

        Computer computer2=new Computer("Acer","Blue");
        computer2.id=3;

        System.out.println(computer.id);
        System.out.println(computer.model);
        System.out.println(computer.color);

        System.out.println(computer1.id);
        System.out.println(computer1.model);
        System.out.println(computer1.color);

        System.out.println(computer2.id);
        System.out.println(computer2.model);
        System.out.println(computer2.color);

    }
}
