package FileTest;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class MainFileTest {
    /*static boolean isMatchesAnyOne(String src, String mask) {
        if (src.length() == mask.length()) {
            //маска обязательно содержит знак "_"
            String[] maskParts = mask.split("_");
            int pos = 0;
            boolean flag = false;
            for (String part : maskParts) {
                if (part.isEmpty()) {
                    pos++;
                    continue;
                }
                if (src.indexOf(part) == pos) {
                    flag = true;
                    pos += part.length() + 1;
                } else {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
        return false;
    }*/
    static ArrayList<Integer, String> getMaskHash(String mask) {
        // ключ = 1, если после части маски любой единичный символ ('_')
        // 2 - если после данной части маски идет серия любых символов ('*')
        // 0 - если часть последняя в маске
        ArrayList<Integer, String> out = new HashMap<>();
        String part = "";
        char[] maskChars = mask.toCharArray();
        int type = 0;
        for (int i = 0; i < maskChars.length; ) {
            if (maskChars[i] == '_' || maskChars[i] == '*') {
                type = maskChars[i] == '_' ? 1 : 2;
                out.put(type, part);
                part = "";
                type = 0;
                i += 2;
            } else {
                part += maskChars[i++];
            }
            if (i >= maskChars.length) {
                out.put(type, part);
                break;
            }
        }
        return out;
    }

    static boolean isMatchesMask(String src, String mask) {
        boolean flag = false;
        HashMap<Integer, String> maskParts = getMaskHash(mask);
        int start = 0;
        for(Integer key:maskParts.keySet()){
            String partText = maskParts.get(key);
            if(!partText.isEmpty() && src.contains(partText)) {
                if (key == 1) {

                }
            }
        }
        return flag;

    }

    public static void main(String[] args) {
/*//        File cur = new File(".");
//        System.out.println(cur.getAbsolutePath());
//        String src = "леша на полке клопа нашел";
        String src = "abcdefWQ";
        String mask = "___def__";
        String src1 = "abc0";
        String mask1 = "abc_";
        String src2 = "_abc";
        String mask2 = "_abc";
        String src3 = "aAbBcCdQQ";
        String mask3 = "a_b_c_d_";

//        System.out.println(isMatchesAnyOne(src,mask));
//        System.out.println(isMatchesAnyOne(src1,mask1));
//        System.out.println(isMatchesAnyOne(src2,mask2));
//        System.out.println(isMatchesAnyOne(src3,mask3));

        String src4 = "abc0123456";
        String mask4 = "abc*";
//        String src4 = "abcABCdef";
//        String mask4 = "abc_def_";*/
        while (true) {
            Scanner scn1 = new Scanner(System.in);
            Scanner scn2 = new Scanner(System.in);
            System.out.print("Введите маску: ");
            String src = scn1.next();
            if (src.equals("0")) break;
            System.out.print("Введите строку: ");
            String mask = scn2.next();
            if (mask.equals("0")) break;
            boolean res1 = isMatchesAnyOne(src, mask);
            System.out.println(res1);
//            boolean res2 = isMatchesAnyMany(src,mask);
        }

    }
}
