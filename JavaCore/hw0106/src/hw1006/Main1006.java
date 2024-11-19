package hw1006;

import java.util.Random;

public class Main1006 {
    public static void main(String[] args) {

        int[] userArr1 = new int[]{20, 11, 12, 20, 3, 4, 5, 3, 11, 20};

        int limit = 20;
        int val = 11;
        int newVal = 99;

        MyList1006 mylist1 = new MyList1006(userArr1);
        MyList1006 mylist2 = new MyList1006();
        Random rnd = new Random();
        for (int i = 0; i < limit; i++) {
            mylist2.add(rnd.nextInt(30)-15);
        }
        MyList1006 mylist3 = new MyList1006();
        for (int i = 0; i < limit; i++) {
            mylist3.add(rnd.nextInt(5)+1);
        }
        System.out.println("\n---------- Удаление значения --------------");
        System.out.println("Исходный массив:");
        mylist1.printArray();
        System.out.println(mylist1.getSize() + "/" + mylist1.getArrayLenght());
        System.out.printf("Удаляем все вхождения значения %d:\n", val);
        mylist1.removeByValue(val);
        mylist1.printArray();
        System.out.println(mylist1.getSize() + "/" + mylist1.getArrayLenght());
        System.out.println("---------- Сортировка --------------");
        System.out.println("Исходный массив:");
        mylist2.printArray();
        mylist2.sort(false);
        System.out.println("Отсортированный массив:");
        mylist2.printArray();
        System.out.println(mylist2.getSize() + "/" + mylist2.getArrayLenght()+"/"+mylist2.getCount());

//        mylist1.sort(false);
//        mylist3.printArray();

        System.out.println("---------- Удаляем все дубликаты --------------");
        System.out.println("Исходный массив:");
        mylist3.printArray();
        mylist3.removeAllDuplicates();
        System.out.println("Обработанный массив:");
        mylist3.printArray();
        System.out.println(mylist3.getSize() + "/" + mylist3.getArrayLenght());

        MyList1006 mylist4 = new MyList1006();
        for (int i = 0; i < limit; i++) {
            mylist4.add(rnd.nextInt(30)+15);
        }
        System.out.println("\n#2 Исходный массив:");
        mylist4.printArray();
        mylist4.sortBubble(true);
        System.out.println("Обработанный массив:");
        mylist4.printArray();
        System.out.println(mylist4.getSize() + "/" + mylist4.getArrayLenght()+"/"+mylist4.getCount());
    }
}
