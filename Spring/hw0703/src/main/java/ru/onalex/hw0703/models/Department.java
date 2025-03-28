package ru.onalex.hw0703.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import ru.onalex.hw0703.DTOs.DepartmentDTO;

@Entity
@Table(name="Departments") //Кафедры
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name",columnDefinition = "nvarchar(100)",unique=true)
    @NotNull
    @NotEmpty
    private String name;

    @Column(name="Financing",columnDefinition = "money default 0")
    @NotNull
    @PositiveOrZero
    private double financing;

    public DepartmentDTO getDTO(){
        return new DepartmentDTO(id, financing, name);
    }

//    @OneToOne
//    @JoinColumn(name = "FacultyId",referencedColumnName = "id")
//    private Faculty faculty;
//
//    @OneToOne(mappedBy = "department")
//    private Group group;
}
