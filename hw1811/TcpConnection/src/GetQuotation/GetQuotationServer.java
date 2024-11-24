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
            }
        } catch (IOException e) {
            System.err.println();        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputLine;

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String timeConnect = formatter.format(new Date());
            Quotations q = new Quotations();
            ArrayList<String> userQuotations = new ArrayList<>();
            boolean isAuthorized = false;
            boolean isLoginRequested = false;
            boolean isExit = false;
            Boolean isPasswordRequested = false;
            String authUser = "";

            while ((inputLine = in.readLine()) != null) {

                System.out.println("Received: " + inputLine);
                //System.out.printf("auth:%b, login req:%b, pass req:%b, user:%s\n",isAuthorized,isLoginRequested,isPasswordRequested,authUser);
                if ("q".equalsIgnoreCase(inputLine)){
                    if(isAuthorized) {
                        String timeDisconnect = formatter.format(new Date());
                        Log log = new Log(authUser, timeConnect, timeDisconnect, userQuotations);
                        writeLog(log);
                        out.println("До свидания!");
                    }
                    break;
                }else if(!isAuthorized){
                    if(!isLoginRequested) {
                        out.println("Введите имя пользователя: ");
                        isLoginRequested = true;
                    }else{
                        if (!inputLine.isEmpty()) {
                            Users users = new Users();
                            if(!isPasswordRequested || authUser.isEmpty()) { //вероятно, пришел запрос имени пользователя){

                                if (users.checkUser(inputLine)) { //проверяем, есть ли указанное имя польз. в БД
                                    authUser = inputLine;
                                    out.println("Введите пароль: "); //если есть - запрашиваем пароль
                                    isPasswordRequested = true;
                                } else {
                                    System.out.println("user " + authUser + " is absent!");
                                    isLoginRequested = false;
                                    isPasswordRequested = false;
                                    out.println("Нет такого пользователя!");
                                }

                            } else {
                                if (users.checkUserPass(authUser, inputLine)) {
                                    isAuthorized = true;
                                    System.out.println(authUser + " is authorized!");
                                    out.println("AUTH");
                                }else{
                                    out.println("Пользователь НЕ авторизован!");
                                    isLoginRequested = false;
                                    isPasswordRequested = false;
                                }
                            }
                        }else{
                            isLoginRequested = false;
                            isPasswordRequested = false;
                            out.println("Недопустимое значение!");
                            System.out.println("Request from Client is empty!");
                        }
                    }
                }else {
                    if(inputLine.equals("n")) {
                        userQuotations.add(q.getRandomQuotation());
                        out.println("Цитата: " + userQuotations.getLast());
                    }
                    else{
                        out.println("Недопустимая команда!");
                    }
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            System.err.println();        }
    }

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
}
