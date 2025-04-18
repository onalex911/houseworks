package Test1006;

class Main1006 {
    public static int[] removeAllDubs(int[] arr){
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                if(arr[i] == arr[j]){
                    //int shiftLeft = j;
                    for (int k = j+1; k < length; ) {
                        arr[k] = arr[++k];
                    }
                    length--;
                    j--;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,1,2,3,1};
        int[] out = removeAllDubs(arr);
        for (int i = 0; i < out.length; i++) {
            System.out.print(out[i]+ " ");

        }
    }
}