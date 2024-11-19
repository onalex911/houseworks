class MyList<T> {
    T[] arr;
    int size;

    {
        size = 0;
        arr = (T[]) new Object[size];
    }

    public void add(T num) {
        T[] tmp = (T[]) new Object[size+1];

        for (int i = 0; i < size; i++) {
            tmp[i] = arr[i];
        }
        tmp[size] = num;
        size++;
        arr = tmp;
    }

    public void addStart(T num) {
        T[] tmp = (T[]) new Object[size+1];

        tmp[0] = num;

        for (int i = 0; i < size; i++) {
            tmp[i + 1] = arr[i];
        }
        size++;
        arr = tmp;
    }


    public void printInfo() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}