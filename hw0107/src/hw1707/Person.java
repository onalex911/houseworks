package hw1707;

import static hw1707.ContactsHandler.fieldDelim;
import static hw1707.ContactsHandler.recordDelim;

//Написать CRUD operation над объектом
//CRUD - operation должно проходить в contacts.txt
public class Person {
    private int id;
    private String name;
    private String surname;

    private int age;

    public Person() {
    }

    public Person(int id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return id + ") " + name + " " + surname + " " + age;
    }

    public String toFileString() {
        return "" + id + fieldDelim + name + fieldDelim + surname + fieldDelim + age + recordDelim;
    }
}
