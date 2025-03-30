package ru.onalex.hw1503.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.onalex.hw1503.services.TeacherService;

import java.util.Date;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private TeacherService teacherService;

//    @GetMapping
//    public String home(Model model) {
//        return teacherService.getHomePage(model);
//    }
    @GetMapping()
    public String home(Model model,
                       @RequestParam(name="page",required = false ,defaultValue = "0") int page,
                       @RequestParam(name="size",required = false ,defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return teacherService.getTeacherPage(model,pageable);
    }

//    @GetMapping()()
//        public String homeFiltered(Model model,
//                           @RequestParam(name="page",required = false ,defaultValue = "0") int page,
//                           @RequestParam(name="size",required = false ,defaultValue = "10") int size) {
//            PageRequest pageable = PageRequest.of(page, size);
//            return teacherService.getTeacherPage(model,pageable);
//        }

    @GetMapping("/sorted/{field}")
    public String getSorted(Model model, @PathVariable String field) {
        return teacherService.getTeachersSorted(model, field);
    }
    @GetMapping("/sortedby/{field}")
    public String getSortedBy(Model model, @PathVariable String field) {
        return teacherService.getTeachersSortedBy(model, field);
    }
    @GetMapping("/filtered-by/{field}/{substring}")
    public String getSorted(Model model, @PathVariable String field,@PathVariable String substring) {
        return teacherService.getTeachersFilteredOrderedBy(model, field, substring);
    }

    @GetMapping("/filter")
    public String getFilteredSpec(Model model,
        @RequestParam(name = "name", defaultValue = "",required = false) String name,
        @RequestParam(name = "surname", defaultValue = "",required = false) String surname,
        @RequestParam(name = "position", defaultValue = "",required = false) String position,
        @RequestParam(name = "emp-date",defaultValue = "1900-01-01",required = false) String empDate,
        @RequestParam(name = "salary-from",defaultValue = "1.00",required = false) String salaryFrom,
        @RequestParam(name = "premium-from",defaultValue = "1.00",required = false) String premiumFrom){
            return teacherService.getTeachersSpec(model, name, surname, position, empDate, salaryFrom, premiumFrom);
    }
}
