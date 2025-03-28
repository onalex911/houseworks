package ru.onalex.hw1403.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.onalex.hw1403.models.Teacher;
import ru.onalex.hw1403.repositories.TeacherRepository;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teacher")
public class TeachersController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        if(teachers != null && !teachers.isEmpty()){
            return ResponseEntity.ok(teachers);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Teacher> getTeachersById(@PathVariable int id) {
        return teacherRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) {
            Teacher createdResource = teacherRepository.save(teacher); // Сохраняем ресурс
            // Создаем URI для созданного ресурса
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/get/{id}")
                    .buildAndExpand(createdResource.getId())
                    .toUri();
            return ResponseEntity.created(location).body(createdResource); // Возвращаем 201 CREATED
    }
    //обработчик ошибочно переданных или пропущенных данных
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id,@RequestBody Teacher teacher) {
        return teacherRepository
                .findById(id)
                .map(exists -> {
                    if(teacher.getName() != null)
                        exists.setName(teacher.getName());

                    if(teacher.getSurname() != null)
                        exists.setSurname(teacher.getSurname());

                    if(teacher.getEmploymentDate() != null)
                        exists.setEmploymentDate(teacher.getEmploymentDate());

                    if(teacher.getIsAssistant() != null)
                        exists.setIsAssistant(teacher.getIsAssistant());

                    if(teacher.getIsProfessor() != null)
                        exists.setIsProfessor(teacher.getIsProfessor());

                    if(teacher.getPosition() != null)
                        exists.setPosition(teacher.getPosition());

                    if(teacher.getSalary() > 0)
                        exists.setSalary(teacher.getSalary());

                    if(teacher.getPremium() > 0)
                        exists.setPremium(teacher.getPremium());

                    return ResponseEntity.ok(teacherRepository.save(exists));

                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int id) {
        return teacherRepository
                .findById(id)
                .map(teacher -> {
                    teacherRepository.delete(teacher);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
