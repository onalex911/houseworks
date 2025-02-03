package FactoryMethod;

public class IndianFactory extends NationFactory{
    @Override
    public Nation createNation() {
        return new Indian();
    }
}
