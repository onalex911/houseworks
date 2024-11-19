public class CellArray {
    private int capacity;
    private int size;
    private Cell[] arr;
    {
        capacity = 9;
        size=0;
    }

    public CellArray() {
        this.arr = new Cell[capacity];
    }

//    public MyArray(T[] arr) {
//        this.arr = arr;
//    }
    public void add(Cell val){
        this.arr[size++] = val;
    }

    private void checkCapacity(int size){
        if(size >= capacity){
            capacity *= 2;
            Cell[] tmp = new Cell[capacity];
            for (int i = 0; i < this.size; i++) {
                tmp[i] = this.arr[i];
            }
            this.arr = tmp;
        }
    }

    public Cell[] getArr() {
        return arr;
    }

    public Cell getCellByIndex(int index) {
        return arr[index];
    }

    public int getSize() {
        return size;
    }
}
