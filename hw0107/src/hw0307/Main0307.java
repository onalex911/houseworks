package hw0307;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main0307 {

    public static void main(String[] args) {
        String warnNoLang = "ВНИМАНИЕ! Нет такого языка в словаре!";
        String warnNoWord = "ВНИМАНИЕ! Нет такого слова в словаре!";
        HashMap<String, ArrayList<String>> ruWords = new HashMap<>();
        HashMap<String, HashMap<String, ArrayList<String>>> transTo = new HashMap<>();
        HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>> transFrom = new HashMap<>();

        ArrayList<String> ruMean1 = new ArrayList<>();
        ruMean1.add("идти");
        ruMean1.add("ехать");
        ruMean1.add("двигаться");
        ruWords.put("go", ruMean1);

        ArrayList<String> ruMean2 = new ArrayList<>();
        ruMean2.add("мочь");
        ruMean2.add("уметь");
        ruMean2.add("банка");
        ruWords.put("can", ruMean2);

        ArrayList<String> ruMean3 = new ArrayList<>();
        ruMean3.add("ехать");
        ruMean3.add("везти");
        ruMean3.add("вести");
        ruWords.put("drive", ruMean3);

        transTo.put("ru", ruWords);
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
//            System.out.printf("\nВсего в словаре слов: %d, значений: %d\n\n", numWords, dictInfo.getNumMeanigs());
            System.out.println();
            if (numWords > 0)
                System.out.println("1 - перевод слов");
            System.out.println("2 - добавить слово в словарь");
            System.out.println("3 - показать все языки словаря");
            System.out.println("4 - показать все слова в словаре одного языка");
            System.out.println("0 - выход");
            System.out.print("Выберите действие: ");
            scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == 0) break;
            switch (resp) {
                case 1: //перевести слово
                case 2: //добавить новое слово в словарь
                    if (numWords == 0) {
                        System.out.println("Пока в словаре нет введенных слов");
                        continue;
                    }

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
                    int resultOfAdd = dictionary.add(fromLang, toLang, wordFrom);
                    if (resultOfAdd < 0) {
                        System.out.println("Слово " + wordFrom + " уже есть в словаре. Введите другое.");
                    } else if (resultOfAdd == 0) {
                        System.out.println("ВНИМАНИЕ! Ничего не добавлено.");
                    } else {
                        System.out.println("Слово " + wordFrom + " добавлено с количеством значений: " + resultOfAdd);
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
