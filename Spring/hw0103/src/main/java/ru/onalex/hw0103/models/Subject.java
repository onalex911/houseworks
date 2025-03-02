package ru.onalex.hw0103.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name",columnDefinition = "nvarchar(100)",unique=true)
    @NotNull
    @NotEmpty
    private String name;

    @OneToOne(mappedBy = "subject")
    private Lecture lecture;}
