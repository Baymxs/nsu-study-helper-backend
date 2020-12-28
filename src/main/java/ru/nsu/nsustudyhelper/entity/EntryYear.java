package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "entry_years")
@Setter
@Getter
public class EntryYear extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entryYear;

    @OneToMany(mappedBy = "entryYear")
    private Set<ExaminationProcess> examinationProcessSet;
}
