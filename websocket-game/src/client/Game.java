package client;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static int NumRounds = 5;

    List<GameRound> gameArray = new ArrayList<>();
    private int counter = NumRounds;
    private long timeStart;
    Player pl1;
    Player pl2;
    private boolean isDraw = false;
    private boolean playerGaveUp = false;
//    private long timeElapse;

    public Game(){

    }
    public Game(Player pl1, Player pl2) {
//        this.id = id;
        this.pl1 = pl1;
        this.pl2 = pl2;
    }
    public void startGame() {
        timeStart = System.currentTimeMillis();
    }

    public long getGameTime(){
        return System.currentTimeMillis() - timeStart;
    }

    public void addRoundToGame(GameRound round){
//        if(counter > 0) {
            gameArray.add(round);
//            counter--;
//            return true;
//        }
//        return false;
    }

    public Player getGameWinner(){
        if(gameArray.size() == NumRounds) {
            int cntDraw = 0;
            int cnt1 = 0;
            int cnt2 = 0;
            Player pl1 = gameArray.get(0).getPl1();
            Player pl2 = gameArray.get(0).getPl2();
            for (GameRound round : gameArray) {
                if (!round.isDraw()) {
                    if (round.getWinner() == pl1) cnt1++;
                    else cnt2++;
                }else
                    cntDraw++;
            }
            if(cntDraw == NumRounds) return new Player("NOBODY (it's a draw)","");
            return cnt1 == cnt2 ? null : (cnt1 > cnt2 ? pl1 : pl2);
        }else return null;
    }

    public int getGameArraySize(){
        return gameArray.size();
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public void setPl1(Player pl1) {
        this.pl1 = pl1;
    }

    public void setPl2(Player pl2) {
        this.pl2 = pl2;
    }

    public Player getPl1() {
        return pl1;
    }
    public Player getPl2() {
        return pl2;
    }
    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setPlayerGaveUp(boolean playerGaveUp) {
        this.playerGaveUp = playerGaveUp;
    }

    public boolean isPlayerGaveUp() {
        return playerGaveUp;
    }

    public static long[] getPartedTime(long mlSeconds){
        long[] out = new long[3];
        out[0] = mlSeconds / (1000 * 3600);         //hours
        out[1] = (mlSeconds / (1000 * 60)) % 60;    //minutes
        out[2] = (mlSeconds / 1000) % 60;           //seconds
        return out;
    }
}
