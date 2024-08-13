import java.io.IOException;
import java.util.*;

public class MenuHandler {
    private String menuName;
    private String menuString;
    private int menuMaxValue;
    private int menuExitValue;
    private final HashMap<String, MenuSettings> MenuMap = new HashMap<>();
    private User currentUser;
    //private String currentMenuId;
    public static String errMsg = "\n ОШИБКА! ";
    public static String warnMsg = "\n ВНИМАНИЕ! ";
    public static String inputPhrase = " Введите значение: ";
    public static String inputSearchRequest = " Введите поисковый запрос: ";
    public static String wrongValue = "Введено неверное значение.";
    public static String wrongPhoneNum = "Введено значение, не соответствующее формату телефонного номера";
    public static String inputIsEmpty = "Введено пустое значение.";
    public static String tryAgain = "\n Попробуйте еще раз.";
    public static String nothingDeleted = "\n Ничего не удалено.";
    private final String noUserData = "Нет данных о пользователях.";
    private final String noContactData = "Нет данных о контактах. Выбранное меню недоступно.";
    private final String noFoundContactData = "Контактов не найдено.";
    public static final int MaxAttempts = 5;
    public static final int MinPhoneLength = 3;
    public static final int MaxPhoneLength = 20;
    public static final String separator = "_____________________________________________________";

    public static final String RegisteredUsers =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |     Список зарегистрированных пользователей     |\n" +
            " |_________________________________________________|";
    public static final String SortingAZNameHeader =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |        Сортировка по Имени от А до Я            |\n" +
            " |_________________________________________________|";

    private final String SortingZANameHeader =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |        Сортировка по Имени от Я до А            |\n" +
            " |_________________________________________________|";
    public static final String SortingAZSurnameHeader =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |       Сортировка по Фамилии от А до Я           |\n" +
            " |_________________________________________________|";

    public static final String SortingZASurnameHeader =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |       Сортировка по Фамилии от Я до А           |\n" +
            " |_________________________________________________|";

    public static final String Sorting09NumberHeader =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |         Сортировка по Номеру от 0 до 9          |\n" +
            " |_________________________________________________|";

    public static final String Sorting90NumberHeader =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |         Сортировка по Номеру от 9 до 0          |\n" +
            " |_________________________________________________|";
    public static final String SearchHeader =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |               Результаты поиска                 |\n" +
            " |_________________________________________________|";
    public static final String PrintHeader =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |               Печать контактов                  |\n" +
            " |_________________________________________________|";

    {
        String loginMenu =
            " ___________________________________________________\n" +
            " |                                                 |\n" +
            " |            << Телефонная книга >>               |\n" +
            " |              Выберите действие :                |\n" +
            " |_________________________________________________|\n" +
            " |                                                 |\n" +
            " | 0 - Выход          ( Выход из программы )       |\n" +
            " |-------------------------------------------------|\n" +
            " | 1 - Авторизация    ( Вход для зарег. польз. )   |\n" +
            " |-------------------------------------------------|\n" +
            " | 2 - Регистрация    ( Зарегистрироваться )       |\n" +
            " |-------------------------------------------------|\n" +
            " | 3 - Печать польз.  ( Список загистрир. польз. ) |\n" +
            " |_________________________________________________|\n";
        MenuMap.put("LoginMenu", new MenuSettings(loginMenu, 3, 0));

    }

    public MenuHandler(String menuName) {
        this.menuName = menuName;
        for (String name : MenuMap.keySet()) {
            if (name.equals(menuName)) {
                menuString = MenuMap.get(name).getMenuText();
                menuMaxValue = MenuMap.get(name).getMaxValue();
                menuExitValue = MenuMap.get(name).getExitValue();
                break;
            }
        }
    }

