package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "semesters")
@Setter
@Getter
public class Semester extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int semesterNumber;

    @OneToMany(mappedBy = "semester")
    private Set<ExaminationProcess> examinationProcessSet;
}
