package hw2905;

public class Game {
    Player player1;
    Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String play() {
        int a = player1.gesture.getGesturePower();
        int b = player2.gesture.getGesturePower();
        if (a == b) return "НИЧЬЯ!";
        String outText = "выиграл ";
        if (Math.abs(a - b) == 1) {
            return outText + (a > b ? player1.getName() : player2.getName());
        }
        return outText + (a > b ? player2.getName() : player1.getName());
    }
}
