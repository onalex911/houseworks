import java.util.Scanner;

public class hw2503 {
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
                byte end = (byte)(height - 1); //предпоследняя позиция (для расчета ширины)
                byte kolFig = typeFigure == 0 ? max : 1;
                byte k = 1;
                do{
                    if(kolFig > 1) typeFigure = k;
                    byte i = 0;
                    do{
                        byte width = 0; //ширина фигуры
                        byte start = 0; //начальная позиция, с которой начинается левый край фигуры
                        switch (typeFigure) {
                            //прямоугольный треугольник вершиной вверх
                            case 1: width = i;
                                break;
                            //прямоугольный треугольник вершиной вниз
                            case 2: width = (byte)(end - i);
                                break;
                            //равнобедренный треугольник вершиной вверх
                            case 3: width = (byte)(end + i);
                                start = (byte)(end - i);
                                break;
                            //равнобедренный треугольник вершиной вниз
                            case 4: width = (byte)(2*end - i);
                                start = i;
                                break;
                            //ромб
                            case 5: byte delta = i <= end/2 ? i : (byte)(end - i);
                                width = (byte)(end/2 + delta);
                                start = (byte)(end/2 - delta);
                                break;
                            default:
                                System.out.printf("Нет фигуры с номером %d!",typeFigure);
                        }
                        //рисуем одну строку
                        byte j = 0;
                        do{
                            if(width == 0) break; //пропускаем шаг с "нулевой" шириной, т.к. у нас в любом случае рисуется один заполнитель (стр. 70)
                            if (j < start || (!filled && j > start) && ( i > 0 && i < height - 1))
                                System.out.print(spacer);
                            else
                                System.out.print(filler);
                        }while(++j < width);
                        System.out.println(filler); //любая строка любой фигуры заканчивается "заполнителем"
                    }while(++i < height);
                    System.out.println();
                }while(++k <= kolFig);
            }
        }else
            System.out.println("ОШИБКА! Введен неверный номер фигуры.");
    }
}
