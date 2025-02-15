package AbstractFactory;

public class ItalianFood implements NationFood {

    @Override
    public void cookFood() {
        System.out.println(ACTION + "пицца");
    }
}
