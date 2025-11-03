package coffee.order;

import java.util.Objects;

public class Order {
    private final int number;
    private final String name;

    public Order(int number, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("Order number must be positive");
        }
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return number + " | " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return number == order.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
