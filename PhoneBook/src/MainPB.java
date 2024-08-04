import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class MainPB {
    public static String inputPhrase = "Введите значение: ";
    public static String errMsg = "ОШИБКА! ";
    public static String warnMsg = "ВНИМАНИЕ! ";
    public static String wrongValue = "Введено неверное значение.";
    public static String inputIsEmpty = "Введено пустое значение.";
    public static String tryAgain = "\nПопробуйте еще раз.";

    public static String workDirName = "DB";

    static void checkWorkDir() throws NullPointerException, SecurityException {
        File dir = new File(workDirName);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void main(String[] args) {

        Scanner scn1, scn2, scn3, scn4, scn5, scn6, scn7, scn8, scn9;
        while (true) { //login menu
            MenuHandler mh = new MenuHandler("LoginMenu");
            System.out.println(mh.getMenuString());
            System.out.print(inputPhrase);
            scn1 = new Scanner(System.in);
            boolean needBreak = false;
            try {
                int response = scn1.nextInt();
                if (response == mh.getMenuExitValue())
                    break;
                try {
                    UserDataBase userDB;// = new UserDataBase();
                    String login, name, email, password;
                    switch (response) {
                        case 1: //Sign In
                            //запрашиваем имя/пароль пользователя
                            //выводим меню контактов для авторизовавшегося пользователя
                            break;
                        case 2: //Sign Up
                            userDB = new UserDataBase();
                            while (true) {
                                System.out.print("Придумайте и введите логин: ");
                                scn2 = new Scanner(System.in);
                                login = scn2.next();
                                if (login.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                } else {
                                    if (userDB.isLoginExists(login)) {
                                        System.out.println(warnMsg + "Введенный логин уже существует." + tryAgain);
                                    } else
                                        break;
                                }
                            }
                            while (true) {
                                System.out.print("Введите ваше имя: ");
                                scn3 = new Scanner(System.in);
                                name = scn3.nextLine();
                                if (name.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                } else
                                    break;
                            }
                            /*while (true) {
                                System.out.println("Введите ваш email: ");
                                scn4 = new Scanner(System.in);
                                email = scn4.next();
                                if (email.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                } else {
                                    if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+$")) {
                                        System.out.println(warnMsg + "Введенное значение не соответствует формату адреса электронной почты." + tryAgain);
                                    } else
                                        break;
                                }
                            }*/
                            while (true) {
                                System.out.print("Придумайте и введите пароль: ");
                                scn5 = new Scanner(System.in);
                                password = scn5.next();
                                if (password.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                } else {
                                    scn6 = new Scanner(System.in);
                                    System.out.print("Введите пароль еще раз: ");
                                    String password2 = scn6.next();
                                    if (password2.equals(password))
                                        break;
                                    else
                                        System.out.println(warnMsg + "Пароли не совпадают." + tryAgain);
                                }
                            }
                            userDB.addUser(new User(login, name, password));
                            break;
                        case 3: //Print Users
                            userDB = new UserDataBase();
                            String usersNames = userDB.getUsersNames();
                            if(usersNames.isEmpty()){
                                System.out.println(warnMsg + "Нет зарегистрированных пользователей.");
                            }else {
                                System.out.println("Список зарегистрированных пользователей:\n" + usersNames);
                            }
                            break;
                        default:
                            System.out.println(warnMsg + wrongValue + tryAgain);
                    }
                } catch (Exception ex) {
                    System.out.println(errMsg + "Не удалось прочитать файл пользователей\n" + ex.getMessage());
                    break;
                }
            } catch (Exception ime) {
                System.out.println(errMsg + ime.getMessage() + tryAgain);
                continue;
            }
            if (needBreak) break;
        }
        System.out.println("\n\nДо новых встреч!");
    }
}
