package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
