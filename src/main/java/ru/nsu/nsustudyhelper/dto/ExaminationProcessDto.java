package ru.nsu.nsustudyhelper.dto;

import lombok.Data;

@Data
public class ExaminationProcessDto {
    private Long id;

    private EntryYearDto entryYear;

    private SemesterDto semester;

    private SubjectDto subject;

    private ExaminationTypeDto examinationType;
}
