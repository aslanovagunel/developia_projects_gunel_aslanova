package ders5.ders;

public class Bank {
    double balance;

    void addMoney(double amount){
        balance+=amount;
        System.out.println("Hesaba "+ amount+ " AZN əlavə edildi");
        availableMoney();
    }

    void drawMoney(double amount){
        balance-=amount;
        System.out.println("Hesabdan "+ amount+ " AZN çıxarıldı");
        availableMoney();

    }
    void availableMoney(){
        System.out.println("Mövcud balans: "+balance);
    }

}
