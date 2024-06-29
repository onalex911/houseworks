import java.util.Random;
import java.util.Scanner;

public class Game {
    private static String[] types;
    private static String[] signs;
    private static String exitText;
    private int countGames;
    //private static int countGames;
    private long timeStart, timeEnd;
    private Player pl1;
    private Player pl2;

    private Field field;

    static {
        exitText = "0 - выход";
        types = new String[]{"Человек - человек", "Человек - компьютер"};
        signs = new String[]{"X", "O"};
        //countGames = 1;
    }

    Game() {
        this.field = new Field();
    }

    Game(Player pl) {
        this();
        this.pl1 = pl;
        this.pl2 = null;

    }

    Game(Player pl1, Player pl2) {
        this(pl1);
        this.pl2 = pl2;
    }

    Game(Player pl1, Player pl2, Field field) {
        this(pl1, pl2);
        this.field = field;
    }

    public static String getPlayersText() {
        String out = "";
        for (int i = 0; i < types.length; i++)
            out += (i + 1) + " - " + types[i] + "\n";

        out += exitText;
        return out;
    }

    public static String getSignsText() {
        String out = "";
        for (int i = 0; i < signs.length; i++)
            out += (i + 1) + " - «" + signs[i] + "», ";

        out += exitText;
        return out;
    }

    public static int getTypesCount() {
        return types.length;
    }

    public static String getSign(int ind) {
        if (ind >= 0 && ind < signs.length)
            return signs[ind];
        else if (ind == -1)
            return " ";
        return Errors.MainMessage + "Нет игрового символа с указанным индексом (" + ind + ")";
    }

    private void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    private void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void resetCountGames() {
        countGames = 1;
    }

    public int getCountGames() {
        return countGames;
    }

    public Player play() {
        Scanner sc0;
        int exitMode = 0;
        Player winner = null;
        Random rnd = new Random();

        while (true) {
            field.initMainArray();
            System.out.println("\n---------------- Игра №" + this.countGames + " ------------------------");
            if (this.countGames > 1) {
                int tmp = pl1.getSign();
                pl1.setSign(pl2.getSign());
                pl2.setSign(tmp);
            }
            System.out.println("Игрок " + pl1.getName() + ", знак «" + getSign(pl1.getSign()) + "»");
            System.out.println("Игрок " + pl2.getName() + ", знак «" + getSign(pl2.getSign()) + "»");
            Player firstMotion = pl1.getSign() == 0 ? pl1 : pl2;
            Player secondMotion = firstMotion == pl1 ? pl2 : pl1;
            System.out.println("Первым ходит игрок " + firstMotion.getName());

            int countMotions = 1;

            Scanner sc1;
            this.setTimeStart(System.currentTimeMillis());
            while (true) {
                System.out.println("    Ход №" + countMotions);
                System.out.println(field.draw(true));
                sc1 = new Scanner(System.in);
                int fieldIndex;

                Player nowPlays = (countMotions % 2 == 1) ? firstMotion : secondMotion;
                System.out.print("Ход игрока " + nowPlays.getName());
                if (nowPlays.isHuman()) {
                    System.out.print(" (0 - выход): ");
                    String step = sc1.next();
                    if (step.equals("0")) {
                        exitMode = 1;
                        break;
                    }
                    fieldIndex = field.getFieldIndex(step.toLowerCase());
                    if (fieldIndex < 0) {
                        System.out.println(Errors.MainMessage + "Неверно указаны координаты ячейки. Попробуйте еще раз.");
                        continue;
                    }

                    if (!field.setPosition(fieldIndex, nowPlays.getSign())) {
                        System.out.println(Errors.MainMessage + "Указанная позиция уже занята. Укажите другую.");
                        continue;
                    }
                } else {
                    System.out.println(":");
                    do {
                        fieldIndex = rnd.nextInt(8);
                    } while (!field.setPosition(fieldIndex, nowPlays.getSign()));
                }
                if (countMotions >= 5 && field.isWinCombination(nowPlays.getSign())) {
                    exitMode = -1;
                    winner = nowPlays;
                    System.out.println(field.draw(true));
                    break;
                }
                if (countMotions >= field.getMainArraySize()) {
                    System.out.println("Все позиции заняты! Игра окончена.");
                    exitMode = 1;
                    break;
                }
                countMotions++;
            }
            this.setTimeEnd(System.currentTimeMillis());
            System.out.println("Время игры: " + this.getGameTimeString());
            if (exitMode > 0) {
                System.out.println("Хотите закончить игру? (0 - да, любой символ - нет): ");
                String appr = sc1.next();
                if (appr.equals("0")) {
                    exitMode++;
                    break;
                } else exitMode = 0;
            } else if (exitMode < 0) {
                winner.incrCountWin();
                System.out.println("ПОБЕДИЛ игрок " + winner.getName() + "!\n");
                System.out.print("Сыграть еще? (1 - да, 0 - нет): ");
                if (sc1.next().equals("0")) {
                    exitMode = 1;
                    break;
                }
            }
            this.countGames++;
        }
        if (exitMode == 2) {
            System.out.println("Игра прервана. Победителей и проигравших нет.");
            //System.out.println("Время игры: " + this.getGameTimeString());
            return null;
        } else if (exitMode == 1) {
            if (pl1.getCountWin() > pl2.getCountWin())
                return pl1;
            else if (pl1.getCountWin() < pl2.getCountWin())
                return pl2;
        }
        return null;
    }

    public String getGameTimeString() {
        String outText = "";
        int hours, mins;
        hours = 0;
        mins = 0;
        long seconds = (this.timeEnd - this.timeStart) / 1000;
        if (seconds >= 3600) {
            hours = (int) (seconds / 3600);
            seconds = seconds % 3600;
            outText += hours + " ч., ";
        }
        if (seconds >= 60) {
            mins = (int) (seconds / 60);
            seconds = seconds % 60;
            outText += mins + " м., ";
        }
        outText += seconds + " сек.";
        return outText;
    }

}
