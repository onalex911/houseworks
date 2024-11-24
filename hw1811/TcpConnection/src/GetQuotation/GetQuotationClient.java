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
            String serverAnswer = "";
            boolean isAuthorized = false;

            //out.println("HELLO");
            System.out.println("Нажмите Enter...");

            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                serverAnswer = in.readLine();

                if ("q".equalsIgnoreCase(userInput)){
                    System.out.println(serverAnswer);
                    break;
                }else{
                    if(serverAnswer.equals("AUTH")) {
                        isAuthorized = true;
                        System.out.println("Пользователь авторизован успешно!");
                    }
                    else if(serverAnswer != null) {
                        System.out.println("[SRV] " + serverAnswer);
                    }
                    if(isAuthorized)
                        System.out.print("Введите 'n' для получения новой цитаты или 'q' - для выхода: ");

                }
            }
        } catch (IOException e) {
            System.err.println("Something went wrong...");        }
    }
}
