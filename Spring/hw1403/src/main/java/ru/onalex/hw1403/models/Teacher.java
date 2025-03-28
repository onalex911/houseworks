package ru.onalex.hw1403.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="Teachers")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="EmploymentDate")
//    @Min(value = "1990-01-01")
    @NotNull
    private Date employmentDate;

    @Column(name="IsAssistant")
    @NotNull
    private Boolean isAssistant;

    @Column(name="IsProfessor")
    @NotNull
    private Boolean isProfessor;

    @Column(name="Name",columnDefinition = "nvarchar(max)")
    @NotNull()
    @NotBlank(message = "Name must not be blank!")
    private String name;

    @Column(name="Position",columnDefinition = "nvarchar(max)")
    @NotBlank
    @NotNull
    private String position;

    @Column(name="Premium",columnDefinition = "money default 0")
    @NotNull()
    @Positive(message = "Premium must be greater than 0!")
    private double premium;

    @Column(name="Surname",columnDefinition = "nvarchar(max)")
    @NotNull()
    @NotBlank(message = "Surname must not be blank!")
    private String surname;

    @Column(name="Salary",columnDefinition = "money")
    @NotNull(message = "Salary must not be empty!")
    @Positive(message = "Salary must be greater than 0!")
    private double salary;

//    @OneToOne(mappedBy = "teacher")
//    private Lecture lecture;

//    public TeacherDTO getDTO(){
//        return new TeacherDTO(id,employmentDate,isAssistant,isProfessor,name,position,premium,salary,surname);
//    }
}
