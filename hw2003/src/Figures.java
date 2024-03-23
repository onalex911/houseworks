import java.util.Scanner;

public class Figures {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите номер фигуры, которую требуется нарисовать:");
        System.out.println(" 1 - прямоугольный треугольник вершиной вверх");
        System.out.println(" 2 - прямоугольный треугольник вершиной вниз");
        System.out.println(" 3 - равнобедренный треугольник вершиной вверх");
        System.out.println(" 4 - равнобедренный треугольник вершиной вниз");
        System.out.println(" 5 - ромб");
        byte typeFigure = sc.nextByte();
        boolean filled;
        char filler = '*';//символ "заполнитель" фигуры
        char spacer = ' ';//символ "пустоты"

        if(typeFigure > 0 && typeFigure <=5){
            System.out.println("Фигура должна быть закрашена?\n 0 (и меньше) - нет\n 1 (и больше) - да");
            filled = sc.nextByte() > 0;
            String nech = typeFigure >= 3 ? "нечетное " : "";
            System.out.print("Введите кол-во строк для высоты фигуры ("+nech+"число от 3 до 99):");
            byte height = sc.nextByte();
            if(height < 3 || height > 99){
                System.out.println("ОШИБКА! Введено неверное число строк");
            }else{
                if(typeFigure >=3 && height%2 == 0){
                    System.out.println("ВНИМАНИЕ! Вы ввели четное число ("+typeFigure+"). Оно будет уменьшено на 1-цу");
                    height--; //уменьшаем значение на 1, если введено четное число
                }
                int i = -1;
                while(++i < height){
                    int width = 0;
                    int start = 0;
                    int end = height - 1;
                    switch (typeFigure) {
                        //прямоугольный треугольник вершиной вверх
                        case 1: width = i;
                                break;
                        //прямоугольный треугольник вершиной вниз
                        case 2: width = end - i;
                                break;
                        //равнобедренный треугольник вершиной вверх
                        case 3: width = end + i;
                                start = end - i;
                                break;
                        //равнобедренный треугольник вершиной вниз
                        case 4: width = 2*end - i;
                                start = i;
                                break;
                        //ромб
                        case 5: int delta = i <= end/2 ? i : end - i;
                                width = end/2 + delta;
                                start = end/2 - delta;
                                break;
                    }
                    //рисуем одну строку
                    int j = -1;
                    while(++j < width){
                        if (j < start || (!filled && j > start) && ( i > 0 && i < height - 1))
                            System.out.print(spacer);
                        else
                            System.out.print(filler);
                    }
                    System.out.println(filler); //любая строка любой фигуры заканчивается "заполнителем"
                }
            }
        }else
            System.out.println("ОШИБКА! Введен неверный номер фигуры");
    }
}
