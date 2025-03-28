package ru.onalex.hw1403.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.onalex.hw1403.models.Teacher;
import ru.onalex.hw1403.repositories.TeacherRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TeachersControllerTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    //1  Тестирование GET запроса для получения списка ресурсов
    @Test
    public void get_all_teachers_return_all_teachers() throws Exception {

        List<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> teachers2 = new ArrayList<>();
        Random rnd = new Random();
        int numForCheck = rnd.nextInt(teachers.size());
        Teacher teacher = teachers.get(numForCheck);
        mockMvc.perform(
                get("/api/teacher/all")
                        .contentType(MediaType.APPLICATION_JSON))
//                        .content(objectMapper.writeValueAsString(teachers2)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(teachers)))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
//                .andExpect(jsonPath("$[" + numForCheck + "].name",is(teacher.getName())))
//                .andExpect(jsonPath("$[" + numForCheck + "].surname",is(teacher.getSurname())))
//                .andExpect(jsonPath("$[" + numForCheck + "].position",is(teacher.getPosition())))
//                .andExpect(jsonPath("$[" + numForCheck + "].isAssistant",is(teacher.getIsAssistant())))
//                .andExpect(jsonPath("$[" + numForCheck + "].isProfessor",is(teacher.getIsProfessor())))
//                .andExpect(jsonPath("$[" + numForCheck + "].salary",is(teacher.getSalary())))
//                .andExpect(jsonPath("$[" + numForCheck + "].premium",is(teacher.getPremium())));
//        System.out.println("teachers2 = " + teachers2.size());
    }
    //2 Тестирование GET запроса для получения конкретного ресурса
    @Test
    public void get_teacher_valid_ok_not_found_return_teacher() throws Exception {
        int numForCheck = 20;
        Teacher teacher = teacherRepository.findById(numForCheck).get();
        mockMvc.perform(
                get("/api/teacher/get/{id}",numForCheck)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(teacher.getId())))
                .andExpect(jsonPath("$.name",is(teacher.getName())))
                .andExpect(jsonPath("$.surname",is(teacher.getSurname())))
                .andExpect(jsonPath("$.isAssistant",is(teacher.getIsAssistant())))
                .andExpect(jsonPath("$.isProfessor",is(teacher.getIsProfessor())))
                .andExpect(jsonPath("$.position",is(teacher.getPosition())))
                .andExpect(jsonPath("$.salary",is(teacher.getSalary())))
                .andExpect(jsonPath("$.premium",is(teacher.getPremium())));

        mockMvc.perform(
                get("/api/teacher/get/{id}",1000))
                        .andExpect(status().isNotFound());

    }

    //3 Тестирование POST запроса для создания ресурса
    @Test
    @SneakyThrows
    public void create_teacher_valid_data_return_teacher() {
        Teacher teacher = new Teacher();
        AtomicReference<String> locationHeader = new AtomicReference<>("");
        String dateString = "2025-03-18";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        teacher.setName("Oleg");
        teacher.setSurname("Gazmanov");
        teacher.setEmploymentDate(format.parse(dateString));
        teacher.setIsAssistant(false);
        teacher.setIsProfessor(false);
        teacher.setPosition("Singer");
        teacher.setSalary(2345.56);
        teacher.setPremium(1678.89);
        mockMvc.perform(
                        post("/api/teacher")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andDo(result -> {
                    // Получаем URI из заголовка Location
                    locationHeader.set(result.getResponse().getHeader("Location"));
                });
//        System.out.println("Returned URI is: " + locationHeader.get());
        mockMvc.perform(
                get(locationHeader.get()))
                .andExpect(status().isOk());
    }

    //4 Тестирование валидации данных при создании ресурса
    @Test
    public void create_teacher_invalid_data_return_bad_fields() throws Exception {
        Teacher teacher = new Teacher();
        AtomicReference<String> locationHeader = new AtomicReference<>("");
        String dateString = "2025-03-18";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        teacher.setName("Oleg");
        teacher.setSurname("");
        teacher.setEmploymentDate(format.parse(dateString));
        teacher.setIsAssistant(false);
        teacher.setIsProfessor(false);
        teacher.setPosition("Singer");
        teacher.setSalary(6666.66);
        teacher.setPremium(-1.00);
        mockMvc.perform(
                        post("/api/teacher")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.surname").value("Surname must not be blank!"))
                .andExpect(jsonPath("$.premium").value("Premium must be greater than 0!"));
    }

    //5 Тестирование PUT запроса для обновления ресурса
    @Test
    public void update_teacher_valid_data_check_updated_teacher() throws Exception {
        int idForUpdate = 40;
        double newSalary = 9999.99;
        double newPremium = 1111.11;
        String newPosition = "Higher Decision";
        Teacher teacher = teacherRepository.findById(idForUpdate).get();
        Teacher updatedTeacher = new Teacher();
        updatedTeacher.setSalary(newSalary);
        updatedTeacher.setPremium(newPremium);
        updatedTeacher.setPosition(newPosition);
        mockMvc.perform(
                        put("/api/teacher/{id}",idForUpdate)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updatedTeacher)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position",is(newPosition)))
                .andExpect(jsonPath("$.salary",is(newSalary)))
                .andExpect(jsonPath("$.premium",is(newPremium)))
        //проверяем целостность неизмененных данных
                .andExpect(jsonPath("$.name",is(teacher.getName())))
                .andExpect(jsonPath("$.surname",is(teacher.getSurname())))
                .andExpect(jsonPath("$.isAssistant",is(teacher.getIsAssistant())))
                .andExpect(jsonPath("$.isProfessor",is(teacher.getIsProfessor())));

        mockMvc.perform(
                        put("/api/teacher/{id}",55)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTeacher)))
                .andExpect(status().isNotFound());
    }
}
