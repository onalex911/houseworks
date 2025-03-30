package ru.onalex.hw1503.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.onalex.hw1503.models.Teacher;

import java.util.List;

@Repository
public interface TeacherPgRepository extends PagingAndSortingRepository<Teacher,Integer> {
//    @Query(value = "SELECT * FROM Teachers WHERE :filter_field LIKE '%'+:filter_substring OR Surname LIKE '%'+:filter_substring+'%' OR Surname LIKE :filter_substring+'%'",nativeQuery = true)
    @Query(value = "SELECT * FROM Teachers WHERE :filter_field LIKE :filter_substring",nativeQuery = true)
    List<Teacher> selectFiltered(String filter_field,String filter_substring);
}
