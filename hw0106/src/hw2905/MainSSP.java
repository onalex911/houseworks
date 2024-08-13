package hw2905;

import java.util.Random;
import java.util.Scanner;

public class MainSSP {
    public static void main(String[] args) {
        Gesture[] gestures = Gesture.values();
        /*for (int i = 0; i < gestures.length; i++) {
            System.out.println(gestures[i].getPower());
        }*/
        // int k, n, b;
        Random rnd = new Random();
        Scanner scn;
        String userChoiceTxt = "";
        int userChoice;
        int computerChoice;
        System.out.println("Добро пожаловать в игру «Камень-Ножницы-Бумага!»\n");
        Player pl1 = new Player("Пользователь");
        Player pl2 = new Player("Компьютер");

        while (true) {
            Game game = new Game(pl1, pl2);
            scn = new Scanner(System.in);
            System.out.println("Введите жест:\n" +
                    "К (k) - камень\n" +
                    "Н (n) - ножницы\n" +
                    "Б (b) - бумага");
            System.out.print("Ваш выбор: ");
            userChoiceTxt = scn.next();
            switch (userChoiceTxt.toUpperCase()) {
                case "К":
                case "K":
                    pl1.chooseGesture(Gesture.STONE);
                    break;
                case "Н":
                case "N":
                    pl1.chooseGesture(Gesture.SCISSORS);
                    break;
                case "Б":
                case "B":
                    pl1.chooseGesture(Gesture.PAPER);
                    break;
                default:
                    System.out.println("Ведено неверное значение. Попробуйте еще раз.\n");
                    continue;
            }
            System.out.println("Игрок " + pl1.getName() + " показал " + pl1.getGestureName());

            computerChoice = rnd.nextInt(gestures.length);
            pl2.chooseGesture(gestures[computerChoice]);
            System.out.println("Игрок " + pl2.getName() + " показал " + pl2.getGestureName());

            System.out.println("\nРезультат: " + game.play());

            System.out.print("\nСыграем еще? (0 - нет, любой другой символ - да):");
            String isMore = scn.next();
            if (isMore.equals("0"))
                break;
        }
        System.out.println("\n------------------------");
        System.out.println("До новых встреч!");
    }
}
