package ru.onalex.hw0103.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name",columnDefinition = "nvarchar(10)",unique=true)
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "Year")
    @NotNull
    @Range(min = 1,max = 5)
    private int year;

    @OneToOne
    @JoinColumn(name = "DepartmentId",referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "group")
    private Set<GroupsCurators> groupsCurators = new HashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<GroupsLectures> groupsLectures = new HashSet<>();
}
