package hw1707;

import java.util.Scanner;

public class Main1707 {
    public static void main(String[] args) {
        Scanner scn0;
        String errMsg = "ОШИБКА!";
        String warnMsg = "ВНИМАНИЕ!";
        String repeat = "Попробуйте еще раз.";
        while (true) {
            System.out.println("ГЛАВНОЕ МЕНЮ");
            System.out.println("1 - Создать запись");
            System.out.println("2 - Показать запись");
            System.out.println("3 - Редактировать запись");
            System.out.println("4 - Удалить запись");
            System.out.println("5 - Показать все записи");
            System.out.println("0 - Выход");
            System.out.print("Выберите действие:");
            scn0 = new Scanner(System.in);
            String choice = scn0.next();

            if (choice.equals("0")) break;

            switch (choice) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                default:
                    System.out.println(errMsg + " Введено недопустимое значение. " + repeat);
            }
        }
    }
}

//Написать CRUD operation над объектом
//CRUD - operation должно проходить в contacts.txt
class Person {
    private String name;
    private String surname;

    private int age;

    public Person() {
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + age;
    }

}

