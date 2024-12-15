package server;
/*
    серверный класс для хранения информации о текущих играх
    для того, чтобы понимать, от кого кому из игроков передавать сообщения
*/

import java.util.ArrayList;
import java.util.List;

//игры
public class ActiveGames {
    List<Pair> games = new ArrayList<>();

    public ActiveGames(String pId1,String pId2) {
        Pair game = new Pair(pId1,pId2);
        this.games.add(game);
    }

    //предполагается, что один игрок может участвовать только в одной активной игре
    public int getPlayersGame(String pId){
        for (int i = 0; i < games.size(); i++) {
            if(games.get(i).checkPlayer(pId)){
                return i;
            }
        }
        return -1;
    }

    public String getRivalFor(String pId){
        for (int i = 0; i < games.size(); i++) {
            if(games.get(i).checkPlayer(pId)){
                return games.get(i).getRival(pId);
            }
        }
        return "";

    }

    private class Pair{
        private String pId1;
        private String pId2;

        private Pair(String pId1, String pId2) {
            this.pId1 = pId1;
            this.pId2 = pId2;
        }
        public boolean checkPlayer(String pId){
            return pId1.equals(pId) || pId2.equals(pId);
        }
        public String getRival(String pId){
            if(pId1.equals(pId))
                return pId2;
            else if(pId2.equals(pId))
                return pId1;
            else return "";
        }
    }


}
