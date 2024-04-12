package hw0304;

public class MySort2 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter array length: ");
//        int len = sc.nextInt();
        int len = 5;
        int[] arr = {15,9,-8,10,9};
        int[] ind = new int[len];
        int[] out = new int[len];

//        Random rnd = new Random();
//        for (int i = 0; i < len; i++) {
//            arr[i] = rnd.nextInt(5) + rnd.nextInt(5) - rnd.nextInt(10);
//            System.out.printf("%d ",arr[i]);
//        }
        //инициализация массива индексов
        for (int i = 0; i < len; i++) {
            ind[i] = -1;
        }
        int min, max;
        if(arr[0] < arr[arr.length-1]){
            min = arr[arr.length-1];
            max = arr[0];
        }else{
            min = arr[0];
            max = arr[arr.length-1];
        }
        int x = 0; int y = len-1;
        boolean exists = false;
        boolean even = len%2 == 0;
        for (int i = 0; i < len; i++) { //обход всего массива поэлементно
            if(i<len-1) {
                for (int j = 0; j < len; j++) {
                    //if(i==j) continue;
                    for (int k = 0; k < len; k++) {
                        if (ind[k] == i) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) continue;
                    if (arr[j] < min) {
                        min = arr[j];
                        ind[x] = j;
                    } else if (arr[j] > max) {
                        max = arr[j];
                        ind[y] = j;
                    }
                }
                out[x++] = min;
                out[y--] = max;
                max = (min + max) - (min = max);
            }else{
                out[x]=arr[i];
                break;
            }
        }
        //System.out.printf("\nmin = %d, max = %d\n",min,max);
        for (int i = 0; i < len; i++)
            System.out.printf("%d ",ind[i]);
        System.out.println();
        for (int i = 0; i < len; i++)
            System.out.printf("%d ",out[i]);


    }
}
