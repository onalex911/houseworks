package ru.onalex.hw0703.utils;

import ru.onalex.hw0703.DTOs.DepartmentDTO;
import ru.onalex.hw0703.DTOs.TeacherDTO;
import ru.onalex.hw0703.models.Department;
import ru.onalex.hw0703.models.Faculty;
import ru.onalex.hw0703.models.Group;
import ru.onalex.hw0703.models.Teacher;
import ru.onalex.hw0703.repositories.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class UniverUtils {

    public static String TABLEHEAD = "<style>th, td{border-collapse:collapse;border:1px solid black;padding:5px;margin:0;}</style>\n<table><thead>";
    public static String TABLEFOOT = "\n</tbody></table>";

    public static String getDeptsTable(DepartmentRepository departmentRepository) {
        String[] hdTitles = {"ID","Department Name","Financing"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));
        ((List<Department>) departmentRepository.findAll())
            .stream()
            .map(Department::getDTO)
            .sorted(Comparator.comparing(DepartmentDTO::getId).reversed())
            .forEach(dept -> table
                .append("<tr><td>")
                .append(dept.getId())
                .append("</td><td>")
                .append(dept.getName())
                .append("</td><td>")
                .append(dept.getFinancing())
                .append("</td></tr>\n"));

        table.append(TABLEFOOT);
        return table.toString();
    }

    public static String getGroupTable(GroupRepository groupRepository) {
        String[] hdTitles = {"Group Name","Group Rating"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        ((List<Group>) groupRepository.findAll())
            .stream()
            .map(Group::getDTO)
            .forEach(group -> table.append("<tr><td>")
                .append(group.getName())
                .append("</td><td>")
                .append(group.getRating()).append("</td></tr>"));

        table.append(TABLEFOOT);
        return table.toString();
    }

    //3. Вывести для преподавателей их фамилию, процент ставки по отношению к надбавке и процент ставки по отношению к зарплате (сумма ставки и надбавки).
    public static String getTeachersTable(TeacherRepository teacherRepository) {
        String[] hdTitles = {"Surname","Salary","Premium","salary/premium, %","salary/pay, %"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        List<TeacherDTO> teachersData = new ArrayList<>();
        teachersData = ((List<Teacher>) teacherRepository.findAll())
                .stream()
                .map(Teacher::getDTO)
                .toList();

        for (TeacherDTO teacher : teachersData) {
            table.append("<tr><td>")
                .append(teacher.getSurname())
                .append("</td><td>")
                .append(teacher.getSalary())
                .append("</td><td>")
                .append(teacher.getPremium())
                .append("</td><td>")
                .append(String.format("%.2f", teacher.getSalary()/teacher.getPremium()*100))
                .append("</td><td>")
                .append(String.format("%.2f", teacher.getSalary()/(teacher.getSalary() + teacher.getPremium())*100))
                .append("</td></tr>");
        }

        table.append(TABLEFOOT);
        return table.toString();
    }
    //4
    public static String getFacultiesDeans(FacultyRepository facultyRepository) {
        StringBuilder strings = new StringBuilder();
       ((List<Faculty>) facultyRepository.findAll())
            .stream()
            .map(Faculty::getDTO).forEach(faculty -> strings
                .append("The dean of faculty ")
                .append(faculty.getName())
                .append(" is ")
                .append(faculty.getDean())
                .append("<br>"));
        return strings.toString();
    }
    //5
    public static String getProfessorsSalary(TeachersJpaRepository teachersJpaRepository, double salary) {
        StringBuilder strings = new StringBuilder();
        teachersJpaRepository.findByIsProfessorAndSalaryGreaterThan(true, salary)
               .forEach(teacher -> strings
                   .append("The professor ")
                   .append(teacher.getName())
                   .append(" ")
                   .append(teacher.getSurname())
                   .append(": ")
                   .append(teacher.getSalary())
                   .append("<br>")
        );
    return strings.toString();
    }
    //6
    public static String getDeptsFinBetween(DepartmentJpaRepository departmentJpaRepository, double from, double to) {
        StringBuilder strings = new StringBuilder();
        ((List<Department>) departmentJpaRepository.findByFinancingBetween(from, to))
               .forEach(dept -> strings
                   .append("The department of ")
                   .append(dept.getName())
                   .append(": ")
                   .append(dept.getFinancing())
                   .append("<br>")
        );
    return strings.toString();
    }
    //7
    public static String getFacultiesBut(FacultyButRepository facultyButRepository) {

        StringBuilder strings = new StringBuilder();
        ((List<Faculty>) facultyButRepository.findByNameNotContains("Computer Science"))
//        ((List<Faculty>) facultyButRepository.findByNameNotContains("Ipsum"))
                .forEach(faculty -> strings
                        .append("The name of faculty ")
                        .append(faculty.getName())
                        .append("<br>"));
        return strings.toString();
    }
    //8
    public static String getNotProfessors(TeachersJpaRepository teachersJpaRepository) {

        StringBuilder strings = new StringBuilder();
        ((List<Teacher>) teachersJpaRepository.findByIsProfessorIsNot(true))
                .forEach(teacher -> strings
                        .append("The teacher ")
                        .append(teacher.getName())
                        .append(" ")
                        .append(teacher.getSurname())
                        .append(" is not a professor")
                        .append("<br>")
                );
        return strings.toString();
    }
    //9
    public static String getAssistantPremium(TeachersJpaRepository teachersJpaRepository, double from, double to) {

        String[] hdTitles = {"Assistant's surname","Position","Salary","Premium"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        ((List<Teacher>) teachersJpaRepository.findByIsAssistantAndPremiumBetween(true, from, to))
                .forEach(teacher -> table
                        .append("<tr><td>")
                        .append(teacher.getSurname())
                        .append("<td></td>")
                        .append(teacher.getPosition())
                        .append("</td><td>")
                        .append(String.format("%.2f", teacher.getSalary()))
                        .append("</td><td>")
                        .append(String.format("%.2f", teacher.getPremium()))
                        .append("</td></tr>")
                );
        table.append(TABLEFOOT);
        return table.toString();
    }
    //10
    public static String getAssistantSalary(TeachersJpaRepository teachersJpaRepository) {
        String[] hdTitles = {"Assistant's surname","Salary"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        ((List<Teacher>) teachersJpaRepository.findByIsAssistant(true))
                .forEach(teacher -> table
                        .append("<tr>\n<td>")
                        .append(teacher.getSurname())
                        .append("</td>\n<td>")
                        .append(String.format("%.2f", teacher.getSalary()))
                        .append("</td>\n</tr>\n")
                );
        table.append(TABLEFOOT);
        return table.toString();
    }
    //11
    public static String getTeachersEmployedBefore(TeachersJpaRepository teachersJpaRepository) {
        String[] hdTitles = {"Teacher's surname","Position"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        String dateString = "2000-01-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateString);
            ((List<Teacher>) teachersJpaRepository.findByEmploymentDateBefore(date))
                .forEach(teacher -> table
                    .append("<tr>\n<td>")
                    .append(teacher.getSurname())
                    .append("</td>\n<td>")
                    .append(teacher.getPosition())
                    .append("</td>\n</tr>\n")
                );
            table.append(TABLEFOOT);
            return table.toString();

        } catch (ParseException e) {
           return "Ошибка при преобразовании даты: " + e.getMessage();
        }
    }
    //12
    public static String getDeptsNotSoftDev(DepartmentJpaRepository departmentJpaRepository ) {
        String[] hdTitles = {"ID","Name of Department"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        List<Department> departments = departmentJpaRepository.findAllByOrderByNameAsc();
        for (Department dept : departments) {
            if (dept.getName().equals("Software Development"))
                break;
            table.append("<tr><td>")
                    .append(dept.getId())
                    .append("</td><td>")
                    .append(dept.getName())
                    .append("</td></tr>");
        }
        table.append(TABLEFOOT);
        return table.toString();
    }
    //13
    public static String getAssistantPayment(TeachersJpaRepository teachersJpaRepository,double summ) {

        String[] hdTitles = {"Assistant's surname","Payment"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        ((List<Teacher>) teachersJpaRepository.findByIsAssistant(true))
                .stream().filter(teacher -> teacher.getSalary() + teacher.getPremium() > summ)
                .forEach(teacher -> table
                        .append("<tr>\n<td>")
                        .append(teacher.getSurname())
                        .append("</td>\n<td>")
                        .append(String.format("%.2f", teacher.getSalary() + teacher.getPremium()))
                        .append("</td>\n</tr>\n")
                );
        table.append(TABLEFOOT);
        return table.toString();
    }
    //14
    public static String getGroupByYearWithRatings(GroupJpaRepository groupJpaRepository,int year, int ratingFrom, int ratingTo) {

        String[] hdTitles = {"Group Name","Group Rating"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        ((List<Group>) groupJpaRepository.findByYearEqualsAndRatingBetween(year, ratingFrom, ratingTo))
                .stream()
                .map(Group::getDTO)
                .forEach(group -> table.append("<tr><td>")
                        .append(group.getName())
                        .append("</td><td>")
                        .append(group.getRating()).append("</td></tr>"));

        table.append(TABLEFOOT);
        return table.toString();
    }
    //15
    public static String getAssistantPayments(TeachersJpaRepository teachersJpaRepository) {

        String[] hdTitles = {"Assistant's surname","Salary","Premium"};
        StringBuilder table = new StringBuilder();
        table.append(TABLEHEAD).append(drawTblHeader(hdTitles));

        ((List<Teacher>) teachersJpaRepository.scubidu(550.0,200.0))
                .forEach(teacher -> table
                        .append("<tr>\n<td>")
                        .append(teacher.getSurname())
                        .append("</td>\n<td>")
                        .append(String.format("%.2f", teacher.getSalary()))
                        .append("</td>\n<td>")
                        .append(String.format("%.2f", teacher.getPremium()))
                        .append("</td>\n</tr>\n")
                );
        table.append(TABLEFOOT);
        return table.toString();
    }

    public static String drawTblHeader(String[] titles) {
        String output = TABLEHEAD + "<tr>";
        for (String title : titles) {
            output += "<th>" + title + "</th>";
        }
        return output + "</tr></thead><tbody>";
    }
}
