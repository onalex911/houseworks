package hw2905;

class Player {
    String name;
    int plMove;
    Gesture gesture;

    public void chooseGesture(Gesture gesture) {
        this.gesture = gesture;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public String getGestureName(){
        return gesture.getGestureName();
    }
    //для отладки
    public int getGesturePower(){
        return gesture.getGesturePower();
    }
}
