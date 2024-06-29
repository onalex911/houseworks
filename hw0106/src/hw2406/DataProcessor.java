package hw2406;
//        Создайте класс DataProcessor с методом findData(String key), который выбрасывает DataNotFoundException, если данные по ключу не найдены.
//        Написание тестового класса:

public class DataProcessor {
    private String[] logins;
    private String[] users;
    private int size;

    public DataProcessor(String[] logins, String[] users) {

        if(logins.length != users.length)
            throw new RuntimeException("Несовпадение размеров массивов для БД пользователей!");
        this.logins = logins;
        this.users = users;
        this.size = logins.length;
    }

    public String getUserByLogin(String login){
        for (int i = 0; i < size; i++) {
            if(logins[i].equals(login))
                return users[i];
        }
        return "";
    }

    public String findData(String key) throws DataNotFoundException {
        String result = getUserByLogin(key);
        if (result.equals(""))
            throw new DataNotFoundException("ОШИБКА поиска данных: не обнаружен пользователь с логином " + key);

        return result;
    }
}
