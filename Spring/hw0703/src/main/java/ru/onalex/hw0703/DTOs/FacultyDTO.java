package ru.onalex.hw0703.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
public class FacultyDTO {
    private int id;
    private String name;
    private String dean;

    public FacultyDTO(int id, String name, String dean) {
        this.id = id;
        this.name = name;
        this.dean = dean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }
}

