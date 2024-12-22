package client;

import common.Gesture;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final String NOBODY_WINS = "NOBODY (it's a draw)";
    public static int NumRounds = 5;

    List<GameRound> gameArray = new ArrayList<>();
    private long timeStart;
    Player pl1;
    Player pl2;
    Player winner = null;
    private boolean isDraw = false;
    private boolean playerGaveUp = false;
    private int gameMode; //0 - comp-comp; 1 - human-comp; 2 - human-human

    public Game(int gameMode){
        this.gameMode = gameMode;
    }
    public Game(Player pl1, Player pl2) {
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
            gameArray.add(round);
    }

    public Player calcGameWinner(){
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
            if(cntDraw == NumRounds || cnt1 == cnt2){
                isDraw = true;
                return new Player(NOBODY_WINS,"");
            }
            winner = cnt1 > cnt2 ? pl1 : pl2; //запоминаем победителя раунда
            return winner;
        }else return null;
    }

    public Player getGameWinner() {
        return winner != null ? winner : calcGameWinner();
    }

    public List<GameRound> getGameArray() {
        return gameArray;
    }

    public int getGameArraySize(){
        return gameArray.size();
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

    public void setWinner(Player winner) {
        this.winner = winner;
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

    public int getGameMode() {
        return gameMode;
    }

    public String getGameInfo(){
        String out = "";
        for (int i = 0; i < gameArray.size(); i++) {
            GameRound gr = gameArray.get(i);

            out += (i + 1) + ") " + gr.getPl1().getName() + ": " + Gesture.getNameByPower(gr.getGestures()[0]);
            out += ", " + gr.getPl2().getName() + ": " + Gesture.getNameByPower(gr.getGestures()[1]);
            out += " Result: " + (gr.isDraw() ? "DRAW" : "Winner - " + gr.getWinner().getName()) + "\n";

        }
        return out;
    }
}
