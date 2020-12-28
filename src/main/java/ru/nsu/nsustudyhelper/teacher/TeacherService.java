package ru.nsu.nsustudyhelper.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.dto.TeacherDto;
import ru.nsu.nsustudyhelper.entity.Teacher;
import ru.nsu.nsustudyhelper.repository.TeacherRepository;
import ru.nsu.nsustudyhelper.util.dtotransformservice.DtoTransformService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final DtoTransformService dtoTransformService;
    private final TeacherRepository teacherRepository;

    public Set<TeacherDto> getAllTeachers(long teacherId) {
        Set<TeacherDto> set = new HashSet<>();

        for (Teacher teacher : teacherRepository.findAll()) {
            set.add(dtoTransformService.convertToTeacherDto(teacher));
        }

        return set;
    }
}
