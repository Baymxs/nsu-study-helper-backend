package ru.nsu.nsustudyhelper.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nsustudyhelper.dto.ExamCommentsDto;
import ru.nsu.nsustudyhelper.dto.MarkSettingDto;
import ru.nsu.nsustudyhelper.dto.UserMarkDetailsDto;
import ru.nsu.nsustudyhelper.entity.ExamCommentSettingDto;
import ru.nsu.nsustudyhelper.exam.ExamService;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/teacher/")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {
    private final TeacherService teacherService;

}