    public MenuHandler(String menuName, User currentUser) {
        this(menuName);
        //this.currentUser = currentUser;
        setCurrentUser(currentUser);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        String mainMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |              << Телефонная книга >>             |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |" + insertName("Основное меню") + "|\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | 0 - Выйти из программы                          |\n" +
                " |-------------------------------------------------|\n" +
                " | 1 - Контакты    ( Доб. / Ред. / Удалить )       |\n" +
                " |-------------------------------------------------|\n" +
                " | 2 - Печать      ( Всё / Специальная )           |\n" +
                " |-------------------------------------------------|\n" +
                " | 3 - Сортировка  ( по Имени / Фамилии / Номеру ) |\n" +
                " |-------------------------------------------------|\n" +
                " | 4 - Поиск       ( по Имени / Фамилии / Номеру ) |\n" +
                " |-------------------------------------------------|\n" +
                " | 5 - Назад       ( Выход из Тел. книги )         |\n" +
                " |_________________________________________________|";
        MenuMap.put("MainMenu", new MenuSettings(mainMenu, 5, 0));
        String contactMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |" + insertName("Основное меню -> Контакты") + "|\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Назад        ( Возврат в основное меню )   |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Добавить     ( Добавить новый контакт )    |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Редактир.    ( Редактировать контакт )     |\n" +
                " |-------------------------------------------------|\n" +
                " |  2 - Удалить      ( Удаление контакта/-ов )     |\n" +
                " |_________________________________________________|";
        MenuMap.put("ContactMenu", new MenuSettings(contactMenu, 2, -1));
        String contactEditMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |" + insertName("Основное меню -> Контакты -> Редактировать") + "|\n" +
                " |         Что требуется отредактировать ?         |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Назад       ( Возврат в основное меню )    |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Имя         ( Редактировать имя )          |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Фамилию     ( Редактировать фамилию )      |\n" +
                " |-------------------------------------------------|\n" +
                " |  2 - Тел. номер  ( Редактировать тел. номер )   |\n" +
                " |-------------------------------------------------|\n" +
                " |  3 - Всё         ( Ред. имя/фамилию/тел.номер ) |\n" +
                " |_________________________________________________|";
        MenuMap.put("ContactEditMenu", new MenuSettings(contactEditMenu, 3, -1));
        String printMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |" + insertName("Основное меню -> Печать") + "|\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Назад        ( Возврат в основное меню )   |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Печать       ( Всё )                       |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Печать       ( Специальная )               |\n" +
                " |_________________________________________________|";
        MenuMap.put("PrintMenu", new MenuSettings(printMenu, 1, -1));
        String sortingMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |         Основное меню -> Сортировка             |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Назад        ( Возврат в основное меню )   |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Имя          ( от А до Я / от Я до А )     |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Фамилия      ( от А до Я / от Я до А )     |\n" +
                " |-------------------------------------------------|\n" +
                " |  2 - Тел. номер   ( от 0 до 9 / от 9 до 0 )     |\n" +
                " |_________________________________________________|";
        MenuMap.put("SortingMenu", new MenuSettings(sortingMenu, 2, -1));
        String sortingNameMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |     Основное меню -> Сортировка -> по Имени     |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Назад        ( Возврат в основное меню )   |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Имя          ( от А до Я )                 |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Имя          ( от Я до А )                 |\n" +
                " |_________________________________________________|";
        MenuMap.put("SortingNameMenu", new MenuSettings(sortingNameMenu, 1, -1));
        String sortingSurnameMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |    Основное меню -> Сортировка -> по Фамилии    |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Назад       ( Возврат в основное меню )    |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Фамилия     ( от А до Я )                  |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Фамилия     ( от Я до А )                  |\n" +
                " |_________________________________________________|";
        MenuMap.put("SortingSurnameMenu", new MenuSettings(sortingSurnameMenu, 1, -1));
        String sortingNumberMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |    Основное меню -> Сортировка -> по Номеру     |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Назад         ( Возврат в основное меню )  |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Тел. номер    ( от 0 до 9 )                |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Тел. номер    ( от 9 до 0 )                |\n" +
                " |_________________________________________________|";
        MenuMap.put("SortingNumberMenu", new MenuSettings(sortingNumberMenu, 1, -1));
        String searchMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |            Основное меню -> Поиск               |\n" +
                " |                                                 |\n" +
                " |_________________________________________________|\n" +
                " |                                                 |\n" +
                " | -1 - Назад       ( Возврат в основное меню )    |\n" +
                " |-------------------------------------------------|\n" +
                " |  0 - Имя         ( Поиск по имени )             |\n" +
                " |-------------------------------------------------|\n" +
                " |  1 - Фамилия     ( Поиск по фамилии )           |\n" +
                " |-------------------------------------------------|\n" +
                " |  2 - Тел. номер  ( Поиск по тел. номеру )       |\n" +
                " |_________________________________________________|\n";
        MenuMap.put("SearchMenu", new MenuSettings(searchMenu, 2, -1));
    }

