package hw0306;

import java.util.Random;

public class Main0306 {
    public static void main(String[] args) {

        int[] userArr1 = new int[]{20, 1, 2, 20, 3, 4, 5, 20};
        int[] userArr2 = new int[]{20, 1, 2, 20, 3, 4, 5, 20};
        int[] userArr3 = new int[]{20, 1, 2, 20, 3, 4, 5, 20};
        int[] userArr4 = new int[]{20, 1, 2, 20, 3, 4, 5, 20};
        int[] userArr5 = new int[]{20, 1, 2, 20, 3, 4, 5, 20};
        int[] userArr6 = new int[]{20, 1, 2, 20, 3, 4, 5, 20};

        int val = 20;
        int newVal = 99;

        MyList mylist1 = new MyList(userArr1);
        MyList mylist2 = new MyList(userArr2);
        MyList mylist3 = new MyList(userArr3);
        MyList mylist4 = new MyList(userArr4);
        MyList mylist5 = new MyList(userArr5);
        MyList mylist6 = new MyList(userArr6);
        System.out.println("Исходный массив:");
        mylist1.printArray();
        mylist1.removeByValue(val);
        System.out.printf("Удаляем первое вхождение значения %d:\n",val);
        mylist1.printArray();
        mylist2.removeByValueEnd(val);
        System.out.printf("Удаляем последнее вхождение значения %d:\n",val);
        mylist2.printArray();
        mylist3.removeAllByValue(val);
        System.out.printf("Удаляем все вхождения значения %d:\n",val);
        mylist3.printArray();
        //System.out.println(mylist3.getArrayLenght()); //проверка длины массива после удаления всех вхождений

        mylist4.replaceByValue(val,newVal);
        System.out.printf("Заменяем первое вхождение значения %d на %d:\n",val,newVal);
        mylist4.printArray();
        mylist5.replaceByValueEnd(val,newVal);
        System.out.printf("Заменяем последнее вхождение значения %d на %d:\n",val,newVal);
        mylist5.printArray();
        mylist6.replaceAllByValue(val,newVal);
        System.out.printf("Заменяем все вхождения значения %d на %d:\n",val,newVal);
        mylist6.printArray();
    }
}
