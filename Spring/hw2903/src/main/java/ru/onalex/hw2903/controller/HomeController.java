package ru.onalex.hw2903.controller;

import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.onalex.hw2903.entity.Teacher;
import ru.onalex.hw2903.models.TeacherModel;
import ru.onalex.hw2903.repository.TeacherRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
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

//    public HomeController(Faker faker, TeacherRepository teacherRepository) {
//        this.faker = faker;
//        this.teacherRepository = teacherRepository;
//
//        try {
//            initData();
//
//        }catch (ParseException e){
//            System.out.println(e.getMessage());
//        }
//    }

    private void initData() throws ParseException {
//        if (teacherRepository.count()==0){
//        List<Teacher> teachers = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = sdf.parse("01/01/2000");
        Date endDate = new Date();
        TimeUnit timeUnit = TimeUnit.DAYS;
        for (int i = 0; i < 10; i++) {
            String name = faker.name().name();
            String lastName = faker.name().lastName();
            String position = faker.company().profession();
//            Date empDate = faker.date().past((int)(ChronoUnit.DAYS.between( (Temporal) startDate, (Temporal) endDate)),timeUnit);
//            Date empDate = faker.date().birthday();
            Date empDate = faker.date().past();
            LocalDate empDateLD = dateToLocalDate(empDate);

            double salary = Double.parseDouble(faker.commerce().price(10000.0,99999.0).replace(',','.'));
            double premuim = Double.parseDouble(faker.commerce().price(1000.0,9999.0).replace(',','.'));
            boolean isAssist = faker.bool().bool();
            boolean isProf = faker.bool().bool();
            teachers.add(new Teacher(name,lastName,position,empDateLD,isAssist,isProf,salary,premuim));
        }
            teacherRepository.saveAll(teachers);
//        return teachers;
    }

    @GetMapping("/generate")
    public String generate(Model model) {
        try {
            initData();
            model.addAttribute("teachers", teachers);
        }catch (ParseException e){
            model.addAttribute("error",e.getMessage());
        }
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
        Optional<Teacher> person = teacherRepository.findById(id);
//        Person person = personRepository.getById(id);
        teacherRepository.delete(person.get());
        String answer = "Person deleted => " + person.isPresent();
        return answer;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("title","Create Page");
        model.addAttribute("teacher",new Teacher());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid Teacher teacher, BindingResult bindir, Model model){
        ///////////////////////////////////////////////////////////////////

//        bindir.hasErrors()-> kakie libo osibki
//        bindir.hasFieldErrors("age")-> konkretnaya osibka
//        bindir.getAllErrors("age")-> spisok vsex osibok
//        bindir.getFieldErrors("age")-> soobsenie konkretnoy osibok
//        bindir.getFieldError("age")-> 1 soobsenie konkretnoy osibok
//         bindir.reject("errorrrrrrrr");
//         bindir.reject("age","errorrrrrrrr");

        if (bindir.hasErrors()){
            model.addAttribute("error","Check your input");
            model.addAttribute("teacher",teacher);
//            model.addAttribute("errors",bindir.getAllErrors());
            return "create";
        }
        teacherRepository.save(teacher);
        model.addAttribute("title","Home Page");
        model.addAttribute("teachers",teacherRepository.findAll());
        return "index";
    }

}


