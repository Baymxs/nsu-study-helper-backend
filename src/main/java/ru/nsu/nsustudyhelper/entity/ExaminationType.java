package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "examination_types")
@Setter
@Getter
public class ExaminationType extends BaseEntityWithId {
    private String description;

    @OneToMany(mappedBy = "examinationType")
    private Set<ExaminationProcess> examinationProcessSet;
}
