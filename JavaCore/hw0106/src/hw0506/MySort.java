package hw0506;

public class MySort {
    int[] arr;

    public MySort(int[] arr) {
        this.arr = arr;
    }

    public void printArray() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public void sort() {
        this.sort(true);
    }
    public void sort(boolean isUp) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (isUp ? (arr[i] > arr[j]) : (arr[i] < arr[j]))
                    arr[j] = (arr[i] + arr[j]) - (arr[i] = arr[j]);
            }
        }
    }
}
