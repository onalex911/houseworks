import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHandler {
    private String menuString;
    private int menuMaxValue;
    private int menuExitValue;
    private final HashMap<String, MenuSettings> MenuMap = new HashMap<>();
    private User currentUser;
    private String currentMenuId;
    public static String errMsg = "ОШИБКА! ";
    public static String warnMsg = "ВНИМАНИЕ! ";
    public static String inputPhrase = "Введите значение: ";
    public static String wrongValue = "Введено неверное значение.";
    public static String inputIsEmpty = "Введено пустое значение.";
    public static String tryAgain = "\nПопробуйте еще раз.";
    public static final int MaxAttempts = 5;
    public static final String SortingAZNameHeader = "  _________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |        Сортировка по Имени от А до Я            |\n" +
            " |_________________________________________________|";

    private final String SortingZANameHeader = "  _________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |        Сортировка по Имени от Я до А            |\n" +
            " |_________________________________________________|";
    public static final String SortingAZSurNameHeader = "  _________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |       Сортировка по Фамилии от А до Я           |\n" +
            " |_________________________________________________|";

    public static final String SortingZASurNameHeader = "  _________________________________________________\n" +
            " |                                                 |\n" +
            " |             << Телефонная книга >>              |\n" +
            " |       Сортировка по Фамилии от Я до А           |\n" +
            " |_________________________________________________|";

    public static final String Sorting09Header = "  _________________________________________________\n" +
            " |                                                 |\n" +
            " |             << Телефонная книга >>              |\n" +
            " |         Сортировка по Номеру от 0 до 9          |\n" +
            " |_________________________________________________|";

    public static final String Sorting90Header = "  _________________________________________________\n" +
            " |                                                 |\n" +
            " |             << Телефонная книга >>              |\n" +
            " |         Сортировка по Номеру от 9 до 0          |\n" +
            " |_________________________________________________|";

    {
        String loginMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
                " |              Выберите действие :                |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | 0 - Exit           ( Exit the program )         |\n" +
                " |-------------------------------------------------|\n" +
                " | 1 - Sign In        ( Enter go to existing user )|\n" +
                " |-------------------------------------------------|\n" +
                " | 2 - Sign Up        ( Register new user )        |\n" +
                " |-------------------------------------------------|\n" +
                " | 3 - Print Users    ( Print existing user )      |\n" +
                " |_________________________________________________|\n";
        MenuMap.put("LoginMenu", new MenuSettings(loginMenu, 3, 0));

    }

    MenuHandler(String nameMenu) {
        for (String name : MenuMap.keySet()) {
            if (name.equals(nameMenu)) {
                menuString = MenuMap.get(name).getMenuText();
                menuMaxValue = MenuMap.get(name).getMaxValue();
                menuExitValue = MenuMap.get(name).getExitValue();
                break;
            }
        }
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        String mainMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |                Основное меню                    |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | 0 - Save and Exit ( Save and Exit the program)  |\n" +
                " |-------------------------------------------------|\n" +
                " | 1 - Contacts      ( Add / Edite / Delete)       |\n" +
                " |-------------------------------------------------|\n" +
                " | 2 - Print         ( All / Specific)             |\n" +
                " |-------------------------------------------------|\n" +
                " | 3 - Sorting       ( Name / Surname / Number )   |\n" +
                " |-------------------------------------------------|\n" +
                " | 4 - Search        ( by Name / Surname / Number )|\n" +
                " |-------------------------------------------------|\n" +
                " | 5 - Come Back     ( Save and Exit phonebook )   |\n" +
                " |_________________________________________________|";
        MenuMap.put("MainMenu", new MenuSettings(mainMenu, 5, 0));
        String contactMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |           Основное меню -> Контакты             |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Come back    ( Return to the main menu)    |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Add          ( Add new contact )           |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Edit         ( Edit contact )              |\n" +
                " |-------------------------------------------------|\n" +
                " |  2 - Delete       ( Delete contact )            |\n" +
                " |_________________________________________________|";
        MenuMap.put("ContactMenu", new MenuSettings(contactMenu, 2, -1));
        String contactEditMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |    Основное меню -> Контакты -> Редактировать   |\n" +
                " |         Что требуется отредактировать ?         |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Come back    ( Return to the Contact menu) |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Name         ( Edit contact name )         |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Surname      ( Edit contact surname )      |\n" +
                " |-------------------------------------------------|\n" +
                " |  2 - Number       ( Edit contact number )       |\n" +
                " |-------------------------------------------------|\n" +
                " |  3 - All          ( Edit name/surname/number )  |\n" +
                " |_________________________________________________|";
        MenuMap.put("ContactEditMenu", new MenuSettings(contactEditMenu, 3, -1));
        String printMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |            Основное меню -> Печать              |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Come back    ( Return to the main menu )   |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Print        ( All )                       |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Print        ( Specific )                  |\n" +
                " |_________________________________________________|";
        MenuMap.put("PrintMenu", new MenuSettings(printMenu, 1, -1));
        String sortingMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |         Основное меню -> Сортировка             |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Come back    ( Return to the main menu )   |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Name         ( Sort  A-Z / Z-A )           |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Surname      ( Sort  A-Z / Z-A )           |\n" +
                " |-------------------------------------------------|\n" +
                " |  2 - Number       ( Sort  1-9 / 9-1 )           |\n" +
                " |_________________________________________________|";
        MenuMap.put("SortingMenu", new MenuSettings(sortingMenu, 2, -1));
        String sortingNameMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |     Основное меню -> Сортировка -> по Имени     |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Come back    ( Return to the Sorting )     |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Name         ( Sort  A - Z )               |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Name         ( Sort  Z - A )               |\n" +
                " |_________________________________________________|";
        MenuMap.put("SortingNameMenu", new MenuSettings(sortingNameMenu, 1, -1));
        String sortingSurnameMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |    Основное меню -> Сортировка -> по Фамилии    |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Come back    ( Return to the Sorting )     |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Surname         ( Sort  A - Z )            |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Surname         ( Sort  Z - A )            |\n" +
                " |_________________________________________________|";
        MenuMap.put("SortingSurnameMenu", new MenuSettings(sortingSurnameMenu, 1, -1));
        String sortingNumberMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |    Основное меню -> Сортировка -> по Номеру     |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Come back    ( Return to the Sorting )     |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Number       ( Sort  1 - 9 )               |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Number       ( Sort  9 - 1 )               |\n" +
                " |_________________________________________________|";
        MenuMap.put("SortingNumberMenu", new MenuSettings(sortingNumberMenu, 1, -1));
        String searchMenu = "  _________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
                " |" + insertName("Владелец: "+this.currentUser.getName()) + "|\n" +
                " |            Основное меню -> Поиск               |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Come back  ( Return to the Main menu )     |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Name       ( Search by Name  )             |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Surname    ( Search by Surname )           |\n" +
                " |-------------------------------------------------|\n" +
                " |  2 - Number     ( Search by  Number )           |\n" +
                " |_________________________________________________|\n";
        MenuMap.put("SearchMenu", new MenuSettings(searchMenu, 2, -1));
    }

    public String getMenuString() {
        return menuString;
    }

    public int getMenuMaxValue() {
        return menuMaxValue;
    }

    public int getMenuExitValue() {
        return menuExitValue;
    }

    public boolean execute(String menuId) {
        this.currentMenuId = menuId;
        switch (currentMenuId) {
            case "LoginMenu":
                doLoginMenu();
                break;
            case "MainMenu":
                return doMainMenu();
                //break;
            case "ContactMenu":
                doContactMenu();
                break;
            case "ContactEditMenu":
                doContactEditMenu();
                break;
            case "PrintMenu":
                doPrintMenu();
                break;
            case "SortingMenu":
                doSortingMenu();
                break;
            case "SortingNameMenu":
                doSortingNameMenu();
                break;
            case "SortingSurnameMenu":
                doSortingSurnameMenu();
                break;
            case "SortingNumberMenu":
                doSortingNumberMenu();
                break;
            case "SearchMenu":
                doSearchMenu();
                break;
            default:
                System.out.println(errMsg+"Неверный ключ меню.");
        }
        return true; //выход в предыдущее меню
    }

    private void doLoginMenu() throws InputMismatchException {
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
                            userDB = new UserDataBase();
                            User currentUser = null;
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
                                if (++attempt >= MaxAttempts) {
                                    System.out.println(errMsg + "Вы исчерпали количество попыток для авторизации. Программа будет закрыта!");
                                    needBreak = true;
                                    break;
                                }
                            }
                            if (!needBreak) {
                                //выводим меню контактов для авторизовавшегося пользователя
                                mh.setCurrentUser(currentUser);
                                needBreak =!mh.execute("MainMenu");
                                //needBreak=true;
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
                            if (usersNames.isEmpty()) {
                                System.out.println(warnMsg + "Нет зарегистрированных пользователей.");
                            } else {
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
    }

    private boolean doMainMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return false;
            switch (resp){
                case 1: doContactMenu();
                break;
                case 2: doPrintMenu();
                break;
                case 3: doSortingMenu();
                    break;
                case 4: doSearchMenu();
                    break;
                case 5: return true;
            }
        }
    }

    private void doContactMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return;
            if (resp == 1) {
                execute("ContactEditMenu");
            }

            System.out.println("Contact Edit Menu working...");
        }
    }

    private void doContactEditMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return;

            System.out.println("Menu working ...");
        }
    }

    private void doPrintMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return;


            System.out.println("Menu working ...");
        }
    }

    private void doSortingMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return;

            System.out.println("Menu working ...");
        }
    }

    private void doSortingNameMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return;

            System.out.println("Contact Edit Menu working ...");
        }
    }

    private void doSortingSurnameMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return;

            System.out.println("Menu working ...");
        }
    }

    private void doSortingNumberMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return;

            System.out.println("Menu working ...");
        }
    }

    private void doSearchMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(currentMenuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(currentMenuId).getExitValue())
                return;

            System.out.println("Menu working ...");
        }
    }

    public static String insertName(String name) {
        int maxWidth = 49;
        if (name.length() < maxWidth) {
            int begSp = (maxWidth - name.length()) / 2;
            int endSp = maxWidth - name.length() - begSp;
            return " ".repeat(begSp) + name + " ".repeat(endSp);
        } else {
            return name;
        }
    }
}
