package ru.onalex.hw0103.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="LectureRoom",columnDefinition = "nvarchar(max)")
    @NotNull
    @NotEmpty
    private String lectureRoom;

    @OneToOne
    @JoinColumn(name = "SubjectId",referencedColumnName = "id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "TeacherId",referencedColumnName = "id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture")
    private Set<GroupsLectures> groupsLectures = new HashSet<>();

}
