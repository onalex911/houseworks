package server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/chat")
public class ChatServer {
    private static final Set<Session> sessions = new HashSet<>();
    @OnOpen
    public void onOpen(Session session){
        sessions.add(session);
        broadcasting("New client connected: " + session.getId());
        System.out.println("Total clients: " + sessions.size());

    }

    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
        String message = "Client disconnected: " + session.getId();
        broadcasting(message);
        System.out.println("Total clients: " + sessions.size());
        System.out.println(message);
    }

    @OnMessage
    public void onMessage(String message, Session session){
        String msg = "Received message: " + message + " from " + session.getId();
        broadcasting(msg);
        System.out.println(msg);
    }

    @OnError
    public void onError(Throwable error, Session session){
        System.out.println("Error from: " + session.getId() + ": " + error.getMessage());
        sessions.remove(session);
    }

    private void broadcasting(String message) {
        for(Session session : sessions){
            try{
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                System.err.println("Error sending message to " + session.getId() + e.getMessage());
                sessions.remove(session);
            }
        }
    }
}
