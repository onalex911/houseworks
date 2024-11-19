
package hw0306;

public class MyList {
    int[] arr;

    public MyList(int[] arr) {
        this.arr = arr;
    }

    private boolean isExists(int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num)
                return true;
        }
        return false;
    }

    public void printArray() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private void add(int num){
        int[] out = new int[arr.length+1];
        for (int i = 0; i < arr.length; i++) {
            out[i] = arr[i];
        }
        out[out.length-1] = num;
        this.arr = out;
    }

    public void removeByValue(int num) { //udalit pervoe sovpadenie
        if (isExists(num)) {
            int[] out = new int[this.arr.length - 1];
            boolean f = false;
            for (int i = 0, j = 0; i < arr.length; i++) {
                if (arr[i] != num || f)
                    out[j++] = arr[i];
                else {
                    f = true;
                    continue;
                }
            }
            arr = out;
        }
    }

    public void removeByValueEnd(int num) { //udalit posledneyee sovpadenie
        if (isExists(num)) {
            int[] out = new int[this.arr.length - 1];
            boolean f = false;
            for (int i = arr.length - 1, j = out.length - 1; i >= 0; i--) {
                if (arr[i] != num || f)
                    out[j--] = arr[i];
                else {
                    f = true;
                    continue;
                }
            }
            arr = out;
        }
    }

    public void removeAllByValue(int num) { //udalit vse sovpadayusie
        if (isExists(num)) {
            MyList tmp = new MyList(new int[]{}); //временный класс с массивом несовпадающих значений
            for (int i = 0, j = 0; i < arr.length; i++) {
                if (arr[i] != num)
                     tmp.add(arr[i]);
                else continue;
            }
            arr = tmp.getArr();
        }
    }

    public void replaceByValue(int num, int newNum) { //zamenit pervoe sovpadenie
        if (isExists(num)) {
            boolean f = false;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == num && !f) {
                    arr[i] = newNum;
                    f=true;
                }
            }
        }
    }

    public void replaceByValueEnd(int num, int newNum) { //zamenit posl sovpadenie
        if (isExists(num)) {
            boolean f = false;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] == num && !f){
                    arr[i] = newNum;
                    f = true;
                }
            }
        }
    }

    public void replaceAllByValue(int num, int newNum) { //zamenit vse sovpadenie
        if (isExists(num)) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == num)
                    arr[i] = newNum;
            }
        }
    }

    public int[] getArr() {
        return arr;
    }
    public int getArrayLenght(){
        return arr.length;
    }
}
