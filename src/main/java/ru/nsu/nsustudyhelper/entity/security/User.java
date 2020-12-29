package ru.nsu.nsustudyhelper.entity.security;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.nsustudyhelper.entity.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User extends BaseEntityWithId {
    private String name;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<ExaminationComment> comments;

    @OneToMany(mappedBy = "user")
    private Set<ExamMark> examMarks;

    @OneToMany(mappedBy = "user")
    private Set<TeacherComment> teacherCommentSet;

    @OneToMany(mappedBy = "user")
    private Set<TeacherRating> teacherRatingSet;
}

