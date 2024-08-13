public class Bus extends CityTransport {
    private final String name = "автобус";
    private final String  typeEngine = "дизель";
    private final boolean isRailway = false;
    private final boolean isOnGround = true;

    public String getSound() {
        return "Би-бип!";
    }

    @Override
    public String toString() {
        return "Вид городского транспорта: " + name +
                "\nтип двигателя: " + typeEngine +
                "\n" + (isRailway ? "рельсовый" : "колесный") +
                ", " + (isOnGround ? "наземный" : "подземный") +
                "\nЕсли слышен звук: «" + getSound() + "» - это едет " + name;
    }
}
