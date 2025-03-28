package ru.onalex.hw1403.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.onalex.hw1403.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
