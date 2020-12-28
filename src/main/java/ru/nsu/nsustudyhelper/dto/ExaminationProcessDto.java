package ru.nsu.nsustudyhelper.dto;

import lombok.Data;
import ru.nsu.nsustudyhelper.entity.EntryYear;
import ru.nsu.nsustudyhelper.entity.ExaminationType;
import ru.nsu.nsustudyhelper.entity.Semester;
import ru.nsu.nsustudyhelper.entity.Subject;

@Data
public class ExaminationProcessDto {
    private Long id;

    private EntryYear entryYear;

    private Semester semester;

    private Subject subject;

    private ExaminationType examinationType;
}
