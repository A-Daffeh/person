package com.cs544.project.person.service;

import com.cs544.project.person.entity.Admin;
import com.cs544.project.person.entity.User;
import com.cs544.project.person.repository.IUserDao;
import com.cs544.project.person.value_object.ApplicationsResponseTemplate;
import com.cs544.project.person.value_object.Job;
import com.cs544.project.person.value_object.JobApplication;
import com.cs544.project.person.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AdminService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    RestTemplate restTemplate;

    public User addAdmin(Admin admin){
        return userDao.save(admin);
    }

    public User getAdmin(Long id){
        return userDao.findById(id).orElse(null);
    }

    public User updateAdmin(Long id, Admin admin){
        User storedAdmin = userDao.findById(id).orElse(null);
        if(storedAdmin != null){
            if(Objects.nonNull(admin.getName()) && !"".equalsIgnoreCase(admin.getName())){
                storedAdmin.setName(admin.getName());
            }
            if(Objects.nonNull(admin.getEmail()) && !"".equalsIgnoreCase(admin.getEmail())){
                storedAdmin.setEmail(admin.getEmail());
            }
            if(Objects.nonNull(admin.getPassword()) && !"".equalsIgnoreCase(admin.getPassword())){
                storedAdmin.setPassword(admin.getPassword());
            }
            userDao.save(storedAdmin);
        }
        return storedAdmin;
    }

    public void deleteAdmin(Long id){
        userDao.deleteById(id);
    }

    public Job addJob(Job job) {
        return restTemplate.postForObject("http://JOB-SERVICE/jobs/add", job, Job.class);
    }

    public ResponseTemplate getJob(Long id){
        return restTemplate.getForObject("http://JOB-SERVICE/jobs/"+id, ResponseTemplate.class);
    }

    public ResponseTemplate updateJob(Long id, Job job) {
        ResponseTemplate storedJob = getJob(id);
        if(Objects.nonNull(job.getJobTitle()) && !"".equalsIgnoreCase(job.getJobTitle())){
            storedJob.getJob().setJobTitle(job.getJobTitle());
        }
        if(Objects.nonNull(job.getDescription()) && !"".equalsIgnoreCase(job.getDescription())){
            storedJob.getJob().setDescription(job.getDescription());
        }
        restTemplate.put("http://JOB-SERVICE/jobs/"+id, storedJob.getJob());
        return getJob(id);
    }

    public String deleteJob(Long id){
        restTemplate.delete("http://JOB-SERVICE/jobs/"+id, Job.class);
        return "Job with id: "+ id+" successfully deleted";
    }

    public List<ApplicationsResponseTemplate> getJobApplicationsByJobId(Long jobId) {
        return restTemplate.getForObject("http://APPLICATIONS/applications/jobs/"+jobId, ArrayList.class);
    }

    public List<ApplicationsResponseTemplate> getJobApplicationsByJobTitle(String jotTitle) {
        return restTemplate.getForObject("http://APPLICATIONS/applications/jobs/job/"+jotTitle, ArrayList.class);
    }

    public ApplicationsResponseTemplate processApplication(Long id, JobApplication application) {
        JobApplication storedApplication = getApplicationById(id).getJobApplication();
        if(Objects.nonNull(application.getStatus()) && !"".equalsIgnoreCase(application.getStatus())){
            storedApplication.setStatus(application.getStatus());
        }
        restTemplate.put("http://APPLICATIONS/applications/"+id, storedApplication, ApplicationsResponseTemplate.class);
        return getApplicationById(id);
    }

    public ApplicationsResponseTemplate getApplicationById(Long applicationId) {
        return restTemplate.getForObject("http://APPLICATIONS/applications/" + applicationId, ApplicationsResponseTemplate.class);
    }
}
