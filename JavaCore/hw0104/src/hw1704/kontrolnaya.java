package hw1704;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class kontrolnaya {
    static int[] join(int[] a1, int[] a2) {
        int newLen = a1.length + a2.length;
        int[] out = new int[newLen];
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i < newLen; i++) {
            if (c1 < a1.length && c2 < a2.length)
                out[i] = a1[c1] < a2[c2] ? a1[c1++] : a2[c2++];
            else
                out[i] = c1 == a1.length ? a2[c2++] : a1[c1++];
        }
        return out;
    }

    static String drawRomeBlock(int val,int level){
        String output = "";
        int lim;
        String dec,fiv,un;
        switch(level){
            case 1: un = "I"; fiv = "V"; dec="X";
                break;
            case 2: un = "X"; fiv = "L"; dec="C";
                break;
            case 3: un = "C"; fiv = "D"; dec="M";
                break;
            default: return "-";
        }
        if(val == 9)
            output += un + dec;
        else if(val == 4)
            output += un + fiv;
        else {
            if (val < 5) {
                lim = val;
            } else {
                output += fiv;
                lim = val - 5;
            }
            for (int i = 0; i < lim; i++)
                output += un;
        }
        return output;
    }
    static String getRome(int val){
        String output = "";
        int lim;
        for (int i = 0; i < val/1000; i++)
            output += "M";
        for (int i = 3; i >=1 ; i--)
            output += drawRomeBlock((int)(val%Math.pow(10,i)/Math.pow(10,i-1)),i);

        return output;
    }

    public static void main(String[] args) {
        int[] arr1 = {-5, 0 ,2,6,10,111,345,444};
        int[] arr2 = {-1, 0 ,1,1,1,300,301,302,303,304,344,346,347,404,420,500,600,700,800};
        int[] arr3 = join(arr1, arr2);
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }

        System.out.println("\n--------------------------------------------");
        //        2) Пользователь вводит число (1 - 3999)
//
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите целое число от 1 до 3999: ");
        int val = sc.nextInt();
        if(val <1 || val >=4000)
            System.out.println("Введено недопустимое число!");
        else
            System.out.println("Римское число: "+getRome(val));
    }
}
