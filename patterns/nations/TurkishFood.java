package AbstractFactory;

public class TurkishFood implements NationFood {

    @Override
    public void cookFood() {
        System.out.println(ACTION + "кебаб");
    }
}
