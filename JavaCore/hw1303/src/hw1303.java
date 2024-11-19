import java.util.Scanner;

public class hw1303 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//    1) Проверка на простое число:
//    Пользователь вводит число. Напишите программу, которая проверяет, является ли это число простым, и выводит соответствующее сообщение.
        System.out.println("1. Введите целое число (до 120):");
        int a = sc.nextInt();
        String pril;
        switch (a) {
            case 2:
            case 3:
            case 5:
            case 7:
            case 11:
            case 13:
            case 17:
            case 19:
            case 23:
            case 29:
            case 31:
            case 37:
            case 41:
            case 43:
            case 47:
            case 53:
            case 59:
            case 61:
            case 67:
            case 71:
            case 73:
            case 79:
            case 83:
            case 89:
            case 97:
            case 101:
            case 103:
            case 107:
            case 113:
                pril = " - простое";
                break;
            default:
                pril = " не является простым";
        }
        System.out.println("Число " + a + pril);
        System.out.println("-------------------------------------");
////
////    2) Оценки студентов:
////    Программа получает оценку студента (от 0 до 100). Выведите сообщение, соответствующее следующему:
////
////    "A" для оценок от 90 до 100
////    "B" для оценок от 80 до 89
////    "C" для оценок от 70 до 79
////    "D" для оценок от 60 до 69
////    "F" для оценок менее 60
        System.out.println("2. Введите количество баллов:");
        byte bally = sc.nextByte();
        if (bally < 60) {
            System.out.println("F");
        } else if (bally < 70) {
            System.out.println("D");
        } else if (bally < 80) {
            System.out.println("C");
        } else if (bally < 90) {
            System.out.println("B");
        } else if (bally <= 100) {
            System.out.println("A");
        } else {
            System.out.println("Введенное количество баллов не соответствует ни одной оценке!");
        }
        System.out.println("-------------------------------------");
//////
//////
//////    3) Калькулятор дня недели:
//////    Пользователь вводит число от 1 до 7, представляющее день недели. Используйте оператор switch, чтобы вывести название этого дня.
        System.out.println("3. Введите целое число от 1 до 7-ми:");
        String dayName = "";
        byte b = sc.nextByte();
        if (b >= 1 && b <= 7) {
            switch (b) {
                case 1 -> dayName = "понедельник";
                case 2 -> dayName = "вторник";
                case 3 -> dayName = "среда";
                case 4 -> dayName = "четверг";
                case 5 -> dayName = "пятница";
                case 6 -> dayName = "суббота";
                case 7 -> dayName = "воскресенье";
            }
            System.out.println(dayName);
        } else
            System.out.println("Вы ввели неверное число!");
        System.out.println("-------------------------------------");
////
////    4) Подсчет налога:
////    Пользователь вводит свой доход. Напишите программу, которая использует различные ставки налога в зависимости от дохода и выводит сумму налога, который он должен заплатить.
        System.out.println("4. Введите сумму своего дохода:");
        float tax;
        int value = sc.nextInt();
        if (value <= 30_000)
            tax = (float) (value * 0.1);
        else if (value <= 300_000)
            tax = (float) (value * 0.11);
        else if (value <= 500_000)
            tax = (float) (value * 0.12);
        else if (value <= 1_000_000)
            tax = (float) (value * 0.13);
        else if (value <= 3_000_000)
            tax = (float) (value * 0.15);
        else if (value > 3_000_000)
            tax = (float) (value * 0.17);
        else tax = 0.0f;
        if (tax > 0)
            System.out.printf("Размер вашего налога = %.2f", tax);
        else
            System.out.print("Введено неверное число");
        System.out.println("-------------------------------------");

//
//    5) Определение времени года:
//    Пользователь вводит номер месяца (от 1 до 12). Используйте оператор switch, чтобы определить время года (зима, весна, лето, осень) и вывести соответствующее сообщение.
        System.out.println("5. Введите целое число от 1 до 12-ти:");
        String seasonName = "";
        byte monthNum = sc.nextByte();
        if (monthNum >= 1 && monthNum <= 12) {
            switch (monthNum) {
                case 12:
                case 1:
                case 2:
                    seasonName = "зима";
                    break;
                case 3:
                case 4:
                    ;
                case 5:
                    seasonName = "весна";
                    break;
                case 6:
                case 7:
                case 8:
                    seasonName = "лето";
                    break;
                case 9:
                case 10:
                case 11:
                    seasonName = "осень";
            }
            System.out.println("Время года - " + seasonName);
        } else
            System.out.println("Вы ввели неверное число!");
        System.out.println("-------------------------------------");

//    6) Проверка треугольника:
//    Пользователь вводит три стороны треугольника. Напишите программу, которая определяет, является ли треугольник равносторонним, равнобедренным, прямоугольным или обычным, и выводит соответствующее сообщение.
        System.out.println("6. Введите длины 3-х сторон треугольника ABC (AB, BC, AC):");
        String typeOfTriangle = "";
        //стороны в double, чтобы проверить равнобедренность и прямоугольность одновременно (например, 1 1 1,4 или 5 5 7,07)
        double ab = sc.nextDouble();
        double bc = sc.nextDouble();
        double ac = sc.nextDouble();
        //кадраты сторон
        double ab2 = Math.round(Math.pow(ab, 2));
        double bc2 = Math.round(Math.pow(bc, 2));
        double ac2 = Math.round(Math.pow(ac, 2));

        if (ab2 + bc2 == ac2 || ab2 + ac2 == bc2 || bc2 + ac2 == ab2)
            typeOfTriangle = "прямоугольный";

        if (ab == bc || ab == ac || bc == ac) {
            typeOfTriangle += (ab == bc && ab == ac) ? "равносторонний" : (typeOfTriangle != "" ? " и " : "") + "равнобедренный";
        } else {
            if (typeOfTriangle == "")
                typeOfTriangle = "обычный";
        }

        System.out.println("Треугольник " + typeOfTriangle);
        System.out.println("-------------------------------------");

