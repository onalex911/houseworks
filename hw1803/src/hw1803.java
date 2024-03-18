public class hw1803 {
    public static void main(String[] args){
        byte colRow = 7;
        byte colCol = (byte) 15;
        byte zvStart;
        byte zvEnd;
        byte delta = (byte)(colCol - colRow);

        //равнобедренный треугольник
        for(byte i = 0; i < colRow; i++){
            zvStart = (byte)(delta - i);//8
            zvEnd = (byte)(delta + i);//8
            for (byte j = 0; j < colCol; j++) {
                 if(j<zvStart)
                     System.out.print(" ");
                 else if(j>zvEnd)
                     continue;
                 else
                     System.out.print("*");
            }
            System.out.println();
        }
        //прямоугольный треугольник
        //прямоугольный треугольник основанием вверх
        //нак
    }
}
