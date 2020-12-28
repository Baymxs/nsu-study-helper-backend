package ru.nsu.nsustudyhelper.subjectstatistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.nsustudyhelper.dto.MarkDto;
import ru.nsu.nsustudyhelper.dto.MarkStatisticDto;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/subject-statistic/")
@RequiredArgsConstructor
@Slf4j
public class SubjectStatisticController {
    private final SubjectStatisticService subjectStatisticService;

    @GetMapping("{examination-id}/mark/")
    public MarkDto getUserMark(Principal principal, @PathVariable(name = "examination-id") long examinationId) {
        return subjectStatisticService.getMark(principal, examinationId);
    }

    @GetMapping("/mark-statistic/")
    public Set<MarkStatisticDto> getUserMarkStatistic(Principal principal) {
        return subjectStatisticService.getMarkStatistic(principal);
    }
}
