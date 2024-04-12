package hw0804;

import java.util.Random;
import java.util.Scanner;

public class Methods1 {
    //Напишите методы которые
//
//1) Калькулятор ( + , - , \ , * ) Написать 4 метода и конструктор (if либо switch)
    static int slozhenie(int x, int y) {
        return (int) (x + y);
    }

    static int vychitanie(int x, int y) {
        return (int) (x - y);
    }

    static int delenie(int x, int y) {
        return (int) (x / y);
    }

    static int umnozhenie(int x, int y) {
        return (int) (x * y);
    }

    //2) Написать метод который рисует квадрат
    static void drawKvadrat(int size) {
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print("* ");
                }
                System.out.println();
            }
        } else
            System.out.println("Недостаточный размер!");
    }

    //
//3) Написать метод который возвращает квадрат
    static String getKvadrat(int size) {
        String result = "";
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    result += "* ";
                }
                result += "\n";
            }
        } else
            result = "Недостаточный размер!";
        return result;
    }

    //
//4) Написать метод который принимает bool
//true -> рисует треугольник
//false-> рисует пустой треугольник (только границы)
    static void drawTriangle(int size, boolean filled) {
        String symb = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == size - 1 || i == size - 1)
                    symb = "* ";
                else
                    symb = filled ? "* " : "  ";
                System.out.print(symb);
            }
            System.out.println("*");
        }
    }

    //
//
//*** не обязательно делать
//
//5) Метод принимает 2 массива,и возращает 3ий массив (one + two) в отсартированном виде
    // третий параметр - отсортировать массив:
    //   true -  в порядке возрастания
    //   false - в порядке убывания
    static int[] test(int[] one, int[] two, boolean asc) {
        int newLength = one.length + two.length;
        int[] result = new int[newLength];
        for (int i = 0; i < one.length; i++) {
            result[i] = one[i];
        }
        int k = one.length;
        for (int i = 0; i < two.length; i++) {
            result[k++] = two[i];
        }
        int a,b;
        for (int i = 0; i < newLength; i++) {
            a = asc ? result[i] : result[i] * -1;
            for (int j = i + 1; j < newLength; j++) {
                b = asc ? result[j] : result[j] * -1;
                if(a > b)
                    result[i] = (result[i] + result[j]) - (result[j] = result[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Задание №1");
        System.out.print("Введите первое число: ");
        int x = sc.nextInt();
        System.out.print("Введите второе число: ");
        int y = sc.nextInt();
        System.out.print("Введите знак операции(+,-,*,/): ");
        String oper = sc.next();
        int result = 0;
        String errMsg = "";
        switch (oper) {
            case "+":
                result = slozhenie(x, y);
                if (x > 0 && y > 0 && result < 0)
                    errMsg = "Переполнение!";
                break;
            case "-":
                result = vychitanie(x, y);
                if (x < 0 && y < 0 && result > 0)
                    errMsg = "Переполнение!";
                break;
            case "*":
                result = umnozhenie(x, y);
                if (x > 0 && y > 0 && result < 0)
                    errMsg = "Переполнение!";
                break;
            case "/":
                if (y == 0)
                    errMsg = "На ноль делить нельзя!";
                else {
                    result = delenie(x, y);
                    if (x < 0 && y < 0 && result > 0)
                        errMsg = "Переполнение!";
                }
                break;
            default:
                errMsg = "Введен неизвестный символ операции (" + oper + ")";
        }
        System.out.println();
        if (errMsg != "")
            System.out.printf("ОШИБКА! %s\n", errMsg);
        else
            System.out.printf("Результат операции: %d", result);
        System.out.println("\n------------------------------------\n");

        System.out.println("Задание №2");
        drawKvadrat(3);
        System.out.println("\n------------------------------------\n");

        System.out.println("Задание №3");
        System.out.println(getKvadrat(5));
        System.out.println("\n------------------------------------\n");

        System.out.println("Задание №4");
        drawTriangle(7,false);
        System.out.println("\n------------------------------------\n");

        System.out.println("Задание №5");
        //правило сортировки результирующего массива
        boolean ascending = false; //true;
        String ascText = ascending ? "возрастанию" : "убыванию";
        //создаем 2 массива...
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];
        //...заполняем их случайными значениями
        Random rnd = new Random();
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = rnd.nextInt(100);
            arr2[i] = rnd.nextInt(100) - arr1[i];
        }
        System.out.print("Первый массив: ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]+" ");
        }
        System.out.println();
        System.out.print("Второй массив: ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]+" ");
        }
        System.out.println();
        //получаем результирующий массив
        int[] out = test(arr1,arr2,ascending);
        //выводим результирующий массив
        System.out.print("Результат, отсортированный по " + ascText +": ");
        for (int i = 0; i < out.length; i++) {
            System.out.print(out[i]+" ");
        }
        System.out.println("\n------------------------------------\n");
    }
}