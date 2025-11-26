package app.order;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    private final ConcurrentHashMap<Long, Order> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Order> findAll() {
        Collection<Order> values = storage.values();
        return new ArrayList<>(values);
    }

    public Order add(Order order) {
        long id = idGenerator.incrementAndGet();
        order.setId(id);
        if (order.getCreationDate() == null) {
            order.setCreationDate(LocalDateTime.now());
        }
        if (order.getProducts() != null) {
            double sum = order.getProducts().stream()
                    .map(Product::getCost)
                    .filter(c -> c != null)
                    .mapToDouble(Double::doubleValue)
                    .sum();
            order.setTotalCost(sum);
        }
        storage.put(id, order);
        return order;
    }
}
