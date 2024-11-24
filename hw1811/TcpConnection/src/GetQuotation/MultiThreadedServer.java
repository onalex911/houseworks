package GetQuotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
//                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    System.out.println("Received: " + inputLine);
//                    out.println("Echo: " + inputLine);
//                    if ("exit".equalsIgnoreCase(inputLine)) break;
//                }
//                clientSocket.close();
                new Thread(() -> handleClient(clientSocket)).start(); // 3 -> heap allocation - 1M -> 1M thread -> ThreadPool(150)
            }
        } catch (IOException e) {
            System.err.println();        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                out.println("Echo: " + inputLine);
                if ("exit".equalsIgnoreCase(inputLine)) break;
            }
        } catch (IOException e) {
            System.err.println();        }
    }
}
