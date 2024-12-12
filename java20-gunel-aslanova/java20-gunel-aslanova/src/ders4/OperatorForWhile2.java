package ders4;

public class OperatorForWhile2 {
    public static void main(String[] args) {
        // Ders praktikasi3
        // sual1

        for (int i = 50; i >= 20; i--) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("--------------");

        // sual2

        for (float i = 0; i <= 5; i+=0.1) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("--------------");

        // sual3

        int num=649084;
        int max,eded;
        max=num%10;
        while (num>0){
            eded=num%10;
            num=num/10;

            if (max<=eded) max=eded;

        }
        System.out.println("max: "+max);
        System.out.println("--------------");

        // sual4

        int num1=649084;
        int min,eded1;
        min=num1%10;
        while (num1>0){
            eded1=num1%10;
            num1=num1/10;

            if (min>=eded1) min=eded1;

        }
        System.out.println("min: "+min);
        System.out.println("--------------");
    }
}
