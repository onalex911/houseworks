package FactoryMethod;

public class Slavic implements Nation{
    @Override
    public String getNationName() {
        return "Славяне";
    }

    @Override
    public String getHouseName() {
        return "Изба";
    }

    @Override
    public String getFoodName() {
        return "Пельмени";
    }
}
