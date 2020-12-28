package ru.nsu.nsustudyhelper.subjectstatistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.nsustudyhelper.dto.MarkDto;
import ru.nsu.nsustudyhelper.dto.UserMarkDetailsDto;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/exam/")
@RequiredArgsConstructor
@Slf4j
public class ExamController {
    private final ExamService examService;

    @GetMapping("{exam-id}/details/")
    public UserMarkDetailsDto getUserDetails(Principal principal, @PathVariable(name = "exam-id") long examinationId) {
        return examService.getUserDetails(principal, examinationId);
    }
}
