package ru.onalex.hw0703.DTOs;

public class DepartmentDTO {
    private int id;
    private double financing;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFinancing() {
        return financing;
    }

    public void setFinancing(double financing) {
        this.financing = financing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentDTO(int id, double financing, String name) {
        this.id = id;
        this.financing = financing;
        this.name = name;
    }
}
