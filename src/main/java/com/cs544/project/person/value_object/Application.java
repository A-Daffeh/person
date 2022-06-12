package com.cs544.project.person.value_object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    private Long applicationId;
    private String applicationDomain;
    private String applicantName;
    private String applicantContact;
    private String status;
    private Long jobId;
    private Long applicantId;
}
