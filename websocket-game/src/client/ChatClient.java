package client;

import javax.websocket.Session;
import javax.websocket.*;

@ClientEndpoint
public class ChatClient {
    private Session session;
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        System.out.println("Connected to the server!");
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("Message: " + message);
    }

    @OnClose
    public void onClose(){
        System.out.println("Disconnected from server!");
    }

    @OnError
    public void onError(Throwable error){
        System.out.println("Error: " + error.getMessage());
    }


    public void sendMessage(String message){
        try{
            session.getBasicRemote().sendText(message);
        }catch (Exception e){
            System.err.println("Error from exception: " + e.getMessage());
        }
    }
}