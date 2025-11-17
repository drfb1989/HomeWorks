package orders.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orders.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class OrderServletTest {

    private OrderServlet servlet;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new OrderServlet();
        servlet.init();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testCreateOrder() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Order order = new Order();
        order.setCost(BigDecimal.TEN);
        byte[] jsonBody = objectMapper.writeValueAsBytes(order);
        when(request.getInputStream()).thenReturn(new TestServletInputStream(jsonBody));


        TestServletOutputStream out = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(out);


        servlet.doPost(request, response);
        verify(response).setStatus(HttpServletResponse.SC_CREATED);


        String responseJson = out.getContent();
        Order saved = objectMapper.readValue(responseJson, Order.class);
        assertEquals(BigDecimal.TEN, saved.getCost());
    }

    static class TestServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream bais;

        TestServletInputStream(byte[] data) {
            this.bais = new ByteArrayInputStream(data);
        }

        @Override
        public int read() {
            return bais.read();
        }

        @Override
        public boolean isFinished() {
            return bais.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            // не нужен для теста
        }
    }
    static class TestServletOutputStream extends ServletOutputStream {

        private final StringBuilder sb = new StringBuilder();

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            // не нужен для теста
        }

        @Override
        public void write(int b) {
            sb.append((char) b);
        }

        String getContent() {
            return sb.toString();
        }
    }
}
