package ders5.ev;

public class Computer {
    Integer id;
    String brand;
    String model;
    String color;

    public Computer() {
    }

    public Computer(String model) {
        this.model = model;
    }

    public Computer(String model,String color){
        this.model = model;
        this.color = color;
    }

}
