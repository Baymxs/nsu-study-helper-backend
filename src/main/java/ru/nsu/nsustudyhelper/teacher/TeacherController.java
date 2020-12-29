package ru.nsu.nsustudyhelper.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nsustudyhelper.dto.CommentSettingDto;
import ru.nsu.nsustudyhelper.dto.CommentsDto;
import ru.nsu.nsustudyhelper.dto.RatingSettingDto;
import ru.nsu.nsustudyhelper.dto.TeacherDetailsDto;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/teacher/")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("{teacher-id}/details")
    public TeacherDetailsDto getTeacherDetails(Principal principal, @PathVariable(name = "teacher-id") long teacherId) {
        return teacherService.getTeacherDetails(principal, teacherId);
    }

    @PostMapping("{teacher-id}/rating")
    public void setUserTeacherRating(Principal principal, @PathVariable(name = "teacher-id") long teacherId, @RequestBody RatingSettingDto ratingSettingDto) {
        teacherService.setUserRating(principal, teacherId, ratingSettingDto);
    }

    @GetMapping("{teacher-id}/comments")
    public CommentsDto getTeacherComments(Principal principal, @PathVariable(name = "teacher-id") long teacherId) {
        return teacherService.getTeacherComments(principal, teacherId);
    }

    @PostMapping("{teacher-id}/comment")
    public void setTeacherComment(Principal principal, @PathVariable(name = "teacher-id") long teacherId, @RequestBody CommentSettingDto commentSettingDto) {
        teacherService.setTeacherComment(principal, teacherId, commentSettingDto);
    }
}
