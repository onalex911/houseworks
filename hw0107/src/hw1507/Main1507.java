package hw1507;

import hw0307.Main0307;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Main1507 {

    static int count = 0;

    static boolean isCorrectName(String name) {
        char[] chars = name.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\\' || chars[i] == ':' || chars[i] == '?' || chars[i] == '$' || chars[i] == '\"' || chars[i] == '<' || chars[i] == '>' || chars[i] == '|')
                return false;
        }
        return true;
    }

    static boolean containsSlash(String name) {
        char[] chars = name.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/') return true;
        }
        return false;
    }

    static void deleteDir(File directory) {
        if (directory.isDirectory()) {
            File[] innerFiles = directory.listFiles();
            if (innerFiles != null) {
                for (File obj : innerFiles) {
                    deleteDir(obj);
                }
            }
        }
        //return directory.delete() ? 1 : 0;
        if (directory.delete())
            Main1507.count++;
    }

    public static void main(String[] args) {

        Scanner scn0;
        Scanner scn1;
        String folderName;
        String fileName;
        while (true) {
            System.out.println("\n-----------------------------------------");
            System.out.println(" СОЗДАНИЕ И УДАЛЕНИЕ ДИРЕКТОРИЙ И ФАЙЛОВ");
            System.out.println("-----------------------------------------\n");
            System.out.println("Основное меню");
            System.out.println("1 - Добавить Папку");
            System.out.println("2 - Добавить Файл");
            System.out.println("3 - Удалить");
            System.out.println("4 - Переименовать");
            System.out.println("5 - Просмотр");
            System.out.println("0 - Выход");
            System.out.print("Выберите действие: ");

            scn0 = new Scanner(System.in);
            try {
                int resp = scn0.nextInt();
                if (resp == 0) {
                    System.out.println("\nДо свидания!");
                    break;
                }
                Boolean flag = false;
                switch (resp) {
                    case 1: // Добавить Папку
                        System.out.print("Введите имя папки или имена вложенных папок, разделенных знаком \"/\": ");
                        scn1 = new Scanner(System.in);
                        folderName = scn1.next();
                        if (isCorrectName(folderName)) {
                            File folder = new File(folderName);
                            //File fldrs = new File("q1/w2/e3/r4");
                            try {
                                if (containsSlash(folderName))
                                    flag = folder.mkdirs();
                                else
                                    flag = folder.mkdir();
                            } catch (Exception p) {
                                System.out.println("ОШИБКА при создании папки(-ок). " + p.toString());
                            }
                            if (!flag) System.out.println("Папка " + folderName + " не создана.");
                        } else {
                            System.out.println("ВНИМАНИЕ! Имя папки содержит недопустимые символы! Папка не создана.");
                        }
                        break;
                    case 2: // Добавить Файл
                        System.out.print("Введите имя файла: ");
                        scn1 = new Scanner(System.in);
                        fileName = scn1.next();
//                        if (isCorrectName(fileName) && !containsSlash(fileName)) {
                        if (isCorrectName(fileName)) {
                            File file = new File(fileName);
                            try {
                                flag = file.createNewFile();
                            } catch (Exception p) {
                                System.out.println("ОШИБКА при создании файла. " + p.getMessage());
                            }
                            if (!flag) System.out.println("ВНИМАНИЕ! Файл " + fileName + " НЕ СОЗДАН!");
                        } else {
                            System.out.println("ВНИМАНИЕ! Имя файла содержит недопустимые символы! Файл не создан.");
                        }
                        break;
                    case 3: // Удалить
                        System.out.print("Введите имя файла/папки для удаления: ");
                        Scanner scn3 = new Scanner(System.in);
                        Scanner scn4;
                        String response = scn3.next();
                        if (!response.trim().isEmpty()) {
                            File fileToDel = new File(response);
                            if (fileToDel.exists()) {
                                if (fileToDel.isFile()) {
                                    System.out.print("Вы действительно хотите удалить файл: " + fileToDel.getName() + "? (y/n): ");
                                    scn4 = new Scanner(System.in);
                                    String respYesNo = scn4.next();
                                    if (respYesNo.equals("y") && fileToDel.delete()) {
                                        System.out.println("Файл: " + fileToDel.getName() + " УДАЛЕН.");
                                    } else {
                                        System.out.println("ВНИМАНИЕ! Файл: " + fileToDel.getName() + " не удален.");
                                    }
                                } else if (fileToDel.isDirectory()) {
                                    File[] innerFiles = fileToDel.listFiles();
                                    if (innerFiles != null) {
                                        System.out.print("Папка " + fileToDel.getName() + " содержит вложенные папки и/или файлы. Удалить их все? (y/n): ");
                                        scn4 = new Scanner(System.in);
                                        String respYesNo = scn4.next();
                                        if (respYesNo.equals("y")) {
                                            deleteDir(fileToDel);
                                            System.out.println("Удалено " + Main1507.count + " объектов.");
                                            Main1507.count = 0;
                                        } else {
                                            System.out.println("ВНИМАНИЕ! Папка: " + fileToDel.getName() + " не удалена.");
                                        }

                                    } else {
                                        System.out.print("Вы действительно хотите удалить папку: " + fileToDel.getName() + "? (y/n): ");
                                        scn4 = new Scanner(System.in);
                                        String respYesNo = scn4.next();
                                        if (respYesNo.equals("y") && fileToDel.delete()) {
                                            System.out.println("Папка: " + fileToDel.getName() + " УДАЛЕНА.");
                                        } else {
                                            System.out.println("ВНИМАНИЕ! Папка: " + fileToDel.getName() + " не удалена.");
                                        }
                                    }
                                }
                            } else {
                                System.out.println("ВНИМАНИЕ! Файл/папка " + response + " не существует");
                            }
                        } else {
                            System.out.println("ВНИМАНИЕ! Введено пустое значение! Попробуйте еще раз.");
                        }
                        break;
                    case 4: // Переименовать
                        Scanner scn5 = new Scanner(System.in);
                        System.out.print("Введите имя файла/паки, котороую вы хотите переименовать: ");
                        String fileToRename = scn5.next();
                        if (!fileToRename.trim().isEmpty()) {
                            File obj = new File(fileToRename);
                            if (obj.exists()) {
                                String objType = obj.isDirectory() ? "папки" : "файла";
                                Scanner scn6 = new Scanner(System.in);
                                System.out.print("Введите новое имя "+objType+": ");
                                String newName = scn6.next();
                                if(!newName.trim().isEmpty() && obj.renameTo(new File(newName))){
                                    System.out.printf("Переименование %s %s в %s произошло успешно!\n",objType,fileToRename,newName);
                                }else{
                                    System.out.printf("ВНИМАНИЕ! Переименование %s %s НЕ ПРОИЗОШЛО!\n",objType,fileToRename);
                                }
                            } else {
                                System.out.println("ВНИМАНИЕ! Файл/папка с именем " + fileToRename + " не существует! Попробуйте еще раз.");
                            }
                        } else {
                            System.out.println("ВНИМАНИЕ! Введено пустое значение! Попробуйте еще раз.");
                        }
                        break;
                    case 5: // Просмотр
                        File file = new File(".");
                        File[] files = file.listFiles();
                        assert files != null;
                        for (File fl : files) {
                            long secs = fl.lastModified();
                            Timestamp ts = new Timestamp(secs);

//                            Date date = new Date(ts.getTime());
                            Date date = new Date(fl.lastModified());

//                            int min = (int)(secs%60);
//                            System.out.printf("%s\t\t\t%0.3f Kb, %0d.%0d.%0d %0d:%0d:%0d\n",fl.getName(),fl.length(),);
                            System.out.printf("%s\t\t\t%.3f Kb, %s\n", fl.getName(), (float) fl.length() / 1000, date);
                        }
                        break;
                    default:
                        System.out.println("ВНИМАНИЕ! Введено неверное значение.");
                }
            } catch (Exception e) {
                System.out.println("ВНИМАНИЕ! Введен недопустимый символ. Попробуйте еще раз." + e.getMessage());
            }

        }
    }
}
