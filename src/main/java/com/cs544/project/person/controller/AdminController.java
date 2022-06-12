package com.cs544.project.person.controller;

import com.cs544.project.person.entity.Admin;
import com.cs544.project.person.entity.User;
import com.cs544.project.person.service.AdminService;
import com.cs544.project.person.value_object.Job;
import com.cs544.project.person.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/add")
    public User addAdmin(@RequestBody Admin admin){
//      TODO: admin's company must be filled in
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

//  TODO: add job
    @PostMapping("/jobs/add")
    public Job addJob(@RequestBody Job job){
        return adminService.addJob(job);
    }

    //  TODO: Get a job
    @GetMapping("/jobs/{id}")
    public ResponseTemplate getJob(@PathVariable Long id){
        return adminService.getJob(id);
    }

//    TODO: edit job
    @PutMapping("/jobs/{id}")
    public ResponseTemplate editJob(@PathVariable Long id, @RequestBody Job job){
        return adminService.updateJob(id, job);
    }

//    TODO: delete job
    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id){
        return adminService.deleteJob(id);
    }

//    TODO: Get all applicants


//    TODO: Get all applicants by job title



}
