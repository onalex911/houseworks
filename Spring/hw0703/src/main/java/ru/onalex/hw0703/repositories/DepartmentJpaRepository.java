package ru.onalex.hw0703.repositories;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onalex.hw0703.models.Department;

import java.util.List;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<Department, Integer> {
//    List<Department> findByNameNotContains(String name);
    List<Department> findByFinancingBetween(double min, double max);
    List<Department> findAllByOrderByNameAsc();
}
