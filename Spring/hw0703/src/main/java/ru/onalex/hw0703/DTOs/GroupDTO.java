package ru.onalex.hw0703.DTOs;

import ru.onalex.hw0703.models.Group;

public class GroupDTO {
    private int id;
    private String name;
    private int rating;
    private int year;

    public GroupDTO(int id, String name, int rating, int year) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

//    public Group getGroup(){
//        return new Group(this.id, this.name, this.rating, this.year);
//    }
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
