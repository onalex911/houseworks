package FactoryMethod;

public class Indian implements Nation{
    @Override
    public String getNationName() {
        return "Индейцы";
    }

    @Override
    public String getHouseName() {
        return "Вигвам";
    }

    @Override
    public String getFoodName() {
        return "Мясо бизона";
    }
}
