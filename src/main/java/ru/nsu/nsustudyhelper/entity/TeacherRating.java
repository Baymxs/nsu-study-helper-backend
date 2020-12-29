package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.nsustudyhelper.entity.security.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_ratings")
@Setter
@Getter
public class TeacherRating extends BaseEntityWithId {
    @ManyToOne
    private User user;

    @ManyToOne
    private Teacher teacher;

    private int rating;
}
