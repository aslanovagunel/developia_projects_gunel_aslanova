package ders5;

public class Product {
    String name;
    double price;
    int stock;

    public Product(String name, double price,int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        availableStock(stock);
    }

    void availableStock(int stock){
        this.stock--;
        System.out.println(this.stock);
    }

    void nameAndPrice(Product product){
        System.out.println(this.name+" "+this.price+" "+this.stock);
    }
}
