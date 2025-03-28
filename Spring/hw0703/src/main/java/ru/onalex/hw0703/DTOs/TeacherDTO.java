package ru.onalex.hw0703.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Date;

//@Data
public class TeacherDTO {

    private int id;
    private Date employmentDate;
    private Boolean isAssistant;
    private Boolean isProfessor;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public void setAssistant(Boolean assistant) {
        isAssistant = assistant;
    }

    public void setProfessor(Boolean professor) {
        isProfessor = professor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public Boolean getAssistant() {
        return isAssistant;
    }

    public Boolean getProfessor() {
        return isProfessor;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getPremium() {
        return premium;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
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
