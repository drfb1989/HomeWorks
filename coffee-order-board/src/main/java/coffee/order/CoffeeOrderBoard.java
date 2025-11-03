package coffee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class CoffeeOrderBoard {
    private static final Logger log = LoggerFactory.getLogger(CoffeeOrderBoard.class);

    // Queue of order numbers in serving order (arrival order)
    private final Deque<Integer> queue = new ArrayDeque<>();
    // Lookup by number -> Order
    private final Map<Integer, Order> orders = new LinkedHashMap<>();
    // Next natural number to assign
    private int nextNumber = 1;

    public Order add(String customerName) {
        int number = nextNumber++;
        Order order = new Order(number, customerName);
        orders.put(number, order);
        queue.addLast(number);

        log.info("Added order {} for '{}'", number, customerName);
        log.debug("Queue after add: {}");
        return order;
    }

    public Order deliver() {
        Integer head = queue.pollFirst();
        if (head == null) {
            log.info("No orders to deliver (queue is empty)");
            return null;
        }
        Order delivered = orders.remove(head);
        log.info("Delivered next order: {}", delivered);
        log.debug("Queue after deliver(): {}");
        return delivered;
    }

    public Order deliver(int number) {
        Order target = orders.remove(number);
        if (target == null) {
            log.warn("Attempted to deliver non-existing order #{}", number);
            return null;
        }
        // remove from queue wherever it is
        boolean removedFromQueue = queue.remove(number);
        if (!removedFromQueue) {
            // Should not happen: orders and queue out of sync
            log.error("Order #{} missing from queue while present in map!", number);
        }
        log.info("Delivered specific order: {}", target);
        log.debug("Queue after deliver({}): {}");
        return target;
    }

    public void draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("Num | Name").append(System.lineSeparator());
        for (Integer num : queue) {
            Order o = orders.get(num);
            if (o != null) {
                sb.append(o.getNumber()).append(" | ").append(o.getName())
                        .append(System.lineSeparator());
            } else {
                // Data integrity issue; log as error but keep going.
                log.error("Queue contains number #{} that is missing in orders map", num);
            }
        }
        String output = sb.toString().stripTrailing();
        System.out.println(output);
        log.info("Drawn queue ({} item{}).", queue.size(), queue.size() == 1 ? "" : "s");
        log.debug("Draw content:\n{}", output);
    }

    private String queueView() {
        List<String> parts = new ArrayList<>(queue.size());
        for (Integer num : queue) {
            Order o = orders.get(num);
            parts.add(o == null ? (num + " | <missing>") : o.toString());
        }
        return parts.toString();
    }

    public int size() {
        return queue.size();
    }
}
