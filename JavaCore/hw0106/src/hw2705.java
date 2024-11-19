abstract class CityTransport {
    protected String name;
    protected int price;
    protected String typeEngine;
    private boolean isRailway;
    protected boolean isOnGround;

    protected void setIsRailway(boolean isRailway){
        this.isRailway = isRailway;
    }

    public boolean getIsRailway() {
        return isRailway;
    }

    public String getIsRailwayText(){
        return this.isRailway ? "yes" : "no";
    }

    public abstract String getSound();
}

public class hw2705 {
    public static void main(String[] args) {
        Bus bus = new Bus();
        Tram tram = new Tram();
        Trolleybus troll = new Trolleybus();
        Subway metro = new Subway();
        System.out.println(bus+"\n");
        System.out.println(tram+"\n");
        System.out.println(troll+"\n");
        System.out.println(metro);
    }
}
