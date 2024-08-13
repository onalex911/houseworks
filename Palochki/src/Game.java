import java.util.Random;
import java.util.Scanner;

public class Game {
    private static String[] types;
    private static String exitText;
    private int countGames;
    private long timeStart, timeEnd;
    private Player pl1;
    private Player pl2;

    static {
        exitText = "0 - выход";
        types = new String[]{"Человек - человек", "Человек - компьютер"};
    }

    {
        countGames = 1;
    }

    Game() {

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

    public static String getPlayersText() {
        String out = "";
        for (int i = 0; i < types.length; i++)
            out += (i + 1) + " - " + types[i] + "\n";

        out += exitText;
        return out;
    }

    public static int getTypesCount() {
        return types.length;
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
        //Random rnd = new Random();
        Player firstMotion = new Player();
        Player secondMotion = new Player();

        while (true) {
            Field field = new Field();
            System.out.println("\n---------------- Игра №" + this.countGames + " ------------------------");
            if (this.countGames > 1) {
                Player tmpPlayer = firstMotion;
                firstMotion = secondMotion;
                secondMotion = tmpPlayer;
            } else {
                firstMotion = pl1;
                secondMotion = pl2;
            }
            //System.out.println("Игрок 1: " + firstMotion.getName());
            //System.out.println("Игрок 2: " + secondMotion.getName());
            System.out.println("Первым ходит " + firstMotion.getName());

            int countMotions = 1;

            Scanner sc1;
            this.setTimeStart(System.currentTimeMillis());
            while (true) {
                int numSticks;
                Player nowPlays = (countMotions % 2 == 1) ? firstMotion : secondMotion;
                System.out.println("\n    Ход №" + countMotions + ". Ходит " + nowPlays.getName());
                System.out.println(field.drawSticks());
                sc1 = new Scanner(System.in);

                System.out.print("Введите кол-во палочек, которые хотите убрать");
                if (nowPlays.isHuman()) {
                    System.out.print(" (0 - выход): ");
                    numSticks = sc1.nextInt();
                    if (numSticks == 0) {
                        exitMode = 1;
                        break;
                    }


                } else {
                    System.out.print(": ");
                    int subst = field.getSticksRest() > field.getWinNumber() ? field.getWinNumber() : field.getSticksToLose();
                    numSticks = field.getSticksRest() - subst > field.getMaxSticksTake() ? field.getMinSticksTake() : field.getSticksRest() - subst;

                    System.out.println(numSticks);
                }
                try {
                    field.takeSticks(numSticks);
                    if (field.getSticksRest() == field.getSticksToLose()) {
                        exitMode = -1;
                        winner = nowPlays;
                        System.out.println(field.drawSticks());
                        break;
                    }
                    countMotions++;
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    continue;
                }
            }
            this.setTimeEnd(System.currentTimeMillis());

            if (exitMode > 0) {
                System.out.println("Хотите закончить игру? (0 - да, любой символ - нет): ");
                String appr = sc1.next();
                if (appr.equals("0")) {
                    exitMode++;
                    break;
                } else exitMode = 0;
            } else if (exitMode < 0) {
                winner.incrCountWin();
                System.out.println("\nПОБЕДИЛ игрок " + winner.getName() + "!\n");
                System.out.println("Время игры: " + this.getGameTimeString());
                System.out.print("Сыграть еще? (1 - да, 0 - нет): ");
                if (sc1.next().equals("0")) {
                    exitMode = 1;
                    break;
                }
            }
            this.countGames++;
        }
        if (exitMode == 2) {
            System.out.println("\nИгра прервана. Победителей и проигравших нет.");
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
