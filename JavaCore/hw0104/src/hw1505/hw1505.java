package hw1505;

import java.util.Random;
import java.util.Scanner;

class Book {
    // - Название книги (title)
    String title;
    //   - Автор книги (author)
    String[] author;
    //   - Год издания (year)
    short year;
    //   - Жанр (genre)
    String genre;
    //   - Количество страниц (numPages)
    int numPages;

    // значения полей по умолчанию
    {
        title = "не указано";
        author = new String[]{"Фамилия", "Имя", "Отчество"};
        year = 0;
        genre = "не определен";
        numPages = 0;
    }

    //- Конструктор без параметров.
    public Book() {
    }

    //   - Конструктор, который принимает название книги, автора и год издания в качестве параметров.
    public Book(String title, String[] author, short year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    //   - Конструктор, который принимает все поля книги в качестве параметров.
    public Book(String title, String[] author, short year, String genre, int numPages) {
        this(title, author, year);
        this.genre = genre;
        this.numPages = numPages;
    }

    //   - Метод printInfo(), который выводит информацию о книге в консоль.
    public void printInfo() {
        System.out.println("Название:       " + this.title);
        System.out.println("Автор:          " + this.author[0] + " " + this.author[1] + " " + this.author[2]);
        System.out.println("Год издания:    " + this.year);
        System.out.println("Жанр:           " + this.genre);
        System.out.println("Кол-во страниц: " + this.numPages);
    }
}

class Cube {
    byte numSides;
    byte sideVal[];
    String[][] sideView;
    String material;
    int weight;
    String color;

    {
        numSides = 6;
        sideVal = new byte[]{1, 2, 3, 4, 5, 6};
        sideView = new String[][]{
                {
                        "     ",
                        "  *  ",
                        "     "
                }, {
                "    *",
                "     ",
                "*    "
        }, {
                "    *",
                "  *  ",
                "*    "
        }, {
                "*   *",
                "     ",
                "*   *"
        }, {
                "*   *",
                "  *  ",
                "*   *"
        }, {
                "*   *",
                "*   *",
                "*   *"
        }
        };
        material = "пластик";
        weight = 10;
        color = "белый";
    }

    Cube() {
    }

    Cube(String material, int weight, String color) {
        this.material = material;
        this.weight = weight;
        this.color = color;
    }

    public byte drop() {
        Random rnd = new Random();
        return (byte) rnd.nextInt(this.numSides);
    }

    public byte getNumSides() {
        return this.numSides;
    }

    public String getMaterial() {
        return this.material;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getColor() {
        return this.color;
    }

    public byte getSideVal(byte sideIndex) {
        return this.sideVal[sideIndex];
    }

    public void drawSide(byte numSide) {
        System.out.println("/-------\\");
        System.out.println("| " + this.sideView[numSide][0] + " |");
        System.out.println("| " + this.sideView[numSide][1] + " |");
        System.out.println("| " + this.sideView[numSide][2] + " |");
        System.out.println("\\-------/");
    }
}

class Window {
    String title;
    int width;
    int minWidth;
    int maxWidth;
    int height;
    int minHeight;
    int maxHeight;
    String innerText;
    String[] innerTextArr;

    int padding;
    boolean autoHeight; //признак автоматического подбора высоты окна в зависимости от объема текста

    {
        title = "Текст заголовка";
        width = 50;
        minWidth = 5;
        maxWidth = 80;
        height = 10;
        minHeight = 5;
        maxHeight = 25;
        innerText = "Lorem ipsum dolor sit amet, consecte tuer adipiscing elit. Maecenas porttitor congue massa.";
        padding = 2;  //верт. граница окна плюс зазаор между ней и текстом;
        autoHeight = false;
    }

    Window() {
    }

    Window(int width, int height) {
        this.width = width;
        this.height = height;
    }

