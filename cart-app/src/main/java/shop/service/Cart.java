package shop.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import shop.model.Product;
import shop.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {

    private final ProductRepository productRepository;
    private final List<Product> items = new ArrayList<>();

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProductById(Long id) {
        productRepository.findById(id)
                .ifPresent(items::add);
    }

    public void removeProductById(Long id) {
        items.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
