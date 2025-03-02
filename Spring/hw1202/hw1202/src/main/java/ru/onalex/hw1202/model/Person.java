package ru.onalex.hw1202.model;

import java.time.LocalDate;

public class Person {
    int id;
    static int count;
    private String name;
    private int birthYear;


    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.id = ++count;
    }

    @Override
    public String toString() {
        return id + ": " + name + ", " + getAge() + " years old";
    }

    public int getAge(){
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - birthYear;
    }

    public static int getBirthYear(int age){
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        LocalDate currentDate = LocalDate.now();
        setBirthYear(currentDate.getYear() - age);
    }
}

