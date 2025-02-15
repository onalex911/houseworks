package AbstractFactory;

class JapaneeseAbstractFactory implements NationAbstractFactory {
    @Override
    public NationName createNation() {
        return new JapaneeseNation();
    }

//    @Override
//    public NationCapital createNationCapital() {
//        return new JapaneeseCapital();
//    }
//
//    @Override
//    public NationFood createNationFood() {
//        return new JapaneeseFood();
//    }
}
