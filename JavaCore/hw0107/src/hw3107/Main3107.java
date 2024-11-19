package hw3107;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

//Создать программу которая будет считать из файла который содержит N-количество слов ( сказку)
//1) Количество глассных символов
//2) Количество согласных символов
//3) Количество чисел символов
//4) Количество символов
//5) Количество указанного символа
//6) Количество указанного слово
//
//Выполнить данное задание используя потоки
public class Main3107 {

    public static String glas = "aeiouyAEIOUY";
    public static String soglas = "bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ";
    public static String numbers = "1234567890";

    public static void main(String[] args) {

        Scanner scn1 = new Scanner(System.in);
        System.out.print("Введите символ, количество которого нужно подсчитать: ");
        String symbForSearch = scn1.next();
        Scanner scn2 = new Scanner(System.in);
        System.out.print("Введите слово, количество которого нужно подсчитать: ");
        String wordForSearch = scn2.next();

        long startTime = System.nanoTime();
        File file = new File("src/files/lorem.txt");
        if (file.exists()) {
            try {

                ExecutorService executorService = Executors.newFixedThreadPool(6);

                //1) Количество глассных символов
                Runnable task1 = () -> {
                    try {
                        FileReader fr = new FileReader(file);
                        char[] buffer = new char[(int) file.length()];
                        int countChars = fr.read(buffer);
                        if (countChars > 0) {
                            long count = IntStream.range(0, buffer.length)
                                    .mapToObj(i -> buffer[i])
                                    .filter(c -> glas.indexOf(c) != -1)
                                    .count();
                            System.out.println("1) Гласных: " + count);
                        }
                    } catch (IOException ioe) {
                    }
                };
                //2) Количество согласных символов
                Runnable task2 = () -> {
                    try {
                        FileReader fr = new FileReader(file);
                        char[] buffer = new char[(int) file.length()];
                        int countChars = fr.read(buffer);
                        if (countChars > 0) {
                            long count = IntStream.range(0, buffer.length)
                                    .mapToObj(i -> buffer[i])
                                    .filter(c -> soglas.indexOf(c) != -1)
                                    .count();
                            System.out.println("2) Согласных: " + count);
                        }
                    } catch (IOException ioe) {
                    }

                };
                //3) Количество чисел символов
                Runnable task3 = () -> {
                    try {
                        FileReader fr = new FileReader(file);
                        char[] buffer = new char[(int) file.length()];
                        int countChars = fr.read(buffer);
                        if (countChars > 0) {
                            long count = IntStream.range(0, buffer.length)
                                    .mapToObj(i -> buffer[i])
                                    .filter(c -> numbers.indexOf(c) != -1)
                                    .count();
                            System.out.println("3) Числовых символов: " + count);
                        }
                    } catch (IOException ioe) {
                    }
                };
                //4) Количество символов
                Runnable task4 = () -> {
                    try {
                        FileReader fr = new FileReader(file);
                        char[] buffer = new char[(int) file.length()];
                        int countChars = fr.read(buffer);
                        System.out.println("4) Всего символов: " + countChars);
                    }catch (IOException ioe){
                    }
                };
                //5) Количество указанного символа
                char firstChar = symbForSearch.toCharArray()[0];
                Runnable task5 = () -> {
                    try {
                        FileReader fr = new FileReader(file);
                        char[] buffer = new char[(int) file.length()];
                        int countChars = fr.read(buffer);
                        if (countChars > 0) {
                            long count = IntStream.range(0, buffer.length)
                                    .mapToObj(i -> buffer[i])
                                    .filter(c -> (char) c == firstChar)
                                    .count();
                            System.out.println("5) Символов \'" + firstChar + "\': " + count);
                        }
                    } catch (IOException ioe) {
                    }
                };
                //6) Количество указанного слово
                Runnable task6 = () -> {
                    try {
                        FileReader fr = new FileReader(file);
                        char[] buffer = new char[(int) file.length()];
                        int countChars = fr.read(buffer);
                        if (countChars > 0) {
                            String word = "";
                            long count = 0;
                            for (int i = 0; i < buffer.length; i++) {
                                if (buffer[i] == ' ' || buffer[i] == '\n' || buffer[i] == '.' || buffer[i] == ',' || buffer[i] == '!' || buffer[i] == '?' || buffer[i] == ':' || buffer[i] == ';' && word.length() > 0) {
                                    if (word.equalsIgnoreCase(wordForSearch)) {
                                        count++;
                                    }
                                    word = "";
                                } else {
                                    word += buffer[i];
                                }
                            }
                            if (word.equalsIgnoreCase(wordForSearch))
                                count++;
                            System.out.println("6) Слов \'" + wordForSearch + "\': " + count);
                        }
                    } catch (IOException ioe) {
                    }
                };
                executorService.execute(task1);
                executorService.execute(task2);
                executorService.execute(task3);
                executorService.execute(task4);
                executorService.execute(task5);
                executorService.execute(task6);
                executorService.shutdown();

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Total time: " + (endTime - startTime) / 1_000_000 + " ms");
    }
}
