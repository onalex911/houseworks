package ru.onalex.hw0703.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import ru.onalex.hw0703.DTOs.TeacherDTO;

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
    @NotBlank
    @NotNull
    private String name;

    @Column(name="Position",columnDefinition = "nvarchar(max)")
    @NotBlank
    @NotNull
    private String position;

    @Column(name="Premium",columnDefinition = "money default 0")
    @NotNull
    @PositiveOrZero
    private double premium;

    @Column(name="Surname",columnDefinition = "nvarchar(max)")
    @NotBlank
    @NotNull
    private String surname;

    @Column(name="Salary",columnDefinition = "money")
    @NotNull
    private double salary;

//    @OneToOne(mappedBy = "teacher")
//    private Lecture lecture;

    public TeacherDTO getDTO(){
        return new TeacherDTO(id,employmentDate,isAssistant,isProfessor,name,position,premium,salary,surname);
    }
}
