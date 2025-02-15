package FactoryMethod;

//класс для описания конкретного продукта
public class Eskimo implements Nation {


    @Override
    public String getNationName() {
        return "Эскимосы";
    }

    @Override
    public String getHouseName() {
        return "Иглу";
    }

    @Override
    public String getFoodName() {
        return "Строганина";
    }
}
