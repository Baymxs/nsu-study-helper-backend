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

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NavigationService {
    private final DtoTransformService dtoTransformService;

    private final EntryYearRepository entryYearRepository;

    private final SemesterRepository semesterRepository;

    private final ExaminationProcessRepository examinationProcessRepository;

    public List<EntryYearDto> getAllEntryYears() {
        List<EntryYearDto> list = new ArrayList<>();

        for (EntryYear entryYear : entryYearRepository.findAllByOrderByEntryYearAsc()) {
            list.add(dtoTransformService.convertToEntryYearDto(entryYear));
        }

        return list;
    }

    public List<SemesterDto> getAllSemesters() {
        List<SemesterDto> list = new ArrayList<>();

        for (Semester semester : semesterRepository.findAllByOrderBySemesterNumberAsc()) {
            list.add(dtoTransformService.convertToSemesterDto(semester));
        }

        return list;
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
