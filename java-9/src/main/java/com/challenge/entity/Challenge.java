package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Challenge {
    @OneToMany
    private List<Acceleration> accelerations;

    @OneToMany
    private List<Submission> submissions;

    @NotNull
    @Size(max=100)
    private String name;

    @NotNull
    @Size(max=50)
    private String slug;

    @CreatedDate
    private Date createdAt;
}
