package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "subjects")
@Setter
@Getter
public class Subject extends BaseEntityWithId {
    private String name;

    @OneToMany(mappedBy = "subject")
    private Set<ExaminationProcess> examinationProcessSet;
}
