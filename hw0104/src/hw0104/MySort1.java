package hw0104;
import java.util.Random;

public class MySort1 {

    public static void main(String[] args) {
        int[] inArr = new int[10];              //исходный массив
        int[] ind = new int[inArr.length];      //массив индексов найденных минимумов
        int[] outArr = new int[inArr.length];   //заготовка под отсортированный массив
        int count = 0;
        //инициализируем массив индексов "невозможными" значениями
        int incredible = -1;
        for (int i = 0; i < ind.length; i++) {
            ind[i]= incredible;
        }

        //заполняем исходный массив случайными значениями
        int maxVal = 100;//Integer.MAX_VALUE;
        Random rnd = new Random();
        System.out.println("исходный массив: ");
        for (int i = 0; i < inArr.length; i++) {
            inArr[i] = rnd.nextInt(maxVal) - rnd.nextInt(maxVal);
            System.out.printf("%d, ",inArr[i]);
        }
        //находим максимальное значение массива и записываем его индекс...
        int arrMax = inArr[0];
        for (int i = 1; i < inArr.length; i++) {
            if (inArr[i] > arrMax) {
                arrMax = inArr[i];
                ind[ind.length - 1] = i;
            }
            count++;
        }
        //...и записываем его в выходной массив в крайнюю позиция
        outArr[outArr.length-1] = arrMax;
        //System.out.println("\nMax = "+arrMax);

        //ищем минимумы, циклически проверяя массив и находя минимальное значение
        for (int i = 0; i < inArr.length-1; i++) {
            int min = arrMax;
            int indMin = 0;
            for (int j = 0; j < inArr.length; j++) {
                boolean isInInd = false;
                //проверяем, есть ли уже данная позиция в массиве индексов...
                for (int k = 0; k < ind.length; k++) {
                    if(ind[k] == incredible) break;//пропускаем "невозможные" значения
                    if (ind[k] == j) {
                        isInInd = true;
                        break;
                    }
                    count++;
                }
                if(isInInd) continue; //...если есть - пропускаем итерацию
                if(inArr[j]<=min) {
                    min = inArr[j];
                    indMin = j;
                }
                count++;
            }
            ind[i] = indMin; //записываем индекс минимального значения
            outArr[i] = inArr[indMin]; //записываем очередное минимальное значение
            count++;
        }

        /*System.out.print("\nмассив индексов: ");
        for (int i = 0; i < ind.length; i++) {
            System.out.print(ind[i]+" ");
        }*/
        System.out.println("\nотсортированный массив: ");
        for (int i = 0; i < outArr.length; i++) {
            System.out.print(outArr[i]+" ");
        }
        System.out.println("\n\ncount = "+count);
    }
}
