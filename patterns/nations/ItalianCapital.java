package AbstractFactory;

public class ItalianCapital implements NationCapital{
    @Override
    public void buildCapital() {
        System.out.println(ACTION + "Рим");
    }
}
