package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.ExamMark;
import ru.nsu.nsustudyhelper.entity.Teacher;
import ru.nsu.nsustudyhelper.entity.TeacherRating;
import ru.nsu.nsustudyhelper.entity.security.User;

import java.util.Set;

public interface TeacherRatingRepository extends CrudRepository<TeacherRating, Long> {
    TeacherRating findByUserAndTeacher(User user, Teacher teacher);

    Set<TeacherRating> findAllByTeacher(Teacher teacher);
}
