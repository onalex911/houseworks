package GetQuotation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class GetQuotationServer {

    public static final String LOGSPATH = "logs/";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
                System.out.println("Server started...");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(() -> handleClient(clientSocket)).start(); // 3 -> heap allocation - 1M -> 1M thread -> ThreadPool(150)
//                    new Thread(new HandleClient(clientSocket)).start(); // 3 -> heap allocation - 1M -> 1M thread -> ThreadPool(150)
//                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                    String inputLine;
//                    while ((inputLine = in.readLine()) != null) {
//                        System.out.println("Received: " + inputLine);
//                        out.println("Echo: " + inputLine);
//                        if ("exit".equalsIgnoreCase(inputLine)) break;
//                    }
//                    clientSocket.close();
                }
//            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.err.println();
        }
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
//    private static void handleClient(Socket clientSocket) {
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
//            String inputLine;
//            String info = "aaa";//clientSocket.getChannel().toString();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//            String timeConnect = formatter.format(new Date());
//            Quotations q = new Quotations();
//            ArrayList<String> userQuotations = new ArrayList<>();
//            boolean isAuthorized = false;
//            boolean isLoginRequested = false;
//            boolean isExit = false;
//            Boolean isPasswordRequested = false;
//            String authUser = "";
//
//            int i = 0;
//
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println("Received: " + inputLine);
//                /*System.out.printf("auth: %b, login req: %b, pass req: %b, authUser=%s\n",isAuthorized,isLoginRequested,isPasswordRequested,authUser);
//                if(!isAuthorized){
//                        if(!isLoginRequested) {
//                            out.println("[SRV] Введите имя пользователя: ");
//                            isLoginRequested = true;
//                        }else{
//                            if (!inputLine.isEmpty()) {
//
//                                Users users = new Users();
//                                if (authUser.isEmpty()) { //вероятно, пришел запрос имени пользователя
//                                    if (users.checkUser(inputLine)) { //проверяем, есть ли указанное имя польз. в БД
//                                        authUser = inputLine;
//                                        out.println("[SRV] Введите пароль: "); //если есть - запрашиваем пароль
//                                        isPasswordRequested = true;
//                                    }else{
//                                        System.out.println("user "+authUser+" is absent!");
//                                    }
//                                } else {
//                                    if (users.checkUserPass(authUser, inputLine)) {
//                                        isAuthorized = true;
//                                    }
//                                }
//                            }else{
//                                System.out.println("Request from Client is empty!");
//                            }
//                        }
//                        System.out.println(i++);
//                }else {
//                    if(inputLine.equals("q")) {
//                        userQuotations.add(q.getRandomQuotation());
//                        out.println("[SRV] Цитата: " + userQuotations.getLast());
//                    }
//                    else{
//                        System.out.println("Недопустимая команда!");
//                    }
//                }*/
//                if ("exit".equalsIgnoreCase(inputLine)) {
////                    if(isAuthorized) {
////                        String timeDisconnect = formatter.format(new Date());
////                        Log log = new Log(info, timeConnect, timeDisconnect, userQuotations);
////                        writeLog(log);
////                    }
//                    out.println("До свидания, "+authUser);
//                    break;
//                }else{
//                    out.println("Echo: " + inputLine);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println();        }
//    }

    private static void writeLog(Log log){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HHmmssSSS");
        File file = new File(LOGSPATH + formatter.format(new Date()) + ".log");

        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Невозможно создать файл логирования.");
                }
            }

            FileWriter fw = new FileWriter(file,true);
            fw.write(log.toString());
            fw.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

//    private static class HandleClient implements Runnable {
//        private final Socket clientSocket;
//
//        public HandleClient(Socket clientSocket) {
//            this.clientSocket = clientSocket;
//        }
//
//        @Override
//        public void run() {
//            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
//
//                out.println("Добро пожаловать на сервер цитат!");
//                out.println("Введите 0 для случайной цитаты, 1-5 для конкретной цитаты или 'EXIT' для выхода.");
//                out.println("");
//
//                Quotations q = new Quotations();
//
//                String input;
//                while ((input = in.readLine()) != null) {
//                    if ("exit".equalsIgnoreCase(input)) {
//                        out.println("До свидания!");
//                        break;
//                    }
//
//                    try {
//                        int choice = Integer.parseInt(input);
//                        if (choice == 0) {
//                            out.println("Случайная цитата: " + q.getRandomQuotation());
//                        } else if (choice > 0 && choice <= q.getQArraySize()) {
//                            out.println("Цитата: " + q.getQuotation(choice));
//                        } else {
//                            out.println("Неправильный номер. Попробуйте снова.");
//                        }
//                    } catch (NumberFormatException e) {
//                        out.println("Введите корректный номер или 'exit'.");
//                    }
//                }
//                System.out.println("Клиент отключился: " + clientSocket.getInetAddress());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
