package hw2204;

public class stringMeth2 {
    //-------------------служебные методы---------------------------
    static void printArray(char[] arr) {
        System.out.print("'"); //маркер начала массива символов
        for (int i = 0; i < length(arr); i++) {
            System.out.print(arr[i]);
        }
        System.out.print("'"); //маркер конца массива символов
        System.out.println();
    }

    static void printArray(char[][] arr) {
        int outLen = length(arr);
        for (int i = 0; i < outLen; i++) {
            System.out.print("'"); //маркер начала массива символов
            for (int j = 0; j < length(arr[i]); j++) {
                System.out.print(arr[i][j]);
            }
            System.out.print("'"); //маркер конца массива символов
            if (i < outLen - 1)
                System.out.println(",");
        }
        System.out.println();
    }

    //возвращает длину массива
    static int length(char[] arr) {
        return arr.length;
    }

    //возвращает длину массива массивов
    static int length(char[][] arr) {
        return arr.length;
    }

    //возвращает копию исходного массива с добавленным в конец новым значением
    static char[] addToArray(char[] arr, char newValue) {
        int arrLen = length(arr);
        char[] outArr = new char[arrLen + 1];
        for (int i = 0; i < arrLen; i++) {
            outArr[i] = arr[i];
        }
        outArr[arrLen] = newValue;
        return outArr;

    }

    //возвращает копию исходного массива массивов с добавленным в конец новым массивом
    static char[][] addToArray(char[][] arr, char[] newValue) {
        int arrLen = length(arr);
//        int arrLen = length(arr[0]) == 0 ? 0 : length(arr);
        char[][] outArr = new char[arrLen + 1][];
        if (arrLen > 0) {
            for (int i = 0; i < arrLen; i++) {
                outArr[i] = arr[i];
            }
            outArr[arrLen] = newValue;
        } else {
            outArr[0] = newValue;
        }
        return outArr;
    }

    //преобразовать символ к верхнему регистру
    static char charToUpperCase(char c) {
        return c >= 0x61 && c <= 0x7A ? (char) (c - 0x20) : c;
    }
    //----------------конец служебных методов----------------------

    //concat
    static char[] concat(char[] a1, char[] a2) {
        char[] resArr = new char[length(a1) + length(a2)];
        int k = 0;
        for (int i = 0; i < length(resArr); i++)
            resArr[i] = i < length(a1) ? a1[i] : a2[k++];

        return resArr;
    }

    //equals
    static boolean equals(char[] a1, char[] a2) {
        if (length(a1) == length(a2)) {
            for (int i = 0; i < length(a1); i++) {
                if (a1[i] != a2[i])
                    return false;
            }
            return true;
        }
        return false;
    }

    //equalsIgnoreCase
    static boolean equalsIgnoreCase(char[] a1, char[] a2) {
        if (length(a1) == length(a2)) {
            for (int i = 0; i < length(a1); i++) {
                if (charToUpperCase(a1[i]) != charToUpperCase(a2[i]))
                    return false;
            }
            return true;
        }
        return false;
    }

    //startsWith
    static boolean startsWith(char[] c, char[] arr) {
        for (int i = 0; i < length(c); i++) {
            if (i < length(arr))
                if (c[i] != arr[i]) return false;
                else break;
        }
        return true;
    }

    //endsWith
    static boolean endsWith(char[] c, char[] arr) {
        int lenC = length(c);
        int lenArr = length(arr);

        if (lenC > lenArr) return false;
        for (int i = 0; i < lenC; i++) {
            if (c[i] != arr[lenArr - lenC + i])
                return false;
        }
        return true;
    }

    //subString
    static char[] subString(char[] arr, int startPos, int endPos) {
        if (startPos < 0 || endPos < startPos)
            return new char[]{};    //в случае неверно заданных параметров подстроки возвращаем пустой массив
        if (endPos >= length(arr))       //если в качестве конечного значения указано слишком большое число,
            endPos = length(arr) - 1;    //ограничиваем его длиной исходного массива
        char[] outArr = new char[endPos - startPos + 1];
        for (int i = 0, j = 0; i < length(arr); i++) {
            if (i >= startPos && i <= endPos)
                outArr[j++] = arr[i];
        }
        return outArr;
    }

    //compareTo
    static int compareTo(char[] sourceArr, char[] arrToCompare) {
        if (length(sourceArr) > length(arrToCompare))
            return -1;  //если исходный массив длиннее сравниваемого
        if (length(sourceArr) < length(arrToCompare))
            return 1;   //если исходный массив короче сравниваемого

        for (int i = 0; i < length(sourceArr); i++) {
            if (sourceArr[i] != arrToCompare[i])
                return sourceArr[i] - arrToCompare[i]; //если длмны массивов одинаковы, но сами массивы - разные
        }
        return 0; //если массивы одинаковы
    }

    //replace
    static char[] replace(char[] sourceArr, char oldVal, char newVal) {
        char[] outArr = new char[length(sourceArr)];
        for (int i = 0; i < length(sourceArr); i++) {
            outArr[i] = sourceArr[i] == oldVal ? newVal : sourceArr[i];
        }
        return outArr;
    }

