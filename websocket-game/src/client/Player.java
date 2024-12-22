package client;

import common.Gesture;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Player {
    private String name;
    private final String sessionId;
    private boolean isAuthorized;   //авторизован (имеет имя)
    public Gesture gesture; //последний выбранный жест
    private static final String[] namePart = new String[]{
            "eugen","sof","alex","andr","ax","ey","rom","bor","gen","eg","lax","iy","ol","max","an","0","olg","a","na","mari","iri","gali","tam","ara","jur","no","oy","milian","is","aya","al","la","o","i"
    };

    public Player() {
        this.name = "Computer (server)";
        this.sessionId = "SERVER";
//        this.session = null;
        this.isAuthorized = true;
    }

    public Player(String name,String sessionId) {
        this.name = name.isEmpty() ? generateName() : name;
        this.sessionId = sessionId;
        this.isAuthorized = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(sessionId, player.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sessionId);
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void chooseGesture(Gesture gesture) {
        this.gesture = gesture;
    }

    public String getGestureName(){
        return gesture.getGestureName();
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
