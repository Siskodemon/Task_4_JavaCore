package onine_store;

public class Product {
    String name_product;
    Integer price;

    public Product(String name_product, int price){
        this.name_product = name_product;
        this.price = price;
    }

    public void getProduct() {
        System.out.println("Название прорукта: " + name_product + ", Цена продукта: " + price + ";");
    }

    public String getName_product() {
        return name_product;
    }
}
