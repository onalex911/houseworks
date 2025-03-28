package ru.onalex.hw0703.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.onalex.hw0703.models.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Integer> {
}
