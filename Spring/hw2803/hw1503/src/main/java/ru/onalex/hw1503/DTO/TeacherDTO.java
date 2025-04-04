package ru.onalex.hw1503.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherDTO {

    private int id;
    private Date employmentDate;
    private Boolean isAssistant;
    private Boolean isProfessor;
    private String name;


    public void setAssistant(Boolean assistant) {
        isAssistant = assistant;
    }

    public void setProfessor(Boolean professor) {
        isProfessor = professor;
    }

    public Boolean getAssistant() {
        return isAssistant;
    }

    public Boolean getProfessor() {
        return isProfessor;
    }

    private String position;
    private double premium;
    private String surname;
    private double salary;

    public TeacherDTO(int id, @NotNull Date employmentDate, @NotNull Boolean isAssistant, @NotNull Boolean isProfessor, @NotBlank @NotNull String name, @NotBlank @NotNull String position, @NotNull @PositiveOrZero double premium, @NotNull double salary, @NotBlank @NotNull String surname) {
        this.id = id;
        this.employmentDate = employmentDate;
        this.isAssistant = isAssistant;
        this.isProfessor = isProfessor;
        this.name = name;
        this.position = position;
        this.premium = premium;
        this.salary = salary;
        this.surname = surname;
    }

}
