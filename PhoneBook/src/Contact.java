import java.util.stream.IntStream;

public class Contact {
    private long userId;
    private int id;
    private String name;
    private String surname;
    private String numberText;
    private long number;

    public static final int feldsLenght = 4;

    public Contact(long userId, String name, String surname, String numberText) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.numberText = numberText;
        this.number = strToLong(numberText);
    }

    public Contact(long userId, int contactId, String name, String surname, String numberText) {
        this(userId, name, surname, numberText);
        this.id = contactId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumberText() {
        return numberText;
    }

    public long getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNumber(String contactNumberText) {
        this.numberText = contactNumberText;
        this.number = strToLong(contactNumberText);
    }

    public String toString() {
        return "ID: " + id + ", Имя: " + name + ", Фамилия: " + surname + ", тел.: " + numberText;
    }

    static long strToLong(String str) {
        IntStream streamFromString = str.chars();
        try {
            String txtResult = streamFromString
                    .filter(Character::isDigit)
                    .mapToObj(c -> String.valueOf((char) c))
                    .reduce("", String::concat);

            return Long.parseLong(txtResult);
        } catch (Exception ex) {
            return 0;
        }
    }
}
