import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Задания.
//        1. Запросите у пользователя его имя и выведите в ответ: «Привет, его имя!».
        System.out.println("Задание №1.");
        System.out.println("Введите имя:");
        String name = sc.nextLine();
        System.out.println("«Привет, "+ name +"!»\n");

//        2. Запросите у пользователя год его рождения, посчитайте, сколько ему лет и выведите результат. Текущий год укажите в коде как константу (final).
        System.out.println("Задание №2.");
        final short currentYear = 2024;
        System.out.println("Введите год вашего рождения:");
        short userBirthYear = sc.nextShort();
        System.out.println("пользователю полных лет: " + (currentYear - userBirthYear) + " (при условии, что у него уже был день рождения в этом году)\n");

//        3. Запросите у пользователя длину стороны квадрата и выведите периметр такого квадрата.
        System.out.println("Задание №3.");
        System.out.println("Введите размер стороны квадрата:");
        int sideLenght = sc.nextInt();
        System.out.println("периметр квадрата со строной " + sideLenght + " = " + (4 * sideLenght) + "\n");

//        4. Запросите у пользователя радиус окружности и выведите площадь такой окружности.
        final float pi = 3.14159265f;
        System.out.println("Задание №4.");
        System.out.println("Введите радиус окружности:");
        int radius = sc.nextInt();
        System.out.println("4: площадь окружности с радиусом " + radius + " = " + (pi * radius * radius) + "\n");

//        5. Запросите у пользователя расстояние в км между двумя городами и за сколько часов он хочет добраться. Посчитайте скорость, с которой необходимо двигаться, чтобы успеть вовремя.
        System.out.println("Задание №5.");
        System.out.println("Введите расстояние в километрах:");
        int distance = sc.nextInt(); //расстояние в километрах
        System.out.println("Введите время в часах:");
        int requiredTime = sc.nextInt(); //время в часах
        System.out.println("требуемая скорость = " + (distance / requiredTime) + " км/ч\n");

//        6. Реализуйте конвертор валют. Пользователь вводит доллары, программа переводит в евро. Курс валюты храните в константе.
        final float kursEurDoll = 0.9178f;
        System.out.println("Задание №6.");
        System.out.println("Введите сумму в долларах:");
        float userDollars = sc.nextFloat();
        System.out.printf("$%.2f = %.2f euro\n\n", userDollars, userDollars * kursEurDoll);

//        7. Пользователь указывает объем флешки в Гб. Программа должна посчитать сколько файлов размером в 820 Мб помещается на флешку.
        System.out.println("Задание №7.");
        System.out.println("Введите объем флешки в Гб:");
        int userSize = sc.nextInt();
        final short mbInGb = 1024;
        short fileSize = 820;
        System.out.println("на флешку " + userSize + "Гб помещается " + (mbInGb * userSize / fileSize) + " файлов размером по " + fileSize + "Мб каждый\n");

//        8. Пользователь вводит сумму денег в кошельке и цену одной шоколадки. Программа выводит сколько шоколадок может купить пользователь и сколько сдачи у него останется.
        System.out.println("Задание №8.");
        System.out.println("8: Введите сумму денег в кошельке:");
        float userMoney = sc.nextFloat();
        System.out.println("Введите цену одной шоколадки:");
        float chocolatePrice = sc.nextFloat();
        System.out.println("количество шоколадок: " + ((int) (userMoney/chocolatePrice)) + "\n");
//
//
//        9**. Запросите у пользователя трехзначное число и выведите его задом наперед. Для решения задачи вам понадобится оператор % (остаток от деления).
        System.out.println("Задание №9.");
        System.out.println("Введите трехзначное число:");
        short number = sc.nextShort();
        System.out.println(((number%10)*100 + number%100 - number%10 + (number - number%100)/100) + "\n");

//        10. Запросите у пользователя целое число и выведите в ответ, четное число или нет. В задании используйте логические операторы.
        System.out.println("Задание №10.");
        System.out.println("Введите целое число:");
        int userNumber1 = sc.nextInt();
        String result1;
        if(userNumber1%2 == 0){
            result1 = "четное";
        }else {
            result1 = "нечетное";
        }
        System.out.println("введено " + result1 + " число\n");

//        11. Запросите у пользователя целое число и выведите в ответ, положительное число или нет. В задании используйте логические операторы.
        System.out.println("Задание №11.");
        System.out.println("Введите целое число:");
        int userNumber2 = sc.nextInt();
        String result2;
        if(userNumber2 >= 0){
            result2 = "положительное";
        }else {
            result2 = "отрицательное";
        }
        System.out.println("введено " + result2 + " число\n");

//        12. Запросите у пользователя целое число и выведите в ответ, Делиться ли число на  7 и 5 без остаток или нет. В задании используйте логические операторы.
        final byte a = 7;
        final byte b = 5;
        System.out.println("Задание №12.");
        System.out.println("Введите целое число:");
        int userNumber3 = sc.nextInt();
        String result3;
        if(userNumber3%a == 0){
            result3 = " делится на " + a;
        }else if(userNumber3%b == 0){
            result3 = " делится на " + b;
        }else {
            result3 = " не делится ни на " + a + ", ни на " + b;
        }
        System.out.println("число " + userNumber3 + result3 + " без остатка\n");
//
//        13. У Никиты на 5 яблок больше чем у Саши
//        У Саши в 2 раза больше чем у Насти
//        У Насти количество яблок состовляет Сумма количеств яблок у Фарида и Олега
//        У Олега количество яблок на 2 меньше чем у Фарида
//        и у Фарида 10 яблок
//        Распичатать количество яблок у каждого человека
        int Farid = 10;
        int Oleg = Farid - 2;
        int Nastya = Farid + Oleg;
        int Sasha = Nastya * 2;
        int Nikita = Sasha + 5;
        System.out.println("Задание №13.");
        System.out.println("Количества яблок у ребят:");
        System.out.println("- у Никиты " + Nikita);
        System.out.println("- у Саши " + Sasha);
        System.out.println("- у Насти " + Nastya);
        System.out.println("- у Олега " + Oleg);
        System.out.println("- у Фарида " + Farid);
    }
}