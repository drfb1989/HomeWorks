package app.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    void add(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void update(Customer customer);
    void delete(Long id);
}
