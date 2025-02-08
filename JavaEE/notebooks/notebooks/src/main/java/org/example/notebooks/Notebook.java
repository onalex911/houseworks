package org.example.notebooks;

import lombok.Data;

@Data
public class Notebook{
    int id;
    String name;
    String description;
    String photoPath;
    int price;

    public Notebook(int id, String name, String description, String photoPath, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoPath = photoPath;
        this.price = price;
    }
}
