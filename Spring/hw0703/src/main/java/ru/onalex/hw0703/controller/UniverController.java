package ru.onalex.hw0703.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.onalex.hw0703.repositories.*;

import static ru.onalex.hw0703.utils.UniverUtils.*;

@RestController
@RequestMapping("/api/univer")
public class UniverController {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private TeachersJpaRepository teachersJpaRepository;
    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;
    @Autowired
    private FacultyButRepository facultyButRepository;
    @Autowired
    private GroupJpaRepository groupJpaRepository;

//1
    @GetMapping("/depts")
    public String getDepts() {
        return getDeptsTable(departmentRepository);
    }
//2
    @GetMapping("/groups")
    public String getGroup() {
        return getGroupTable(groupRepository);
    }
//3
    @GetMapping("/teachers")
    public String getTeachers() {
        return getTeachersTable(teacherRepository);
    }
//4
    @GetMapping("/faculties-deans")
    public String getFaculties() {
        return getFacultiesDeans(facultyRepository);
    }
//5
    @GetMapping("/professors/{salary}")
    public String getProfessors(@PathVariable(name="salary") int salary) {
        return getProfessorsSalary(teachersJpaRepository,salary);
    }
//6
    @GetMapping("/depts-fin/{from}/{to}")
    public String getDepartmentFin(
            @PathVariable(name = "from") double from,
            @PathVariable(name = "to") double to
    ) {
        return getDeptsFinBetween(departmentJpaRepository,from,to);
    }
    //7
    @GetMapping("/faculties-but")
    public String getFacultiesButCS() {
        return getFacultiesBut(facultyButRepository);
    }
    //8
    @GetMapping("/not-prof")
    public String getNotProf() {
        return getNotProfessors(teachersJpaRepository);
    }
    //9
    @GetMapping("/assist-prem/{from}/{to}")
    public String getAssistPrem(
            @PathVariable(name = "from") int from,
            @PathVariable(name = "to") int to
    ) {
        return getAssistantPremium(teachersJpaRepository,from,to);
    }
    //10
    @GetMapping("/assist-salary")
    public String getAssistSal() {
        return getAssistantSalary(teachersJpaRepository);
    }
    //11
    @GetMapping("/teachers-emp-before")
    public String getTeachersEmpBefore() {
        return getTeachersEmployedBefore(teachersJpaRepository);
    }
    //12
    @GetMapping("/not-sd")
    public String getDeptsNotSD() {
        return getDeptsNotSoftDev(departmentJpaRepository);
    }

    //13
    @GetMapping("/assist-pay/{greater}")
    public String getAssistPay(@PathVariable(name = "greater") double summ) {
        return getAssistantPayment(teachersJpaRepository, summ);
    }
    //14
    @GetMapping("/gr-yr-rat/{year}/{from}/{to}")
    public String getGrYrRat(
            @PathVariable(name = "year") int year,
            @PathVariable(name = "from") int from,
            @PathVariable(name = "to") int to
    ) {
        return getGroupByYearWithRatings(groupJpaRepository,year,from,to);
    }
    //15
    @GetMapping("/assist-payments")
    public String getAssistPayments() {
        return getAssistantPayments(teachersJpaRepository);
    }

}
