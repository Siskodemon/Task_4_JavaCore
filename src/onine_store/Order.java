package onine_store;

import java.util.Random;

public class Order {
    Customer customer;
    Product product;
    Integer quantity;

    public Order (Customer customer, Product product, Integer quantity){
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public void getOrder (){
        System.out.printf("Покупатель: %s; Состав заказа: %s, в кол-ве %d ед.;\n", customer.full_name, product.name_product, quantity);
    }

}
