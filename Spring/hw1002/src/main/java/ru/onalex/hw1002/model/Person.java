package ru.onalex.hw1002.model;

import java.time.LocalDate;

public class Person {
    int id;
    static int count;
    private String firstName;
    private String lastName;
    private int birthYear;


    public Person(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.id = ++count;
    }

    @Override
    public String toString() {
        return id + ": " + firstName + " " + lastName + ", " + getAge() + " years old";
    }

    public int getAge(){
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - birthYear;
    }

    public static int getBirthYear(int age){
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setAge(int age) {
        LocalDate currentDate = LocalDate.now();
        setBirthYear(currentDate.getYear() - age);
    }
}
