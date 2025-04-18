package ru.onalex.hw2903.models;


import java.util.Date;

//@Entity
//@Table(name="Teachers")
//@Data
public class TeacherModel {
    private int id;
    private String name;
    private String surname;
    private String position;
    private Date employmentDate;
    private Boolean isAssistant;
    private Boolean isProfessor;
    private double premium;
    private double salary;
    private static int count = 1;

    public TeacherModel(String name, String surname, String position, Date employmentDate, Boolean isAssistant, Boolean isProfessor, double premium, double salary) {
        this.id = count++;
        this.employmentDate = employmentDate;
        this.isAssistant = isAssistant;
        this.isProfessor = isProfessor;
        this.name = name;
        this.position = position;
        this.premium = premium;
        this.surname = surname;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", employmentDate=" + employmentDate +
                ", isAssistant=" + isAssistant +
                ", isProfessor=" + isProfessor +
                ", premium=" + premium +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Boolean getAssistant() {
        return isAssistant;
    }

    public void setAssistant(Boolean assistant) {
        isAssistant = assistant;
    }

    public Boolean getProfessor() {
        return isProfessor;
    }

    public void setProfessor(Boolean professor) {
        isProfessor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
