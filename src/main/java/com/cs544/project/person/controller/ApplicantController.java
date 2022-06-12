package com.cs544.project.person.controller;

import com.cs544.project.person.entity.Applicant;
import com.cs544.project.person.entity.User;
import com.cs544.project.person.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicants")
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

    // TODO: View all jobs

    // TODO: View jobs by title

    // TODO: apply for Job

    // TODO: View job applications

    // TODO: View specific application

    // TODO: Edit application

    // TODO: Delete application
}
