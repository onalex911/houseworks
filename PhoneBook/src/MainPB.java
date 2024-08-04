import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class MainPB {
    public static String errMsg = "ОШИБКА! ";
    public static String warnMsg = "ВНИМАНИЕ! ";

    public static String workDirName = "DB";
    public static final int MaxAttempts = 5;

    static void checkWorkDir() throws NullPointerException, SecurityException {
        File dir = new File(workDirName);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void main(String[] args) {
        MenuHandler mh = new MenuHandler("LoginMenu");
        mh.execute("LoginMenu");
        //Scanner scn1, scn2, scn3, scn4, scn5, scn6, scn7, scn8, scn9;
        /*
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
                            userDB = new UserDataBase();
                            User currentUser;
                            int attempt = 0;
                            //запрашиваем имя/пароль пользователя
                            while (true) {
                                System.out.print("Введите логин: ");
                                scn2 = new Scanner(System.in);
                                login = scn2.next();
                                if (login.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                } else {
                                    if (!userDB.isLoginExists(login)) {
                                        System.out.println(warnMsg + "Введенный логин не существует." + tryAgain);
                                    } else {
                                        currentUser = userDB.getUserByLogin(login);
                                        System.out.print("Введите пароль: ");
                                        scn3 = new Scanner(System.in);
                                        password = scn3.next();
                                        if (password.isEmpty()) {
                                            System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                        } else {
                                            if (!password.equals(currentUser.getPasswordHash())) {
                                                System.out.println(warnMsg + "Введен неверный пароль." + tryAgain);
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                }
                                if(++attempt >= MaxAttempts){
                                    System.out.println(errMsg+"Вы исчерпали количество попыток для авторизации. Программа будет закрыта!");
                                    needBreak = true;
                                    break;
                                }
                            }
                            if(!needBreak){
                                //выводим меню контактов для авторизовавшегося пользователя

                            }
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
//                            while (true) {
//                                System.out.println("Введите ваш email: ");
//                                scn4 = new Scanner(System.in);
//                                email = scn4.next();
//                                if (email.isEmpty()) {
//                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
//                                } else {
//                                    if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+$")) {
//                                        System.out.println(warnMsg + "Введенное значение не соответствует формату адреса электронной почты." + tryAgain);
//                                    } else
//                                        break;
//                                }
//                            }
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
        }*/
        System.out.println("\n\nДо новых встреч!");
    }
}
