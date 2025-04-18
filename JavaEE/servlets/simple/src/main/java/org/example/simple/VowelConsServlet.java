package org.example.simple;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "VowelConsServlet", value = "/vow-cons-servlet")
public class VowelConsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Безопасное чтение JSON из тела запроса
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        Result result = new Result();
        Gson gson = new Gson();
        try {
            result.setOverall(sb.toString().length());
            UserInputVC userInputVC = gson.fromJson(sb.toString(), UserInputVC.class);
            String text = userInputVC.getText();
            long numVowels = countLettersByType(text,vowels);
            result.setVowels(numVowels,getUniqueLetters(text,vowels));
            long numConsonants = countLettersByType(text,consonants);
            result.setConsonants(numConsonants,getUniqueLetters(text,consonants));
            long numPunctuations = countLettersByType(text,punctuation);
            result.setPunctuations(numPunctuations,getUniqueLetters(text,punctuation));

            // Установка ответа в формате JSON
            response.setContentType("application/json");

        } catch (NumberFormatException e) {
            result.setError("Wrong number format");
        } catch (Exception e) {
            result.setError(e.getMessage());
        } finally {

            PrintWriter out = response.getWriter();
            String dataForOut = "{\"error\":\"empty string\"}";
            try {
                dataForOut = gson.toJson(result);
            } catch (Exception e) {
                result.setError(e.getMessage());
            } finally {
                out.print(dataForOut);
                out.flush();
            }
        }
    }

    class Result {
        private NumLetters vowels;
        private NumLetters consonants;
        private NumLetters punctuations;
        private String error;
        private long overall;


        public void setVowels(long num,Set<Character> letters) {
            this.vowels = new NumLetters(num, letters);
        }

        public void setConsonants(long num,Set<Character> letters) {
            this.consonants = new NumLetters(num, letters);
        }
        public void setPunctuations(long num,Set<Character> letters) {
            this.punctuations = new NumLetters(num, letters);
        }

        public void setError(String error) {
            this.error = error;
        }

        public void setOverall(long overall) {
            this.overall = overall;
        }
    }

    static class NumLetters{
        long num;
        String letters;

        public NumLetters(long num, Set<Character> lettersArr) {
            this.num = num;
            StringBuilder letters = new StringBuilder();
            long i = 0;
            for (Character ch: lettersArr) {
                letters.append(ch.toString() + " ");
//                if(++i < lettersArr.size() -1)
//                    letters.append(", ");

            }
            this.letters = letters.toString();
        }
    }

    public static String vowels = "аеёиоуыэюяaeiou";
    public static String punctuation = ",.;:!?\"'«»-–—()[]{}\\/*+`~@#$%^&<>";
    public static String consonants = "бвгджзйклмнпрстфхцчшщьъюbcdfghjklmnpqrstvwxyz";

    public static long countLettersByType(String str, String letterSet) {
        // Используем Stream API для подсчета гласных
        return str.toLowerCase().chars()
                .filter(ch -> letterSet.indexOf(ch) != -1)
                .count();
    }
    public static Set<Character> getUniqueLetters(String str, String letterSet) {
        // Используем Stream API для извлечения уникальных гласных

        Set<Character> out = str.toLowerCase().chars()
                .filter(ch -> letterSet.indexOf(ch) != -1)
                //.sorted((a,b) -> a - b)
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        return out;
    }
}
