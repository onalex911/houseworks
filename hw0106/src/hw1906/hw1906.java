package hw1906;

import static java.lang.Integer.valueOf;

class Pair<T, U> {
    private T valT;
    private U valU;

    public Pair(T valT, U valU) {
        this.valT = valT;
        this.valU = valU;
    }

    public void setValT(T valT) {
        this.valT = valT;
    }

    public void setValU(U valU) {
        this.valU = valU;
    }

    public T getValT() {
        return valT;
    }

    public U getValU() {
        return valU;
    }
}

/*
class Cell {
    private int x;
    private int y;
    char value;
    private boolean isSet;
    {
        value='.';
        isSet=false;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setValue(char value) {
        this.value = value;
        if(value != '.') isSet = true;
    }

    public char getValue() {
        return value;
    }
}
*/

public class hw1906 {

    //private static final T T = ;

    public static <T> int myFind(T[] arr, T val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(val))
                return i;
        }
        return -1;
    }

    //    public static <T> T[] mergeArrays(T[] arr1, T[] arr2) {
//        T[] out = (T[]) new Object[arr1.length + arr2.length];
//        for (int i = 0; i < out.length; i++) {
//            out[i] = i < arr1.length ? arr1[i] : arr2[i - arr1.length];
//        }
//        return out;
//    }
    public static<T> T[] mergeArrays(T[] arr1, T[] arr2) {
        Object[] out = new Object[arr1.length + arr2.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = i < arr1.length ? arr1[i] : arr2[i - arr1.length];
        }
        return (T[]) out;
    }

    public static <T> T[] copyArray(T[] source, T[] target) {
        T[] tmp = (T[]) new Object[source.length];
        for (int i = 0; i < source.length; i++) {
            tmp[i] =  source[i];
        }
        target = tmp;
        return target;
    }

    public static <T> void printArr(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static <T> T getVal(T val){
        return val;
    }

    public static void main(String[] args) {
        String myString = "Hello!";
        int a = 999;

        //      Integer b = (Object) getVal(a);
        //      System.out.println(b+"\n--------------");

        Pair pair1 = new Pair<>(myString, a);
        Pair pair2 = new Pair<>(a, myString);

        System.out.println("pair1: первое значение = " + pair1.getValT() + ", второе значение = " + pair1.getValU());
        System.out.println("pair2: первое значение = " + pair2.getValT() + ", второе значение = " + pair2.getValU());
        System.out.println("----------------------------");

        Integer arrInt1[] = new Integer[]{90, 80, 70, 60, 50, 80};
        Integer arrInt2[] = new Integer[]{45, 35, 25, 15, 5, 0};
        int val = 80;
        System.out.println("Поиск значения " + val + " в массиве arr1: " + myFind(arrInt1, val));

        Character[] arr2 = new Character[]{'К', 'т', 'о', ' ', 'з', 'д', 'е', 'с', 'ь', '?'};
        //  кастовка массива char в Character автоматически не происходит :(
        char[] tmp = "Кто здесь?".toCharArray();
//        Character[] arr2 = new Character[tmp.length];
//        for (int i = 0; i < tmp.length; i++) {
//            arr2[i] = tmp[i];
//        }
        char valC = '?';
        System.out.println("Поиск значения " + valC + " в массиве arr2: " + myFind(arr2, valC));
        System.out.println("----------------------------");
        System.out.println("Слияние массивов");
        String arrStr1[] = new String[]{"А", "роза"};
        String arrStr2[] = new String[]{"упала","на", "лапу", "Азора"};
        Boolean arrBool1[] = new Boolean[]{true,false,false,true};
        Boolean arrBool2[] = new Boolean[]{false,true,true,false};
        //printArr(mergeArrays(arrBool1, arrBool2));                // так выбрасывается исключение ClassCastException
        //printArr((Boolean[]) mergeArrays(arrBool1, arrBool2));    // и так выбрасывается исключение ClassCastException
        printArr((Object[]) mergeArrays(arrBool1, arrBool2));       // так работает!
        printArr((Object[]) mergeArrays(arrInt1, arrInt2));
        printArr((Object[]) mergeArrays(arrStr1, arrStr2));
        System.out.println("----------------------------");
        System.out.println("Копирование элементов одного массива в другой");
        // видимо, я не совсем понял задание: что значит "скопировать"? Ну вот создаю пустой массив и копирую туда другой массив...
        String[] target1 = new String[]{};
        printArr((Object[]) copyArray(arrStr2,target1));
        Boolean[] target2 = new Boolean[]{};
        printArr((Object[]) copyArray(arrBool1,target2));
        System.out.println("----------------------------");
    }
}
