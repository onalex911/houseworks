package testHM;

import java.util.ArrayList;
import java.util.HashMap;

public class MaintTestHM {
    public static void printHM(HashMap<String,ArrayList<String>> hm){
        for(String key:hm.keySet()){
            if(hm.get(key).size() > 0) {
                System.out.println(key + ": ");
                for (String val : hm.get(key))
                    System.out.print(val + ", ");
                System.out.println();
            }else System.out.println("Нет значений для языка "+key);
        }
        System.out.println();
    }

    static<K,T> void printKeys(HashMap<K,T> hm){
        for(K key:hm.keySet()){
            System.out.println(key);
            T t = hm.get(key);
            System.out.println(t);
//            if(t.getClass().getName().equals("HashMap")){
//                printKeys(t);
//            }
        }
    }
    public static void main(String[] args) {
        ArrayList<String> mean1 = new ArrayList<>();
        ArrayList<String> mean3 = new ArrayList<>();

        mean1.add("first");
        mean1.add("second");
        mean1.add("third");
        ArrayList<String> mean2 = new ArrayList<>();
        mean2.add("pervyi");
        mean2.add("vtoroy");
        mean2.add("tretiy");
        HashMap<String, ArrayList<String>> wordMean = new HashMap<>();
        wordMean.put("en",mean1);
        wordMean.put("ru",mean2);

        printKeys(wordMean);
        //printHM(wordMean);

        /*wordMean.put("jp",mean3);
        mean3.add("ichi");
        mean3.add("ni");
        mean3.add("san");
        //printHM(wordMean);

        HashMap<String,HashMap<String,ArrayList<String>>> word = new HashMap<>();
        word.put("schet",wordMean);
        printKeys(word);*/
    }
}
