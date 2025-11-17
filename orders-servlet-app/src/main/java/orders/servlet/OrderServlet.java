package orders.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orders.model.Order;
import orders.repository.OrderRepository;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/orders/*")
public class OrderServlet extends HttpServlet {

    private final OrderRepository orderRepository = new OrderRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        super.init();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Order order = objectMapper.readValue(req.getInputStream(), Order.class);

        if (order.getDate() == null) {
            order.setDate(LocalDateTime.now());
        }

        orderRepository.save(order);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setContentType("application/json");
        objectMapper.writeValue(resp.getOutputStream(), order);
    }
}