    public boolean execute() throws InputMismatchException, IOException, NullPointerException, DataNotFoundException {
        //this.currentMenuId = menuId;
        switch (menuName) {
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
                System.out.println(errMsg + "Неверный ключ меню.");
        }
        return true; //выход в предыдущее меню
    }

    private void doLoginMenu() {
        Scanner scn1, scn2, scn3, scn4, scn5;
        while (true) { //login menu
            //MenuHandler mh = new MenuHandler("LoginMenu");

            System.out.println(menuString);
            System.out.print(inputPhrase);
            scn1 = new Scanner(System.in);
            boolean needBreak = false;
            try {
                UserDataBase userDB = new UserDataBase();
                int response = scn1.nextInt();
                if (response == menuExitValue) {
                    break;
                }

                String login, name, password;
                switch (response) {
                    case 1: //Sign In
                        if (!userDB.isExistData()) {
                            System.out.println(warnMsg + noUserData);
                            continue;
                        }
                        User currentUser = null;
                        int attempt = 0;
                        boolean isOk;
                        //запрашиваем имя/пароль пользователя
                        while (true) {
                            isOk = false;
                            System.out.print(" Введите логин: ");
                            scn2 = new Scanner(System.in);
                            login = scn2.next();
                            if (login.isEmpty()) {
                                System.out.println(warnMsg + inputIsEmpty + tryAgain);
                            } else {
                                if (userDB.isLoginExists(login)) {
                                    currentUser = userDB.getUserByLogin(login);
                                }
                                while (true) {
                                    System.out.print(" Введите пароль: ");
                                    scn3 = new Scanner(System.in);
                                    password = scn3.nextLine();
                                    if (password.isEmpty()) {
                                        System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                        continue;
                                    } else {
                                        if (currentUser != null && password.equals(currentUser.getPasswordHash())) {
                                            isOk = true;
                                            break;
                                        } else {
                                            System.out.println(warnMsg + "Введены неверные данные авторизации." + tryAgain);
                                            break;
                                        }
                                    }
                                }
                            }
                            if (isOk) {
                                System.out.println("\nДобро пожаловать, " + currentUser.getName() + "!");
                                MenuHandler mainMenu = new MenuHandler("MainMenu", currentUser);
                                needBreak = mainMenu.execute();
                                break;
                            } else if (++attempt >= MaxAttempts) {
                                System.out.println(errMsg + "Вы исчерпали количество попыток для авторизации. Программа будет закрыта!");
                                needBreak = true;
                                break;
                            }
                        }
                        break;
                    case 2: //Sign Up
                        userDB.getUserDB();
                        while (true) {
                            System.out.print(" Придумайте и введите логин: ");
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
                            System.out.print(" Введите ваше имя: ");
                            scn3 = new Scanner(System.in);
                            name = scn3.nextLine();
                            if (name.isEmpty()) {
                                System.out.println(warnMsg + inputIsEmpty + tryAgain);
                            } else
                                break;
                        }
                        while (true) {
                            System.out.print(" Придумайте и введите пароль: ");
                            scn4 = new Scanner(System.in);
                            password = scn4.nextLine();
                            if (password.isEmpty()) {
                                System.out.println(warnMsg + inputIsEmpty + tryAgain);
                            } else {
                                scn5 = new Scanner(System.in);
                                System.out.print(" Введите пароль еще раз: ");
                                String password2 = scn5.nextLine();
                                if (password2.equals(password))
                                    break;
                                else
                                    System.out.println(warnMsg + "Пароли не совпадают." + tryAgain);
                            }
                        }
                        userDB.addUser(new User(login, name, password));
                        break;
                    case 3: //Print Users
                        if (!userDB.isExistData()) {
                            System.out.println(warnMsg + noUserData);
                            continue;
                        }
                        String usersNames = userDB.getUsersNames();
                        if (usersNames.isEmpty()) {
                            System.out.println(warnMsg + "Нет зарегистрированных пользователей.");
                        } else {
                            System.out.println(RegisteredUsers);
                            System.out.println(usersNames);
                        }
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                }

            } catch (InputMismatchException ime) {
                System.out.println(warnMsg + wrongValue + tryAgain);
                continue;
            } catch (IOException ex) {
                System.out.println(errMsg + "Не удалось прочитать файл пользователей\n" + ex.getMessage());
                break;
            } catch (NullPointerException ex) {
                System.out.println(errMsg + "Неверный указатель\n" + ex.getMessage());
                break;
            } catch (DataNotFoundException ex) {
                System.out.println(errMsg + "Данные не найдены\n" + ex.getMessage());
                break;
            }
            if (needBreak) break;
        }
    }

