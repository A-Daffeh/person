package com.cs544.project.person.value_object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private Long jobId;
    private String jobTitle;
    private String description;
    private Long companyId;

}
