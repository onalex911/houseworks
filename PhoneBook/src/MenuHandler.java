import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHandler {
    private String menuString;
    private int menuMaxValue;
    private int menuExitValue;
    private final HashMap<String, MenuSettings> MenuMap = new HashMap<>();
    public static String inputPhrase = "Введите значение: ";
    public static String wrongValue = "Введено неверное значение.";
    public static String inputIsEmpty = "Введено пустое значение.";
    public static String tryAgain = "\nПопробуйте еще раз.";

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
        String mainMenu = "_________________________________________________\n" +
                " |                                                 |\n" +
                " |            << Телефонная книга >>               |\n" +
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
        String contactMenu = " _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
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
        String contactEditMenu = " _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
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
        String printMenu = " _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
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
        String sortingNameMenu = " _________________________________________________\n" +
                " |                                                 |\n" +
                " |             << Телефонная книга >>              |\n" +
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

    public String getMenuString() {
        return menuString;
    }

    public int getMenuMaxValue() {
        return menuMaxValue;
    }

    public int getMenuExitValue() {
        return menuExitValue;
    }

    public void execute(String menuId) {
        switch (menuId) {
            case "LoginMenu":
                doLoginMenu(menuId);
                break;
            case "MainMenu":
                doMainMenu(menuId);
                break;
            case "ContactMenu":
                doContactMenu(menuId);
                break;
            case "ContactEditMenu":
                doContactEditMenu(menuId);
                break;
            case "PrintMenu":
                doPrintMenu(menuId);
                break;
            case "SortingMenu":
                doSortingMenu(menuId);
                break;
            case "SortingNameMenu":
                doSortingNameMenu(menuId);
                break;
            case "SortingSurnameMenu":
                doSortingSurnameMenu(menuId);
                break;
            case "SortingNumberMenu":
                doSortingNumberMenu(menuId);
                break;
            case "SearchMenu":
                doSearchMenu(menuId);
                break;
            default:
                System.out.println();
        }
    }

    private void doLoginMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                execute("MainMenu");
            }

            System.out.println("Login Menu working...");
        }
    }

    private void doMainMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                execute("ContactMenu");
            }
            System.out.println("Contact Menu working...");
        }
    }

    private void doContactMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                execute("ContactEditMenu");
            }

            System.out.println("Contact Edit Menu working...");
        }
    }

    private void doContactEditMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                System.out.println("Set Name working ...");
            }

            System.out.println("Contact Edit Menu working ...");
        }
    }

    private void doPrintMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                System.out.println("Set Name working ...");
            }

            System.out.println("Contact Edit Menu working ...");
        }
    }

    private void doSortingMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                System.out.println("Set Name working ...");
            }

            System.out.println("Contact Edit Menu working ...");
        }
    }

    private void doSortingNameMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                System.out.println("Set Name working ...");
            }

            System.out.println("Contact Edit Menu working ...");
        }
    }

    private void doSortingSurnameMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                System.out.println("Set Name working ...");
            }

            System.out.println("Contact Edit Menu working ...");
        }
    }

    private void doSortingNumberMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                System.out.println("Set Name working ...");
            }

            System.out.println("Contact Edit Menu working ...");
        }
    }

    private void doSearchMenu(String menuId) throws InputMismatchException {
        while (true) {
            System.out.println(MenuMap.get(menuId).getMenuText());
            System.out.print(inputPhrase);
            Scanner scn = new Scanner(System.in);
            int resp = scn.nextInt();
            if (resp == MenuMap.get(menuId).getExitValue())
                return;
            if (resp == 1) {
                System.out.println("Set Name working ...");
            }

            System.out.println("Contact Edit Menu working ...");
        }
    }
}
