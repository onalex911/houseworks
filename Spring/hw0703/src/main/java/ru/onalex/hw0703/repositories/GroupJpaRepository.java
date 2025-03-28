package ru.onalex.hw0703.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onalex.hw0703.models.Group;

import java.util.List;

@Repository
public interface GroupJpaRepository extends JpaRepository<Group,Integer> {
    List<Group> findByYearEqualsAndRatingBetween(int year, int start, int end);
}
