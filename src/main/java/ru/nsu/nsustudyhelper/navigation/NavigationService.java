package ru.nsu.nsustudyhelper.navigation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.dto.EntryYearDto;
import ru.nsu.nsustudyhelper.dto.ExaminationProcessDto;
import ru.nsu.nsustudyhelper.dto.SemesterDto;
import ru.nsu.nsustudyhelper.entity.EntryYear;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.Semester;
import ru.nsu.nsustudyhelper.repository.EntryYearRepository;
import ru.nsu.nsustudyhelper.repository.ExaminationProcessRepository;
import ru.nsu.nsustudyhelper.repository.SemesterRepository;
import ru.nsu.nsustudyhelper.util.dtotransformservice.DtoTransformService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class NavigationService {
    private final DtoTransformService dtoTransformService;

    private final EntryYearRepository entryYearRepository;

    private final SemesterRepository semesterRepository;

    private final ExaminationProcessRepository examinationProcessRepository;

    public Set<EntryYearDto> getAllEntryYears() {
        Set<EntryYearDto> set = new HashSet<>();

        for (EntryYear entryYear : entryYearRepository.findAllByOrderByEntryYearAsc()) {
            set.add(dtoTransformService.convertToEntryYearDto(entryYear));
        }

        return set;
    }

    public Set<SemesterDto> getAllSemesters() {
        Set<SemesterDto> set = new HashSet<>();

        for (Semester semester : semesterRepository.findAll()) {
            set.add(dtoTransformService.convertToSemesterDto(semester));
        }

        return set;
    }

    public Set<ExaminationProcessDto> getAllExaminations(int entryYear, int semester) {
        Set<ExaminationProcessDto> set = new HashSet<>();

        EntryYear enYear = entryYearRepository.
                findByEntryYear(entryYear).
                orElseThrow(() -> new IllegalArgumentException("Год " + entryYear + " не найден."));

        Semester sem = semesterRepository.
                findBySemesterNumber(semester).
                orElseThrow(() -> new IllegalArgumentException("Семестр " + semester + " не найден."));

        for (ExaminationProcess examinationProcess : examinationProcessRepository.getAllByEntryYearAndSemester(enYear, sem)) {
            set.add(dtoTransformService.convertToExaminationProcessDto(examinationProcess));
        }

        return set;
    }
}
