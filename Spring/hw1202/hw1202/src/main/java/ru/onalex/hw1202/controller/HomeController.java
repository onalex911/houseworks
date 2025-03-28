package ru.onalex.hw1202.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.onalex.hw1202.model.Person;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    private List<Person> people;
    public HomeController() {
        people = new ArrayList<>();
        people.addAll(List.of(
                new Person("Alec", 1958),
                new Person("Robert", 1965),
                new Person("Johnny", 1963),
                new Person("Robert", 1943),
                new Person("Tom", 1962),
                new Person("Jude", 1972),
                new Person("Anthony", 1972),
                new Person("Audrey", 1929),
                new Person("Milla", 1975),
                new Person("Uma", 1970),
                new Person("Nicole", 1967),
                new Person("Demi", 1962),
                new Person("Charlize", 1975),
                new Person("Emma", 1990)
        ));
    }

    // 1
    @GetMapping("/users")
    public ResponseEntity<List<Person>> allPeople() {
        if(people.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    // 2
    @PostMapping("/users")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        people.add(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    // 3
    @GetMapping("/users/{index}")
    public ResponseEntity<Person> getUserById(@PathVariable(name = "index") Integer id) {
        Person person = people.stream().filter(p -> p.getId()==id).findFirst().orElse(null);
        return person == null ? ResponseEntity.noContent().build() : new ResponseEntity<>(person, HttpStatus.OK);

    }
    // 4
    @PutMapping("/users/{index}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable(name = "index") Integer id,
            @RequestBody Person newPerson) {
        Person person = people.stream().filter(p -> p.getId()==id).findFirst().orElse(null);
        if(person==null){
            return ResponseEntity.noContent().build();
        }
        if(newPerson.getAge() != 0){
            if(newPerson.getAge() < 0 || newPerson.getAge() > 100)
                return ResponseEntity.badRequest().body(person);

            person.setAge(newPerson.getAge());

        }
        if(!newPerson.getName().isEmpty()){
            if(newPerson.getName().length() > 50)
                return ResponseEntity.badRequest().body(person);

            person.setName(newPerson.getName());

        }

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    // 5
    @DeleteMapping("/users/{index}")
    public ResponseEntity<Person> deletePerson(@PathVariable(name = "index") Integer id) {
        Person person = people.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if(person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        people.removeIf(p -> p.getId() == id);
        return new ResponseEntity<>(person, HttpStatus.ACCEPTED);

    }
    // 6
    @DeleteMapping("/users/clear")
    public ResponseEntity<?> deleteAll() {
        if(people.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        people.clear();
        return new ResponseEntity<>(people, HttpStatus.ACCEPTED);

    }
}
