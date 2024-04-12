package hw0804;

public class Methods1 {
    //Напишите методы которые
//
//1) Калькулятор ( + , - , \ , * ) Написать 4 метода и конструктор (if либо switch)
    static int slozhenie(int x,int y){
        return x + y;
    }
    static int vychitanie(int x,int y){
        return x - y;
    }
    static int delenie(int x,int y){
        return y != 0 ? x / y : null;
    }
    static int umnozhenie(int x,int y){
        return x * y;
    }
    //2) Написать метод который рисует квадрат
//
//3) Написать метод который возвращает квадрат
//
//4) Написать метод который принимает bool
//true -> рисует треугольник
//false-> рисует пустой треугольник (только границы)
//
//
//*** не обязательно делать
//
//5) Метод принимает 2 массива,и возращает 3ий массив (one + two) в отсартированном виде
//int[] test (int[] one , int[] two){
//
//}
    public static void main(String[] args) {
        System.out.println(slozhenie(100,50));
    }
}