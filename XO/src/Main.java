import java.util.Scanner;

//import Game;
public class Main {


    private static Field field;

    static void printRules(Field field) {
        System.out.println("ПРАВИЛА ИГРЫ:");
        System.out.println("Игроки выбирают знак, которым они будут играть:");
        System.out.println("«" + Game.getSign(0) + "» (ходит первым) или «" + Game.getSign(1) + "», ");
        System.out.println("затем поочередно ставят свои знаки в игровое поле (размером " + field.getDimX() + " на " + field.getDimY() + ") таким образом, чтобы первым заполнить своим знаком любую горизонталь, вертикаль или диагональ.");
        System.out.println("Для того, чтобы поставить знак в определенное поле, нужно указать его номер (в формате «CтрокаCтолбец», например «B2»):\n");
        System.out.println(field.draw());
    }

    public static void main(String[] args) {
        Scanner scn;
        Field field = new Field();
        System.out.println("Добро пожаловать в игру «Крестики-нолики»");
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
                System.out.print("Введите знак " + addInv + "игрока (" + Game.getSignsText() + "): ");
                sc1 = new Scanner(System.in);

                if (sc1.hasNextInt()) {
                    int plSign1 = sc1.nextInt();
                    if (plSign1 == 0) break;
                    int plSign2 = (--plSign1 == 0 ? 1 : 0);
                    pl1.setName(plName1);
                    pl1.setSign(plSign1);
                    pl1.setHuman(true);

                    if (vybTypeGame == 1) {
                        System.out.print("Введите имя второго игрока: ");
                        sc2 = new Scanner(System.in);
                        String plName2 = sc2.nextLine();

                        pl2.setName(plName2);
                        pl2.setSign(plSign2);
                        pl2.setHuman(true);
                    } else //остальные варианты игры - только с компьютером
                        pl2.setSign(plSign2);

                } else {
                    System.out.println(Errors.MainMessage + Errors.wrongInt);
                    break;
                }

                System.out.print("Начать игру? (1 - да, 0 - нет):");
                int isStartGame = scn.nextInt();
                if (isStartGame == 0) break;

                Game game = new Game(pl1, pl2, field);
                game.resetCountGames();

                Player winner = game.play();
                System.out.println("Сыграно раундов: " + game.getCountGames());
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
