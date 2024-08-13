public class Player {
    private String name;
    private int countWin;
    private boolean isHuman;

    {
        countWin = 0;
        name = "Компьютер";
        isHuman = false;
    }

    Player() {
    }

    Player(String name) {
        this.name = name;
        this.isHuman = true;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountWin() {
        return countWin;
    }

    public void incrCountWin() {
        this.countWin++;
    }
}
