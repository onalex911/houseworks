package hw1504;

public class StringMeth {
    static void printArray(char[] arr) {
        System.out.print("'"); //маркер начала массива символов
        for (int i = 0; i < length(arr); i++) {
            System.out.print(arr[i]);
        }
        System.out.print("'"); //маркер конца массива символов
        System.out.println();
    }
//    в случаи ошибки возвращать (-1,@)

    //        String name = "FaridFF"; // char[] arr
//        System.out.println("Size         : " + name.length()); // int
    static int length(char[] arr) {
        return arr.length;
    }

    //        System.out.println("Index        : " + name.charAt(0));//char
    static char charAt(char[] arr, int ind) {
        int arrLength = length(arr);
        if (arrLength == 0 || ind < 0 || ind > arrLength - 1) return '@';
        return arr[ind];
    }

    //        System.out.println("Lower        : " + name.toLowerCase());//char[]
    static char[] toLowerCase(char[] arr) {
        char[] outArr = new char[length(arr)];
        for (int i = 0; i < length(arr); i++)
            outArr[i] = arr[i] >= 0x41 && arr[i] <= 0x5A ? (char)(arr[i] + 0x20) : arr[i];

        return outArr;
    }

    //        System.out.println("Upper        : " + name.toUpperCase()); +char[]
    static char[] toUpperCase(char[] arr) {
        char[] outArr = new char[length(arr)];
        for (int i = 0; i < length(arr); i++)
            outArr[i] = arr[i] >= 0x61 && arr[i] <= 0x7A ? (char)(arr[i] - 0x20) : arr[i];

        return outArr;
    }

    //        System.out.println("Trim         : " + name.trim());char[]
    static char[] trim(char[] arr) {
        int arrLength = length(arr);
        int start = arrLength;
        int end = 0;
        for (int i = 0; i < arrLength; i++) {
            if (arr[i] != 0x20) {
                start = i;
                break;
            }
        }
        for (int i = arrLength - 1; i >= 0; i--) {
            if (arr[i] != 0x20) {
                end = i;
                break;
            }
        }
        if (start <= end) {
            char[] outArr = new char[end - start + 1];
            for (int i = 0; i <= end - start; i++) {
                outArr[i] = arr[start + i];
            }
            return outArr;
        }else{
            char[] outArr = {'@'};
            return outArr;
        }
    }

    //        System.out.println("IndexOf      : " + name.indexOf("F"));int
    static int indexOf(char symb, char[] arr) {
        for (int i = 0; i < length(arr); i++) {
            if (arr[i] == symb)
                return i;
        }
        return -1;
    }

    //        System.out.println("LastIndexOf  : " + name.lastIndexOf("F"));int
    static int lastIndexOf(char symb, char[] arr) {
        for (int i = length(arr) - 1; i >= 0; i--) {
            if (arr[i] == symb)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        char[] myArray = {'A', ' ', 'R', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' ', 'n', 'a', ' ', 'l', 'a', 'p', 'u', ' ', 'A', 'z', 'o', 'r', 'a'};
        System.out.println("length(): " + length(myArray));
        System.out.println("----------------------------");
        System.out.println("charAt(): " + charAt(myArray,13));
        System.out.println("----------------------------");
        System.out.print("toLowerCase(): ");
        printArray(toLowerCase(myArray));
        System.out.println("----------------------------");
        System.out.print("toUpperCase(): ");
        printArray(toUpperCase(myArray));
        System.out.println("----------------------------");
        char[] myArray2 = {' ', ' ', ' ', ' ', 'A', ' ', 'R', 'o', 'z', 'a', ' ', ' ', ' ', ' ', ' ', ' '};
        char[] myArray3 = {'A', ' ', 'R', 'o', 'z', 'a', ' ', ' ', ' ', ' ', ' ', ' '};
        char[] myArray4 = {' ', ' ', ' ', ' ', 'A', ' ', 'R', 'o', 'z', 'a'};
        char[] myArray5 = {' ', ' ', ' ', ' ', ' ', 'A', ' ', ' ', ' ', ' ', ' ', ' '};
        char[] myArray6 = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        System.out.println("trim(): ");
        printArray(trim(myArray));
        printArray(trim(myArray2));
        printArray(trim(myArray3));
        printArray(trim(myArray4));
        printArray(trim(myArray5));
        printArray(trim(myArray6));
        System.out.println("----------------------------");
        System.out.println("indexOf(): " + indexOf('z',myArray));
        System.out.println("----------------------------");
        System.out.println("lastIndexOf(): "+ lastIndexOf('z',myArray));
        System.out.println("----------------------------");


    }
}
