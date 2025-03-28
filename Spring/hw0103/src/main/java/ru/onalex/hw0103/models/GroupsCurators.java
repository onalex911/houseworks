package ru.onalex.hw0103.models;

import jakarta.persistence.*;

@Entity
public class GroupsCurators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name= "curator_id")
    private Curator curator;

    @ManyToOne
    @JoinColumn(name= "group_id")
    private Group group;


}
