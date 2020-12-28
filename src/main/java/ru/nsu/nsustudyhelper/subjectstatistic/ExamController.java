package ru.nsu.nsustudyhelper.subjectstatistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nsustudyhelper.dto.ExamCommentsDto;
import ru.nsu.nsustudyhelper.dto.MarkSettingDto;
import ru.nsu.nsustudyhelper.dto.TeacherDto;
import ru.nsu.nsustudyhelper.dto.UserMarkDetailsDto;
import ru.nsu.nsustudyhelper.entity.ExamCommentSettingDto;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/exam/")
@RequiredArgsConstructor
@Slf4j
public class ExamController {
    private final ExamService examService;

    @GetMapping("{exam-id}/details")
    public UserMarkDetailsDto getUserDetails(Principal principal, @PathVariable(name = "exam-id") long examinationId) {
        return examService.getUserDetails(principal, examinationId);
    }

    @PostMapping("{exam-id}/mark")
    public void setUserExamMark(Principal principal, @PathVariable(name = "exam-id") long examinationId, @RequestBody MarkSettingDto markSettingDto) {
        examService.setUserMark(principal, examinationId, markSettingDto);
    }

    @GetMapping("{exam-id}/teachers")
    public Set<TeacherDto> getExamTeachers(@PathVariable(name = "exam-id") long examinationId) {
        return examService.getExamTeachers(examinationId);
    }

    @GetMapping("{exam-id}/comments")
    public ExamCommentsDto getExamComments(Principal principal, @PathVariable(name = "exam-id") long examinationId) {
        return examService.getExamComments(principal, examinationId);
    }

    @PostMapping("{exam-id}/comment")
    public void setExamComment(Principal principal, @PathVariable(name = "exam-id") long examinationId, @RequestBody ExamCommentSettingDto examCommentSettingDto) {
        examService.setExamComment(principal, examinationId, examCommentSettingDto);
    }
}
