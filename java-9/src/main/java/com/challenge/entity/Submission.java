package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Submission {
    @EmbeddedId
    private SubmissionId id;

    @NotNull
    private Long score;

    @CreatedDate
    private Date createdAt;
}
