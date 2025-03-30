package ru.onalex.hw1503.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.onalex.hw1503.DTO.TeacherDTO;
import ru.onalex.hw1503.models.Teacher;
import ru.onalex.hw1503.repositories.TeacherPgRepository;
import ru.onalex.hw1503.repositories.TeacherRepository;
import ru.onalex.hw1503.repositories.TeacherRepositorySpec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    public TeacherRepository teacherRepository;
    @Autowired
    private TeacherPgRepository teacherPgRepository;

    @Autowired
    private TeacherRepositorySpec teacherRepositorySpec;

    public String getTeachersSpec(Model model,String name,String surname,String position,String startEmpDate,String salaryFrom,String premiumFrom) {
        Date startDate = new Date();
        Double salaryFromValue = Double.valueOf(salaryFrom);
        Double premiumFromValue = Double.valueOf(premiumFrom);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = formatter.parse(startEmpDate);
//            System.out.println("Дата в формате Date: " + date);
            Specification<Teacher> spec = Specification.where(null);

            if (name != null && !name.isEmpty()) {
                spec = spec.and(TeacherSpecifications.nameMatches(name));
            }
            if (surname != null && !surname.isEmpty()) {
                spec = spec.and(TeacherSpecifications.surnameMatches(surname));
            }
            if (position != null && !position.isEmpty()) {
                spec = spec.and(TeacherSpecifications.positionMatches(position));
            }
            if (salaryFromValue >= 1) {
                spec = spec.and(TeacherSpecifications.salaryGreater(salaryFromValue));
            }
            if (premiumFromValue >= 1) {
                spec = spec.and(TeacherSpecifications.premiumGreater(premiumFromValue));
            }
            if (startEmpDate != null) {
                spec = spec.and(TeacherSpecifications.hasEmpoyeeDateAfter(startDate));
            }
            List<Teacher> teachers = teacherRepositorySpec.findAll(spec);
            model.addAttribute("teachers", teachers);
        } catch (ParseException e) {
            model.addAttribute("error","Ошибка преобразования: " + e.getMessage());
        }

        return "index";

    }

//    public String getHomePage(Model model){
//        List<TeacherDTO> teachers  =  getTeachers();
//        String title = "Hello from Java!";
////        Teacher teacher = teachers.get(30);
//        model.addAttribute("pageable", null);
//        model.addAttribute("teachers", teachers);
//        model.addAttribute("title", title);
//        model.addAttribute("errors", "");
//        return "index";
//    }
    public String getTeacherPage(Model model, PageRequest pageable){
            List<TeacherDTO> teachers  = getTeachers(pageable);
            String title = "Hello from Java!";
            long count = teacherRepository.count();
            long pages = count/pageable.getPageSize();
            long size = count%pageable.getPageSize();
            String href = "http://localhost:8081/home?page=";
//            model.addAttribute("pageable", pageable);
            model.addAttribute("href", href);
            model.addAttribute("pages", pages);
            model.addAttribute("size", size);
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
    private List<TeacherDTO> getTeachers(PageRequest pageable){
        List<TeacherDTO> teachersDTO = new ArrayList<>();
        List<Teacher> teachers = teacherPgRepository.findAll(pageable).getContent();

        for (Teacher teacher : teachers) {
            teachersDTO.add(teacher.getDTO());
        }
        return teachersDTO;
    }

    public String getTeachersSortedBy(Model model,String field) {
        String finalField = field;
        List<Teacher> teachers = teacherRepository.selectOrderedBy(finalField);
//        List<TeacherDTO> teachers = getTeachers();
        model.addAttribute("teachers", teachers);
        return "index";
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
    public String getTeachersFilteredOrderedBy(Model model, String field, String substring){
        String filterField = field;
        List<TeacherDTO> teachersDTO = new ArrayList<>();
        //String condition = "Surname LIKE '%'"+substring+" OR Surname LIKE '%'"+substring+"'%' OR Surname LIKE "+substring+"'%' ORDER BY "+finalField;
        List<Teacher> teachersPg = teacherPgRepository.selectFiltered(filterField,substring);
        for (Teacher teacher : teachersPg) {
            teachersDTO.add(teacher.getDTO());
        }
//        teachersDTO.stream()
//                .sorted((t1, t2) -> {
//                    switch (finalField) {
//                        case "name":
//                            return (t1.getName().compareTo(t2.getName()));
//                        case "surname":
//                            return t1.getSurname().compareTo(t2.getSurname());
//                        case "position":
//                            return t1.getPosition().compareTo(t2.getPosition());
//                        case "empdate":
//                            return t1.getEmploymentDate().compareTo(t2.getEmploymentDate());
//                        case "salary":
//                            return (int) (t1.getSalary() - t2.getSalary());
//                        case "premium":
//                            return (int) (t1.getPremium() - t2.getPremium());
//                        default:
//                            return t1.getId() - t2.getId();
//                    }
//                }).toList();
        model.addAttribute("teachers", teachersDTO);

        return "index";
    }
}
