import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHandler {
    private String menuName;
    private String menuString;
    private int menuMaxValue;
    private int menuExitValue;
    private final HashMap<String, MenuSettings> MenuMap = new HashMap<>();
    private User currentUser;
    //private String currentMenuId;
    public static String errMsg = "ОШИБКА! ";
    public static String warnMsg = "ВНИМАНИЕ! ";
    public static String inputPhrase = "Введите значение: ";
    public static String wrongValue = "Введено неверное значение.";
    public static String inputIsEmpty = "Введено пустое значение.";
    public static String tryAgain = "\nПопробуйте еще раз.";
    public static final int MaxAttempts = 5;
    public static final int MinPhoneLength = 3;
    public static final int MaxPhoneLength = 20;
    public static final String separator = "_______________________________________________________________";

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
        String contactMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |" + insertName("Основное меню -> Контакты") + "|\n" +
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
        String contactEditMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |" + insertName("Основное меню -> Контакты -> Редактировать") + "|\n" +
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
        String printMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
                " |" + insertName("Основное меню -> Печать") + "|\n" +
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
        String sortingMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
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
        String sortingNameMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
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
        String sortingSurnameMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
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
        String sortingNumberMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
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
        String searchMenu = " ___________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
                " |" + insertName("Владелец: " + currentUser.getName()) + "|\n" +
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
        Scanner scn1, scn2, scn3, scn4, scn5, scn6, scn7, scn8, scn9;
        while (true) { //login menu
            //MenuHandler mh = new MenuHandler("LoginMenu");

            System.out.println(menuString);
            System.out.print(inputPhrase);
            scn1 = new Scanner(System.in);
            boolean needBreak = false;
            try {
                int response = scn1.nextInt();
                if (response == menuExitValue)
                    break;

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
                                    password = scn3.nextLine();
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
                            MenuHandler mainMenu = new MenuHandler("MainMenu", currentUser);

                            needBreak = !mainMenu.execute();
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
                        while (true) {
                            System.out.print("Придумайте и введите пароль: ");
                            scn5 = new Scanner(System.in);
                            password = scn5.nextLine();
                            if (password.isEmpty()) {
                                System.out.println(warnMsg + inputIsEmpty + tryAgain);
                            } else {
                                scn6 = new Scanner(System.in);
                                System.out.print("Введите пароль еще раз: ");
                                String password2 = scn6.nextLine();
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
                    return false;
                switch (resp) {
                    case 1:
                        MenuHandler contMenu = new MenuHandler("ContactMenu", currentUser);
                        contMenu.execute();
                        break;
                    case 2:
                        MenuHandler prnMenu = new MenuHandler("PrintMenu", currentUser);
                        prnMenu.execute();
                        break;
                    case 3:
                        MenuHandler sortMenu = new MenuHandler("SortingMenu", currentUser);
                        sortMenu.execute();
                        break;
                    case 4:
                        MenuHandler searchMenu = new MenuHandler("SearchMenu", currentUser);
                        searchMenu.execute();
                        break;
                    case 5:
                        return true;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
                }
            } catch (InputMismatchException ime) {
                System.out.println(warnMsg + wrongValue + tryAgain);
            }
        }
    }

    private void doContactMenu() throws IOException, NullPointerException, DataNotFoundException {
//        MenuHandler mh = new MenuHandler("ContactMenu");
//        mh.setCurrentUser(currentUser);
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            ContactsDataBase contDB;
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;
                Scanner scn1, scn2, scn3;
                switch (resp) {
                    case 0: //Add
                        String name, surname, number;
                        while (true) {
                            System.out.print("Введите имя: ");
                            scn1 = new Scanner(System.in);
                            name = scn1.nextLine();
                            if (name.isEmpty()) {
                                System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                continue;
                            }
                            System.out.print("Введите фамилию: ");
                            scn2 = new Scanner(System.in);
                            surname = scn2.nextLine();
                            if (surname.isEmpty()) {
                                System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                continue;
                            }
                            while (true) {
                                System.out.print("Введите номер телефона: ");
                                scn3 = new Scanner(System.in);
                                number = scn3.nextLine();
                                boolean needExit = false;
                                if (number.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                } else if (!checkPhoneNumber(number)) {
                                    System.out.println(warnMsg + "Введено значение не соответствующее формату телефонного номера" + tryAgain);
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
                            System.out.print("Введите строку для поиска записи, которую требуется удалить (0 - для выхода): ");
                            scn = new Scanner(System.in);
                            try {
                                String mask = scn.nextLine();
                                if (mask.isEmpty()) {
                                    System.out.println(warnMsg + inputIsEmpty + tryAgain);
                                    continue;
                                } else if (mask.equals("0")) {
                                    //needExit = true;
                                    break;
                                }

                                contDB = new ContactsDataBase(currentUser.getId());
                                contDB.printContactsByMask(mask);
                                if (contDB.getFoundContactsSize() == 0) {
                                    System.out.println("По введенному запросу ничего не найдено." + tryAgain);
                                } else if (contDB.getFoundContactsSize() == 1) {
                                    System.out.print("Вы действительно хотите удалить данную запись? (y/n): ");
                                    scn1 = new Scanner(System.in);
                                    if (scn1.next().equals("y")) {
                                        contDB.deleteFoundContacts(0);
                                    }
                                } else if (contDB.getFoundContactsSize() > 1) {
                                    while(true) {
                                        System.out.print("Выберите ID, который требуется удалить (0 - удалить все записи; -1 - выход): ");
                                        scn2 = new Scanner(System.in);
                                        try{
                                            int idToDel = scn2.nextInt();
                                            if(idToDel < 0)
                                                break;
                                            else{
                                                contDB.deleteFoundContacts(idToDel);
                                                System.out.println("\nЗапись(-и) успешно удалены.\n");
                                                break;
                                            }
                                        }catch (InputMismatchException imex){
                                            System.out.println(warnMsg+wrongValue+tryAgain);
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

    private void doContactEditMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuName).getExitValue())
                return;

            switch (resp) {
                case 0://Edit name
                case 1://Edit surname
                case 2://Edit number
                case 3://Edit all
            }
        }
    }

    private void doPrintMenu() throws RuntimeException, IOException {
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
                        contDB.getContactsByMask("*","");
                        contDB.printPaged();
                        System.out.println(separator);
                        break;
                    case 1:
                        scn1 = new Scanner(System.in);
                        System.out.print("Введите строку для поиска: ");
                        String mask = scn1.nextLine();
//                        contDB.printByMask(mask);
                        contDB.getContactsByMask(mask,"");
                        contDB.printPaged();
                        System.out.println(separator);
                        break;
                    default:
                        System.out.println(warnMsg + wrongValue + tryAgain);
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

    private void doSortingMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuName).getExitValue())
                return;

            System.out.println("Menu working ...");
        }
    }

    private void doSortingNameMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuName).getExitValue())
                return;

            System.out.println("Contact Edit Menu working ...");
        }
    }

    private void doSortingSurnameMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuName).getExitValue())
                return;

            System.out.println("Menu working ...");
        }
    }

    private void doSortingNumberMenu() throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuName).getExitValue())
                return;

            System.out.println("Menu working ...");
        }
    }

    private void doSearchMenu() throws IOException {
        Scanner scn,scn1,scn2,scn3;
        while (true) {
            System.out.println(MenuMap.get(menuName).getMenuText());
            System.out.print(inputPhrase);
            scn = new Scanner(System.in);
            try {
                int resp = scn.nextInt();
                if (resp == MenuMap.get(menuName).getExitValue())
                    return;

                ContactsDataBase contDB = new ContactsDataBase(currentUser.getId());
                System.out.print("Введите поисковую фразу: ");
                scn1 = new Scanner(System.in);
                String phrase = scn1.nextLine();
                if(phrase.isEmpty()){
                    System.out.println(warnMsg+wrongValue+tryAgain);
                    continue;
                }
                String fieldName = "";
                switch (resp){
                    case 0://Name
                        fieldName="Имя";
                        contDB.getContactsByMask(phrase,"name");
                        break;
                    case 1://Surname
                        fieldName="Фамилия";
                        contDB.getContactsByMask(phrase,"surname");
                        break;
                    case 2://Number
                        fieldName="Телефонный номер";
                        contDB.getContactsByMask(phrase,"number");
                        break;
                    default: System.out.println(warnMsg+wrongValue+tryAgain);
                    //continue;
                }
                //ContactsDataBase.Num =
                System.out.println("\nКонтактов, содержащих "+phrase+" в поле «"+fieldName+"»: "+contDB.getFoundContactsSize());
                contDB.printPaged();
                //break;
            }catch (InputMismatchException imex){
                System.out.println(warnMsg+wrongValue+tryAgain);
            }catch(DataNotFoundException dnfex){
                System.out.println(errMsg+dnfex.getMessage());
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

    public static boolean checkEmail(String email) {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+$");
    }
}
