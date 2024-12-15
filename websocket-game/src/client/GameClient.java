package client;

import common.ServerMsg;

import javax.websocket.Session;
import javax.websocket.*;
import java.util.ArrayList;
import java.util.List;

@ClientEndpoint
public class GameClient {
    private Session session;
    private final String playerName;
//    private final List<ServerMsg> msgBuffer = new ArrayList<>();
    private boolean newMsg = false;
    private String newMsgTxt = "";


    public GameClient(String playerName) {
        this.playerName = playerName;
    }

    public String getNewMsgTxt() {
        return newMsgTxt;
    }
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        System.out.println(playerName + " is connected to the server!");
    }

    @OnMessage
    public void onMessage(String message){

        if(!message.isEmpty()){
            newMsg = true;
            newMsgTxt = message;
            //addMsgToBuffer(new ServerMsg(message));
        }
        //System.out.println("Incoming message: " + message);
    }

    @OnClose
    public void onClose(){
        System.out.println(playerName + " disconnected from server!");
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

//    public ServerMsg getMsgFromBuffer(int ind) {
//        if(!newMsgTxt.isEmpty()) {
////            if (ind < 0) ind = msgBuffer.size() - 1;
//            return new ServerMsg(newMsgTxt);
//        }
//        return null;
//    }
//    public void removeMsgFromBuffer(int ind) {
//        if(msgBuffer.size() > 0) {
//            if (ind < 0)
//                ind = msgBuffer.size() - 1;
//            msgBuffer.remove(ind);
//        }
//    }

//    public void addMsgToBuffer(ServerMsg msg) {
//        msgBuffer.add(msg);
//        newMsg = true;
//    }

    public boolean isNewMsg() {
        return newMsg;
    }

    public void setNewMsg(boolean newMsg) {
        this.newMsg = newMsg;
    }

    public void clearBuffer(){
        newMsgTxt = "";
        newMsg = false;
    }

    public Player getFreeUser(){
        return new Player("","");
    }
//    public void printBuffer(){
//        for(ServerMsg msg:msgBuffer){
//            System.out.println(msg);
//        }
//    }

//    public int getBufferSize(){
//        return msgBuffer.size();
//    }
}