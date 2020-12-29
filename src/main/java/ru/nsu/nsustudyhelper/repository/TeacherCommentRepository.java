package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.ExaminationComment;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.Teacher;
import ru.nsu.nsustudyhelper.entity.TeacherComment;
import ru.nsu.nsustudyhelper.entity.security.User;

import java.util.List;

public interface TeacherCommentRepository extends CrudRepository<TeacherComment, Long> {
    TeacherComment findByTeacherAndUser(Teacher teacher, User user);

    List<TeacherComment> findAllByTeacherOrderByIdDesc(Teacher teacher);
}

