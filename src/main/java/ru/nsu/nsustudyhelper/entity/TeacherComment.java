package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.nsustudyhelper.entity.security.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_comments")
@Setter
@Getter
public class TeacherComment extends BaseEntityWithId {
    @ManyToOne
    private User user;

    @ManyToOne
    private Teacher teacher;

    private String commentText;

}
