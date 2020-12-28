package ru.nsu.nsustudyhelper.navigation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.nsustudyhelper.dto.EntryYearDto;
import ru.nsu.nsustudyhelper.dto.ExaminationProcessDto;
import ru.nsu.nsustudyhelper.dto.SemesterDto;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/navigation/")
@RequiredArgsConstructor
@Slf4j
public class NavigationController {
    private final NavigationService navigationService;

    @GetMapping("/entry-year/all")
    public List<EntryYearDto> getAllEntryYears() {
        return navigationService.getAllEntryYears();
    }

    @GetMapping("/semester/all")
    public List<SemesterDto> getAllSemesters() {
        return navigationService.getAllSemesters();
    }

    @GetMapping("/exam/all/{entryYear}/{semester}")
    public Set<ExaminationProcessDto> getAllExaminations(@PathVariable int entryYear, @PathVariable int semester) {
        return navigationService.getAllExaminations(entryYear, semester);
    }
}
