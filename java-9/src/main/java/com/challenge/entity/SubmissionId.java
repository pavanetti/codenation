package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class SubmissionId implements Serializable {
    @ManyToOne
    private User user;

    @ManyToOne
    private Challenge challenge;
}
