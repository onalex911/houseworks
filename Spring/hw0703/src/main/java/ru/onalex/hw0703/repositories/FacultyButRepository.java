package ru.onalex.hw0703.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onalex.hw0703.models.Faculty;

import java.util.List;

@Repository
public interface FacultyButRepository extends JpaRepository<Faculty, Integer> {
    List<Faculty> findByNameNotContains(String name);

}
