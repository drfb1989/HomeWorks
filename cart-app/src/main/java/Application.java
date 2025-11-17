package shop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shop.config.AppConfig;
import shop.model.Product;
import shop.repo.ProductRepository;
import shop.service.Cart;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ProductRepository productRepository = context.getBean(ProductRepository.class);
        Scanner scanner = new Scanner(System.in);

        Cart cart = context.getBean(Cart.class);

        System.out.println("=== PRODUCT LIST ===");
        printProducts(productRepository.findAll());

        boolean running = true;
        while (running) {
            System.out.println("\nChoose action:");
            System.out.println("1 - Add product to cart");
            System.out.println("2 - Remove product from cart");
            System.out.println("3 - Show cart");
            System.out.println("4 - New cart");
            System.out.println("0 - Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter product id to add: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    cart.addProductById(id);
                }
                case "2" -> {
                    System.out.print("Enter product id to remove: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    cart.removeProductById(id);
                }
                case "3" -> {
                    System.out.println("=== CART ITEMS ===");
                    printProducts(cart.getItems());
                    System.out.println("TOTAL: " + cart.getTotal());
                }
                case "4" -> {
                    cart = context.getBean(Cart.class); // НОВАЯ корзина
                    System.out.println("New cart created.");
                }
                case "0" -> running = false;
                default -> System.out.println("Unknown command");
            }
        }

        context.close();
        System.out.println("Bye!");
    }

    private static void printProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("<empty>");
            return;
        }
        for (Product p : products) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getPrice());
        }
    }
}
