package coffee.order;

import java.util.Objects;

public class Order {
    private int number;
    private String name;

    public Order(int number, String name) {
        this.number = number;
        this.name = name;
    }


    public Order() {
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
        return number == order.number &&
                Objects.equals(name, order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }
}
