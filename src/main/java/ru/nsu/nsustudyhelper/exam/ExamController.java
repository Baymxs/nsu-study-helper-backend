package ru.nsu.nsustudyhelper.exam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nsustudyhelper.dto.CommentsDto;
import ru.nsu.nsustudyhelper.dto.MarkSettingDto;
import ru.nsu.nsustudyhelper.dto.TeacherDto;
import ru.nsu.nsustudyhelper.dto.UserMarkDetailsDto;
import ru.nsu.nsustudyhelper.dto.CommentSettingDto;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/exam/")
@RequiredArgsConstructor
@Slf4j
public class ExamController {
    private final ExamService examService;

    @GetMapping("{exam-id}/details")
    public UserMarkDetailsDto getExamDetails(Principal principal, @PathVariable(name = "exam-id") long examinationId) {
        return examService.getExamDetails(principal, examinationId);
    }

    @PostMapping("{exam-id}/mark")
    public void setUserExamMark(Principal principal, @PathVariable(name = "exam-id") long examinationId, @RequestBody MarkSettingDto markSettingDto) {
        examService.setUserMark(principal, examinationId, markSettingDto);
    }

    @GetMapping("{exam-id}/teachers")
    public List<TeacherDto> getExamTeachers(@PathVariable(name = "exam-id") long examinationId) {
        return examService.getExamTeachers(examinationId);
    }

    @GetMapping("{exam-id}/comments")
    public CommentsDto getExamComments(Principal principal, @PathVariable(name = "exam-id") long examinationId) {
        return examService.getExamComments(principal, examinationId);
    }

    @PostMapping("{exam-id}/comment")
    public void setExamComment(Principal principal, @PathVariable(name = "exam-id") long examinationId, @RequestBody CommentSettingDto commentSettingDto) {
        examService.setExamComment(principal, examinationId, commentSettingDto);
    }
}
