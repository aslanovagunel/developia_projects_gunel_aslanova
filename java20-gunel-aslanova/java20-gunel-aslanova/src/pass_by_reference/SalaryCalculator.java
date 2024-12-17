package pass_by_reference;

public class SalaryCalculator {
    void calculateSalary(Person person){
        person.salary=500;
        int total=0;

        for (int i = 1; i <= person.experienceYear ; i++) {
            total+= (int) (person.salary);
        }

        System.out.println(total);

    }
}
