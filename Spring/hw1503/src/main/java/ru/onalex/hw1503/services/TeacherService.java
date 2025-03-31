package ru.onalex.hw1503.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.onalex.hw1503.models.Teacher;
import ru.onalex.hw1503.repositories.TeacherRepositorySpec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepositorySpec teacherRepositorySpec;

    public String getTeachersSpec(
            Model model,
            String name,
            String surname,
            String position,
            String startEmpDate,
            String salaryFrom,
            String premiumFrom,
            int page,
            int size,
            String sortDirection,
            String sortField) {
        Date startDate = new Date();
        Double salaryFromValue = Double.valueOf(salaryFrom);
        Double premiumFromValue = Double.valueOf(premiumFrom);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("/home/filter?");
        String sortParam = "";

        try {
            startDate = formatter.parse(startEmpDate);
//            System.out.println("Дата в формате Date: " + date);
            Specification<Teacher> spec = Specification.where(null);

            if (name != null && !name.isEmpty()) {
                spec = spec.and(TeacherSpecifications.nameMatches(name));
                paramsBuilder.append("&name=").append(name);
                model.addAttribute("name", name);
            }
            if (surname != null && !surname.isEmpty()) {
                spec = spec.and(TeacherSpecifications.surnameMatches(surname));
                paramsBuilder.append("&surname=").append(surname);
                model.addAttribute("surname", surname);
            }
            if (position != null && !position.isEmpty()) {
                spec = spec.and(TeacherSpecifications.positionMatches(position));
                paramsBuilder.append("&position=").append(position);
                model.addAttribute("position", position);
            }
            if (salaryFromValue >= 1) {
                spec = spec.and(TeacherSpecifications.salaryGreater(salaryFromValue));
                paramsBuilder.append("&salaryfrom=").append(salaryFromValue);
                model.addAttribute("salaryfrom", salaryFromValue);
            }
            if (premiumFromValue >= 1) {
                spec = spec.and(TeacherSpecifications.premiumGreater(premiumFromValue));
                paramsBuilder.append("&premiumfrom=").append(premiumFromValue);
                model.addAttribute("premiumfrom", premiumFromValue);
            }
            if (startEmpDate != null) {
                spec = spec.and(TeacherSpecifications.hasEmpoyeeDateAfter(startDate));
                paramsBuilder.append("&empdate=").append(startEmpDate);
                model.addAttribute("empdate", startEmpDate);
            }
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
//            paramsBuilder.append("&sort-field=").append(sortField);
            sortParam = "&sort-field=" + sortField;

            Pageable pageable = PageRequest.of(page, size, sort);
            Page<Teacher> teachers = teacherRepositorySpec.findAll(spec,pageable);
            model.addAttribute("teachers", teachers);
            model.addAttribute("page", page);
            model.addAttribute("pages", teachers.getTotalPages());
            model.addAttribute("size", size);
            model.addAttribute("params", paramsBuilder.toString());
            model.addAttribute("sortparam", sortParam);

            long count = teacherRepositorySpec.count(spec);
            model.addAttribute("count", count);
        } catch (ParseException e) {
            model.addAttribute("error","Ошибка преобразования: " + e.getMessage());
        }

        return "index";

    }


}
