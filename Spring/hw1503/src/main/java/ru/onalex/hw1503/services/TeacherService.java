package ru.onalex.hw1503.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.onalex.hw1503.DTO.TeacherDTO;
import ru.onalex.hw1503.models.Teacher;
import ru.onalex.hw1503.repositories.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    public TeacherRepository teacherRepository;

    public String getHomePage(Model model){
        List<TeacherDTO> teachers  =  getTeachers();
        String title = "Hello from Java!";
//        Teacher teacher = teachers.get(30);
        model.addAttribute("teachers", teachers);
        model.addAttribute("title", title);
        model.addAttribute("errors", "");
        return "index";
    }

    private List<TeacherDTO> getTeachers(){
        List<TeacherDTO> teachersDTO = new ArrayList<>();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            teachersDTO.add(teacher.getDTO());
        }
        return teachersDTO;
    }

    public String getTeachersSorted(Model model,String field){
        String finalField = field;
        List<TeacherDTO> teachers = getTeachers()
                .stream()
                .sorted((t1, t2) -> {
                    switch (finalField) {
                        case "name":
                            return (t1.getName().compareTo(t2.getName()));
                        case "surname":
                            return t1.getSurname().compareTo(t2.getSurname());
                        case "position":
                            return t1.getPosition().compareTo(t2.getPosition());
                        case "empdate":
                            return t1.getEmploymentDate().compareTo(t2.getEmploymentDate());
                        case "salary":
                            return (int) (t1.getSalary() - t2.getSalary());
                        case "premium":
                            return (int) (t1.getPremium() - t2.getPremium());
                        default:
                            return t1.getId() - t2.getId();
                    }
                }).toList();
        model.addAttribute("teachers", teachers);

        return "index";
    }
}
