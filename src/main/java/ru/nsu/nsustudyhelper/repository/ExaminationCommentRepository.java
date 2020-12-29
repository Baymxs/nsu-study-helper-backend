package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.ExaminationComment;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.security.User;

import java.util.List;

public interface ExaminationCommentRepository extends CrudRepository<ExaminationComment, Long> {
    ExaminationComment findByExaminationProcessAndUser(ExaminationProcess examinationProcess, User user);

    List<ExaminationComment> findAllByExaminationProcessOrderByIdDesc(ExaminationProcess examinationProcess);
}
