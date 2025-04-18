package ru.onalex.hw2903.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onalex.hw2903.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer>{
}
