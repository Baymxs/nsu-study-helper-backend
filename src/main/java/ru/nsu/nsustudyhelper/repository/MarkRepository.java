package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.Mark;
import ru.nsu.nsustudyhelper.entity.security.User;

import java.util.Set;

public interface MarkRepository extends CrudRepository<Mark, Long> {
    Mark findByUserAndExaminationProcess(User user, ExaminationProcess examinationProcess);

    Set<Mark> findAllByUser(User user);
}
