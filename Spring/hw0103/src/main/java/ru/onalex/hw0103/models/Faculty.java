package ru.onalex.hw0103.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name="Faculties") //Факультеты
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name",columnDefinition = "nvarchar(100)",unique=true)
    @NotNull
    @NotEmpty
    private String name;

    @Column(name="Financing",columnDefinition = "money default 0")
    @NotNull
    @PositiveOrZero
    private double financing;

    @OneToOne(mappedBy = "faculty")
    private Department department;
//    @OneToMany(mappedBy = "subject")

}
