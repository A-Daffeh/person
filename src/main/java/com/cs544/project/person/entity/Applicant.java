package com.cs544.project.person.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Data
@Getter
@Setter
public class Applicant extends User {

    @Lob
    private String biography;
}
