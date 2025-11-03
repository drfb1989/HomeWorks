package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final String clientName;
    private final LocalDateTime connectTime;
    private final Map<String, ClientHandler> activeClients;

    public ClientHandler(Socket socket, String clientName, LocalDateTime connectTime,
                         Map<String, ClientHandler> activeClients) {
        this.socket = socket;
        this.clientName = clientName;
        this.connectTime = connectTime;
        this.activeClients = activeClients;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("Ви підключені як " + clientName + ". Напишіть 'exit' щоб відключитися.");

            String line;
            while ((line = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line.trim())) {
                    out.println("Ви відключені. До побачення!");
                    break;
                } else {
                    out.println("[SERVER] Команда отримана: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("[SERVER] Помилка при обробці клієнта " + clientName + ": " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void disconnect() {
        try {
            socket.close();
        } catch (IOException ignored) {}

        activeClients.remove(clientName);
        System.out.println("[SERVER] " + clientName + " відключився. Активні клієнти: " + activeClients.size());
    }
}
