package hw0307;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main0307 {

    public static void main(String[] args) {
        String warnNoLang = "ВНИМАНИЕ! Нет такого языка в словаре!";
        String warnNoWord = "ВНИМАНИЕ! Нет такого слова в словаре!";
        ArrayList<String> meanTo = new ArrayList<>();
        HashMap<String, ArrayList<String>> wordsFrom = new HashMap<>();
        HashMap<String, HashMap<String, ArrayList<String>>> transTo = new HashMap<>();
        HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>> transFrom = new HashMap<>();

        ArrayList<String> ruMean1 = new ArrayList<>();
//        ruMean1.add("идти");
//        ruMean1.add("ехать");
//        ruMean1.add("двигаться");
//        wordsFrom.put("go", ruMean1);
//
//        ArrayList<String> ruMean2 = new ArrayList<>();
//        ruMean2.add("мочь");
//        ruMean2.add("уметь");
//        ruMean2.add("банка");
//        wordsFrom.put("can", ruMean2);
//
//        ArrayList<String> ruMean3 = new ArrayList<>();
//        ruMean3.add("ехать");
//        ruMean3.add("везти");
//        ruMean3.add("вести");
//        wordsFrom.put("drive", ruMean3);

        // создаем "пустое" слово для обеспечения целостности структуры
        // после добавления первого слова его нужно удалить
        if (ruMean1.isEmpty()) wordsFrom.put("0", meanTo);

        transTo.put("ru", wordsFrom);
        transFrom.put("en", transTo);
        Dictionary dictionary = new Dictionary(transFrom);
//        var words = dictInfo.getMeanings("ru","en","ехать");
//        System.out.println(dictInfo.printArray(words));
//        System.out.println("--------------------------");
        int numWords = 0;
        int numMeanings = 0;
        Scanner scn;
        System.out.println("\nСЛОВАРЬ");
        while (true) {
            numWords = dictionary.getNumWords();
//            if (numWords > 0)
//                numMeanings = getNumMeanings(dictionary);
            System.out.printf("\nВсего в словаре слов: %d, значений: %d\n", numWords, dictionary.getNumMeanigs());
            System.out.println("Основное меню");
            if (numWords > 0)
                System.out.println("1 - перевод слов");
            System.out.println("2 - добавить слово в словарь");
            System.out.println("3 - показать все языки словаря");
            if (numWords > 0)
                System.out.println("4 - показать все слова в словаре одного языка");
            System.out.println("0 - выход");
            System.out.print("Выберите действие: ");
            scn = new Scanner(System.in);
            int resp = 0;
            try {
                resp = scn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введено неверное значение!");
                continue;
            }
            if (resp == 0) break;
            switch (resp) {
                case 1: //перевести слово
                    if (numWords == 0) {
                        System.out.println("Пока в словаре нет введенных слов");
                        continue;
                    }
                case 2: //добавить новое слово в словарь
                    Scanner sc1 = new Scanner(System.in);
                    System.out.print("С какого языка переводим (язык A)? Введите обозначение: ");
                    String fromLang = sc1.next();
                    if (!dictionary.isLangExists(fromLang)) {
                        System.out.println(warnNoLang);
                        continue;
                    }

                    Scanner sc2 = new Scanner(System.in);
                    System.out.print("На какой язык переводим (язык B)? Введите обозначение: ");
                    String toLang = sc2.next();
                    Scanner sc3 = new Scanner(System.in);
                    if (!dictionary.isLangExists(toLang)) {
                        System.out.println(warnNoLang);
                        continue;
                    }

                    System.out.print("Введите слово на языке A: ");
                    String wordFrom = sc3.next();
                    if (resp == 1) {
                        if (dictionary.isWordExists(fromLang, wordFrom)) {
                            var words = dictionary.getMeanings(fromLang, toLang, wordFrom);
                            System.out.print("\nВарианты перевода: ");
                            System.out.println(dictionary.printArray(words));
                        } else {
                            System.out.println(warnNoWord);
                        }
                        continue;
                    }

                    /*------------------------- добавление ------------------------------------*/
                    var words = dictionary.getWordsOf(fromLang);
                    boolean newWord = true;
                    for (String word : words) {
                        if (word.equals(wordFrom)) {
                            System.out.println("Слово " + wordFrom + " уже есть в словаре. Введите другое.");
                            newWord = false;
                            break;
                        }
                    }
                    if (!newWord) break;
                    Scanner scn5;
                    String newMeaning = "";
                    int count = 0;
                    ArrayList<String> meanings = new ArrayList<>();
                    while (true) {
                        System.out.print("Введите значение для слова " + wordFrom + " (0 - выход): ");
                        scn5 = new Scanner(System.in);
                        newMeaning = scn5.next();
                        if (newMeaning.equals("0")) {
                            if (count > 0) {
                                dictionary.add(fromLang, toLang, wordFrom, meanings);
                            }
                            break;
                        }
                        if (newMeaning.trim().isEmpty()) continue;
                        if (meanings.contains(newMeaning)) {
                            System.out.println("ВНИМАНИЕ! Значение " + newMeaning + " уже имеется для слова " + wordFrom + "! Введите новое значение.");
                            continue;
                        }
                        meanings.add(newMeaning);
                        count++;
                    }
                    /*------------------------- конец добавления ------------------------------*/
                    
                    if (count == 0) {
                        System.out.println("ВНИМАНИЕ! Ничего не добавлено.");
                    } else {
                        System.out.println("Слово " + wordFrom + " добавлено с количеством значений: " + count);
                    }
                    break;
                case 3: //показать все языки словаря
                    System.out.println(dictionary.printArray(dictionary.getLanguages()));
                    break;
                case 4: //показать все слова выбранного языка
                    Scanner sc5 = new Scanner(System.in);
                    System.out.print("Введите обозначение языка: ");
                    String lang = sc5.next();
                    if (dictionary.isLangExists(lang))
                        System.out.println(dictionary.printArray(dictionary.getWordsOf(lang)));
                    else
                        System.out.println(warnNoLang);
                    break;
                default:
                    System.out.println("ВНИМАНИЕ! Введено недопустимое значение. Попробуйте еще раз.");
            }
        }
        System.out.println("\nДо свидания!");
    }
}
