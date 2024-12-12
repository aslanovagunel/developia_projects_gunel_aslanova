package ders4;

public class OperatorForWhile {
    public static void main(String[] args) {
        // Ders  praktikasi2
        // sual1

        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("---------------");

        // sual2

        int num1 = 5, num2 = 7, sum = 0;
        sum = num1 + num2;
        System.out.println("sum: " + sum);

        System.out.println("---------------");

        // sual3

        int num3 = 5, num4 = 7, hasil = 1;
        hasil = num3 * num4;
        System.out.println("hasil: " + hasil);

        System.out.println("---------------");

        // sual4

        double radius = 5.2;
        double uzunluq = 2 * 3.14 * radius;
        System.out.println("cevrenin uzunluq: " + uzunluq);

        System.out.println("---------------");

        // sual5

        int number = 637;
        int sum1 = 0;
        while (number > 0) {
            sum1 += number % 10;
            number = number / 10;
        }
        System.out.println("sum: " + sum1);

        System.out.println("---------------");

        // sual6

        int number1 = 59;
        int count = 0;
        for (int i = 1; i <= number1; i++) {
            if (number1 % i == 0) count++;
        }
        if (count == 2) System.out.println("sade");
        else if (count > 2) System.out.println("murekkeb");
        else System.out.println("ne sade ne murekkeb");

        System.out.println("---------------");

        // sual7

        int sum2 = 0;
        for (int i = 1; i <= 100; i++) {
            sum2 += i;
        }
        System.out.println(sum2);

        System.out.println("---------------");

        // sual8

        for (int i = 1; i < 100; i++) {
            if (i % 7 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        System.out.println("---------------");

        // sual9

        int eded = 89;
        if (eded % 2 == 0) System.out.println("cut");
        else System.out.println("tek");

        System.out.println("---------------");

        // sual10

        for (int i = 0; i < 200; i++) {
            if (i % 3 == 0 && i % 7 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        System.out.println("---------------");

        // sual11

        int count2 = 0;
        for (int i = 1; i < 290; i++) {
            int count1 = 0;
            for (int j = 1; j < 290; j++) {
                if (i % j == 0)
                    count1++;
            }
            if (count1 == 2) {
                System.out.print(i + " ");
                count2++;
            }
            if (count2 == 20) break;
        }
    }
}
