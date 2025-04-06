package ru.onalex.hw2903.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Table(name = "Teachers")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "Name", columnDefinition = "nvarchar(max)")
    @NotNull()
    @NotBlank(message = "Name must not be blank!")
    private String name;

    @Column(name = "Surname", columnDefinition = "nvarchar(max)")
    @NotNull()
    @NotBlank(message = "Surname must not be blank!")
    private String surname;

    @Column(name = "Position", columnDefinition = "nvarchar(max)")
    @NotBlank(message = "Position must not be blank!")
    @NotNull
    private String position;

    @Column(name = "EmploymentDate")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message="Employment date must be in the past or present")
//    private Date employmentDate;
    private LocalDate employmentDate;

    @Column(name = "IsAssistant")
    @NotNull
    private Boolean isAssistant;

    @Column(name = "IsProfessor")
    @NotNull
    private Boolean isProfessor;

    @Column(name = "Premium", columnDefinition = "money default 0")
    @NotNull()
    @Positive(message = "Premium must be greater than 0!")
    private double premium;


    @Column(name = "Salary", columnDefinition = "money")
    @NotNull(message = "Salary must not be empty!")
    @Positive(message = "Salary must be greater than 0!")
    private double salary;

    public Teacher(String name, String surname, String position, LocalDate empDate, boolean isAssist, boolean isProfessor, double salary, double premium) {
            this.id = id;
            this.employmentDate = empDate;
            this.isAssistant = isAssist;
            this.isProfessor = isProfessor;
            this.name = name;
            this.position = position;
            this.premium = premium;
            this.surname = surname;
            this.salary = salary;

    }

    public Teacher() {

    }
}