    //replaceAll
    static char[] replaceAll(char[] sourceArr, char[] oldVal, char[] newVal) {
        char[] outArr = new char[]{};
        int oldValLen = length(oldVal);
        int newValLen = length(newVal);
        int i = 0;
        while (i < length(sourceArr)) {
            if (sourceArr[i] == oldVal[0]) {
                if (compareTo(subString(sourceArr, i, i + oldValLen - 1), oldVal) == 0) { //подстрока совпадает с искомым массивом
                    for (int j = 0; j < newValLen; j++) { //добавляем в выходной массив новую подстроку
                        outArr = addToArray(outArr, newVal[j]);
                    }
                    i += oldValLen; //пропускаем блок, равный искомому массиву
                    continue;
                }
            }
            outArr = addToArray(outArr, sourceArr[i]);
            i++;
        }
        return outArr;
    }

    //split
    static char[][] split(char[] sourceArr, char[] delimiter) {
        char[][] outArr = new char[][]{};
        char[] tmp = new char[]{};
        int delimLen = length(delimiter);
        int i = 0; //счетчик позиций массива
        int beg = 0;    //позиция начала блока
        while (i < length(sourceArr)) {
            if (sourceArr[i] == delimiter[0]) {
                if (compareTo(subString(sourceArr, i, i + delimLen - 1), delimiter) == 0) {
                    if (beg == i)
                        outArr = addToArray(outArr, new char[]{});
                    else {
                        tmp = subString(sourceArr, beg, i - 1);
                        outArr = addToArray(outArr, tmp);
                    }
                    i += delimLen;
                    beg = i--;
                }
            }
            i++;
        }
        outArr = addToArray(outArr, subString(sourceArr, beg, length(sourceArr) - 1)); //добавляем к массиву последнюю подстроку
        return outArr;
    }
    //реализовать свои методы на примере массива char

    public static void main(String[] args) {
        char[] myArray = {' ','A', ' ', 'R', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' ', 'n', 'a', ' ', 'l', 'a', 'p', 'u', ' ', 'A', 'z', 'o', 'r', 'a'};
        char[] myArray1 = {'A', ' ', 'R', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' '};
        char[] myArray11 = {'A', ' ', 'R', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' '};
        char[] myArray111 = {'A', ' ', 'R', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' ', 'n'};
        char[] myArray12 = {'a', ' ', 'r', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' ', 'n'};
        char[] myArray2 = {'n', 'a', ' ', 'l', 'a', 'p', 'u', ' ', 'A', 'z', 'o', 'r', 'a'};

        char[] myArray22 = {'A'};
        char[] myArray23 = {'A', 'z', 'o', 'r', 'a'};

        //демонстрация методов
        //concat           ->         String
        System.out.print("concat: ");
        printArray(concat(myArray1, myArray2));
        System.out.println("-------------------------------");
        //equals           ->         boolean
        System.out.print("equals: ");
        if (equals(myArray1, myArray11)) {
            System.out.println("EQUALS!");
        } else {
            System.out.println("NOT equals");
        }
        System.out.println("-------------------------------");
        //equalsIgnoreCase ->         boolean
        System.out.print("equalsIgnoreCase: ");
        if (equalsIgnoreCase(myArray111, myArray12)) {
            System.out.println("EQUALS!");
        } else {
            System.out.println("NOT equals");
        }
        System.out.println("-------------------------------");
        //startsWith       ->         boolean
        System.out.print("startsWith: ");
        if (startsWith(myArray22, myArray1)) {
            System.out.println("STARTS!");
        } else {
            System.out.println("Not starts");
        }
        System.out.println("-------------------------------");
        //endsWith         ->         boolean
        System.out.print("endsWith: ");
        if (endsWith(myArray23, myArray)) {
            System.out.println("ENDS!");
        } else {
            System.out.println("Not ends");
        }
        System.out.println("-------------------------------");
        //substring        ->         String
        System.out.print("substring: ");
        printArray(subString(myArray, 10, 17));
        System.out.println("-------------------------------");
        //compareTo        ->         int
        System.out.print("compareTo: ");
        System.out.println(compareTo(myArray1, myArray11));
        System.out.println("-------------------------------");
        //replace          ->         String
        System.out.print("replace: ");
        printArray(replace(myArray, ' ', '_'));
        System.out.println("-------------------------------");
        //replaceAll       ->         String
        System.out.print("replaceAll: ");
        String test1 = "мама мыла раму";
        char[] testString1 = test1.toCharArray();
        char[] oldStr1 = ("ма").toCharArray();
        char[] newStr1 = ("па").toCharArray();
        char[] oldStr2 = ("ла").toCharArray();
        char[] newStr2 = ("л").toCharArray();
        printArray(
                replaceAll(
                        replaceAll(
                                testString1, oldStr1, newStr1
                        ), oldStr2, newStr2
                )
        );
        System.out.println("-------------------------------");
        //split
        String test2 = "Петров; Иван; Николаевич; 1982; муж.";
        char[] testString2 = test2.toCharArray();
        System.out.print("split: ");
        char[][] testArr = new char[][]{};

        testArr = split(testString2, ("; ").toCharArray());
        printArray(testArr);
        System.out.println("Длина разделенного массива = " + length(testArr));
        System.out.println("-------------------------------");
    }
}
