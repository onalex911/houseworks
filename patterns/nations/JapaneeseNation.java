package AbstractFactory;

public class JapaneeseNation implements NationName {
    @Override
    public void createNationName() {
        System.out.println(ACTION + "японцы");
    }
}
