package com.challenge.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
    @NotNull
    @Size(max = 100)
    private String fullname;
}
