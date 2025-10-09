package app;

public class User {
    private String name;
    private Address address; // Композиція: користувач має адресу

    public User(String name) {
        this.name = name;
    }

    // Метод для встановлення адреси
    public void setAddress(Address address) {
        this.address = address;
    }

    // Метод для отримання адреси
    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
