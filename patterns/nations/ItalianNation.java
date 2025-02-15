package AbstractFactory;

public class ItalianNation implements NationName {
    @Override
    public void createNationName() {
        System.out.println(ACTION + "итальянцы");
    }
}
