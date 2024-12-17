package ders5.ders;

public class Start {
    public static void main(String[] args) {
        Bank bank=new Bank();
        bank.addMoney(200);
        bank.drawMoney(40);

        Product product=new Product("heyva",4.6,37);
        Product product1=new Product("nar",3.2,49);

        product.nameAndPrice(product);
        product1.nameAndPrice(product);

        product.availableStock(product.stock);
        product1.availableStock(product.stock);


        Shopping shopping=new Shopping(bank,product,3.2);
        Shopping shopping1=new Shopping(bank,product1,32);
        System.out.println(bank.balance);

    }
}
