package client;

import common.Gesture;

import javax.websocket.Session;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private final String sessionId;
//    private final Session session;
    private boolean isAuthorized;   //авторизован (имеет имя)
    private boolean plays;       //играет
    public Gesture gesture; //последний выбранный жест
    private List<Game> games = new ArrayList<>();   //история всех игр
    private int gameNum = 0; // id текущей игры
    private static final String[] namePart = new String[]{
            "alex","andr","ax","ey","rom","bor","gen","eg","lax","iy","ol","max","an","lya","olg","a","na","mari","iri","gali","tam","ara","jur","no","oy","milian","is","aya","al","la","o","i"
    };

    public Player() {
        this.name = "Computer (server)";
        this.sessionId = "SERVER";
//        this.session = null;
        this.isAuthorized = true;
        this.plays = false;
    }
//    public Player(String name, Session session) {
    public Player(String name,String sessionId) {
        this.name = name.isEmpty() ? generateName() : name;
        this.sessionId = sessionId;
//        this.session = session;
        this.isAuthorized = false;
        this.plays = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public Session getSession() {
//        return session;
//    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public boolean isPlays() {
        return plays;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public void setPlays(boolean plays) {
        this.plays = plays;
    }

    public void chooseGesture(Gesture gesture) {
        this.gesture = gesture;
    }

    public String getGestureName(){
        return gesture.getGestureName();
    }
    //для отладки
    public int getGesturePower(){
        return gesture.getGesturePower();
    }

    public void addGame(Game game){
        games.add(game);
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public static String generateName(){
        Random rnd = new Random();
        while (true){
            String str = namePart[rnd.nextInt(namePart.length - 1)] + namePart[rnd.nextInt(namePart.length - 1)];
            if(str.length() > 3)
                return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
}
