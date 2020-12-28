package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.nsustudyhelper.entity.security.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "marks")
@Setter
@Getter
public class Mark extends BaseEntityWithId {
    @ManyToOne
    private ExaminationProcess examinationProcess;

    @ManyToOne
    private User user;

    private int mark;
}
