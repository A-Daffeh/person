package com.cs544.project.person.controller;

import com.cs544.project.person.entity.Admin;
import com.cs544.project.person.entity.User;
import com.cs544.project.person.service.AdminService;
import com.cs544.project.person.value_object.ApplicationsResponseTemplate;
import com.cs544.project.person.value_object.Job;
import com.cs544.project.person.value_object.JobApplication;
import com.cs544.project.person.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/add")
    public User addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    @GetMapping("/{id}")
    public User getAdmin(@PathVariable Long id){
        return adminService.getAdmin(id);
    }

    @PutMapping("/{id}")
    public User updateAdmin(@PathVariable Long id, @RequestBody Admin admin){
        return adminService.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
        return "Admin Successfully deleted!";
    }

    @PostMapping("/jobs/add")
    public Job addJob(@RequestBody Job job){
        return adminService.addJob(job);
    }

    @GetMapping("/jobs/{id}")
    public ResponseTemplate getJob(@PathVariable Long id){
        return adminService.getJob(id);
    }

    @PutMapping("/jobs/{id}")
    public ResponseTemplate editJob(@PathVariable Long id, @RequestBody Job job){
        return adminService.updateJob(id, job);
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id){
        return adminService.deleteJob(id);
    }

    @GetMapping("/jobs/applications/{id}")
    public List<ApplicationsResponseTemplate> getJAllJobApplications(@PathVariable(name = "id") Long jobId){
        return adminService.getJobApplicationsByJobId(jobId);
    }

    @GetMapping("/jobs/applications/job/{jotTitle}")
    public List<ApplicationsResponseTemplate> getJAllJobApplicationsByJobTitle(@PathVariable String jotTitle){
        return adminService.getJobApplicationsByJobTitle(jotTitle);
    }

    @PutMapping("/jobs/applications/application/{id}")
    public ApplicationsResponseTemplate processApplication(@PathVariable Long id, @RequestBody JobApplication application){
        return adminService.processApplication(id, application);
    }

    @GetMapping("/application/{id}")
    public ApplicationsResponseTemplate getApplicationById(@PathVariable(name = "id") Long applicationId){
        return adminService.getApplicationById(applicationId);
    }

}