//    7) Конвертер единиц:
//    Пользователь вводит число и единицу измерения (например, "метр", "дюйм", "грамм", "фунт" и т. д.). Напишите программу, которая конвертирует это значение в другие единицы измерения (например, миллиметры, сантиметры, килограммы, унции и т. д.) с использованием оператора switch.
        System.out.println("7. Введите число для конвертации:");
        double userValue = sc.nextDouble();
        double result = 0.0;
        String fromTxt = "";
        String toTxt = "";
        if (userValue < 0)
            System.out.println("Ошибка! Длина или масса не могут быть отрицательными");
        else {
            System.out.println("Введите число, соответствующее размерности, из которой требуется конвертация:");
            System.out.println("1 - метры");
            System.out.println("2 - дюймы");
            System.out.println("3 - граммы");
            System.out.println("4 - фунты");

            int unitsFrom = sc.nextInt();

            if (unitsFrom < 1 || unitsFrom > 4)
                System.out.println("Ошибка! Введенное число не соответствует ни одной размерности!");
            else {
                int unitsTo;
                if (unitsFrom < 3) {//длина
                    System.out.println("Введите число, соответствующее размерности, в которую требуется конвертация:");
                    System.out.println("1 - миллиметры");
                    System.out.println("2 - сантиметры");

                    unitsTo = sc.nextInt();

                    if (unitsTo < 1 || unitsTo > 2)
                        System.out.println("Ошибка! Введенное число не соответствует ни одной размерности!");
                    else {
                        switch (unitsFrom) {
                            case 1: //из метров
                                fromTxt = "метров";
                                switch (unitsTo) {
                                    case 1:
                                        result = userValue * 1000; // в миллиметры
                                        toTxt = "миллиметров";
                                        break;
                                    case 2:
                                        result = userValue * 100; // в сантиметры
                                        toTxt = "сантиметров";
                                        break;
                                }
                                break;
                            case 2: //из дюймов
                                fromTxt = "дюймов";
                                switch (unitsTo) {
                                    case 1:
                                        result = userValue * 25.4; // в миллиметры
                                        toTxt = "миллиметров";
                                        break;
                                    case 2:
                                        result = userValue * 2.54; // в сантиметры
                                        toTxt = "сантиметров";
                                        break;
                                }
                        }
                    }
                } else {//масса
                    System.out.println("Введите число, соответствующее размерности, в которую требуется конвертация:");
                    System.out.println("1 - килограммы");
                    System.out.println("2 - унции");

                    unitsTo = sc.nextInt();

                    if (unitsTo < 1 || unitsTo > 2)
                        System.out.println("Ошибка! Введенное число не соответствует ни одной размерности!");
                    else {
                        switch (unitsFrom) {
                            case 3: //из граммов
                                fromTxt = "граммов";
                                switch (unitsTo) {
                                    case 1:
                                        result = userValue / 1000; // в килограммы
                                        toTxt = "килограммов";
                                        break;
                                    case 2:
                                        result = userValue * 0.035274; // унции
                                        toTxt = "унций";
                                        break;
                                }
                                break;
                            case 4: //из фунтов
                                fromTxt = "фунтов";
                                switch (unitsTo) {
                                    case 1:
                                        result = userValue * 0.45359285929009; // в килограммы
                                        toTxt = "килограммов";
                                        break;
                                    case 2:
                                        result = userValue * 16.000017259200017605; // унции
                                        toTxt = "унций";
                                        break;
                                }
                        }
                    }
                }
                System.out.printf("Результат: %.3f %s = %.3f %s\n", userValue, fromTxt, result, toTxt);
            }
        }
//    8) Проверка переполнения:
//    Пользователь вводит два целых числа и оператор (+, -, *, /). Напишите программу, которая проверяет, вызовет ли выполнение операции переполнение, и выводит соответствующее сообщение.
        System.out.println("8. Введите 2 целых числа для операции:");
        byte x = sc.nextByte();
        byte y = sc.nextByte();

        System.out.println("введите  символ операции (+, - , *, /):");
        String sign = sc.next();

        switch (sign) {
            case "+":
                if ((x > 0 && y > 0 && (byte) (x + y) < 0) || (x < 0 && y < 0 && (byte) (x + y) > 0))
                    System.out.println("Переполнение при сложении");
                else
                    System.out.println("Переполнения нет");
                break;
            case "-":
                if ((x < 0 && y > 0 && (byte) (x + y) > 0) || (x > 0 && y < 0 && (byte) (x + y) < 0))
                    System.out.println("Переполнение при вычитании");
                else
                    System.out.println("Переполнения нет");
                break;
            case "*":
                short x1 = x;
                short y1 = y; //по-другому не придумал :(
                if (x1 * y1 > 127 || x1 * y1 < -128)
                    System.out.println("Переполнение при умножении");
                else
                    System.out.println("Переполнения нет");
                break;
            case "/":
                if (y == 0)
                    System.out.println("На ноль делить нельзя!");
                else if (x < 0 && y < 0 && (byte) (x / y) < 0) //
                    //else if(x == -128 && y == -1)//нашел только один случай переполнения при целочисленном делении
                    System.out.println("Переполнение при делении");
                else
                    System.out.println("Переполнения нет");
                break;
            default:
                System.out.printf("Указан непредусмотренный знак (%s)\n", sign);
        }
        for (int i = 0; i < ; i++) {
            
        }
    }
}
