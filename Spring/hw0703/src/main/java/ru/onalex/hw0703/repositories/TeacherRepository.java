package ru.onalex.hw0703.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.onalex.hw0703.models.Group;
import ru.onalex.hw0703.models.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher,Integer> {
    List<Teacher> findByIsProfessorIsNot(Boolean isProfessor);
}
