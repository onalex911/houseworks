package hw1507;

import hw0307.Main0307;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main1507 {

    static int count = 0;
    static int maxLevel = 10;

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

    static void listDir(File directory, int maxLevel) {
        printFileInfo(directory, Main1507.count++);
        File[] innerFiles = directory.listFiles();
        ArrayList<File> dirs = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();
        int countDir = 0;
        int countFile = 0;
        if (innerFiles != null && maxLevel > 0) {
            for (File innerFile : innerFiles) {
                if (innerFile.isDirectory()) {
                    dirs.add(innerFile);
                    countDir++;
                } else {
                    files.add(innerFile);
                    countFile++;
                }
                //printFileInfo(innerFile, 0);
            }
            System.out.println("Папка содержит: вложенных папок – " + countDir + ", файлов – " + countFile);
            for (File curDir : dirs) {
                //printFileInfo(curDir, 0);
                listDir(curDir, maxLevel - 1);
            }
            for (File curFile : files)
                //printFileInfo(curFile,0);
                listDir(curFile, maxLevel - 1);
        }
        Main1507.count--;
    }

    static void printFileInfo(File file, int level) {
        Date date = new Date(file.lastModified());
        String typeTxt = file.isDirectory() ? "DIR" : "FILE";
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        float sizeToShow = file.length();
        String dim = "";
        if (file.length() > 1000000) {
            sizeToShow = (float) file.length() / 1000000;
            dim = "Mb";
        } else if (file.length() > 1000) {
            sizeToShow = (float) file.length() / 1000;
            dim = "Kb";
        }
        System.out.printf("%s\t%s\t%.1f %s, %s\n", file.getAbsolutePath(), typeTxt, sizeToShow, dim, date);
    }

    static ArrayList<MaskParts> getMaskArray(String mask) {
        // ключ = 1, если после части маски любой единичный символ ('_')
        // 2 - если после данной части маски идет серия любых символов ('*')
        // 0 - если часть последняя в маске
        ArrayList<MaskParts> out = new ArrayList<>();
        String part = "";
        char[] maskChars = mask.toCharArray();
        int type = 0;
        for (int i = 0; i < maskChars.length; i++) {
            if (maskChars[i] == '_' || maskChars[i] == '*') {
                type = maskChars[i] == '_' ? 1 : 2;
                out.add(new MaskParts(type, part));
                part = "";
                type = 0;
                //i += 2;
            } else {
                part += maskChars[i];
            }
        }
        if (!part.isEmpty())
            out.add(new MaskParts(type, part));
        return out;
    }

    static boolean isMatchesMask(String src, ArrayList<MaskParts> maskParts) {
        int good = 0;
        int start = 0;
        boolean isExit = false;

        for (int i = 0; i < maskParts.size(); i++) {
            int curType = maskParts.get(i).getType();
            int nextType = i < maskParts.size() - 1 ? maskParts.get(i + 1).getType() : -1;
            String curPart = maskParts.get(i).getPart();
            String nextPart = i < maskParts.size() - 1 ? maskParts.get(i + 1).getPart() : "";
            switch (curType) {
                case 0:
                    if (src.substring(start).equals(curPart))
                        good++;
                    isExit = true;
                    break;
                case 1:
                    String substr = "";
                    if (!curPart.isEmpty()) {
                        if (src.substring(start, start + curPart.length()).equals(curPart)) {
                            int beg = start + curPart.length() + 1;
                            substr = src.substring(beg, beg + nextPart.length());
                            if (substr.equals(nextPart)) {
                                good++;
                                start = beg;
                            }
                        }
                    } else {
                        substr = src.substring(start + 1, start + 1 + nextPart.length());
                        if (substr.equals(nextPart)) {
                            good++;
                            start++;
                        }
                    }
                    break;
                case 2:
                    if (!curPart.isEmpty()) {
                        if (src.substring(start, start + curPart.length()).equals(curPart)) {
                            if (nextType >= 0 && src.indexOf(nextPart) > curPart.length()) {
                                good++;
                                start = src.indexOf(nextPart);
                            } else {
//                                if(!src.substring(start,src.length()).isEmpty()){
                                good++;
                                isExit = true;
//                                }
                            }
                        }
                    } else {
                        if (src.indexOf(nextPart) > i + 1) {
                            good++;
                            start = src.indexOf(nextPart);
                        }
                    }
                    break;
            }
            if (isExit) break;
        }
        return maskParts.size() == good;
    }

    static void findFilesByMask(File fileForSearch, String mask) {
        ArrayList<MaskParts> maskParts = getMaskArray(mask);
        File[] innerFiles = fileForSearch.listFiles();
        if (innerFiles != null) {
            for (File file : innerFiles) {
                if (mask.equals("*") || (mask.equals("_") && file.getName().length() == 1) || isMatchesMask(file.getName(), maskParts)) {
                    printFileInfo(file, Main1507.count++);
                }
                findFilesByMask(file, mask);
            }
        }
        Main1507.count--;
    }

    public static void main(String[] args) {
        Scanner scn0;
        Scanner scn1;
        String folderName;
        String fileName;
        String workDir = "Main1507";
        File workDirFile = new File(workDir);
        if (!workDirFile.exists())
            workDirFile.mkdir();
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
            System.out.println("6 - Поиск файлов по маске");
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
                            File folder = new File(workDir + "/" + folderName);
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
                            File file = new File(workDir + "/" + fileName);
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
                            File fileToDel = new File(workDir + "/" + response);
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
                            File obj = new File(workDir + "/" + fileToRename);
                            if (obj.exists()) {
                                String objType = obj.isDirectory() ? "папки" : "файла";
                                Scanner scn6 = new Scanner(System.in);
                                System.out.print("Введите новое имя " + objType + ": ");
                                String newName = scn6.next();
                                if (!newName.trim().isEmpty() && obj.renameTo(new File(workDir + "/" + newName))) {
                                    System.out.printf("Переименование %s %s в %s произошло успешно!\n", objType, fileToRename, newName);
                                } else {
                                    System.out.printf("ВНИМАНИЕ! Переименование %s %s НЕ ПРОИЗОШЛО!\n", objType, fileToRename);
                                }
                            } else {
                                System.out.println("ВНИМАНИЕ! Файл/папка с именем " + fileToRename + " не существует! Попробуйте еще раз.");
                            }
                        } else {
                            System.out.println("ВНИМАНИЕ! Введено пустое значение! Попробуйте еще раз.");
                        }
                        break;
                    case 5: // Просмотр
                        File file = new File(workDir);
//                        File file = new File("G:\\");
                        Scanner scn6 = new Scanner(System.in);
                        System.out.print("Вывести информацию о вложнных папках и файлах? (y/n): ");
                        String respFull = scn6.next();
                        Scanner scn7 = new Scanner(System.in);
                        System.out.print("Введите кол-во вложенных уровней поддиректорий (относительно " + file.getPath() + "): ");
                        int respDepth = scn7.nextInt();
                        if (respFull.equals("y")) {
                            listDir(file, respDepth);
                        } else {
//                            File[] innerFiles = file.listFiles();
//                            int countDir = 0;
//                            int countFile = 0;
                            listDir(file, 1);
                        }
                        break;
                    case 6:
                        System.out.print("Введите директорию, в которой нужно найти файлы и папки: ");
                        Scanner scn8 = new Scanner(System.in);
                        String curDir = scn8.next();
                        File fileForSearch = new File(curDir);
                        if (fileForSearch.exists()) {
                            System.out.print("Введите маску для поиска: ");
                            Scanner scn9 = new Scanner(System.in);
                            String mask = scn9.next();
                            Main1507.count = 0;
                            findFilesByMask(fileForSearch, mask);
                        } else {
                            System.out.println("Директория " + curDir + " не существует.");
                        }
                        break;
                    default:
                        System.out.println("ВНИМАНИЕ! Введено неверное значение.");
                }
            } catch (Exception e) {
                System.out.println("ОШИБКА! " + e.getMessage());
            }
        }
    }
}
