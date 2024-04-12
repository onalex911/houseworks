package hw0104;

import java.util.Random;

public class MySort1 {
//    public static final int[] inArr = new int[10];
    public static final int[] inArr = new int[15];// = {400,-1,56,-20,-1};
    public static int[] ind = new int[inArr.length];

    static int getIndMin(){
        int min = (int) Math.pow(2,32);
        int indMin = 0;
        for (int i = 0; i < inArr.length; i++) {
            if(isInInd(i)) continue;
            if(inArr[i]<min) {
                min = inArr[i];
                indMin = i;
            }
        }
        return indMin;
    }

    static boolean isInInd(int index){
        for (int i = 0; i < ind.length; i++)
            if(ind[i] == index)
                return true;
        return false;
    }

    public static void main(String[] args) {

        int[] outArr = new int[inArr.length];
        for (int i = 0; i < ind.length; i++) {
            ind[i]= -1; //инициализируем массив индексов "невозможными" значениями
        }

        //заполняем исходный массив случайными значениями
        int maxVal = 50;
        Random rnd = new Random();
        System.out.print("inArr: ");
        for (int i = 0; i < inArr.length; i++) {
            inArr[i] = rnd.nextInt(maxVal) - rnd.nextInt(maxVal);
            System.out.print(inArr[i]+" ");
        }

        for (int i = 0; i < inArr.length; i++) {
            ind[i] = getIndMin();
        }
        for (int i = 0; i < ind.length; i++) {
            outArr[i] = inArr[ind[i]];
        }

        System.out.print("\nind: ");
        for (int i = 0; i < ind.length; i++) {
            System.out.print(ind[i]+" ");
        }
        System.out.println("\noutArr: ");
        for (int i = 0; i < outArr.length; i++) {
            System.out.print(outArr[i]+" ");
        }
        //System.out.println("\nCount = "+count);


        //сортировка последовательным сравнением
        /*for (int i = 0; i < inArr.length; i++) {
            for (int j = i+1; j < inArr.length; j++) {
                if(inArr[j] < inArr[i])
                    inArr[j] = (inArr[i] + inArr[j]) - (inArr[i] = inArr[j]);
            }
        }*/
        //сортировка пузырьком
        /*for (int i = 0; i < inArr.length-1; i++) {
            for (int j = 0; j < inArr.length-i-1; j++) {
                if(inArr[j] > inArr[j+1])
                    inArr[j] = (inArr[j+1] + inArr[j]) - (inArr[j+1] = inArr[j]);
            }
        }
        System.out.println();
        for (int j : inArr) {
            System.out.printf("%d ", j);
        }*/
    }
}
