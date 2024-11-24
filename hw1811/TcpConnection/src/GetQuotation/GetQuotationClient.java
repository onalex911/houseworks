package GetQuotation;

import java.io.*;
import java.net.Socket;

public class GetQuotationClient {
    public static void main(String[] args) {
//        System.out.println("Start");
        try (Socket socket = new Socket("localhost", 8080)) { // 192.0.0.1:8080 - client <-> server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput, serverAnswer;
            out.println("hello");
            System.out.println("Введите 'q' для получения цитаты или 'exit' - для выхода");

            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                int i = 0;
                while((serverAnswer = in.readLine()) == null || serverAnswer.isEmpty() || i < 1000)
                    i++;

                System.out.println("Server response: " + serverAnswer);
                if ("exit".equalsIgnoreCase(userInput)) break;
            }
        } catch (IOException e) {
            System.err.println("Something went wrong...");        }
//        try {
//            Quotations q = new Quotations();
//            ArrayList<String> qqq = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                qqq.add(q.getRandomQuotation());
//            }
//            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss.SSS");
//            Log log = new Log("aaa", formatter.format(new Date()), formatter.format(new Date()), qqq);
//            System.out.println(log);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println("End");
    }
}
