package TestScanner;

import java.util.Scanner;

public class TestScanner {
    public static void main(String[] args) {
        int userVal;
        Scanner sc = new Scanner(System.in);

//        while (true) {
//            System.out.print("\nВведите целое число: ");
//            try {
//                userVal = sc.nextInt();
//                if (userVal == 0)
//                    break;
//                else
//                    System.out.println(userVal * userVal);
//
//            } catch (Exception ex) {
//                System.out.println("\nОшибка! " + ex.toString() + "\n");
//            }
//        }


//        while (true) {
//            System.out.print("\nВведите целое число: ");
//            if(sc.hasNextInt()) {
//                userVal = sc.nextInt();
//                if (userVal == 0) {
//                    break;
//                }
//                else {
//                    System.out.println(userVal * userVal);
//                }
//            } else{
//                System.out.println("\nОШИБКА! Требуется ввести число\n");
//            }
//        }
//
//
        Scanner sc1;
        while (true) {
            sc1 = new Scanner(System.in);
            System.out.print("\nВведите целое число: ");
            if(sc1.hasNextInt()) {
                userVal = sc1.nextInt();
                if (userVal == 0) {
                    break;
                }
                else {
                    System.out.println(userVal * userVal);
                }
            } else{
                System.out.println("\nОШИБКА! Требуется ввести число\n");
                //sc1.reset();
            }
        }
    }
}
