package hw2905;

enum Gesture {
    PAPER("Бумага"),
    SCISSORS("Ножницы"),
    STONE("Камень");
    private final String rusName;
    private final int power;
    Gesture(String rusName) {
        this.rusName = rusName;
        this.power = this.ordinal();
    }
    public String getGestureName(){
        return this.rusName;
    }
    public int getGesturePower(){
        return this.power;
    }

}
