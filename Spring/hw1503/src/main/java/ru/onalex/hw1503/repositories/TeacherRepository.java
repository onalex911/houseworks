package ru.onalex.hw1503.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.onalex.hw1503.models.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query(value = "SELECT * FROM Teachers ORDER BY :field",nativeQuery = true)
    List<Teacher> selectOrderedBy(String field);
//    List<Teacher> findOrdered();
}
