package online_store;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        String[] full_name = new String[]{"Иванов Александр Андреевич", "Меньшиков Иван Николаевич", "Меньшиков Николаей Владимирович", "Иванов Андрей Георгиевич"};
        String[] product_name = new String[]{"Макароны","Чай чёрный","Масло","Сахар","Мука","Яйца 10шт.","Молоко","Морковка"};

        int index = new Random().nextInt(full_name.length-1);
        int nextindex = new Random().nextInt(full_name.length-1);
        while (nextindex == index) nextindex = new Random().nextInt(full_name.length-1);

        Customer[] customers = new Customer[]{
                new Customer(full_name[index], new Random().nextInt(20,60), 89994106105L),
                new Customer(full_name[nextindex], new Random().nextInt(20,60), 89143532629L)
        };

        System.out.println("=====================Покупатели=====================");
        for (Customer c:customers) {
            System.out.println(c.getFull_name());
        }
        System.out.println("====================================================");

        Product[] products = new Product[]{
                new Product("Макароны", 89),
                new Product("Чай чёрный", 100),
                new Product("Масло", 82),
                new Product("Сахар", 51),
                new Product("Молоко", 72)
        };

        Order[] orders = new Order[5];

        /*
        try {
            orders[0] = toMakePurchase("Иванов Александр Андреевич", "Молоко", 10, customers,products);
            orders[0].getOrder();
        } catch (CustomerException | ProductException | AmountException e) {
            e.printStackTrace();
        }
        */
        for (int i=0;i<5;i++) {
            String name = full_name[new Random().nextInt(full_name.length-1)];
            String name_product = product_name[new Random().nextInt(product_name.length-1)];
            try {
                orders[i] = toMakePurchase(name,name_product, new Random().nextInt(1,15), customers, products);
            } catch (ProductException e) {
                System.out.println(e.getMessage());
                orders[i]=null;
            } catch (AmountException e) {
                try {
                    orders[i] = toMakePurchase(name,name_product, 1, customers, products);
                } catch (CustomerException ex) {
                    e.printStackTrace();
                } catch (ProductException ex) {
                    System.out.println(e.getMessage());
                } catch (AmountException ex) {
                    System.out.println(e.getMessage());
                }
            } catch (CustomerException e) {
                e.printStackTrace();
                break;
            }
        }
        int count = 0;
        System.out.println("====================Покупки====================");
        for (Order order : orders) {
            if (order != null){
                order.getOrder();
                count++;
            }
        }
        System.out.println("===============================================");
        System.out.println("Кол-во покупок в массиве: " + count);
        System.out.println("===============================================");
    }
    public static Order toMakePurchase (String fullname, String productname,int quantity,Customer[] customers, Product[] products) throws CustomerException, ProductException, AmountException {
        Customer customer = null;
        Product product = null;
        int count = 0;

        for (Customer s:customers) {
            if (s.getFull_name().equals(fullname)) {
             customer = s;
             count++;
            }
        }
        if (count == 0) throw new CustomerException("Такого покупателя несуществует!!!");

        count =0;
        for (Product s:products) {
            if (s.getName_product().equals(productname)){
                product=s;
                count++;
            }
        }
        if (count == 0) throw new ProductException("Такого товара несуществует!!!");

        if (quantity <0 || quantity> 10) {
            throw new AmountException("Введено недопустимое количество товара(отрицательное кол-во, либо больше 10 единиц)",quantity);
        }

        Order neworder = new Order(customer, product, quantity);
        return neworder;
    }

    static class CustomerException extends Exception{
        public CustomerException(String message){
            super(message);
        }
    }

    static class ProductException extends Exception{
        public ProductException(String message){
            super(message);
        }
    }

    static class AmountException extends Exception{
        private int count;
        public int getCount(){return count;}
        public AmountException(String message,int count){
            super(message);
            this.count = count;
        }
    }
}