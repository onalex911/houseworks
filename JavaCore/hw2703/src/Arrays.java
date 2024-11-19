import java.util.Random;
import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {
        int maxArrSize = 20;
        int maxValue = 100;
        String separator = "";
        int[] arr = new int[maxArrSize];
        Random rnd = new Random();

//        1) «аполнить рандомными числами массив и вывести
        //заполняем массив
        System.out.println("Задание №1");
        for (int i = 0; i < maxArrSize; i++) {
            arr[i] = rnd.nextInt(maxValue);
        }
        //выводим массив
        for (int i = 0; i < maxArrSize; i++) {
            separator = i<maxArrSize-1 ? ", " : ".";
            System.out.print(arr[i]+separator);
        }
        System.out.println("\n-------------------------");
//        2) «аполнить рандомными числами массив и вывести наоборот
        //заполняем и выводим исходный массив
        System.out.println("Задание №2");
        for (int i = 0; i < maxArrSize; i++) {
            arr[i] = rnd.nextInt(maxValue);
            separator = i<maxArrSize-1 ? ", " : ".";
            System.out.print(arr[i]+separator);;
        }
        System.out.println();
        //выводим массив наоборот
        for (int i = maxArrSize-1; i >=0; i--) {
            separator = i > 0 ? ", " : ".";
            System.out.print(arr[i]+separator);;
        }
        System.out.println("\n-------------------------");
//        3) «аполнить рандомными числами массив и вывести сумму
        //заполняем и выводим исходный массив
        System.out.println("Задание №3");
        int summ = 0;
        System.out.print("Содержимое массива: ");
        for (int i = 0; i < maxArrSize; i++) {
            arr[i] = rnd.nextInt(maxValue/5);
            separator = i<maxArrSize-1 ? ", " : ".";
            summ += arr[i]; //подсчитываем сумму
            System.out.print(arr[i]+separator);;
        }
        System.out.printf("\nСумма: %d\n",summ);
        System.out.println("-------------------------");
//        4) «аполнить рандомными числами массив и вывести сумму всех четных чисел
        //заполняем и выводим исходный массив
        System.out.println("Задание №4");
        summ = 0;
        String excl;
        System.out.print("Содержимое массива: ");
        for (int i = 0; i < maxArrSize; i++) {
            arr[i] = rnd.nextInt(maxValue/5);
            separator = i<maxArrSize-1 ? ", " : ".";
            if(arr[i]%2 ==0) {
                summ += arr[i]; //увеличиваем сумму в случае, если число четное
                excl = "!";
            }else
                excl = "";
            System.out.print(arr[i]+excl+separator);;
        }
        System.out.printf("\nСумма: %d\n",summ);
        System.out.println("-------------------------");
//        5) «аполнить рандомными числами массив и вывести сумму всех четных чисел которые дел¤тьс¤ на 4 без остаток
        System.out.println("Задание №5");
        summ = 0;
        System.out.print("Содержимое массива: ");
        for (int i = 0; i < maxArrSize; i++) {
            arr[i] = rnd.nextInt(maxValue/5);
            separator = i<maxArrSize-1 ? ", " : ".";
            if(arr[i]%4 ==0){
                summ += arr[i]; //увеличиваем сумму в случае, если число делиться на 4 без остатка
                excl = "!";
            }else
                excl = "";
            System.out.print(arr[i]+excl+separator);;
        }
        System.out.printf("\nСумма: %d\n",summ);
        System.out.println("-------------------------");
//        6) «аполнить рандомными числами массив и вывести сумму всех четных чисел которые дел¤тьс¤ на 3 и 5 без остаток
        System.out.println("Задание №6");
        summ = 0;
        int maxArrExtSize = maxArrSize+10;
        int[] arrExt = new int[maxArrExtSize];

        System.out.print("Содержимое массива: ");
        for (int i = 0; i < maxArrExtSize; i++) {
            arrExt[i] = rnd.nextInt(maxValue*15);
            separator = i<maxArrExtSize-1 ? ", " : ".";
            if(arrExt[i]%3 ==0 && arrExt[i]%5 ==0) {
                summ += arrExt[i]; //увеличиваем сумму в случае, если число четное и делиться на 3 и 5 без остатка
                excl = "!";
            }else
                excl = "";
            System.out.print(arrExt[i]+excl+separator);;
        }
        System.out.printf("\nСумма: %d\n",summ);
        System.out.println("-------------------------");
//        7) «апросить у ползовател¤ размер массива заполнить массив рандомными числами
        //заполняем массив
        System.out.println("Задание №7");
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int userArrSize = scn.nextInt();
        int[] userArr = new int[userArrSize];
        for (int i = 0; i < userArrSize; i++) {
            userArr[i] = rnd.nextInt(maxValue);
        }
        //выводим массив
        for (int i = 0; i < userArrSize; i++) {
            separator = i<userArrSize-1 ? ", " : ".";
            System.out.print(userArr[i]+separator);
        }
        System.out.println("\n-------------------------");
//        8) «апросить у ползовател¤ размер массива и дать возможность заполнить массив пользователю
        System.out.println("Задание №8");
        System.out.print("Введите размер массива: ");
        int userArrSize2 = scn.nextInt();
        int[] userArr2 = new int[userArrSize2];
        for (int i = 0; i < userArrSize2; i++) {
            System.out.printf("Введите значение %d-го члена массива: ",i+1);
            userArr2[i] = scn.nextInt();
        }
        //выводим массив
        System.out.println("Содержимое введенного массива:");
        for (int i = 0; i < userArrSize2; i++) {
            separator = i<userArrSize2-1 ? ", " : ".";
            System.out.print(userArr2[i]+separator);
        }
        System.out.println("\n-------------------------");

        System.out.println("-------------------------");
//
//
//
//        9)* «аполнить рандомными числами массив и вывести максимальное значение  (массив , иф , доп переменна¤ , цикл)
        //заполняем массив
        int maxVal = 0;
        System.out.println("Задание №9");
        for (int i = 0; i < maxArrSize; i++) {
            arr[i] = rnd.nextInt(maxValue);
            if(arr[i] > maxVal)
                maxVal = arr[i];
        }
        //выводим массив
        for (int i = 0; i < maxArrSize; i++) {
            separator = i<maxArrSize-1 ? ", " : ".";
            System.out.print(arr[i]+separator);
        }
        System.out.printf("\nМаксимальное значание: %d",maxVal);

        System.out.println("\n-------------------------");
//        10)* «аполнить рандомными числами массив и вывести минимальное значение (массив , иф , доп переменна¤ , цикл)
        int minVal = maxValue;
        System.out.println("Задание №10");
        for (int i = 0; i < maxArrSize; i++) {
            arr[i] = rnd.nextInt(maxValue);
            if(arr[i] < minVal)
                minVal = arr[i];
        }
        //выводим массив
        for (int i = 0; i < maxArrSize; i++) {
            separator = i<maxArrSize-1 ? ", " : ".";
            System.out.print(arr[i]+separator);
        }
        System.out.printf("\nМинимальное значание: %d",minVal);

    }
}
