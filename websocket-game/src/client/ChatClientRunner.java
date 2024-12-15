package client;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class ChatClientRunner {
    public static void main(String[] args) throws IOException {
        String uri = "ws://localhost:80/ws/chat";
        Session session = null;
        try{
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            ChatClient client = new ChatClient();
            session = container.connectToServer(client, URI.create(uri));

            Scanner scn = new Scanner(System.in);
            
            System.out.println("Enter 'exit' for quit...");
            while(true){
                String message = scn.nextLine();
                if("exit".equalsIgnoreCase(message)){
                    break;
                }
                client.sendMessage(message);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(session != null) session.close();
        }
    }
}
