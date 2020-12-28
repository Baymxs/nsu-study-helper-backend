package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.Semester;

import java.util.Optional;

public interface SemesterRepository extends CrudRepository<Semester, Integer> {
    Optional<Semester> findBySemesterNumber(int semesterNumber);

    Iterable<Semester> findAllByOrderBySemesterNumberAsc();
}
