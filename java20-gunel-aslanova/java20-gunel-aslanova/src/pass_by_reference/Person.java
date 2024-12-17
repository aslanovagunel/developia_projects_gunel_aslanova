package pass_by_reference;

public class Person {
    String name;
    String surName;
    int experienceYear;
    double salary;
    String phone;
    SalaryCalculator salaryCalculator;

    public Person(String name, String surName, int experienceYear, String phone) {
        this.name = name;
        this.surName = surName;
        this.experienceYear = experienceYear;
        this.phone = phone;
    }

    void printInfo(){
        System.out.println(name+" "+surName+" "+experienceYear+" "+salary+" "+phone);
    }
}
