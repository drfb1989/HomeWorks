package app;

import app.config.AppConfig;
import app.customer.Customer;
import app.customer.CustomerDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao customerDao = context.getBean(CustomerDao.class);

        Customer newCustomer = new Customer("John Doe", "john.doe@example.com", "123-45-6789");
        customerDao.add(newCustomer);

        List<Customer> customers = customerDao.findAll();
        customers.forEach(System.out::println);

        Optional<Customer> maybeCustomer = customerDao.findById(1L);
        maybeCustomer.ifPresent(System.out::println);

        maybeCustomer.ifPresent(c -> {
            c.setFullName("John Updated");
            c.setEmail("john.updated@example.com");
            customerDao.update(c);
        });

        customerDao.delete(1L);

        context.close();
    }
}
