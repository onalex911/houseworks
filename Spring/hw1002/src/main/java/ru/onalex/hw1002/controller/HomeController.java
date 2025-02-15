package ru.onalex.hw1002.controller;

import org.springframework.web.bind.annotation.*;
import ru.onalex.hw1002.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    List<Person> people;
    public static String tblHd = "<h1>Hall of fame</h1>" +
            "<table><thead><tr><th>ID</th><th>Name</th><th>Surname</th><th>Age</th></tr></thead>" +
            "<tbody>";
    public static String tblFooter = "</tbody></table>";

    public HomeController() {
        people = new ArrayList<>();
        people.addAll(List.of(
                new Person("Alec","Baldwin", 1958),
                new Person("Robert","Dawney Jr.", 1965),
                new Person("Johnny","Depp", 1963),
                new Person("Robert","De Niro", 1943),
                new Person("Tom","Cruise", 1962),
                new Person("Jude","Law", 1972),
                new Person("Anthony","Hopkins", 1972),
                new Person("Audrey","Hepburn", 1929),
                new Person("Milla","Jovovich", 1975),
                new Person("Uma","Thurman", 1970),
                new Person("Nicole","Kidman", 1967),
                new Person("Demi","Moore", 1962),
                new Person("Charlize","Theron", 1975),
                new Person("Emma","Watson", 1990)
        ));
    }
    // 1
    @GetMapping("/")
    public String allPeople() {
        if(people.isEmpty()){
            return "No people found";
        }else {
            String out = tblHd;
            for (Person p : people) {
                out += drawRow(p);
            }
            out += tblFooter;
            return out;
        }
    }
    // 2
    @GetMapping("/add")
    public String addPerson(
        @RequestParam(name = "name") String firstName,
        @RequestParam(name = "year") Integer birthYear) {
        people.add(new Person(firstName,"", birthYear));
        return allPeople();
    }
    // 3
    @GetMapping("/clear")
    public String clearPeople(){
        people.clear();
        return allPeople();
    }
    // 4
    @GetMapping("/get/{id}")
    public String getPerson(
            @PathVariable(name = "id") Integer id) {
        Person person = people.stream().filter(p -> p.getId()==id).findFirst().orElse(null);
        return person == null ? printWrong("id") : drawPerson(person);
    }
    // 5
    @GetMapping("/addpv/{name}/{year}")
    public String addPersonPV(
            @PathVariable(name = "name") String firstName,
            @PathVariable(name = "year") Integer birthYear) {
        people.add(new Person(firstName,"", birthYear));
        return allPeople();
    }
    // 6
    @GetMapping("/getbyna")
    public String getByNA(
            @RequestParam(name = "name") String firstName,
            @RequestParam(name = "age") Integer age) {
        Person person = people.stream().filter(p -> p.getAge() == age && Objects.equals(p.getFirstName().toLowerCase(), firstName.toLowerCase())).findFirst().orElse(null);
        return person == null ? printWrong("params") : drawPerson(person);
    }
    // 7
    @GetMapping("/setage/{id}")
    public String setPersonAge(
            @PathVariable(name = "id") Integer id,
            @RequestParam(name = "age") Integer age) {
        Person person = people.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if(person == null) {
            return printWrong("id");
        }
        person.setAge(age);
        return allPeople();
    }
    // 8
    @GetMapping("/delete")
    public String setPersonAge(
            @RequestParam(name = "name") String name) {
        Person person = people.stream().filter(p -> Objects.equals(p.getFirstName().toLowerCase(),name.toLowerCase())).findFirst().orElse(null);
        if(person == null) {
            return printWrong("name");
        }
        people.removeIf(p -> Objects.equals(p.getFirstName(),name));
        return allPeople();
    }
    // 9
    @GetMapping("/sort")
    public String sortPeople(
            @RequestParam(name = "sortby") String kindOfSort) {
        String out = "";
        switch (kindOfSort) {
            case "name": people.sort(Comparator.comparing(Person::getFirstName));
            break;
            case "age": people.sort(Comparator.comparing(Person::getAge));
            break;
            default: out = "<p>Type of sorting is not valid. The list is unsorted</p>";
        }
        return out + allPeople();
    }
    // 10
    @GetMapping("/check")
    public String checkPersonByName(
            @RequestParam(name = "name") String name) {
        List<Person> persons = people.stream().filter(p -> Objects.equals(p.getFirstName().toLowerCase(),name.toLowerCase())).toList();
        if(persons.isEmpty()) {
            return printWrong("name");
        }
        return drawPersons(persons);

    }

    private String drawRow(Person person) {
        return "<tr><td>" + person.getId() + "</td><td>" + person.getFirstName() + "</td>" +
                "<td>" + person.getLastName() + "</td><td>" + person.getAge() + "</td></tr>";
    }

    private String drawPerson(Person person) {
        return tblHd + drawRow(person) + tblFooter;
    }

    private String drawPersons(List<Person> persons) {
        String out = tblHd;
        for (Person person : persons) {
            out += drawRow(person);
        }
        out += tblFooter;
        return out;
    }

    public String printWrong(String name) {
        return "Wrong " + name + "! Actor not found";
    }
}
