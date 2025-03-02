package ru.onalex.hw0103.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name="Departments") //Кафедры
public class Department {
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

    @OneToOne
    @JoinColumn(name = "FacultyId",referencedColumnName = "id")
    private Faculty faculty;

    @OneToOne(mappedBy = "department")
    private Group group;
}
