package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "examination_processes")
@Setter
@Getter
public class ExaminationProcess extends BaseEntityWithId {
    @ManyToOne
    private EntryYear entryYear;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private ExaminationType examinationType;

    @OneToMany(mappedBy = "examinationProcess")
    private Set<ExaminationComment> examinationCommentSet;

    @OneToMany(mappedBy = "examinationProcess")
    private Set<ExamMark> examMarkSet;

    @ManyToMany(mappedBy = "examinationProcesses")
    private Set<Teacher> teachers;
}
