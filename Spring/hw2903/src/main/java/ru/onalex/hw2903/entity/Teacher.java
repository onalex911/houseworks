package ru.onalex.hw2903.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//import ru.onalex.hw1503.DTO.TeacherDTO;

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
    @NotBlank
    @NotNull
    private String position;

    @Column(name = "EmploymentDate")
    @NotNull
//    private Date employmentDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Min(value = 946666800L) //2000-01-01 00:00:00
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


//    @OneToOne(mappedBy = "teacher")
//    private Lecture lecture;

//    public TeacherDTO getDTO(){
//        return new TeacherDTO(id,employmentDate,isAssistant,isProfessor,name,position,premium,salary,surname);
//    }
}
