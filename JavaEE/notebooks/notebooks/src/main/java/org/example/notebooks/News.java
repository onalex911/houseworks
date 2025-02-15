package org.example.notebooks;

import lombok.Data;

import java.util.Date;

@Data
public class News {
    private int id;
    private Date date;
    private String imagePath;
    private String title;
    private String content;

    public News(int id, Date date, String imagePath, String title, String content) {
        this.id = id;
        this.date = date;
        this.imagePath = imagePath;
        this.title = title;
        this.content = content;
    }
}
