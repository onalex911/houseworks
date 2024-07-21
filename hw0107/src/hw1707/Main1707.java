package hw1707;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static hw1707.ContactsHandler.*;

public class Main1707 {

    public static void main(String[] args) throws IOException {

        Scanner scn0;
        String errMsg = "ОШИБКА!";
        String warnMsg = "ВНИМАНИЕ!";
        String repeat = "Попробуйте еще раз.";
        boolean needExit = false;

        while (true) {
            System.out.println("ГЛАВНОЕ МЕНЮ");
            System.out.println("1 - Создать запись");
            System.out.println("2 - Показать все записи");
            System.out.println("3 - Редактировать запись");
            System.out.println("4 - Удалить запись");
            System.out.println("0 - Выход");
            System.out.print("Выберите действие: ");
            scn0 = new Scanner(System.in);
            String choice = scn0.next();

            switch (choice) {
                case "0":
                    System.out.println("\nДо свидания!");
                    needExit = true;
                    break;
                case "1": // Создать запись
                    try {
                        Person newPerson = inputPersonData();
                        if(newPerson == null){
                            needExit = true;
                            break;
                        }
                        File contacts = new File(contactsFileName);
                        if (!contacts.exists()) {
                            if (!contacts.createNewFile()) {
                                System.out.println(errMsg + " Невозможно создать файл " + contactsFileName + ". Вероятно, диск переполнен");
                                needExit = true;
                                break;
                            }
                        }
                        FileWriter fw = new FileWriter(contactsFileName, true);
                        fw.write(newPerson.toFileString());
                        fw.close();
                    } catch (Exception e) {
                        System.out.println(errMsg + " Новая запись не создана! " + e.getMessage());
                    }
                    break;
                case "2": // Показать все записи
                    try {
                        ContactsHandler ch = new ContactsHandler();
                        ArrayList<String> recordsToShow = ch.getRecords();
                        System.out.println("\nКоличество записей в списке контактов: " + recordsToShow.size());
                        for (String rec : recordsToShow) {
                            ArrayList<String> fields = parseStr(rec.toCharArray(), rec.length(), fieldDelim);
                            Person person = new Person(Integer.parseInt(fields.get(0)), fields.get(1), fields.get(2), Integer.parseInt(fields.get(3)));
                            System.out.println(person.toString());
                        }
                        System.out.println("------------------------------------------");
                    } catch (Exception e) {
                        System.out.println(errMsg + " Показ записей невозможен! " + e.getMessage());
                    }
                    break;
                case "3": // Редактировать запись
                    System.out.println(getLastId());
                    break;
                case "4": // Удалить запись
                    break;
                default:
                    System.out.println(errMsg + " Введено недопустимое значение. " + repeat);
            }
            if (needExit) break;
        }
    }
}

