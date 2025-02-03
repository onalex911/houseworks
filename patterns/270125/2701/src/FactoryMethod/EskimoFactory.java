package FactoryMethod;


public class EskimoFactory extends NationFactory{
    @Override
    public Nation createNation() {
        return new Eskimo();
    }
}
