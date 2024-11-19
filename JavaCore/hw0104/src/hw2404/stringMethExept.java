package hw2404;

public class stringMethExept {
    //-------------------служебные методы---------------------------
    static void printArray(char[] arr) {
        System.out.print("'"); //маркер начала массива символов
        //for (int i = 0; i < length(arr); i++) {
        int i = 0;
        while (true) {
            try {
                System.out.print(arr[i++]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                break;
            }
        }
        //}
        System.out.print("'"); //маркер конца массива символов
        System.out.println();
    }

    static void printArray(char[][] arr) {
        int outLen = length(arr);
        int i, j;
        i = 0;
        while (true) {
            try {
                j = 0;
                char[] innerArr = arr[i];
                System.out.print("'"); //маркер начала массива символов
                while (true) {
                    try {
                        System.out.print(innerArr[j++]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        break;
                    }
                }
                System.out.print("'"); //маркер конца массива символов
                if (i < outLen - 1) {
                    System.out.println(",");
                }
                i++;
            } catch (ArrayIndexOutOfBoundsException ex) {
                break;
            }
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
        int i = 0;
        if (arrLen > 0) {
            while (true) {
                try {
                    outArr[i] = arr[i++];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    outArr[--i] = newValue;
                    break;
                }
            }
        } else {
            outArr[0] = newValue;
        }
        return outArr;
    }

    //возвращает копию исходного массива массивов с добавленным в конец новым массивом
    static char[][] addToArray(char[][] arr, char[] newValue) {
        int arrLen = length(arr);
//        int arrLen = length(arr[0]) == 0 ? 0 : length(arr);
        char[][] outArr = new char[arrLen + 1][];
        int i = 0;
        if (arrLen > 0) {
            while (true) {
                try {
                    outArr[i] = arr[i++];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    outArr[--i] = newValue;
                    break;
                }
            }
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
        int a1Len = length(a1);

        for (int i = 0; i < length(resArr); i++)
            resArr[i] = i < a1Len ? a1[i] : a2[i - a1Len];

        return resArr;
    }

    //equals
    static boolean equals(char[] a1, char[] a2) {
        if (a1 == a2)
            return true;
        if (length(a1) == length(a2)) {
            int i = 0;
            while (true) {
                try {
                    if (a1[i] != a2[i])
                        return false;
                    i++;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return true;
                }
            }
        }
        return false;
    }

    //equalsIgnoreCase
    static boolean equalsIgnoreCase(char[] a1, char[] a2) {
        if (length(a1) == length(a2)) {
            int i = 0;
            while (true) {
                try {
                    if (charToUpperCase(a1[i]) != charToUpperCase(a2[i]))
                        return false;
                    i++;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return true;
                }
            }
        }
        return false;
    }

    //startsWith
    static boolean startsWith(char[] c, char[] arr) throws Exception {
//        if (length(c) > length(arr))
//            return false;
        try {
            return equals(subString(arr, 0, length(c) - 1), c);
        } catch (Exception ex) {
            throw new Exception(ex.toString());
        }
    }

    //endsWith
    static boolean endsWith(char[] c, char[] arr) throws Exception {
//        if (length(c) > length(arr))
//            return false;
        try {
            return equals(subString(arr, length(arr) - length(c), length(arr) - 1), c);
        } catch (Exception ex) {
            throw new Exception(ex.toString());
        }
    }

    //subString
    static char[] subString(char[] arr, int startPos, int endPos) throws Exception {
        if (startPos < 0 || endPos < startPos)
            throw new Exception("Неверно заданы параметры массива!");    //в случае неверно заданных параметров подстроки бросаем исключение
        if (endPos >= length(arr))       //если в качестве конечного значения указано слишком большое число,
            endPos = length(arr) - 1;    //ограничиваем его длиной исходного массива
        char[] outArr = new char[endPos - startPos + 1]; //создаем выходной массив необходимого размера
        for (int i = 0, j = 0; i <= endPos; i++) {
            if (i >= startPos)
                outArr[j++] = arr[i];
        }
        return outArr;
    }

    //compareTo
    static int compareTo(char[] sourceArr, char[] arrToCompare) {
        if(sourceArr == arrToCompare) //если массивы ссылаются на один и тот же объект
            return 0;
        if (length(sourceArr) > length(arrToCompare))
            return -1;  //если исходный массив длиннее сравниваемого
        if (length(sourceArr) < length(arrToCompare))
            return 1;   //если исходный массив короче сравниваемого

        int i = 0;
        while (true) {
            try {
                if (sourceArr[i] != arrToCompare[i])
                    return sourceArr[i] - arrToCompare[i]; //если длмны массивов одинаковы, но сами массивы - разные
                i++;
            } catch (ArrayIndexOutOfBoundsException ex) {
                return 0; //если массивы одинаковы
            }
        }
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
    static char[] replaceAll(char[] sourceArr, char[] oldVal, char[] newVal) throws Exception {
        char[] outArr = new char[]{};
        int oldValLen = length(oldVal);
        int newValLen = length(newVal);
        int i = 0;
        while (i < length(sourceArr)) {
            if (sourceArr[i] == oldVal[0]) {
                try {
                    if (compareTo(subString(sourceArr, i, i + oldValLen - 1), oldVal) == 0) { //подстрока совпадает с искомым массивом
                        for (int j = 0; j < newValLen; j++) { //добавляем в выходной массив новую подстроку
                            outArr = addToArray(outArr, newVal[j]);
                        }
                        i += oldValLen; //пропускаем блок, равный искомому массиву
                        continue;
                    }
                } catch (Exception ex) {
                    throw new Exception(ex.toString());
                }
            }
            outArr = addToArray(outArr, sourceArr[i]);
            i++;
        }
        return outArr;
    }

    //split
    static char[][] split(char[] sourceArr, char[] delimiter) throws Exception {
        char[][] outArr = new char[][]{};
        int delimLen = length(delimiter);
        int i = 0; //счетчик позиций массива
        int beg = 0;    //позиция начала блока
        while (i < length(sourceArr)) {
            if (sourceArr[i] == delimiter[0]) {
                try {
                    if (compareTo(subString(sourceArr, i, i + delimLen - 1), delimiter) == 0) {
                        if (beg == i)
                            outArr = addToArray(outArr, new char[]{});
                        else {
                            outArr = addToArray(outArr, subString(sourceArr, beg, i - 1));
                        }
                        i += delimLen;
                        beg = i--;
                    }
                } catch (Exception ex) {
                    throw new Exception(ex.toString());
                }
            }
            i++;
        }
        try {
            outArr = addToArray(outArr, subString(sourceArr, beg, length(sourceArr) - 1)); //добавляем к массиву последнюю подстроку
            return outArr;
        } catch (Exception ex) {
            throw new Exception(ex.toString());
        }
    }
    //реализовать свои методы на примере массива char

    public static void main(String[] args) {
        String errMsg = "ОШИБКА!";
        char[] myArray = {' ', 'A', ' ', 'R', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' ', 'n', 'a', ' ', 'l', 'a', 'p', 'u', ' ', 'A', 'z', 'o', 'r', 'a'};
        char[] myArray1 = {'A', ' ', 'R', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' '};
        char[] myArray11 = {'A', ' ', 'R', 'o', 'z', 'a', ' ', 'u', 'p', 'a', 'l', 'a', ' '};
        char[] myArray2 = {'n', 'a', ' ', 'l', 'a', 'p', 'u', ' ', 'A', 'z', 'o', 'r', 'a'};
        char[] myArray22 = {'A'};
        char[] myArray23 = {'A', 'z', 'o', 'r', 'a'};

        //демонстрация методов
        //concat           ->         String
        System.out.print("concat: ");
        printArray(concat(myArray1, myArray2));
        System.out.println("-------------------------------");

        //equals           ->         boolean
        String outStr = equals(myArray1, myArray11) ? "EQUALS!" : "NOT equals";
        System.out.print("equals: " + outStr);
        System.out.println("\n-------------------------------");

        //equalsIgnoreCase ->         boolean
        outStr = equalsIgnoreCase(myArray1, myArray11) ? "EQUALS!" : "NOT equals";
        System.out.print("equalsIgnoreCase: " + outStr);
        System.out.println("\n-------------------------------");

        //startsWith       ->         boolean
        System.out.print("startsWith: ");
        try {
            outStr = startsWith(myArray22, myArray23) ? "STARTS!" : "Not starts";
            System.out.println(outStr);
        } catch (Exception ex) {
            System.out.println(errMsg + ex.toString());
        }
        System.out.println("-------------------------------");

        //endsWith         ->         boolean
        System.out.print("endsWith: ");
        try {
            outStr = endsWith(myArray23, myArray) ? "ENDS!" : "Not ends";
            System.out.println(outStr);
        } catch (Exception ex) {
            System.out.println(errMsg + ex.toString());
        }
        System.out.println("-------------------------------");

        //substring        ->         String
        System.out.print("substring: ");
        try {
            printArray(subString(myArray, 10, 12));
        } catch (Exception ex) {
            System.out.println(errMsg + ex.toString());
        }
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
        try {
            printArray(
                    replaceAll(
                            replaceAll(
                                    testString1, oldStr1, newStr1
                            ), oldStr2, newStr2
                    )
            );
        } catch (Exception ex) {
            System.out.println(errMsg + ex.toString());
        }
        System.out.println("-------------------------------");

        //split
        String test2 = "Петров; Иван; Николаевич; 1982; муж.";
        char[] testString2 = test2.toCharArray();
        System.out.print("split: ");
        char[][] testArr = new char[][]{};
        try {
            testArr = split(testString2, ("; ").toCharArray());
        } catch (Exception ex) {
            System.out.println(errMsg + ex.toString());
        }
        printArray(testArr);

        //System.out.println("Длина разделенного массива = " + length(testArr));
        System.out.println("-------------------------------");
    }
}
