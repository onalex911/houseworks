package ru.onalex.hw2802.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;

}
