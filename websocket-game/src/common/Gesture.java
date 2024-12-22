package common;

public enum Gesture {
    PAPER("paper"),
    SCISSORS("scissors"),
    STONE("stone");
    private final String name;
    private final int power;

    Gesture(String name) {
        this.name = name;
        this.power = this.ordinal();
    }
    public String getGestureName(){
        return this.name;
    }
    public int getGesturePower(){
        return this.power;
    }

    public static String getNameByPower(int power){
        Gesture[] gestureVal = Gesture.values();
        if(power >= 0 || power < gestureVal.length)
            return gestureVal[power].getGestureName();
        else return "";
    }
}
