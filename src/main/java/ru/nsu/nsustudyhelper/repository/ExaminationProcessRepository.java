package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.EntryYear;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.Semester;

public interface ExaminationProcessRepository extends CrudRepository<ExaminationProcess, Long> {
    Iterable<ExaminationProcess> getAllByEntryYearAndSemester(EntryYear entryYear, Semester semester);
}
