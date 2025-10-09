package app;

public class Main {
    public static void main(String[] args) {

        User user = new User("Artem F.");

        Address address = new Address("Av. Emanuel de Fonseca", "Lisbon", "Portugal");

        user.setAddress(address);

        System.out.println("User: " + user.getName());
        System.out.println("Address: " + user.getAddress());
    }
}
