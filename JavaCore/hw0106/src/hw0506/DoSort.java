package hw0506;

import java.util.Random;

public class DoSort {
    public static void main(String[] args) {
        Random rnd = new Random();
        int[] userArr = new int[10];
        //int[] srtArr = new int[10];
        int maxVal = 100;
        for (int i = 0; i < userArr.length; i++) {
            userArr[i] = rnd.nextInt(maxVal);
        }

        MySort mysort = new MySort(userArr);
        System.out.println("Несортированный массив:");
        mysort.printArray();
        mysort.sort();
        System.out.println("Сортированный по возрастанию:");
        mysort.printArray();
        mysort.sort(false);
        System.out.println("Сортированный по убыванию:");
        mysort.printArray();
    }
}
