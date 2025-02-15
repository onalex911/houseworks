package AbstractFactory;

public class TurkishCapital implements NationCapital{
    @Override
    public void buildCapital() {
        System.out.println(ACTION + "Анкара");
    }
}
