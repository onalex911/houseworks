package hw1004;

public class PrintArray {
    //static int n = 0;
    public static void main(String[] args) {
        int[] mas = {20,13,84,56,123,0};
        printElem(mas);
    }
    //  идея - передавать число, которое не превышало бы длину массива, но постоянно бы изменялось

    //  делаем вариант метода, который вычисляет длину массива и отдает её для первой итерации
    static void printElem(int[] arr) {
        printElem(arr,arr.length);
    }
    //  основной метод, который использует изменяющуюся переменную (n),
    //  по которой вычисляется текущая позиция массива для печати
    static void printElem(int[] arr,int n){
        int k = arr.length - n; //для наглядности сделаем счетчик позиций
        if(k < arr.length) {
            System.out.print(arr[k] + " ");
            printElem(arr,--n);
        }
    }
///////////////////////////////////////////////////
//    вариант, начатый на уроке
//    из main должен вызываться: printElem(mas,0) - что "некрасиво"
//    static void printElem(int[] arr,int n){
//        if(n<arr.length) {
//            System.out.print(arr[n] + " ");
//            printElem(arr,++n);
//        }
//    }
/////////////////////////////////////////////////////
//    вариант со статической переменной, объявленной в классе
//    из main должен вызываться: printElem(mas) - что "красиво", но так мы еще "не умеем"
//    static void printElem(int[] arr){
//        if(n<arr.length) {
//            System.out.print(arr[n++] + " ");
//            printElem(arr);
//        }
//    }
}
