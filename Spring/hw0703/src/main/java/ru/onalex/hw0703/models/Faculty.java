package ru.onalex.hw0703.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import ru.onalex.hw0703.DTOs.FacultyDTO;

@Entity
@Table(name="Faculties") //Факультеты
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name",columnDefinition = "nvarchar(100)",unique=true)
    @NotNull
    @NotEmpty
    private String name;

    @Column(name="Dean",columnDefinition = "nvarchar(max)")
    @NotNull
    @NotEmpty
    private String dean;

//    @Column(name="Financing",columnDefinition = "money default 0")
//    @NotNull
//    @PositiveOrZero
//    private double financing;

//    @OneToOne(mappedBy = "faculty")
//    private Department department;
//    @OneToMany(mappedBy = "subject")

    public FacultyDTO getDTO(){
        return new FacultyDTO(id, name, dean);
    }
}
