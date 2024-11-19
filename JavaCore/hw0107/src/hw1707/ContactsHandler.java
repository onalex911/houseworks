package hw1707;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

    private Person currentPerson;

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

    public void getThisPersonById(int id) throws IOException {
        ArrayList<String> records = getRecords();
        for(String record:records){
            //String idTxt = record.substring(0, record.indexOf("" + fieldDelim));
            if(id == getIdFromRecord(record)){
                ArrayList<String> fields = parseStr(record.toCharArray(),fieldDelim);
                this.currentPerson = new Person(Integer.parseInt(fields.get(0)),fields.get(1),fields.get(2),Integer.parseInt(fields.get(3)));
            }
        }
    }
public Person getPersonById(int id) throws IOException {
        ArrayList<String> records = getRecords();
        for(String record:records){
            //String idTxt = record.substring(0, record.indexOf("" + fieldDelim));
            if(id == getIdFromRecord(record)){
                ArrayList<String> fields = parseStr(record.toCharArray(),fieldDelim);
                return new Person(Integer.parseInt(fields.get(0)),fields.get(1),fields.get(2),Integer.parseInt(fields.get(3)));
            }
        }
        return null;
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void refreshPerson() throws IOException {
        ArrayList<String> records = getRecords();
        FileWriter fw = new FileWriter(contactsFileName);
        for(String record:records){
            if(getIdFromRecord(record) != currentPerson.getId()){
                fw.write(record+"\n");
            }
        }
        fw.write(currentPerson.toFileString());
        fw.close();
    }
    public void refreshPerson(Person person) throws IOException {
        ArrayList<String> records = getRecords();
        FileWriter fw = new FileWriter(contactsFileName);
        for(String record:records){
            if(getIdFromRecord(record) != person.getId()){
                fw.write(record+"\n");
            }
        }
        fw.write(person.toFileString());
        fw.close();
    }
    public void deletePerson() throws IOException {
        ArrayList<String> records = getRecords();
        FileWriter fw = new FileWriter(contactsFileName);
        for(String record:records){
            if(getIdFromRecord(record) != currentPerson.getId()){
                fw.write(record+"\n");
            }
        }
        fw.close();
    }
    public void deletePerson(Person person) throws IOException {
        ArrayList<String> records = getRecords();
        FileWriter fw = new FileWriter(contactsFileName);
        for(String record:records){
            if(getIdFromRecord(record) != person.getId()){
                fw.write(record+"\n");
            }
        }
        fw.close();
    }


    public void printAllRecords() throws IOException {
        ArrayList<String> records = getRecords();
        //ArrayList<String> sortedRecs = new ArrayList<>();

        for (String record : records) {
            ArrayList<String> fields = parseStr(record.toCharArray(), fieldDelim);
            Person person = new Person(Integer.parseInt(fields.get(0)), fields.get(1), fields.get(2), Integer.parseInt(fields.get(3)));
            System.out.println(person.toString());
        }
    }
    public static void printPersons(ArrayList<Person> records) {
        for (Person person : records) {
            System.out.println(person.toString());
        }
    }

    public ArrayList<Person> getSortedRecords() throws IOException {
        ArrayList<String> records = getRecords();
        ArrayList<Person> sortedRecs = new ArrayList<>();
        ArrayList<Integer> unsortedIds = new ArrayList<>();
        for (String rec:records) {
            unsortedIds.add(getIdFromRecord(rec));
        }
        Object[] ids = unsortedIds.toArray();
        for (int i = 0; i < ids.length-1; i++) {
            for (int j = i+1; j < ids.length; j++) {
                if((int)ids[i] > (int)ids[j])
                    ids[j] = ((int)ids[i]+(int)ids[j]) - (int)(ids[i] = ids[j]);
            }
        }
        for (int i = 0; i < ids.length; i++) {
            sortedRecs.add(getPersonById((int)ids[i]));
        }
        return sortedRecs;
    }

    public static int getIdFromRecord(String record){
        return Integer.parseInt(record.substring(0,record.indexOf(""+fieldDelim)));
    }

    public static int getLastId() throws FileNotFoundException, IOException {
        ContactsHandler ch = new ContactsHandler();
        ArrayList<String> records = ch.getRecords();
        int max = 0;
        for(String rec:records){
            int curId = getIdFromRecord(rec);
            if(max < curId)
                max = curId;
        }
        return max;
    }

    public static ArrayList<String> parseStr(char[] strChars, char delim) {
        String tmp = "";
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < strChars.length; i++) {
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

    public static Person inputPersonData() {
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
