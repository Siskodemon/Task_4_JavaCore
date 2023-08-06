package onine_store;

public class Customer {
    String full_name;
    Integer age;
    long phone_number;

    public Customer(String full_name, Integer age, long phone_number){
        this.full_name = full_name;
        this.age = age;
        this.phone_number = phone_number;
    }

    public String getFull_name() {
        return full_name;
    }
}
