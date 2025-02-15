package AbstractFactory;

public class TurkishNation implements NationName {

    @Override
    public void createNationName() {
        System.out.println(ACTION + "турки");
    }
}
