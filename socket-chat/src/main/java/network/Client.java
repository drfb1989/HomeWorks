package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("[CLIENT] Підключено до сервера на порті " + PORT);
            System.out.println(in.readLine());
            String input;
            while (true) {
                System.out.print(">>> ");
                input = userInput.readLine();
                if (input == null || input.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;
                }
                out.println(input);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            System.err.println("[CLIENT] Помилка підключення: " + e.getMessage());
        }
    }
}
