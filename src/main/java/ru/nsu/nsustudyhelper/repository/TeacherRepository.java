package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findAllByExaminationProcessesOrderByIdDesc(ExaminationProcess examinationProcess);
}
