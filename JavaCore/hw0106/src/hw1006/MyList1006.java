
package hw1006;

public class MyList1006 {
    private int[] arr;
    private int capacity;
    private int size;
    private int len4sort;
    //private int extremum;
    //private int extremumInd;
    private long count;
    {
        size = 0;
        capacity = 10;
        this.arr = new int[capacity];
        count = 0;
    }

    public MyList1006() {
        this(new int[]{});
    }

    public MyList1006(int[] arr) {
        size = arr.length;
        addCapacity();
        for (int i = 0; i < size; i++)
            this.arr[i] = arr[i];
    }

    public int getSize() {
        return size;
    }

    public int getArrValByIndex(int index) {
        return this.arr[index];
    }

    public int getArrayLenght() {
        return arr.length;
    }

    private void addCapacity() {
        if (size >= capacity) {
            capacity = 2 * capacity;
            int[] tmp = new int[capacity];
            for (int i = 0; i < size; i++) {
                tmp[i] = arr[i];
            }
            arr = tmp;
        }
    }

    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void add(int num) {
        addCapacity();
        arr[size++] = num;
    }

    public void copy(int count) {
        int[] tmp = new int[capacity];
        for (int i = 0; i < count; i++) {
            tmp[i] = arr[i];
        }

        size = count;
        arr = tmp;
    }

    private int getMinInd(int length) {
        int min = arr[0];
        int extrInd = 0;
        for (int i = 1; i < length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                extrInd = i;
            }
            count++;
        }
        return extrInd;
    }

    private void shiftLeft(int start, int length) {
        for (int i = start; i < length - 1; i++) {
            arr[i] = arr[i + 1];
            count++;
        }
    }

    private MyList1006 getIndexesOf(int num) {
        MyList1006 temp = new MyList1006();
        for (int i = 0; i < this.size; i++) {
            if (arr[i] == num)
                temp.add(i);
            count++;
        }
        return temp;
    }

    public void removeByValue(int num) { //удалить все значения
        MyList1006 indexes = getIndexesOf(num);
        int decr = 0;
        if (indexes.getSize() > 0) {
            for (int i = 0; i < indexes.getSize(); i++) {
                shiftLeft(indexes.getArrValByIndex(i) - decr++, this.size--);
            }
            copy(this.size);
        }
    }

    public void sort(boolean sortFlag) {
        int ind, diap, val, limit;
        diap = size;
        for (int i = 0; i < size; i++) {
            ind = getMinInd(diap);
            val = arr[ind];
            //нужно получить возрастание - сдвигаем весь массив и текущий минимум записываем в последнюю позицию массива
            //нужно получить убывание - сдвигаем диапазон поиска и текущий минимум записываем в последнюю позицию диапазона
            limit = sortFlag ? size : diap;
            shiftLeft(ind, limit);
            arr[limit - 1] = val;
            diap--;//сужаем диапазон поиска
            count++;
        }
    }

    public void removeAllDuplicates() {
        for (int i = 0; i < size; ) {
            MyList1006 temp = getIndexesOf(this.arr[i]);
            if (temp.getSize() > 1) {
                int decr = 0;
                for (int j = 1; j < temp.getSize(); j++) {
                    shiftLeft(temp.getArrValByIndex(j) - decr++, size--);
                }
            } else i++;
        }
    }

    public void sortBubble(boolean isUp) {
        for (int i = 0; i < size; i++) {
            for (int j = isUp ? i + 1 : 0; j < size; j++) {
                if (arr[i] > arr[j])
                    arr[j] = arr[i] + arr[j] - (arr[i] = arr[j]);
                count++;
            }

        }
    }

    public long getCount() {
        return count;
    }
}
