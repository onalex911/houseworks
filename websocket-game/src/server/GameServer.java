package server;

import common.Gesture;
import common.ServerMsg;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@ServerEndpoint("/chat")
public class GameServer {
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
//        System.out.println("Received message: " + message + " from " + session.getId());
        String msg = "";
        String senderId = session.getId();
//        System.out.println("Sender ID: " + senderId);
        ServerMsg inMsg = new ServerMsg(message);
//        System.out.println("Input message: " + inMsg.getMessage());
        if(inMsg.isCmdExists()){
            switch(inMsg.getCmd().trim()){
                case "MOVE":
//                    System.out.println("MOVE detected!");
                    Gesture[] gestures = Gesture.values();
                    Random rnd = new Random();
                    int computerChoice = rnd.nextInt(gestures.length);
                    String outMessage = new ServerMsg("MOVE",String.valueOf(computerChoice)).getMessage();
                    sentToUser(senderId,outMessage);
                    break;
                default:
                    System.out.println(new ServerMsg("ERR","Command " + inMsg.getCmd() + " is not recognised").getMessage());
            }
        }
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
    private void sentToUser(String userId,String message) {
        for(Session session : sessions){
            if(session.getId().equals(userId)) {
                try {
                    session.getBasicRemote().sendText(message);
                    return;
                } catch (Exception e) {
                    System.err.println("Error sending message to " + session.getId() + e.getMessage());
                    sessions.remove(session);
                    return;
                }
            }
        }
        System.out.println("Message " + message + " was not send");
    }
}
