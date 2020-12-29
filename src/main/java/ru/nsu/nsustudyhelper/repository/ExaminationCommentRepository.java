package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.ExaminationComment;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.security.User;

public interface ExaminationCommentRepository extends CrudRepository<ExaminationComment, Long> {
    ExaminationComment findByExaminationProcessAndUser(ExaminationProcess examinationProcess, User user);

    Iterable<ExaminationComment> findAllByExaminationProcessOrderByIdDesc(ExaminationProcess examinationProcess);
}
