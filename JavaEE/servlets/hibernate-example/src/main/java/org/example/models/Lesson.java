package org.example.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class Lesson {
    private long id;
    private String name;

    //Many-to-One
    private Course course; //принадлежность урока к курсу
}
