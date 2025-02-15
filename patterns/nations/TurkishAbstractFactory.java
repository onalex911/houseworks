package AbstractFactory;

class TurkishAbstractFactory implements NationAbstractFactory {
    @Override
    public NationName createNation() {
        return new TurkishNation();
    }

//    @Override
//    public NationCapital createNationCapital() {
//        return new TurkishCapital();
//    }
//
//    @Override
//    public NationFood createNationFood() {
//        return new TurkishFood();
//    }
}
