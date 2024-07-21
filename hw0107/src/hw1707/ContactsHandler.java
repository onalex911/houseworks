package hw1707;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsHandler {
    public static final String contactsFileName = "contacts.txt";
    public static final char fieldDelim = ';';
    public static final int maxBufferSize = 10000;
    public static final char recordDelim = '\n';
    private char[] buffer;
    private int countChars;

    public ContactsHandler() {
        this.buffer = new char[maxBufferSize];
    }

    public ArrayList<String> getRecords() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(contactsFileName);
        countChars = fr.read(buffer);
        String tmp = ""; //строка, накапливающая текстовые данные между разделителями
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < countChars; i++) {
            if (buffer[i] == recordDelim) {
                out.add(tmp);
                tmp = "";
            } else
                tmp += buffer[i];
        }
        if (!tmp.isEmpty())
            out.add(tmp);

        return out;
    }

    public static int getLastId() throws FileNotFoundException, IOException {
        ContactsHandler ch = new ContactsHandler();
        ArrayList<String> records = ch.getRecords();

        String lastRec = records.getLast();
        String idTxt = lastRec.substring(0, lastRec.indexOf(""+fieldDelim));
        return Integer.parseInt(idTxt);
    }

    public static ArrayList<String> parseStr(char[] strChars, int limit, char delim) {
//        char[] strChars = str.toCharArray();

        String tmp = "";
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            if (strChars[i] == delim) {
                out.add(tmp);
                tmp = "";
            } else
                tmp += strChars[i];
        }
        if (!tmp.isEmpty())
            out.add(tmp);

        return out;
    }
    public static Person inputPersonData() throws IOException {
        Scanner scn1, scn2, scn3;
        System.out.print("Введите имя: ");
        scn1 = new Scanner(System.in);
        String name = scn1.nextLine();
        System.out.print("Введите фамилию: ");
        scn2 = new Scanner(System.in);
        String surname = scn2.nextLine();
        System.out.print("Введите возраст: ");
        scn3 = new Scanner(System.in);
        int age = scn3.nextInt();
        try {
            int id = getLastId() + 1;
            return new Person(id, name, surname, age);
        } catch (Exception e) {
            System.out.println("Ошибка получения id: " + e.getMessage());
        }
        return null;
    }
}
