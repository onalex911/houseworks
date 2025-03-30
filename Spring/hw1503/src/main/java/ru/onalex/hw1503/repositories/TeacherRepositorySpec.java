package ru.onalex.hw1503.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.onalex.hw1503.models.Teacher;

import java.util.List;

@Repository
public interface TeacherRepositorySpec extends JpaRepository<Teacher,Integer>, JpaSpecificationExecutor<Teacher> {
}
