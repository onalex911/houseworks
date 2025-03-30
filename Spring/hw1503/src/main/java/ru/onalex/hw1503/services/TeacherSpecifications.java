package ru.onalex.hw1503.services;
import org.springframework.data.jpa.domain.Specification;
import ru.onalex.hw1503.models.Teacher;

import java.util.Date;

public class TeacherSpecifications {

        public static Specification<Teacher> nameMatches(String name) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("name"), name),
                    criteriaBuilder.like(root.get("name"), name + "%"),
                    criteriaBuilder.like(root.get("name"), "%" + name),
                    criteriaBuilder.like(root.get("name"), "%" + name + "%")
            );
        }
        public static Specification<Teacher> surnameMatches(String surname) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("surname"), surname),
                    criteriaBuilder.like(root.get("surname"), surname + "%"),
                    criteriaBuilder.like(root.get("surname"), "%" + surname),
                    criteriaBuilder.like(root.get("surname"), "%" + surname + "%")
            );
        }
        public static Specification<Teacher> positionMatches(String position) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("position"), position),
                    criteriaBuilder.like(root.get("position"), position + "%"),
                    criteriaBuilder.like(root.get("position"), "%" + position),
                    criteriaBuilder.like(root.get("position"), "%" + position + "%")
            );
        }
        public static Specification<Teacher> salaryGreater(double salary) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("salary"), salary);
        }
        public static Specification<Teacher> premiumGreater(double premium) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("premium"), premium);
        }

        public static Specification<Teacher> idFrom(long id) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("id"), id);
        }

        public static Specification<Teacher> hasEmpoyeeDateAfter(Date empDate) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("employmentDate"), empDate);
        }
}
