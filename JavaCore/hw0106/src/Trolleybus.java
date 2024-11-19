public class Trolleybus extends CityTransport {
    private final String name = "троллейбус";
    private final String typeEngine = "электро";
    private final boolean isRailway = false;
    private final boolean isOnGround = true;
    private final String receiver = "штанга";

    public String getSound() {
        return "Мап-мап!";
    }
    public String toString() {
        return "Вид городского транспорта: " + name +
                "\nтип тяги: " + typeEngine + " (токоприемник - " + receiver + ")" +
                "\n" + (isRailway ? "рельсовый" : "колесный") +
                ", " + (isOnGround ? "наземный" : "подземный") +
                "\nЕсли слышен звук: «" + getSound() + "» - это едет " + name;
    }
}
