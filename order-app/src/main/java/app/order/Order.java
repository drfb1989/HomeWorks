package app.order;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalCost;

    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Order() {
    }

    public Order(Long id, Double totalCost, List<Product> products, LocalDateTime createdAt) {
        this.id = id;
        this.totalCost = totalCost;
        this.products = products;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        recalculateTotalCost();
    }

    @PreUpdate
    public void preUpdate() {
        recalculateTotalCost();
    }

    private void recalculateTotalCost() {
        if (products == null) {
            totalCost = 0.0;
            return;
        }
        totalCost = products.stream()
                .map(Product::getPrice)
                .filter(p -> p != null)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
