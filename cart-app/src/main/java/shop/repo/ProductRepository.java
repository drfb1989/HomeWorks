package shop.repo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import shop.model.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product(1L, "Coffee", 2.5));
        products.add(new Product(2L, "Tea", 1.8));
        products.add(new Product(3L, "Cake", 3.2));
    }

    // CREATE
    public void add(Product product) {
        products.add(product);
    }

    // READ all
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    // READ by id
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // UPDATE (по id)
    public boolean update(Product updated) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(updated.getId())) {
                products.set(i, updated);
                return true;
            }
        }
        return false;
    }

    // DELETE by id
    public boolean deleteById(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}
