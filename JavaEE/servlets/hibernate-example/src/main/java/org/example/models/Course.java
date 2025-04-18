package org.example.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "students")
public class Course {
    private long id;
    private String title;

    //One-to-Many
    private List<Lesson> lessons; //состав курса: из каких уроков состоит

    //Many-to-Many
    private List<Student> students; //список студентов, записанных на курс


}
