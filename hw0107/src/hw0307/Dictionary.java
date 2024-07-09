package hw0307;

import java.util.*;

public class Dictionary {
    private int numLangsFrom;
    private int numLangsTo;
    private int numWords;
    private int numMeanigs;
    private HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>> langsFrom;

    {
        numLangsFrom = 0;
        numLangsTo = 0;
        numWords = 0;
        numMeanigs = 0;
    }

    public Dictionary(HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>> langsFrom) {
        this.langsFrom = langsFrom;
        this.getInfo();
    }

    public void getInfo() {
        if (!langsFrom.keySet().isEmpty()) {
            for (String langFrom : langsFrom.keySet()) { //перебираем языки исходные
                var langsTo = langsFrom.get(langFrom);
                //if(!langsTo.keySet().isEmpty()) {
                for (String langTo : langsTo.keySet()) { //перебираем языки целевые
                    var words = langsTo.get(langTo);
                    //if(!words.keySet().isEmpty()){
                    for (String word : words.keySet()) { //перебираем исходные слова в целевом языке
                        ArrayList<String> meanings = words.get(word);
                        numMeanigs += meanings.size();
                        numWords++;
                    }
                    //}
                    numLangsTo++;
                }
                //}
                numLangsFrom++;
            }
        }
    }

    public int getNumLangsFrom() {
        return numLangsFrom;
    }

    public int getNumLangsTo() {
        return numLangsTo;
    }

    public int getNumWords() {
        return numWords;
    }

    public int getNumMeanigs() {
        return numMeanigs;
    }

    private boolean isFrom(String lang) {
        return langsFrom.containsKey(lang);
    }

    private boolean isTo(String lang) {
        for (String lf : langsFrom.keySet()) {
            if (langsFrom.get(lf).containsKey(lang))
                return true;
        }
        return false;
    }

    public boolean isLangExists(String lang) {
        return isFrom(lang) || isTo(lang);
    }

    public boolean isWordExists(String lang, String word) {
        //ArrayList<String> words = getWordsOf(lang);
        for (String curWord : getWordsOf(lang)) {
            if (curWord.equals(word))
                return true;
        }
        return false;
    }

    public ArrayList<String> getMeanings(String from, String to, String word) {
        ArrayList<String> out = new ArrayList<>();
        if (isFrom(from) && isTo(to)) {
            out = langsFrom.get(from).get(to).get(word);
        } else if (isTo(from) && isFrom(to)) {
            for (String realTo : langsFrom.get(to).get(from).keySet()) {
                ArrayList<String> meanings = langsFrom.get(to).get(from).get(realTo);
                for (String meaning : meanings) {
                    if (meaning.equals(word)) {
                        out.add(realTo);
                        break;
                    }
                }
            }
        }
        return out;
    }

    public ArrayList<String> getLanguages() {
        ArrayList<String> out = new ArrayList<>();
        for (String lang1 : langsFrom.keySet()) {
            out.add(lang1);
            for (String lang2 : langsFrom.get(lang1).keySet()) {
                out.add(lang2);
            }
        }
        return out;
    }

    public ArrayList<String> getWordsOf(String lang) {
        HashSet<String> tmp = new HashSet<>(); //защита от повторения слов, когда значению во "внутреннем" словаре соответствует несколько слов во "внешнем" словаре
        ArrayList<String> out = new ArrayList<>();

        for (String lf : langsFrom.keySet()) {
            var lt = langsFrom.get(lf);
            for (String langTo : lt.keySet()) {
                for (String word : lt.get(langTo).keySet()) {
                    if (isFrom(lang)) {
                        tmp.add(word);
                    } else if (isTo(lang)) {
                        for (String meaning : lt.get(langTo).get(word))
                            tmp.add(meaning);
                    }
                }
            }
        }
        for (String word : tmp)
            out.add(word);

        return out;
    }

    public String printArray(ArrayList<String> arr) {
        String out = "";
        for (String word : arr)
            out += word + ", ";

        return out.substring(0, out.length() - 2) + "\n----------------------------------------------";
    }

    public int add(String from, String to, String wordFrom) {
        boolean ext2inner = isFrom(from) && isTo(to);
        var words = getWordsOf(from);
        for (String word : words) {
            if (word.equals(wordFrom)) return -1;
        }
        Scanner scn;
        String newMeaning = "";
        int count = 0;
        ArrayList<String> meanings = new ArrayList<>();
        while (true) {
            System.out.print("Введите значение для слова " + wordFrom + " (0 - выход): ");
            scn = new Scanner(System.in);
            newMeaning = scn.next();
            if (newMeaning.equals("0")) {
                if (count > 0) {
                    if (ext2inner)
                        langsFrom.get(from).get(to).put(wordFrom, meanings);
                    else {
                        for (String meaning : meanings) {
                            ArrayList<String> newWord = new ArrayList<>();
                            newWord.add(wordFrom);
                            langsFrom.get(to).get(from).put(meaning, newWord);
                        }
                    }
                }
                return count;
            }
            if (newMeaning.trim().isEmpty()) continue;
            if (meanings.contains(newMeaning)) {
                System.out.println("ВНИМАНИЕ! Значение " + newMeaning + " уже имеется для слова " + wordFrom + "! Введите новое значение.");
                continue;
            }
            meanings.add(newMeaning);
            count++;
        }
    }
}
