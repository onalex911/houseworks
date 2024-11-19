package hw2406;

//Создание кастомных исключений:
//
//        1)
//        Создайте два класса исключений, которые будут наследоваться от Exception или RuntimeException (в зависимости от ваших потребностей):
//        InvalidUserInputException для обработки некорректного ввода пользователя.
//        DataNotFoundException для ситуации, когда запрашиваемые данные не найдены.
//        Определение конструктора и методов:
//
//        2)В каждом классе исключений определите:
//        Конструктор по умолчанию.
//        Конструктор, принимающий сообщение (String).
//        (Опционально) Конструктор, принимающий сообщение и причину (Throwable).
//        Использование кастомных исключений в программе:

public class InvalidUserInputException extends Exception {
    private String mainMsg;
    private String msg;

    public InvalidUserInputException() {
        super("ОШИБКА ввода логина!");
    }

    public InvalidUserInputException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }
}
