public class Player {
    private String name;
    private int countWin;
    private int sign;
    private boolean isHuman;

    {
        sign = 0;
        countWin = 0;
        name = "Компьютер";
        isHuman = false;
    }

    Player(){
    }
    Player(String name){
        this.name = name;
        this.isHuman = true;
    }
    Player(int sign){
        this.sign = sign;
    }
    Player(String name,int sign){
        this(name);
        this.sign=sign;
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

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }
}
