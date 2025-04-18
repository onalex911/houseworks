package Threads1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
public class TextProcessor {
    private String text;

    public TextProcessor(String filePath) {
        readTextFromFile(filePath);
    }

    private void readTextFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.text = content.toString();
    }

    public void process() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Счетчики
        AtomicInteger letterCount = new AtomicInteger(0);
        AtomicInteger digitCount = new AtomicInteger(0);
        AtomicInteger vowelCount = new AtomicInteger(0);
        AtomicInteger consonantCount = new AtomicInteger(0);
        AtomicInteger wordCount = new AtomicInteger(0);

        // Задачи для подсчета
        executor.execute(() -> letterCount.set(countLetters(text)));
        executor.execute(() -> digitCount.set(countDigits(text)));
        executor.execute(() -> vowelCount.set(countVowels(text)));
        executor.execute(() -> consonantCount.set(countConsonants(text)));
        executor.execute(() -> wordCount.set(countWords(text)));

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("Количество букв: " + letterCount.get());
        System.out.println("Количество цифр: " + digitCount.get());
        System.out.println("Количество гласных букв: " + vowelCount.get());
        System.out.println("Количество согласных букв: " + consonantCount.get());
        System.out.println("Количество слов: " + wordCount.get());
    }

    private int countLetters(String text) {
        return (int) text.chars().filter(Character::isLetter).count();
    }

    private int countDigits(String text) {
        return (int) text.chars().filter(Character::isDigit).count();
    }

    private int countVowels(String text) {
        return (int) text.chars().filter(c -> "аеёиоуыэюяАЕЁИОУЫЭЮЯaeiouyAEIOUY".indexOf(c) != -1).count();
    }

    private int countConsonants(String text) {
        return (int) text.chars().filter(c -> "бвгдёжзйклмнпрстфхцчшщБВГДЁЗЙКЛМНПРСТФХЦЧШЩbcdfghjklmopqrstvwxzBCDFGHJKLMNPQRSTVWXZ".indexOf(c) != -1).count();
    }

    private int countWords(String text) {
        return text.split("\\s+").length;
    }

    public static void main(String[] args) {
        long startTime =  System.nanoTime();
        TextProcessor processor = new TextProcessor("src\\qqq\\lorem2.txt");
        processor.process();
        long endTime =  System.nanoTime();
        System.out.println((endTime-startTime)/1_000_000);
    }
}
