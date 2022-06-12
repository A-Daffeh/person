package com.cs544.project.person.value_object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationsResponseTemplate {
    private Application application;
    private Job job;
}
