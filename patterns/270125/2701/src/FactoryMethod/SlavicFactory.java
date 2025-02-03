package FactoryMethod;


public class SlavicFactory extends NationFactory{
    @Override
    public Nation createNation() {
        return new Slavic();
    }
}
