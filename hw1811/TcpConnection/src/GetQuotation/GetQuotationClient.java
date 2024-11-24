package GetQuotation;

import java.io.*;
import java.net.Socket;

public class GetQuotationClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) { // 192.0.0.1:8080 - client <-> server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Server response: " + in.readLine());
                if ("exit".equalsIgnoreCase(userInput)) break;
            }
        } catch (IOException e) {
            System.err.println("Something went wrong...");        }
    }
}
