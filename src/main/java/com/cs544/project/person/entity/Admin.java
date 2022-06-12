package com.cs544.project.person.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Data
@Getter
@Setter
public class Admin extends User {

    private String companyId;
}
