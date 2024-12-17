package ders5.ders;

public class Shopping {

    public Shopping(Bank bank, Product product, double quantity){
        double total= product.price*quantity;
        if (bank.balance<total){
            System.out.println("Yetərsiz balans");
            return;
        }
        bank.drawMoney(total);
    }
}
