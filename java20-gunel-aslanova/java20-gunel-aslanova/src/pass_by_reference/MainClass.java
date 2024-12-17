package pass_by_reference;

public class MainClass {
    public static void main(String[] args) {

        Person person=new Person("Gunel","Aslanova",6,"+9945986980");
        person.printInfo();

        SalaryCalculator s=new SalaryCalculator();
        s.calculateSalary(person);
    }
}
