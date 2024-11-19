import java.util.Scanner;

//import Game;
public class Main {


    private static Field field;

    static void printRules(Field field) {
        System.out.println("ПРАВИЛА ИГРЫ:");
        System.out.println("Игроки поочередно убирают количество палочек (от 1-ой до 3-х), пока не останется одна палочка.");
        System.out.println("Выигрывает тот игрок, который сходил последним, и после него осталась одна палочка.");
    }

    public static void main(String[] args) {
        Scanner scn;
        //Field field = new Field();
        System.out.println("Добро пожаловать в игру «Палочки»");
        printRules(field); //вывод правил игры
        Scanner sc0, sc1, sc2;

        while (true) {
            System.out.println("\nВыберите, кто с кем будет играть:");
            System.out.println(Game.getPlayersText()); //вывод возможных вариантов соперников
            System.out.print("\nвведите число: ");
            scn = new Scanner(System.in);
            if (scn.hasNextInt()) {
                Player pl1 = new Player();
                Player pl2 = new Player();

                int vybTypeGame = scn.nextInt();
                if (vybTypeGame == 0) break;
                if (vybTypeGame < 0 || vybTypeGame > Game.getTypesCount()) {
                    System.out.println(Errors.MainMessage + "Выбран недопустимый тип игры");
                    break;
                }
                String addInv = vybTypeGame == 1 ? "первого " : "";
                System.out.print("Введите имя " + addInv + "игрока: ");
                sc0 = new Scanner(System.in);
                String plName1 = sc0.nextLine();

                pl1.setName(plName1);
                pl1.setHuman(true);

                if (vybTypeGame == 1) {
                    System.out.print("Введите имя второго игрока: ");
                    sc2 = new Scanner(System.in);
                    String plName2 = sc2.nextLine();

                    pl2.setName(plName2);
                    pl2.setHuman(true);
                }

                Game game = new Game(pl1, pl2);
                game.resetCountGames();

                Player winner = game.play();
                System.out.println("\nСыграно раундов: " + game.getCountGames());
                if (winner != null)
                    System.out.println("\nВыиграл игрок " + winner.getName() + " со счетом " + pl1.getCountWin() + "-" + pl2.getCountWin() + "!");
                else {
                    System.out.println("\nНИЧЬЯ!");
                }

            } else {
                System.out.println(Errors.MainMessage + Errors.wrongInt);
                break;
            }
        }//конец цикла игры между 2-мя выбранными соперниками
        System.out.println("\n-----------------\nДо новых встреч!");
    }
}
