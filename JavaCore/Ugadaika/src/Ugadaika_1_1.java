import java.util.Random;
import java.util.Scanner;

public class Ugadaika_1_1 {
    public static void main(String[] args) {
        int min = 1;
        int max = 100;
        Random rnd = new Random();
        int compVar,userVar;
        int countAttempt;
        String adj = "";
        String errMsg = "ОШИБКА! ";

        System.out.println("\n\nЗдравствуйте! Я - программа «Угадайка» v1.1.");
        Scanner sc;
        while (true) {
            countAttempt = 0; //сброс попыток
            sc = new Scanner(System.in); //инициализируем сканер для текущего раунда
            compVar = rnd.nextInt(min + max);
            //System.out.println("debug!!!--- " + compVar + " ---" );
            System.out.printf("\nКомпьютер загадал число в диапазоне от %d до %d (включительно). Угадайте это число.\n", min, max);
            Scanner scIn;
            while (true) {
                scIn = new Scanner(System.in); //инициализируем сканер для текущей попытки
                System.out.print("Введите число (0 - выход): ");
                if(scIn.hasNextInt()) {
                    userVar = scIn.nextInt();
                    if (userVar == 0) {
                        break;
                    }
                }else{
                    System.out.println("\n" + errMsg + "Необходимо ввести целое число!");
                    continue;
                }

                countAttempt++;
                if (userVar < min || userVar > max) {
                    System.out.printf("Вы ввели число вне диапазона от %d до %d. Попробуйте еще раз.\n", min, max);
                    continue;
                }
                //----------------- Обработка ответа ---------------------------
                if (userVar == compVar) {
                    System.out.printf("\nПОЗДРАВЛЯЮ! Число угадано с %d-й попытки!\n\n", countAttempt);
                    break;
                }else{
                    adj = userVar < compVar ? "БОЛЬШЕ" : "МЕНЬШЕ";
                    System.out.printf("Загаданное число %s, чем %d\n",adj, userVar);
                    continue;
                }
            }
            if(userVar != compVar) {
                System.out.print("Уже уходите? ");
            }
            System.out.print("Может, сыграем еще раз? (0 - нет, любое другое значение - да): ");
            if(sc.hasNextInt() && sc.nextInt() == 0)
                break;
        }
        System.out.println("\n-------------------");
        System.out.println("До новых встреч!");
    }
}
