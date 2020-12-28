package ru.nsu.nsustudyhelper.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.nsustudyhelper.dto.TeacherDto;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/teacher/")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("{teacher-id}/details")
    public Set<TeacherDto> getAllTeachers(@PathVariable(name = "teacher-id") long teacherId) {
        return teacherService.getAllTeachers(teacherId);
    }
}
