package hw0304;

import java.util.Random;

public class MySort2 {
    static final int[] inArr = new int[10];// = {400,-1,56,-20,-1};
    static int[] ind = new int[inArr.length];
    static final int incredible = -1;

    //абсолютно максимальное значение
    static final int absMax = 50; // Integer.MAX_VALUE;
    static int count = 0;

    //возвращает индекс минимального значения массива за вычетом уже найденных минимальных значений
    static int getIndMin(){
        int min = absMax;
        int indMin = 0;
        for (int i = 0; i < inArr.length; i++) {
            if(isInInd(i)) continue;
            if(inArr[i]<=min) {
                min = inArr[i];
                indMin = i;
            }
            count++;
        }
        return indMin;
    }

    //проверяет, имеется ли указанный индекс в массиве индексов
    static boolean isInInd(int index){
        for (int i = 0; i < ind.length; i++) {
            if(ind[i] == incredible)
                return false;
            if (ind[i] == index)
                return true;
            count++;
        }
        return false;
    }

    public static void main(String[] args) {

        int[] outArr = new int[inArr.length]; //выходной массив

        //инициализируем массив индексов "невозможными" значениями
        for (int i = 0; i < ind.length; i++) {
            ind[i] = incredible;
            count++;
        }

        //заполняем исходный массив случайными значениями
        int maxVal = absMax;
        Random rnd = new Random();
        System.out.println("исходный массив: ");
        for (int i = 0; i < inArr.length; i++) {
            inArr[i] = rnd.nextInt(maxVal) - rnd.nextInt(maxVal);
            System.out.printf("%d ",inArr[i]);
        }

        //заполняем массив индексов значениями позиций минимальных членов
        for (int i = 0; i < inArr.length; i++) {
            ind[i] = getIndMin();
            count++;
        }

        System.out.println("\nрезультат сортировки: ");
        for (int i = 0; i < ind.length; i++) {
            outArr[i] = inArr[ind[i]];
            System.out.printf("%d ",outArr[i]);
        }

        System.out.println("\n\ncount = "+count);
    }
}
