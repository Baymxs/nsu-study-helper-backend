package ru.nsu.nsustudyhelper.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.nsu.nsustudyhelper.entity.security.Status;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
@Setter
@Getter
public class BaseEntityWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private Date created = new Date();

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated = new Date();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
}

