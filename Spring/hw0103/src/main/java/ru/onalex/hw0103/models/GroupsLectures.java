package ru.onalex.hw0103.models;

import jakarta.persistence.*;

@Entity
public class GroupsLectures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name= "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name= "group_id")
    private Group group;


}
