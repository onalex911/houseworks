package LC17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MainLC17 {
    static List<String> getCombinations(List<String> first,List<String> second){
        List<String> out = new ArrayList<>();
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                out.add(first.get(i)+second.get(j));
            }
        }
        return out;
    }
    public static void main(String[] args) {
        HashMap<Character,List<String>> buttons = new HashMap<>();
        List<String> tmp2 = new ArrayList<>();
        tmp2.add("a");tmp2.add("b");tmp2.add("c");
        buttons.put('2',tmp2);
        List<String> tmp3 = new ArrayList<>();
        tmp3.add("d");tmp3.add("e");tmp3.add("f");
        buttons.put('3',tmp3);
        List<String> tmp4 = new ArrayList<>();
        tmp4.add("g");tmp4.add("h");tmp4.add("i");
        buttons.put('4',tmp4);
        List<String> tmp5 = new ArrayList<>();
        tmp5.add("j");tmp5.add("k");tmp5.add("l");
        buttons.put('5',tmp5);
        List<String> tmp6 = new ArrayList<>();
        tmp6.add("m");tmp6.add("n");tmp6.add("o");
        buttons.put('6',tmp6);
        List<String> tmp7 = new ArrayList<>();
        tmp7.add("p");tmp7.add("q");tmp7.add("r");tmp7.add("s");
        buttons.put('7',tmp7);
        List<String> tmp8 = new ArrayList<>();
        tmp8.add("t");tmp8.add("u");tmp8.add("v");
        buttons.put('8',tmp8);
        List<String> tmp9 = new ArrayList<>();
        tmp9.add("w");tmp9.add("x");tmp9.add("y");tmp9.add("z");
        buttons.put('9',tmp9);

        Scanner scn = new Scanner(System.in);
        System.out.print("Введите комбинацию из не более, чем 4-х знаков от 2 до 9: ");
        char[] comb = scn.next().toCharArray();
        List<String> result = new ArrayList<>();
        for (int i = 0,k=0; i < comb.length; i++) {
            if(comb[i]-48 > 1 && comb[i]-48 <=9) {
                if (k == 0) {
                    result = buttons.get(comb[i]);
                } else {
                    result = getCombinations(result, buttons.get(comb[i]));
                }
                k++;
            }
        }
        result.forEach(s -> System.out.print(s+" "));
    }
}
