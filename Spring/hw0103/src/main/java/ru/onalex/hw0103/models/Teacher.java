package ru.onalex.hw0103.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name",columnDefinition = "nvarchar(max)")
    @NotBlank
    @NotNull
    private String name;

    @Column(name="Surname",columnDefinition = "nvarchar(max)")
    @NotBlank
    @NotNull
    private String surname;

    @Column(name="Salary",columnDefinition = "money")
    @NotNull
    private double salary;

    @OneToOne(mappedBy = "teacher")
    private Lecture lecture;
}
