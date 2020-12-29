package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Setter
@Getter
public class Teacher extends BaseEntityWithId {
    private String firstName;

    private String middleName;

    private String lastName;

    private String tStatus;

    private String birthYear;

    @ManyToMany
    private Set<ExaminationProcess> examinationProcesses;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherComment> teacherCommentSet;

    @OneToMany(mappedBy = "user")
    private Set<TeacherRating> teacherRatingSet;
}
