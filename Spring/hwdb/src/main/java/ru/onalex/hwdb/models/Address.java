package ru.onalex.hwdb.models;

import jakarta.persistence.*;

@Entity
@Table(name="adresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String zip;
    private String country;
    private String city;
    private String street;
}
