package ru.onalex.cw280225.entities;

//import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

    @NotNull
    @NotBlank
    @NotEmpty
    int phone;

    @Range(min = 0, max = 100)
    int age;

    @Size(min = 5, max = 100, message = "от 5-ти до 100 знаков")
    String address;

//    @JoinTable()
}
