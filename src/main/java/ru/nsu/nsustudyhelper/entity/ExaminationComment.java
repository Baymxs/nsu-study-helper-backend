package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.nsustudyhelper.entity.security.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "examination_comments")
@Setter
@Getter
public class ExaminationComment extends BaseEntityWithId {
    @ManyToOne
    private User user;

    @ManyToOne
    private ExaminationProcess examinationProcess;

    private String commentText;
}
