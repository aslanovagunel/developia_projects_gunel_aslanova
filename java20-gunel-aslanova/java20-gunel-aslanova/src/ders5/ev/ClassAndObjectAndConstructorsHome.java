package ders5.ev;

public class ClassAndObjectAndConstructorsHome {
    public static void main(String[] args) {

        Employee employee=new Employee();
        employee.id=1;
        employee.name="Amalya";
        employee.surname="Asadova";
        employee.phone="8509580";
        employee.salary=3333;

        Employee employee1=new Employee("Sevinc");
        employee1.id=2;
        employee1.surname="Huseynova";
        employee1.phone="850955664680";
        employee1.salary=7333;

        Employee employee2=new Employee("Nusabe","Xanizade");
        employee2.id=3;
        employee2.phone="39-1093-00";
        employee2.salary=8863;

        Employee employee3=new Employee("Gunel","47419454",8894);
        employee3.id=4;
        employee3.surname="Aslanova";

        System.out.println(employee.id + " " + employee.name + " " + employee.surname + " " + employee.phone + " " + employee.salary);
        System.out.println(employee1.id + " " + employee1.name + " " + employee1.surname + " " + employee1.phone +" " + employee1.salary);
        System.out.println(employee2.id + " " + employee2.name + " " + employee2.surname + " " + employee2.phone +" " + employee2.salary);
        System.out.println(employee3.id + " " + employee3.name + " " + employee3.surname + " " + employee3.phone +" " + employee3.salary);

    }
}
