package ru.onalex.hw0703.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.onalex.hw0703.models.Teacher;

import java.util.Date;
import java.util.List;

@Repository
public interface TeachersJpaRepository extends JpaRepository<Teacher,Integer> {
    //5
    List<Teacher> findByIsProfessorAndSalaryGreaterThan(@NotNull Boolean isProfessor, @NotNull double salary);
    //8
    List<Teacher> findByIsProfessorIsNot(Boolean isProfessor);//8
    //9
    List<Teacher> findByIsAssistantAndPremiumBetween(@NotNull Boolean isAssistant, double minimum, double maximum);
    //10, 13
    List<Teacher> findByIsAssistant(@NotNull Boolean isAssistant);
    //11
    List<Teacher> findByEmploymentDateBefore(@NotNull Date employmentDate);
    //15
    @Query(value = "SELECT * FROM Teachers WHERE IsAssistant = 1 AND (Salary < :minSal OR Premium < :minPrem)", nativeQuery = true)
    List<Teacher> scubidu(double minSal, double minPrem);


}
