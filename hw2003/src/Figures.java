import java.util.Scanner;

public class Figures {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        byte max = 5;
        System.out.println("Выберите номер фигуры, которую требуется нарисовать:");
        System.out.println(" 1 - прямоугольный треугольник вершиной вверх;");
        System.out.println(" 2 - прямоугольный треугольник вершиной вниз;");
        System.out.println(" 3 - равнобедренный треугольник вершиной вверх;");
        System.out.println(" 4 - равнобедренный треугольник вершиной вниз;");
        System.out.println(" 5 - ромб;");
        System.out.println(" 0 - вывести все фигуры последовательно.");
        byte typeFigure = sc.nextByte();
        boolean filled;
        char filler = '*';//символ "заполнитель" фигуры
        char spacer = ' ';//символ "пустоты"

        if(typeFigure >= 0 && typeFigure <=max){
            System.out.println("Фигура должна быть закрашена?\n 0 (и меньше) - нет\n 1 (и больше) - да:");
            filled = sc.nextByte() > 0;
            System.out.print("Введите кол-во строк для высоты фигуры (нечетное число от 3 до 99):");
            byte height = sc.nextByte();
            if(height < 3 || height > 99)
                System.out.println("ОШИБКА! Введено неверное число строк.");
            else if(height%2 == 0)
                System.out.println("ОШИБКА! Введено четное число строк.");
            else{
                byte kolFig = typeFigure == 0 ? max : 1;
                byte k = 0;
                while(++k <= kolFig){
                    if(kolFig > 1) typeFigure = k;
                    byte i = 0;
                    while(++i <= height){
                        byte width = 1; //ширина фигуры
                        byte start = 1; //начальная позиция, с которой начинается левый край фигуры
                        switch (typeFigure) {
                            //прямоугольный треугольник вершиной вверх
                            case 1: width = i;//(byte)(i-1);
                                    break;
                            //прямоугольный треугольник вершиной вниз
                            case 2: width = (byte)(height + 1 - i);
                                    break;
                            //равнобедренный треугольник вершиной вверх
                            case 3: width = (byte)(height + i - 1);
                                    start = (byte)(height + 1 - i);
                                    break;
                            //равнобедренный треугольник вершиной вниз
                            case 4: width = (byte)(2*height - i);
                                    start = i;
                                    break;
                            //ромб
                            case 5: byte delta = i <= (height/2 + 1) ? (byte)(i - 1) : (byte)(height - i);
                                    width = (byte)(height/2 + 1 + delta);
                                    start = (byte)(height/2 + 1 - delta);
                                    break;
                        }
                        //рисуем одну строку
                        byte j = 0;
                        while(++j < width){
                            if (j < start || (!filled && j > start) && ( i > 1 && i < height))
                                System.out.print(spacer);
                            else
                                System.out.print(filler);
                        }
                        System.out.println(filler); //любая строка любой фигуры заканчивается "заполнителем"
                    }
                    System.out.println();
                }
            }
        }else
            System.out.println("ОШИБКА! Введен неверный номер фигуры.");
    }
}