    private boolean doMainMenu() throws IOException, NullPointerException, DataNotFoundException {
//        MenuHandler mh = new MenuHandler("MainMenu");
//        mh.setCurrentUser(currentUser);
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return true; //выходим из предыдущего меню и из программы
                if (resp > 1 && resp < MenuMap.get(menuName).getMaxValue()) {
                    //проверяем, есть ли контакты для текущего пользователя
                    ContactsDataBase contDB = new ContactsDataBase(currentUser.getId());
                    if (!contDB.isExistData()) {
                        System.out.println(warnMsg + noContactData + tryAgain);
                        continue;
                    }
                }
                MenuHandler currentMenu;
                String menuName = "";
                switch (resp) {
                    case 1:
                        menuName = "ContactMenu";
                        break;
                    case 2:
                        menuName = "PrintMenu";
                        break;
                    case 3:
                        menuName = "SortingMenu";
                        break;
                    case 4:
                        menuName = "SearchMenu";
                        break;
                    case 5:
                        return false; //возвращаемся в предыдущее меню
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                        continue;
                }

                currentMenu = new MenuHandler(menuName, currentUser);
                currentMenu.execute();
            } catch (InputMismatchException ime) {
                System.out.println(warnMsg + wrongValue + tryAgain);
            }
        }
    }

    private void doContactMenu() throws IOException, NullPointerException, DataNotFoundException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            ContactsDataBase contDB;
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue()) {
                    return;
                }
                if (resp > 0 && resp <= MenuMap.get(menuName).getMaxValue()) {
                    //проверяем, есть ли контакты для текущего пользователя
                    contDB = new ContactsDataBase(currentUser.getId());
                    if (!contDB.isExistData()) {
                        System.out.println(warnMsg + noContactData + tryAgain);
                        continue;
                    }
                }
                Scanner scn1, scn2, scn3, scn4;
                switch (resp) {
                    case 0: //Add
                        String name, surname, number;
                        while (true) {
                            System.out.print(" Введите имя: ");
                            scn1 = new Scanner(System.in);
                            name = scn1.nextLine();
                            if (name.isEmpty()) {
                                System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                continue;
                            }
                            System.out.print(" Введите фамилию: ");
                            scn2 = new Scanner(System.in);
                            surname = scn2.nextLine();
                            if (surname.isEmpty()) {
                                System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                continue;
                            }
                            while (true) {
                                System.out.print(" Введите номер телефона: ");
                                scn3 = new Scanner(System.in);
                                number = scn3.nextLine();
                                boolean needExit = false;
                                if (number.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                } else if (!checkPhoneNumber(number)) {
                                    System.out.println(warnMsg + wrongPhoneNum + tryAgain);
                                } else {
                                    needExit = true;
                                    break;
                                }
                                if (needExit) break;
                            }
                            break;
                        }
                        Contact contact = new Contact(currentUser.getId(), name, surname, number);
                        contDB = new ContactsDataBase(currentUser.getId());
                        contDB.addContact(contact);
                        break;
                    case 1: //Edit
                        MenuHandler contEdit = new MenuHandler("ContactEditMenu");
                        contEdit.setCurrentUser(currentUser);
                        contEdit.execute();
                        break;
                    case 2: //Delete
                        while (true) {
                            //boolean needExit = false;
                            System.out.print(" Введите запрос для поиска записи, которую требуется удалить (0 - для выхода): ");
                            scn = new Scanner(System.in);
                            try {
                                String mask = scn.nextLine();
                                if (mask.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                    continue;
                                } else if (mask.equals("0")) {
                                    System.out.println(nothingDeleted);
                                    break;
                                }

                                contDB = new ContactsDataBase(currentUser.getId());
                                //contDB.getContactsByMask(mask,"");
                                contDB.printContactsByMask(mask); //вывод найденных записей
                                if (contDB.getFoundContactsSize() == 0) {
                                    System.out.println(" По введенному запросу ничего не найдено." + tryAgain);
                                } else if (contDB.getFoundContactsSize() == 1) {
                                    System.out.print(" Вы действительно хотите удалить данную запись? (y/n): ");
                                    scn1 = new Scanner(System.in);

                                    if (scn1.next().equals("y")) {
                                        contDB.deleteFoundContacts(0);
                                    } else {
                                        System.out.println(nothingDeleted);
                                    }
                                } else if (contDB.getFoundContactsSize() > 1) {
                                    while (true) {
                                        System.out.print(" Выберите ID, который требуется удалить (0 - удалить все записи; -1 - выход): ");
                                        scn2 = new Scanner(System.in);
                                        try {
                                            int idToDel = scn2.nextInt();
                                            if (idToDel < 0) {
                                                System.out.println(nothingDeleted);
                                                break;
                                            } else if (idToDel == 0) {
                                                System.out.printf(" Вы действительно хотите удалить ВСЕ (%d) вышеуказанные записи? (y/n): ",contDB.getFoundContactsSize());
                                                scn3 = new Scanner(System.in);

                                                if (scn3.next().equals("y")) {
                                                    contDB.deleteFoundContacts(0);
                                                    System.out.println(" Записи успешно удалены.\n");
                                                    break;
                                                } else {
                                                    System.out.println(nothingDeleted);
                                                }
                                            }else{
                                                if(contDB.isIdInFound(idToDel)) {
                                                    System.out.printf(" Вы действительно хотите удалить запись (ID = %d)? (y/n): ",idToDel);
                                                    scn4 = new Scanner(System.in);

                                                    if (scn4.next().equals("y")) {
                                                        contDB.deleteFoundContacts(idToDel);
                                                        System.out.println(" Запись успешно удалена.\n");
                                                        break;
                                                    } else {
                                                        System.out.println(nothingDeleted);
                                                    }
                                                    break;
                                                }
                                                System.out.println(warnMsg+"Указанный ID отсутствует в списке найденных результатов."+tryAgain);
                                            }
                                        } catch (InputMismatchException imex) {
                                            System.out.println(warnMsg + wrongValue + tryAgain);
                                        }
                                    }
                                }
                                break;
                            } catch (InputMismatchException ime) {
                                System.out.println(warnMsg + wrongValue + tryAgain);
                            }
                        }
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                }
            } catch (InputMismatchException ime) {
                System.out.println(errMsg + wrongValue + tryAgain);
            }
        }
    }

    private void doContactEditMenu() throws DataNotFoundException, IOException {
        Scanner scn, scn2;
        String inputed, fieldName, nameDataTxt;
        while (true) {
            boolean isPhone = false;
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            scn = new Scanner(System.in);
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;

                ContactsDataBase contDB = new ContactsDataBase(currentUser.getId());
                switch (resp) {
                    case 0://Edit name
                        nameDataTxt = "Имя";
                        fieldName = "name";
                        break;
                    case 1://Edit surname
                        nameDataTxt = "Фамилия";
                        fieldName = "surname";
                        break;
                    case 2://Edit number
                        nameDataTxt = "Тел. номер";
                        fieldName = "number";
                        isPhone = true;
                        break;
                    case 3://Edit all
                        nameDataTxt = "";
                        fieldName = "";
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                        continue;
                }
                int idToEdit = 0;
                inputed = inputData(nameDataTxt, false, false); //вводим строку для поиска, она может не соответствовать формату тел. номера
                //contDB.getContactsByMask(inputed, fieldName);
                contDB.printContactsByMask(inputed); //вывод найденных записей
                if (contDB.getFoundContactsSize() == 1) {
                    idToEdit = contDB.getFoundContactId();
                } else if (contDB.getFoundContactsSize() > 1) {
                    while (true) {
                        System.out.print(" Выберите ID, который требуется отредактировать (0 - выход): ");
                        scn2 = new Scanner(System.in);
                        try {
                            idToEdit = scn2.nextInt();
                            break;
                        } catch (InputMismatchException imex) {
                            System.out.println(warnMsg + wrongValue + tryAgain);
                        }
                    }
                } else {
                    System.out.println(" По запросу " + inputed + " ничего не найдено." + tryAgain);
                    continue;
                }
                Contact contToEdit = contDB.getContactById(idToEdit, false); //получаем запись из файла на случай, если уже кто-то (например, из другой сессии) отредактировал или удадлил требуемую запись
                if (contToEdit == null) {
                    System.out.println(errMsg + "Выбранная для редактирования запись отсутствует!" + tryAgain);
                    continue;
                }
                String newVal = "";
                if (!fieldName.isEmpty()) {
                    newVal = inputData(nameDataTxt, isPhone, true);
                    switch (fieldName) {
                        case "name":
                            contToEdit.setName(newVal);
                            break;
                        case "surname":
                            contToEdit.setSurname(newVal);
                            break;
                        case "number":
                            contToEdit.setNumber(newVal);
                            break;
                        default:
                            System.out.println(errMsg + "Выбрано недопустимое поле!" + tryAgain);
                            continue;
                    }
                } else {
                    newVal = inputData("Имя", false, true);
                    if (!newVal.isEmpty())
                        contToEdit.setName(newVal);
                    newVal = inputData("Фамилия", false, true);
                    if (!newVal.isEmpty())
                        contToEdit.setSurname(newVal);
                    newVal = inputData("Тел. номер", true, true);
                    if (!newVal.isEmpty())
                        contToEdit.setNumber(newVal);
                }
                contDB.editContact(contToEdit);
                System.out.println("\n Запись успешно отредактирована.\n");
            } catch (InputMismatchException imex) {
                System.out.println(warnMsg + wrongValue + tryAgain);
            }
        }
    }

    private String inputData(String nameData, boolean isPhoneNum, boolean isNew) {
        while (true) {
            String out = "";
            String newValTxt = isNew ? "новое значение" : "поисковую фразу";
            String nameDataTxt = nameData.equals("") ? "" : " для поля «" + nameData + "»";
            System.out.print(" Введите " + newValTxt + nameDataTxt + ": ");
            Scanner scn = new Scanner(System.in);
            out = scn.nextLine();
            if (out.isEmpty()) {
                System.out.println(warnMsg + inputIsEmpty + "(не ввели " + nameData + ")" + tryAgain);
            } else {
                if (isPhoneNum && !checkPhoneNumber(out)) {
                    System.out.println(warnMsg + wrongPhoneNum + tryAgain);
                } else
                    return out;
            }
        }
    }

    private void doPrintMenu() throws RuntimeException, IOException, DataNotFoundException {

        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            Scanner scn1;

            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;

                ContactsDataBase contDB = new ContactsDataBase(currentUser.getId());
                switch (resp) {
                    case 0:
//                        contDB.printByMask("*");
                        contDB.getContactsByMask("*", "");
                        break;
                    case 1:
                        scn1 = new Scanner(System.in);
                        System.out.print(inputSearchRequest);
                        String mask = scn1.nextLine();
                        contDB.getContactsByMask(mask, "");
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                }
                if (contDB.getFoundContactsSize() > 0) {
                    System.out.println(MenuHandler.PrintHeader);
                    contDB.printPaged();
                } else {
                    System.out.println(warnMsg + noFoundContactData);
                }
            } catch (InputMismatchException ime) {
                System.out.println(errMsg + wrongValue + tryAgain);
            } catch (DataNotFoundException e) {
                throw new RuntimeException(errMsg + "Данные не найдены!");
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
    }

    private void doSortingMenu() throws DataNotFoundException, IOException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;

                MenuHandler contEdit;
                switch (resp) {
                    case 0://Name
                        contEdit = new MenuHandler("SortingNameMenu");
                        break;
                    case 1://Surname
                        contEdit = new MenuHandler("SortingSurnameMenu");
                        break;
                    case 2://Number
                        contEdit = new MenuHandler("SortingNumberMenu");
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                        continue;
                }
                contEdit.setCurrentUser(currentUser);
                contEdit.execute();
            } catch (InputMismatchException imex) {
                System.out.println(warnMsg + wrongValue + tryAgain);
            }
        }
    }

    private void doSortingNameMenu() throws DataNotFoundException, IOException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;

                ContactsDataBase contDB = new ContactsDataBase(currentUser.getId());
                List<Contact> contactList = contDB.getAllContacts();
                switch (resp) {
                    case 0: //sorting Name A-Z
                        System.out.println(SortingAZNameHeader);
                        contDB.printPaged(contactList.stream().sorted((x, y) -> x.getName().compareTo(y.getName())).toList(), 1);
                        break;
                    case 1: //sorting Name Z-A
                        System.out.println(SortingZANameHeader);
                        contDB.printPaged(contactList.stream().sorted((y, x) -> x.getName().compareTo(y.getName())).toList(), 1);
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                        continue;
                }
            } catch (InputMismatchException imex) {
                System.out.println(warnMsg + wrongValue + tryAgain);
            }
        }
    }

    private void doSortingSurnameMenu() throws DataNotFoundException, IOException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;

                ContactsDataBase contDB = new ContactsDataBase(currentUser.getId());
                List<Contact> contactList = contDB.getAllContacts();
                switch (resp) {
                    case 0: //sorting Surname A-Z
                        System.out.println(SortingAZSurnameHeader);
                        contDB.printPaged(contactList.stream().sorted((x, y) -> x.getSurname().compareTo(y.getSurname())).toList(), 1);
                        break;
                    case 1: //sorting Surname Z-A
                        System.out.println(SortingZASurnameHeader);
                        contDB.printPaged(contactList.stream().sorted((y, x) -> x.getSurname().compareTo(y.getSurname())).toList(), 1);
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                        continue;
                }
            } catch (InputMismatchException imex) {
                System.out.println(warnMsg + wrongValue + tryAgain);
            }
        }
    }

    private void doSortingNumberMenu() throws DataNotFoundException, IOException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;

                ContactsDataBase contDB = new ContactsDataBase(currentUser.getId());
                List<Contact> contactList = contDB.getAllContacts();
                switch (resp) {
                    case 0: //sorting Number A-Z
                        System.out.println(Sorting09NumberHeader);
                        contDB.printPaged(contactList.stream().sorted(Comparator.comparingLong(Contact::getNumber)).toList(), 1);
                        break;
                    case 1: //sorting Number Z-A
                        System.out.println(Sorting90NumberHeader);
                        contDB.printPaged(contactList.stream().sorted(Comparator.comparingLong(Contact::getNumber).reversed()).toList(), 1);
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                        continue;
                }
            } catch (InputMismatchException imex) {
                System.out.println(warnMsg + wrongValue + tryAgain);
            }
        }
    }

    private void doSearchMenu() throws IOException {
        Scanner scn, scn1, scn2, scn3;
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            scn = new Scanner(System.in);
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;

                ContactsDataBase contDB = new ContactsDataBase(currentUser.getId());
                System.out.print(inputSearchRequest);
                scn1 = new Scanner(System.in);
                String phrase = scn1.nextLine();
                if (phrase.isEmpty()) {
                    System.out.println(warnMsg + wrongValue + tryAgain);
                    continue;
                }

                String fieldName = "";
                switch (resp) {
                    case 0://Name
                        fieldName = "Имя";
                        contDB.getContactsByMask(phrase, "name");
                        break;
                    case 1://Surname
                        fieldName = "Фамилия";
                        contDB.getContactsByMask(phrase, "surname");
                        break;
                    case 2://Number
                        fieldName = "Телефонный номер";
                        contDB.getContactsByMask(phrase, "number");
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                        //continue;
                }
                if (contDB.getFoundContactsSize() > 0) {
                    System.out.println(MenuHandler.SearchHeader);
                    System.out.println("  Контактов, содержащих \"" + phrase + "\" в поле «" + fieldName + "»: " + contDB.getFoundContactsSize());
                    contDB.printPaged();
                } else {
                    System.out.println(warnMsg + noFoundContactData);
                }

            } catch (InputMismatchException imex) {
                System.out.println(warnMsg + wrongValue + tryAgain);
            } catch (DataNotFoundException dnfex) {
                System.out.println(errMsg + dnfex.getMessage());
                break;
            }
        }
    }

    /////////////////////////////////// static methods
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

    public static boolean checkPhoneNumber(String number) {
        String phoneNum = number.trim();
        if (phoneNum.isEmpty() || phoneNum.length() > MaxPhoneLength)
            return false;
        String template = "[0-9\\s\\+\\-\\(\\)]{" + MinPhoneLength + "," + MaxPhoneLength + "}";
//        String template = "[0-9\\".concat("s")+"\\".concat("+")+"\\".concat("-")+"\\".concat("(")+"\\".concat(")")+"]{"+MinPhoneLength+","+MaxPhoneLength+"}";
        return number.matches(template) && Contact.strToLong(number) > 0;
    }

//    public static boolean checkEmail(String email) {
//        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+$");
//    }
}
