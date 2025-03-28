package ru.onalex.hw0703.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import ru.onalex.hw0703.DTOs.GroupDTO;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name",columnDefinition = "nvarchar(10)",unique=true)
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "Rating")
    @NotNull
    @Range(min = 0,max = 5)
    private int rating;

    @Column(name = "Year")
    @NotNull
    @Range(min = 1,max = 5)
    private int year;


    public GroupDTO getDTO(){
        return new GroupDTO(id, name, rating, year);
    }


//    @OneToOne
//    @JoinColumn(name = "DepartmentId",referencedColumnName = "id")
//    private Department department;
//
//    @OneToMany(mappedBy = "group")
//    private Set<GroupsCurators> groupsCurators = new HashSet<>();
//
//    @OneToMany(mappedBy = "group")
//    private Set<GroupsLectures> groupsLectures = new HashSet<>();
}
