//        DataNotFoundException для ситуации, когда запрашиваемые данные не найдены.

public class DataNotFoundException extends Exception {

    public DataNotFoundException() {
        super("ОШИБКА поиска данных!");
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
