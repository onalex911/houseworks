package AbstractFactory;

class ItalianAbstractFactory implements NationAbstractFactory {
    @Override
    public NationName createNation() {
        return new ItalianNation();
    }

//    @Override
//    public NationCapital createNationCapital() {
//        return new ItalianCapital();
//    }
//
//    @Override
//    public NationFood createNationFood() {
//        return new ItalianFood();
//    }
}
