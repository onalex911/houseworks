package ru.onalex.hw0103.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Curators")
public class Curator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name",columnDefinition = "nvarchar(max)")
    @NotBlank
    @NotNull
    private String name;

    @Column(name="Surname",columnDefinition = "nvarchar(max)")
    @NotBlank
    @NotNull
    private String surname;
}
