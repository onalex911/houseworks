
//Пара элементов
// Напишите класс Pair, который может содержать два объекта любого типа.
// Реализуйте методы для установки и получения каждого элемента пары.
class Pair<T, K> {
    private T a;
    private K b;

    public Pair(T a, K b) {
        this.a = a;
        this.b = b;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public K getB() {
        return b;
    }

    public void setB(K b) {
        this.b = b;
    }
}


public class Main1906 {

    static <T> void print(T[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
    //Обобщенный метод поиска
// Напишите обобщенный метод find, который принимает массив элементов
// и элемент для поиска, и возвращает индекс найденного элемента или -1, если элемент не найден.
    public static <T> int find(T[]arr, T val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(val))
                return i;
        }
        return -1;
    }
    //Метод объединения массивов
// Напишите метод concatArrays, который принимает два массива любого типа
// и возвращает новый массив, содержащий элементы обоих массивов.
    public static <T> T[] concatArrays(T[] arr1,T[] arr2){
        T[] out = (T[]) new Object[arr1.length+arr2.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = i<arr1.length ? arr1[i] : arr2[i-arr1.length];
        }
        return out;
    }
    public static String[] concatArraysStr(String[] arr1,String[] arr2){
        String[] out = new String[arr1.length+arr2.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = i<arr1.length ? arr1[i] : arr2[i-arr1.length];
        }
        return out;
    }

    //Обобщенный метод копирования
// Напишите метод copy, который принимает два списка любого типа
// и копирует элементы из одного списка в другой.
//}
    public static void main(String[] args) {
//        Pair<String, Integer> pair = new Pair<>("Hello", 2022);
//        System.out.println(pair.getA() + " " + pair.getB());
//        pair.setA("Alex");
//        pair.setB(1971);
//        System.out.println(pair.getA() + " " + pair.getB());
//        System.out.println("------------------------");
        //Pair<Character, Boolean> pair2 = new Pair<>('X', true);
//        System.out.println(pair2.getA() + " " + pair2.getB());
//        System.out.println("------------------------");
//        String[] test = {"alfa", "beta", "gamma"};
//        System.out.println(pair.find(test, "gamma"));

//        String arr = "Lorem ipsum";
//        char[] str = arr.toCharArray();
//        Character[] test2 = new Character[str.length];
//        for (int i = 0; i < str.length; i++) {
//            test2[i] = str[i];
//        }

        Integer[] arr = new Integer[]{2,4,56,32,98,-1};
        System.out.println(find(arr,-1));
        String[] arrStr = {"a","roza","upala"};
        System.out.println(find(arrStr,"roza"));
        String[] arrStr2 = {"na","lapu","Azora"};
        String[] result = concatArrays(arrStr2,arrStr);
        print(result);
//        MyList mylist = new MyList();
//        mylist.add("qqq");

    }
}
