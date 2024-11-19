class Tram extends CityTransport {
    private String name = "трамвай";
    private String typeEngine = "электро";
    //private final boolean isRailway = true;
    private final boolean isOnGround = true;
    private final int trackWidth = 1524;
    private final String receiver = "пантограф";
    {
        this.setIsRailway(true);
    }
    public String getSound() {
        return "Дзынь-дзынь!";
    }

    public String toString() {
        return "Вид городского транспорта: " + name +
                "\nтип тяги: " + typeEngine + " (токоприемник - " + receiver + ")" +
                "\n" + this.getIsRailwayText() + (getIsRailwayText().equals("yes") ? "рельсовый (ширина колеи - " + trackWidth + "мм)" : "колесный") +
                ", " + (isOnGround ? "наземный" : "подземный") +
                "\nЕсли слышен звук: «" + getSound() + "» - это движется " + name;
    }
}