package ders5.ev;

public class Employee {
    Integer id;
    String name;
    String surname;
    String phone;
    String address;
    int salary;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
        System.out.println(name);
    }

    public Employee(String name,String surname) {
        this.name = name;
        this.surname=surname;
        System.out.println(name+" "+surname);
    }

    public Employee(String name,String phone,int salary) {
        this.name = name;
        this.phone=phone;
        this.salary=salary;
        System.out.println(name+" "+phone+" "+salary);
    }

}
