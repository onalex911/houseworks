package AbstractFactory;

public class JapaneeseFood implements NationFood {

    @Override
    public void cookFood() {
        System.out.println(ACTION + "онегири");
    }
}
