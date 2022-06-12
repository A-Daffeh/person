package com.cs544.project.person.controller;

import com.cs544.project.person.entity.Applicant;
import com.cs544.project.person.entity.User;
import com.cs544.project.person.service.ApplicantService;
import com.cs544.project.person.value_object.ApplicationsResponseTemplate;
import com.cs544.project.person.value_object.JobApplication;
import com.cs544.project.person.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/applicants")
public class ApplicantController {

    @Autowired
    ApplicantService applicantService;

    @PostMapping("/register")
    public User registerApplicant(@RequestBody Applicant applicant){
        return applicantService.register(applicant);
    }

    @GetMapping("/{id}")
    public User getApplicant(@PathVariable Long id){
        return applicantService.getApplicant(id);
    }

    @PutMapping("/{id}")
    public User updateApplicant(@PathVariable Long id, @RequestBody Applicant applicant){
        return applicantService.updateApplicant(id, applicant);
    }

    @DeleteMapping("/{id}")
    public String deleteApplicant(@PathVariable Long id){
        applicantService.deleteApplicant(id);
        return "Applicant Successfully deleted!";
    }

    @GetMapping("/company/jobs/{id}")
    public List<ResponseTemplate> getAllJobsFromCompany(@PathVariable(name = "id") Long companyId){
        return applicantService.getAllJobsFromCompany(companyId);
    }

    @PostMapping("/jobs/apply")
    public JobApplication applyForJob(@RequestBody JobApplication application){
        return applicantService.applyForJob(application);
    }

    @GetMapping("/applications/{id}")
    public List<ApplicationsResponseTemplate> getAllApplicantsForApplicant(@PathVariable(name = "id") Long applicantId){
        return applicantService.getAllApplicantsForApplicant(applicantId);
    }

    @GetMapping("/application/{id}")
    public ApplicationsResponseTemplate getApplicationById(@PathVariable(name = "id") Long applicationId){
        return applicantService.getApplicationById(applicationId);
    }

    @DeleteMapping("/application/{id}")
    public String deleteApplicationForApplicant(@PathVariable(name = "id") Long applicationId){
        applicantService.deleteApplicationForApplicant(applicationId);
        return "Application with id "+applicationId+" successfully deleted.";
    }

    @GetMapping("/jobs/{name}")
    public ResponseTemplate getAllJobsByTitle(@PathVariable(name = "name") String jobTitle){
        return applicantService.getJobsByTitle(jobTitle);
    }
}
