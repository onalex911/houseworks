package hw2205;

public class replaceAll {
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

    //возвращает длину массива
    static int length(char[] arr) {
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
        if (sourceArr == arrToCompare) //если массивы ссылаются на один и тот же объект
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

    public static void main(String[] args) {
        String errMsg = "ОШИБКА! ";
        //демонстрация метода relaceAll

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


    }
}
