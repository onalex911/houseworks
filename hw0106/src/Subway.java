public class Subway extends CityTransport {
    private final String name = "метро";
    private final String typeEngine = "электро";
    private final boolean isRailway = true;
    private final boolean isOnGround = false;
    private final int trackWidth = 1524;
    private final String receiver = "рельсы";

    public String getSound() {
        return "Вжух!";
    }

    public String toString() {
        return "Вид городского транспорта: " + name +
                "\nтип тяги: " + typeEngine + " (токоприемник - " + receiver + ")" +
                "\n" + (isRailway ? "рельсовый (ширина колеи - " + trackWidth + "мм)" : "колесный") +
                ", " + (isOnGround ? "наземный" : "подземный") +
                "\nЕсли слышен звук: «" + getSound() + "» - это движется " + name;
    }
}
