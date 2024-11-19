package hw1707;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            System.out.println("\n==== ГЛАВНОЕ МЕНЮ ====");
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
                        if (newPerson == null) {
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
                        String strToWrite = newPerson.toFileString();
                        fw.write(strToWrite);
                        fw.close();
                    } catch (Exception e) {
                        System.out.println(errMsg + " Новая запись не создана! " + e.getMessage());
                    }
                    break;
                case "2": // Показать все записи
                    try {
                        ContactsHandler ch = new ContactsHandler();
                        ArrayList<Person> persons = ch.getSortedRecords();
                        printPersons(persons);
                        //ch.printAllRecords();
                        System.out.println("------------------------------------------");
                    } catch (Exception e) {
                        System.out.println(errMsg + " Показ записей невозможен! " + e.getMessage());
                    }
                    break;
                case "3": // Редактировать запись
                    try {
                        ContactsHandler ch = new ContactsHandler();
                        printPersons(ch.getSortedRecords());
                        System.out.print("\nВыберите номер записи для редактирования: ");
                        Scanner scn3 = new Scanner(System.in);
                        Scanner scn4, scn5, scn6, scn7;
                        int id = scn3.nextInt();
                        Person person = ch.getPersonById(id);
                        if (person != null) {
                            while (true) {
                                System.out.println("\n---- РЕДАКТИРОВАНИЕ ЗАПИСИ ----");
                                System.out.println(" 1 - имя (" + person.getName() + ")");
                                System.out.println(" 2 - фамилия (" + person.getSurname() + ")");
                                System.out.println(" 3 - возраст (" + person.getAge() + ")");
                                System.out.println(" 0 - выход из редактирования");
                                System.out.print("Введите номер операции: ");
                                scn4 = new Scanner(System.in);
                                int resp = scn4.nextInt();
                                if (resp == 0) {
                                    //needExit = true;
                                    break;
                                }
                                switch (resp) {
                                    case 1:
                                        System.out.print("Введите новое значение имени: ");
                                        scn5 = new Scanner(System.in);
                                        String newName = scn5.nextLine();
                                        person.setName(newName);
                                        ch.refreshPerson(person);
                                        break;
                                    case 2:
                                        System.out.print("Введите новое значение фамилии: ");
                                        scn6 = new Scanner(System.in);
                                        String newSurName = scn6.nextLine();
                                        person.setSurname(newSurName);
                                        ch.refreshPerson(person);
                                        break;
                                    case 3:
                                        System.out.print("Введите новое значение возраста: ");
                                        scn7 = new Scanner(System.in);
                                        int newAge = scn7.nextInt();
                                        person.setAge(newAge);
                                        ch.refreshPerson(person);
                                        break;
                                    default:
                                        System.out.println(warnMsg + " Введено неверное значение. " + repeat);
                                }
                                //if (needExit) break;
                            }
                        }else{
                            System.out.println(warnMsg + " Нет записи с номером " + id + ". " + repeat);
                        }
                    } catch (Exception e) {
                        System.out.println(errMsg + " Редактирование записей невозможно! " + e.getMessage());
                    }
                    break;
                case "4": // Удалить запись
                    try {
                        ContactsHandler ch = new ContactsHandler();
                        printPersons(ch.getSortedRecords());
                        System.out.print("\nВыберите номер записи для удаления: ");
                        Scanner scn8 = new Scanner(System.in);
                        int resp = scn8.nextInt();
                        ch.getThisPersonById(resp);
                        if (ch.getCurrentPerson() == null) {
                            System.out.println(warnMsg + " Нет записи с номером " + resp + ". " + repeat);
                            break;
                        }
                        System.out.print("Вы действительно хотите удалить запись номер " + resp + "? (y/n): ");
                        Scanner scn9 = new Scanner(System.in);
                        if (scn9.next().equals("y")) {
                            ch.deletePerson();
                            System.out.println("Запись номер " + resp + " успешно удалена.");
                        } else {
                            System.out.println(warnMsg + " Запись номер " + resp + " НЕ УДАЛЕНА!");
                        }
                    } catch (Exception e) {
                        System.out.println(errMsg + " Удаление записей невозможно! " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println(errMsg + " Введено недопустимое значение. " + repeat);
            }
            if (needExit) break;
        }
    }
}

