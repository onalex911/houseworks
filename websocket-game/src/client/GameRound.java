package client;

public class GameRound {
    private final Player pl1;
    private final Player pl2;
    private boolean isDraw;
    private Player winner;
    private final int[] gestures = new int[2];

    public GameRound(Player player1, Player player2) {
        this.pl1 = player1;
        this.pl2 = player2;
    }

    public void play() {
        int a = pl1.gesture.getGesturePower();
        int b = pl2.gesture.getGesturePower();
        if (a == b) {
            winner = null;
            isDraw = true;
        }
        else if (Math.abs(a - b) == 1)
            winner = a > b ? pl1 : pl2;
        else
            winner = a > b ? pl2 : pl1;
        //сохраняем выбранные жесты для статистики
        gestures[0] = a;
        gestures[1] = b;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getPl1() {
        return pl1;
    }

    public Player getPl2() {
        return pl2;
    }

    public int[] getGestures() {
        return gestures;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
}
