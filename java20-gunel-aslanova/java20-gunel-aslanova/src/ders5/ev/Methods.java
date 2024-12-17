package ders5.ev;

public class Methods {
    public static void main(String[] args) {

        Methods methods=new Methods();
        methods.printName(2,"Apple");
        methods.printName(5,"Orange");
        methods.printName(3,"Pear");
    }

    void printName(int count, String name){
        for (int i = 0; i < count; i++) {
            System.out.println(name);
        }
    }
}
