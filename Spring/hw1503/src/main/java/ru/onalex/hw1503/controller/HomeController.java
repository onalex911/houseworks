package ru.onalex.hw1503.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.onalex.hw1503.models.Teacher;
import ru.onalex.hw1503.services.TeacherService;
import ru.onalex.hw1503.repositories.TeacherRepository;


import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private TeacherService teacherService;


    @GetMapping
    public String home(Model model) {
        return teacherService.getHomePage(model);
    }

    @GetMapping("/sorted/{field}")
    public String getSorted(Model model, @PathVariable String field) {
        return teacherService.getTeachersSorted(model, field);
    }
}
