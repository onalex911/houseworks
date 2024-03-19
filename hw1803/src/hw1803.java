public class hw1803 {
    public static void main(String[] args){

        //равнобедренный треугольник
        byte colRow = 8; //кол-во строк
        byte zvStart = colRow; //начальная позиция, с которой будем рисовать звездочки
        byte zvEnd = (byte)(colRow - 1);//упредительное уменьшение конечной позиции
        for(byte i = 0; i < colRow; i++){
            zvStart--;
            zvEnd++;
            for (byte j = 0; j < zvEnd; j++) {
                 if(j<zvStart) //до начала звездочек рисуем пробелы
                     System.out.print(" ");
                 else //рисуем звездочки до конечной позиции
                     System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("----------------------");

        //прямоугольный треугольник
        colRow = 7;
        for (byte i = 0; i < colRow; i++){
            for (byte j = 0; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
        System.out.println("----------------------");

        //прямоугольный треугольник основанием вверх
        //colRow = 7;
        for (byte i = 0; i < colRow; i++){
            for (byte j = 0; j < (colRow - i); j++)
                System.out.print("*");
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
        //colRow = 8;
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
        byte mid = (byte)((colRow+1)/2); //кол-во символов от начала до середины фигуры
        zvStart = mid; //начальная позиция, с которой будем рисовать звездочки
        zvEnd = (byte)(mid - 1); //упредительное уменьшение конечной позиции
        for(byte i = 0; i < colRow; i++){
            if(i < mid){ //ромб расширяется
                zvStart--;
                zvEnd++;
            }
            else { //ромб сужается
                zvStart++;
                zvEnd--;
            }
            //рисуем пробелы до начальной позиции звездочек
            for (byte j = 0; j < zvEnd; j++) {
                if(j<zvStart) //до начала звездочек рисуем пробелы
                    System.out.print(" ");
                else //рисуем звездочки до конечной позиции
                    System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("----------------------");

        //прямоугольник
        zvEnd = 5; //кол-во звездочек в ряду
        colRow = 8; //кол-во рядов
        for (byte i = 0; i < colRow; i++) {
            for (byte j = 0; j < zvEnd; j++) {
                if(j > 0) System.out.print(" "); //ставим пробел перед любой звездочкой, кроме первой
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