    Window(String title, String innerText, int width, int height) {
        this(width, height);
        this.title = title;
        this.innerText = innerText;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setInnerText(String newText) {
        this.innerText = newText;
    }

    public void setWidth(int width) throws Exception {
        if (width < this.minWidth || width > this.maxWidth)
            throw new Exception("Значение ширины (" + width + ") выходит за допустимый диапазон (от " + this.minWidth + " до " + this.maxWidth + ")!");
        this.width = width;
    }

    public void setHeight(int height) throws Exception {
        if (height < this.minHeight || height > this.maxHeight)
            throw new Exception("Значение высоты (" + height + ") выходит за допустимый диапазон (от " + this.minHeight + " до " + this.maxHeight + ")!");
        this.height = height;
    }

    public void setAutoHeight(boolean newVal) {
        this.autoHeight = newVal;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean getAutoHeight() {
        return this.autoHeight;
    }

    private int getTextWidth() {
        return width - 2 * padding;
    }

    private int getTextHeight() {
        return height - 4;
    }

    public void draw() {
        System.out.println(drawHorizLine());
        System.out.println("| " + getTitle() + " |");
        System.out.println(drawHorizLine());
        String[] textArr = getInnerTextArray();
        int limit = autoHeight ? textArr.length : getTextHeight();
        for (int i = 0; i < limit; i++) {
            System.out.print("| ");
            if (i < textArr.length) {
                System.out.print(textArr[i]);
            } else {
                for (int j = 0; j < getTextWidth(); j++) {
                    System.out.print(" ");
                }
            }
            System.out.println(" |");
        }
        System.out.println(drawHorizLine());
    }

    private String drawHorizLine() {
        String outText = "";
        for (int i = 0; i < width; i++) {
            outText += "-";
        }
        return outText;
    }

    /*private int countHeight() {
        return this.innerText.length() / getTextWidth() + 4; //высота окна в зависимости от внутреннего текста + строка заголовка и 3-х гориз. линий
    }*/

    private int countTextHeight() {
        return this.innerText.length() / getTextWidth() + 1; //высота окна в зависимости от внутреннего текста
    }

    private String getTitle() {
        String outText = "";

        if (this.title.length() > getTextWidth()) {
            outText = this.title.substring(0, getTextWidth());
        } else {
            outText = this.title;
            if (this.title.length() < getTextWidth()) {
                for (int i = 0; i < getTextWidth() - this.title.length(); i++) {
                    outText += " ";
                }
            }
        }
        return outText;
    }

    //получить массив строк для размещения в окне на основе
    private String[] getInnerTextArray() {
        int textWidth = getTextWidth();
        int textHeight = countTextHeight();
        String[] outArr = new String[textHeight];
        for (int i = 0; i < textHeight; i++) {
            int start = i * textWidth;
            int end = i * textWidth + textWidth;
            //исключаем пробелы в начале строк
            if (innerText.substring(start, start + 1).equals(" ")) {
                start++;
                end++;
            }
            if (end > innerText.length() - 1) {
                end = innerText.length();
            }
            outArr[i] = innerText.substring(start, end);
        }
        if (outArr[textHeight - 1].length() < textWidth) {
            int dob = textWidth - outArr[textHeight - 1].length();
            for (int i = 0; i < dob; i++) {
                outArr[textHeight - 1] += " ";
            }
        }
        return outArr;
    }

}

public class hw1505 {
    public static void main(String[] args) {
        String errMsg = "ОШИБКА! ";
        // Создайте 3 объекта класса Книга и протестируйте их, вызывая printInfo().
        System.out.println("---------------------- Класс Book ------------------------");
        Book book1 = new Book();
        String title2 = "Чистая архитектура";
        String author2[] = new String[]{"Мартин", "Роберт", ""};
        short year2 = 2023;
        Book book2 = new Book(title2, author2, year2);
        String title3 = "Идиот";
        String author3[] = new String[]{"Достоевский", "Федор", "Михайлович"};
        short year3 = 1989;
        String genre3 = "классическая литература";
        int numPages3 = 1000;
        Book book3 = new Book(title3, author3, year3, genre3, numPages3);

        book1.printInfo();
        System.out.println("----------------------------------------");
        book2.printInfo();
        System.out.println("----------------------------------------");
        book3.printInfo();

        System.out.println("\n\n---------------------- Класс Cube ------------------------");
        Cube cube1 = new Cube();
        byte result1 = cube1.drop();
        Cube cube2 = new Cube("оргстекло", 12, "красный");
        byte result2 = cube2.drop();
        Cube cube3 = new Cube("аллюминий", 9, "серебристый");
        byte result3 = cube3.drop();

        System.out.println("Играет кубик:");
        System.out.println("- материал - " + cube1.getMaterial());
        System.out.println("- вес(г)   - " + cube1.getWeight());
        System.out.println("- цвет     - " + cube1.getColor());
        System.out.println("Число, выпавшее на кубике: " + cube1.getSideVal(result1) + " из " + cube1.getNumSides());
        cube1.drawSide(result1);
        System.out.println();
        System.out.println("Играет кубик:");
        System.out.println("- материал - " + cube2.getMaterial());
        System.out.println("- вес(г)   - " + cube2.getWeight());
        System.out.println("- цвет     - " + cube2.getColor());
        System.out.println("Выпавшее число: " + cube1.getSideVal(result2) + " из " + cube1.getNumSides());
        cube1.drawSide(result2);
        System.out.println();
        System.out.println("Играет кубик:");
        System.out.println("- материал - " + cube3.getMaterial());
        System.out.println("- вес(г)   - " + cube3.getWeight());
        System.out.println("- цвет     - " + cube3.getColor());
        System.out.println("Выпавшее число: " + cube1.getSideVal(result3) + " из " + cube1.getNumSides());
        cube1.drawSide(result3);

        //------- работа с окнами --------
        System.out.println("\n\n---------------------- Класс Window ------------------------");
        Scanner scn,scn1;
        int numOper;
        String inputText = "";
        Window win1 = new Window();
        while (true) {
            System.out.println("Выберите операцию с окном:");
            System.out.println("1 - вывести окно");
            System.out.println("2 - задать заголовок окна");
            System.out.println("3 - задать текст внутри окна");
            System.out.println("4 - задать параметры окна");
            System.out.println("0 - выход");
            System.out.print("Введите номер операции (0-4): ");
            scn = new Scanner(System.in);
            scn1 = new Scanner(System.in);
            numOper = scn.nextInt();
            switch (numOper) {
                case 0:
                    break;
                case 1:
                    win1.draw();
                    break;
                case 2:
                    System.out.print("Введите текст заголовка: ");
                    inputText = scn1.nextLine();
                    win1.setTitle(inputText);
                    break;
                case 3:
                    System.out.print("Введите текст: ");
                    inputText = scn1.nextLine();
                    win1.setInnerText(inputText);
                    break;
                case 4:
                    Scanner scn2;
                    int numParam;
                    while (true) {
                        System.out.println("Выберите параметр, который вы хотите изменить:");
                        System.out.println("1 - ширина (" + win1.getWidth() + ")");
                        System.out.println("2 - высота (" + win1.getHeight() + ")");
                        System.out.println("3 - автоподбор высоты в зависимости от объема текста (" + win1.getAutoHeight() + ")");
                        System.out.println("0 - выход");
                        System.out.print("Введите номер параметра (0-4): ");
                        scn2 = new Scanner(System.in);
                        numParam = scn2.nextInt();
                        int newVal;
                        switch (numParam) {
                            case 0:
                                break;
                            case 1:
                                System.out.print("Введите значение ширины: ");
                                newVal = scn2.nextInt();
                                try {
                                    win1.setWidth(newVal);
                                } catch (Exception ex) {
                                    System.out.println(errMsg + ex.toString());
                                }
                                break;
                            case 2:
                                System.out.print("Введите значение высоты: ");
                                newVal = scn2.nextInt();
                                try {
                                    win1.setHeight(newVal);
                                } catch (Exception ex) {
                                    System.out.println(errMsg + ex.toString());
                                }
                                break;
                            case 3:
                                System.out.print("Введите значение автоподбора (1 - включить, 0 - отключить): ");
                                newVal = scn2.nextInt();
                                win1.setAutoHeight(newVal == 1);
                                break;
                            default:
                                System.out.println(errMsg + "Недопустимый номер параметра (" + numParam + ")");
                        }
                        if (numParam == 0)
                            break;
                    }
                default:
                    System.out.println(errMsg + "Недопустимый номер операции (" + numOper + ")");
            }
            if (numOper == 0)
                break;

        }

    }
}
