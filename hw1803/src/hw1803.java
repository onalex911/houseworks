public class hw1803 {
    public static void main(String[] args){
        byte colRow = 8;
        byte colCol = (byte) (colRow*2 - 1);
        byte zvStart = colRow;
        byte zvEnd = (byte)(colRow - 1);

        //равнобедренный треугольник
        for(byte i = 0; i < colRow; i++){
            zvStart--;
            zvEnd++;
            for (byte j = 0; j < colCol; j++) {
                 if(j<zvStart)
                     System.out.print(" ");
                 else if(j<zvEnd)
                     System.out.print("*");
                 else
                     continue;
            }
            System.out.println();
        }
        System.out.println("----------------------");
        //прямоугольный треугольник
        colRow = 7;
        for (byte i = 0; i < colRow;){
            ++i;
            for (byte j = 0; j < i ; j++)
                System.out.print("*");

            System.out.println();
        }
        System.out.println("----------------------");
        //прямоугольный треугольник основанием вверх
        //нак
        colRow = 7;
        for (byte i = 0; i < colRow;){
            for (byte j = 0; j < (colRow - i) ; j++)
                System.out.print("*");

            i++;
            System.out.println();
        }
        System.out.println("----------------------");

        //линия, наклоненная влево
        colRow = 8;
        for (byte i = 0; i < colRow;){
            ++i;
            for (byte j = 0; j < i ; j++) {
                char s = j < (i - 1) ? ' ' : '*';
                System.out.print(s);
            }
            System.out.println();
        }
        System.out.println("----------------------");
        //линия, наклоненная вправо
        colRow = 8;
        for (byte i = 0; i < colRow;){
            for (byte j = 0; j < (colRow - i) ; j++) {
                char s = j < (colRow - i - 1) ? ' ' : '*';
                System.out.print(s);
            }
            i++;
            System.out.println();
        }
        System.out.println("----------------------");

        //ромб
        colRow = 17;
        zvStart = (byte)((colRow+1)/2);
        zvEnd = (byte)(zvStart - 1);
        for(byte i = 0; i < colRow; i++){

            zvStart = (i < (colRow+1)/2) ? (byte) (zvStart - 1) : (byte) (zvStart + 1);
            zvEnd = (i < (colRow+1)/2) ? (byte) (zvEnd + 1) : (byte) (zvEnd - 1);

            for (byte j = 0; j < colRow; j++) {
                if(j<zvStart)
                    System.out.print(" ");
                else if(j<zvEnd)
                    System.out.print("*");
                else
                    continue;
            }
            System.out.println();
        }
//        System.out.println((byte)(colRow / 2));
        System.out.println("----------------------");
    }
}
