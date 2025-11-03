package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final int PORT = 8080;
    private static final AtomicInteger CLIENT_COUNTER = new AtomicInteger(1);
    private static final Map<String, ClientHandler> ACTIVE_CLIENTS = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("[SERVER] Сервер запущено на порту " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                String clientName = "client-" + CLIENT_COUNTER.getAndIncrement();
                LocalDateTime connectTime = LocalDateTime.now();

                ClientHandler handler = new ClientHandler(socket, clientName, connectTime, ACTIVE_CLIENTS);
                ACTIVE_CLIENTS.put(clientName, handler);

                System.out.println("[SERVER] " + clientName + " успішно підключився (" + connectTime + ")");
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.err.println("[SERVER] Помилка сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
