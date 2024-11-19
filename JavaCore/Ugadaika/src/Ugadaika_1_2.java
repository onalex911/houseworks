package Ugadaika_1_2;

import java.util.Random;
import java.util.Scanner;

public class Ugadaika_1_2 {
    public static void main(String[] args) {
        int min, max;
        Random rnd = new Random();
        int compVar, userVar;
        int countAttempt;
        String adj = "";
        String errMsg = "ОШИБКА! ";
        String shame = "Ай-яй-яй! Обманывать нехорошо:";
        String userReaction = "";

        System.out.println("\n\nЗдравствуйте! Я - программа «Угадайка» v1.2.");
        userVar = 0;
        Scanner sc;
        while (true) {
            sc = new Scanner(System.in);
            min = 1;
            max = 100;
            countAttempt = 0;

            System.out.printf("\nЗагадайте число в диапазоне от %d до %d (включительно) и введите его с клавиатуры.\nКомпьютер попытается его угадать (обещаю, что подсказывать ему я не буду).\n", min, max);
            System.out.print("Введите число (0 - выход): ");

            if (sc.hasNextInt()) {
                userVar = sc.nextInt();
                if (userVar == 0)
                    break;
            } else {
                System.out.println("\n" + errMsg + "Необходимо ввести целое число!");
                continue;
            }

            while (true) {
                System.out.printf("Угадываем в диапазоне от %d до %d\n", min, max);
                compVar = rnd.nextInt(max - min + 1) + min;
                countAttempt++;
                System.out.println("Вариант компьютера: " + compVar);
                System.out.println("- ДА! это верное число (*)");
                System.out.println("- НЕТ! загаданное число МЕНЬШЕ (-)");
                System.out.println("- НЕТ! загаданное число БОЛЬШЕ (+)");
                System.out.println("0 - выход");
                System.out.println("Введите соответствующий знак: ");
                userReaction = sc.next();
                //----------------- Обработка ответа ---------------------------
                if (userReaction.equals("*")) {
                    System.out.printf("Компьютер угадал загаданное число за %d попыток.\n", countAttempt);
                    break;
                } else {
                    //проверка на "забывчивость" игрока
                    if (userVar == compVar) {
                        System.out.printf("\n%s компьютер угадал ваше число (%d) за %d попыток!\n", shame, userVar, countAttempt);
                        break;
                    }
                    if (userReaction.equals("-")) {
                        if (compVar < userVar) {
                            System.out.printf("\n%s на самом деле ваше число БОЛЬШЕ! Скорректируйте ваш ответ.\n", shame);
                            countAttempt--; //не считается за попытку
                        } else
                            max = compVar - 1;
                        continue;
                    } else if (userReaction.equals("+")) {
                        if (compVar > userVar) {
                            System.out.printf("\n%s на самом деле ваше число МЕНЬШЕ! Скорректируйте ваш ответ.\n", shame);
                            countAttempt--; //не считается за попытку
                        } else
                            min = compVar + 1;
                        continue;
                    } else if (userReaction.equals("0")) {
                        break;
                    } else {
                        System.out.println(errMsg + "Введен неверный символ (" + userReaction + ")!");
                    }
                }
            }
            if (userVar != compVar)
                System.out.print("Уже уходите? ");
            System.out.print("Может, сыграем еще раз? (0 - нет, любое другое значение - да): ");
            if (sc.hasNextInt() && sc.nextInt() == 0)
                break;
        }
        System.out.println("\n-------------------");
        System.out.println("До новых встреч!");
    }
}
