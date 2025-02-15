package FactoryMethod;

//фабрика для создания определенного продукта
public class EskimoFactory extends NationFactory{
    @Override
    public Nation createNation() {
        return new Eskimo();
    }
}
