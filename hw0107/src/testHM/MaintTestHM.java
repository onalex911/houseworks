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

    }
}
