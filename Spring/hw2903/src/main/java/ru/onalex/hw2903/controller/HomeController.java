package ru.onalex.hw2903.controller;

import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.onalex.hw2903.entity.Teacher;
import ru.onalex.hw2903.repository.TeacherRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static ru.onalex.hw2903.utils.Utils.dateToLocalDate;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private Faker faker;

    @Autowired
    TeacherRepository teacherRepository;

    List<Teacher> teachers = new ArrayList<>();

    private void initData() {

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.of(2020,1,1);
        TimeUnit timeUnit = TimeUnit.DAYS;
        for (int i = 0; i < 10; i++) {
            String name = faker.name().name();
            String lastName = faker.name().lastName();
            String position = faker.company().profession();
            Date empDateTmp = faker.date().past((int)(ChronoUnit.DAYS.between(startDate,endDate)),timeUnit);
            LocalDate empDate = dateToLocalDate(empDateTmp);
            double salary = Double.parseDouble(faker.commerce().price(10000.0,99999.0).replace(',','.'));
            double premuim = Double.parseDouble(faker.commerce().price(1000.0,9999.0).replace(',','.'));
            boolean isAssist = faker.bool().bool();
            boolean isProf = faker.bool().bool();
            teachers.add(new Teacher(name,lastName,position,empDate,isAssist,isProf,salary,premuim));
        }

        teacherRepository.saveAll(teachers);
    }

    @GetMapping("/generate")
    public String generate(Model model) {
        initData();
        model.addAttribute("teachers", teachers);
        return "index";
    }

    @GetMapping
    public String home(Model model) {
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("teachers", teachers);
            return "index";
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public  String removePersonAjax(@PathVariable int id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()) {
            teacherRepository.delete(teacher.get());
            return "Teacher => " + teacher.get().getName() + " is deleted";
        }else{
            return "Teacher with id=" + id + " is not found!";
        }
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("title","Create Page");
        model.addAttribute("teacher",new Teacher());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid Teacher teacher, BindingResult bindir, Model model){

        if (bindir.hasErrors()){
            model.addAttribute("error","Check your input");
            model.addAttribute("teacher",teacher);
            return "create";
        }
        teacherRepository.save(teacher);
        model.addAttribute("title","Home Page");
        model.addAttribute("teachers",teacherRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        Teacher teacher = teacherRepository.findById(id).get();
        model.addAttribute("title","Create Page");
        model.addAttribute("teacher",teacher);
        return "edit";
    }

    @PutMapping("/edit/{id}")
    public String edit(@Valid Teacher teacher, BindingResult bindir, Model model,@PathVariable int id){
        LocalDate minDate = LocalDate.of(2000, 1, 1); // Установите минимальную дату
        String errors = "";

        if (bindir.hasErrors()){
            model.addAttribute("error","Check your input");
            model.addAttribute("teacher",teacher);
            return "edit";
        }
        if(teacher.getEmploymentDate().isBefore(minDate)) {
            errors += "Date of birth cannot be before " + minDate;
        }

        if(teacherRepository.findById(id).isPresent() && errors.isEmpty()){
            teacherRepository.save(teacher);
            model.addAttribute("title","Home Page");
            model.addAttribute("teachers",teacherRepository.findAll());
            return "index";
        }
        model.addAttribute("error",errors);
        return "edit";
    }

}


