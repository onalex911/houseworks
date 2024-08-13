package FileTest;

import java.io.File;

import java.util.ArrayList;
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
    static ArrayList<MaskParts> getMaskArray(String mask) {
        // ключ = 1, если после части маски любой единичный символ ('_')
        // 2 - если после данной части маски идет серия любых символов ('*')
        // 0 - если часть последняя в маске
        ArrayList<MaskParts> out = new ArrayList<>();
        String part = "";
        char[] maskChars = mask.toCharArray();
        int type = 0;
        for (int i = 0; i < maskChars.length; i++) {
            if (maskChars[i] == '_' || maskChars[i] == '*') {
                type = maskChars[i] == '_' ? 1 : 2;
                out.add(new MaskParts(type, part));
                part = "";
                type = 0;
                //i += 2;
            } else {
                part += maskChars[i];
            }
        }
        if (!part.isEmpty())
            out.add(new MaskParts(type, part));
        return out;
    }

    static boolean isMatchesMask(String src, String mask) {
        if (mask.equals("*")) return true;
        if (mask.equals("_") && src.length() == 1) return true;
        if (src.length() < mask.length() && !mask.endsWith("*")) return false;
        boolean flag = false;
        int good = 0;
        ArrayList<MaskParts> maskParts = getMaskArray(mask);
        int start = 0;
        boolean isExit = false;

        for (int i = 0; i < maskParts.size(); i++) {
            int curType = maskParts.get(i).getType();
            int nextType = i < maskParts.size() - 1 ? maskParts.get(i + 1).getType() : -1;
            String curPart = maskParts.get(i).getPart();
            String nextPart = i < maskParts.size() - 1 ? maskParts.get(i + 1).getPart() : "";
            switch (curType) {
                case 0:
                    if (src.substring(start, src.length()).equals(curPart))
                        good++;
                    isExit = true;
                    break;
                case 1:
                    String substr = "";
                    if (!curPart.isEmpty()) {
                        if (src.substring(start, start + curPart.length()).equals(curPart)) {
                            int beg = start + curPart.length() + 1;
                            substr = src.substring(beg, beg + nextPart.length());
                            if (substr.equals(nextPart)) {
                                good++;
                                start = beg;
                            }
                        }
                    } else {
                        substr = src.substring(start + 1, start + 1 + nextPart.length());
                        if (substr.equals(nextPart)) {
                            good++;
                            start++;
                        }
                    }
                    break;
                case 2:
                    if (!curPart.isEmpty()) {
                        if (src.substring(start, start+curPart.length()).equals(curPart)) {
                            if (nextType >= 0 && src.indexOf(nextPart) > curPart.length()) {
                                good++;
                                start = src.indexOf(nextPart);
                            }else{
//                                if(!src.substring(start,src.length()).isEmpty()){
                                    good++;
                                    isExit = true;
//                                }
                            }
                        }
                    } else {
                        if (src.indexOf(nextPart) > i + 1) {
                            good++;
                            start = src.indexOf(nextPart);
                        }
                    }
                    break;
            }
            if (isExit) break;
        }
        return maskParts.size() == good;

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
            String mask = scn1.next();
            if (mask.equals("0")) break;
            ArrayList<MaskParts> maskArray = getMaskArray(mask);
            for (MaskParts mp : maskArray)
                System.out.println(mp.toString());
            System.out.print("Введите строку: ");
            String src = scn2.next();
            if (src.equals("0")) break;

            boolean res1 = isMatchesMask(src, mask);
            System.out.println(res1);
            //boolean res2 = isMatchesAnyMany(src,mask);
        }

    }
}
