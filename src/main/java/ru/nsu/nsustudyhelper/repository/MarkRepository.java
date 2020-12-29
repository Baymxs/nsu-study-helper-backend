package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.ExamMark;
import ru.nsu.nsustudyhelper.entity.security.User;

import java.util.Set;

public interface MarkRepository extends CrudRepository<ExamMark, Long> {
    ExamMark findByUserAndExaminationProcess(User user, ExaminationProcess examinationProcess);

    Set<ExamMark> findAllByExaminationProcess(ExaminationProcess examinationProcess);

    Set<ExamMark> findAllByUser(User user);
}
