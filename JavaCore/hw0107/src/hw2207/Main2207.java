package hw2207;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main2207 {
    static int count = 0;

    public static void main(String[] args) {
//        1)Фильтрация четных чисел:
//        Дана коллекция целых чисел.
        List<Integer> numbers1 = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            int num = rnd.nextInt(100) - 51;
            numbers1.add(num);
            System.out.print(num + " ");
        }
        System.out.println("\n------------------------------------");
        System.out.println("1)");
        List<Integer> result1 = numbers1.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(result1);
//
////        2)Преобразование строк в их длины:
//        Дана коллекция строк.
        List<String> str = new ArrayList<>();
        String txt1 = "О сколько нам открытий чудных готовит просвещенья дух";
        for (String s : txt1.split(" "))
            str.add(s);
        System.out.println("\n2)");
        List<Integer> result2 = str.stream().map(l -> l.length()).collect(Collectors.toList());
        System.out.println(result2);

//        3)Получение уникальных символов:
//        Дана строка. Получите уникальные символы из строки в отсортированном порядке.

        System.out.println("\n3)");
        System.out.println("Вариант с IntStream");
        //char[] streamFromString = txt1.toCharArray();
        //String qqq = new String();
        // chars();
        Stream<Character> sorted = txt1.chars().mapToObj(c -> (char) c).distinct().sorted(Comparator.reverseOrder());

        for(Character s:sorted.toList())
            System.out.print(s+" ");

        System.out.println("\n-------------------------------");
        //streamFromString.distinct().sorted(Comparator.reverseOrder()).forEach(x -> System.out.print("\'"+(char) x + "\' "));
        System.out.println();
        //отсортировать стрим в обратном порядке не получилось. Получилось только так:
        char[] charArr = txt1.toCharArray();
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < charArr.length; i++) {
            chars.add(charArr[i]);
        }
        System.out.println("Вариант с List<Character>");
        chars.stream().distinct().sorted((x, y) -> y - x).forEach(x -> System.out.print("\'"+(char) x + "\' "));
        System.out.println();
//
//        4)Дана коллекция целых чисел. Вычислите сумму квадратов всех чисел.
        System.out.println("\n4)");
        List<Integer> arrInt = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            arrInt.add(i);
            System.out.print(i + " ");
        }
        System.out.println("\n-----------------");
        System.out.println(arrInt.stream().map(a -> a * a).mapToInt(Integer::intValue).sum());
//
//        5)Преобразование списка в строку:
//        Дана коллекция строк. Объедините их в одну строку, разделяя запятыми.
        String result = str.stream().map(s -> {
            if (++count < str.size())
                return s + ", ";
            else
                return s;
        }).collect(Collectors.joining());
        System.out.println("\n5)\n" + result);
//
//        6)Поиск первого положительного числа:
//        Дана коллекция целых чисел. Найдите первое положительное число.
        int firstPositive = numbers1.stream().filter(x -> x > 0).findFirst().get();
        System.out.println("\n6)\n" + firstPositive);
        //
//        7)Группировка по длине строки:
//        Дана коллекция строк. Группируйте строки по их длине.
        System.out.println("\n7)");
        List<String> result4 = str.stream().sorted((x, y) -> x.length() - y.length()).collect(Collectors.toList());
        System.out.println(result4);
//
//        8)Применение функции к элементам:
//        Дана коллекция строк. Преобразуйте каждую строку в верхний регистр и выведите результат.
        System.out.println("\n8)");
        List<String> result5 = str.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(result5);
//
//        9)Дана коллекция целых чисел. Отфильтруйте четные числа и возьмите первые 3.
        List<Integer> result3 = numbers1.stream().filter(x -> x % 2 == 0).limit(3).toList();
        System.out.println("\n9)");
        result3.forEach(s -> System.out.print(s + " "));
    }
}
